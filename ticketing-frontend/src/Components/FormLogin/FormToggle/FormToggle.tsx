import React, { useState } from 'react'
import UserManagement from '../SmartComponent/UserManagement';
import RegisterManagement from '../SmartComponent/RegisterManagement';

function FormToggle() {
    const [isRegistering, setIsRegistering] = useState(false);

    return (
        <>
            {isRegistering ? (
                <>
                    <RegisterManagement switchToLogin={() => setIsRegistering(false)} /> 
                    <button onClick={() => setIsRegistering(false)}>Switch to Login</button>
                </> ) : 
                (
                <>
                    <UserManagement />
                    <button onClick={() => setIsRegistering(true)}>Switch to Register</button>
                </>
                )
            }
        </>
    )
}

export default FormToggle;