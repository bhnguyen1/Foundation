import React from 'react'
import UserInput from '../DumbComponent/UserInput'
import { FormEvent, useState, useContext } from 'react'
import { AuthContext } from '../../Context/ReducerUserContext';
import UserInfo from '../UserInfo/UserInfo';

function UserManagement() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const context = useContext(AuthContext);

    if(!context) {
        throw new Error("Login must be used within an AuthProvider");
    }
    
    const { dispatch } = context;

    function handleSubmit(event: FormEvent) {
        event.preventDefault();
        if(!username || !password) {
            return;
        }
        //some kind of api call to verify the user credentials
        console.log({username, password});
        if(context) {
            dispatch({type: 'LOGIN', payload: {username, password}});
        }
    }
    
  return (
    <>
        <UserInfo/>
        <UserInput username={username} setUsername={setUsername} password={password} 
        setPassword={setPassword} handleSubmit={handleSubmit}/>
    </>
  )
}

export default UserManagement