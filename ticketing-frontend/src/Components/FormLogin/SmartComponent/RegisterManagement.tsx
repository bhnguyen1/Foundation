import React, { useState, FormEvent} from 'react'
import RegisterInput from '../DumbComponent/RegisterInput';

function RegisterManagement({ switchToLogin } : { switchToLogin: () => void }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    async function handleSubmit(event: FormEvent) {
        event.preventDefault();
        //check for some kind of validation?
        try {
            const response = await fetch('http://localhost:8080/register', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({username, password}),
            });
            if (!response.ok) {
            throw new Error('Failed to register');
            }
            alert('User registered successfully');
            //need a way to switch to the login page
            switchToLogin();
        } catch (error) {
            console.error('Failed to register', error);
        }
    }


  return (
    <RegisterInput username={username} setUsername={setUsername} password={password}
    setPassword={setPassword} handleSubmit={handleSubmit}/>
  )
} 

export default RegisterManagement