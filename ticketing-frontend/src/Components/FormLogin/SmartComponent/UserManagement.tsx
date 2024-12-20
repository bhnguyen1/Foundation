import React from 'react'
import UserInput from '../DumbComponent/UserInput'
import { FormEvent, useState, useContext } from 'react'
import { AuthContext } from '../../Context/ReducerUserContext';
import UserInfo from '../UserInfo/UserInfo';
import { useNavigate } from 'react-router-dom';

function UserManagement() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const context = useContext(AuthContext);
    const navigate = useNavigate();

    if(!context) {
        throw new Error("Login must be used within an AuthProvider");
    }
    
    const { dispatch } = context;

    async function handleSubmit(event: FormEvent) {
        event.preventDefault();
        if(!username || !password) {
            return;
        }
        //some kind of api call to verify the user credentials
        try{
            const response = await fetch('http://localhost:8080/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({username, password}),
            });
            
            if(!response.ok) {
                throw new Error('Failed to login');
            }

            const user = await response.json();
            dispatch({type: 'LOGIN', payload: user});
            navigate('/home');
            //navigate to the home page
        }  catch (error) {
            console.error('Failed to login', error);
        
        }

        // console.log({username, password});
        // if(context) {
        //     dispatch({type: 'LOGIN', payload: {username, password}});
        // }
    }
    
  return (
    <>
        <UserInput username={username} setUsername={setUsername} password={password} 
        setPassword={setPassword} handleSubmit={handleSubmit}/>
        <UserInfo/>
    </>
  )
}

export default UserManagement