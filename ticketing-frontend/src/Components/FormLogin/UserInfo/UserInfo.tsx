import React, { useContext } from 'react';
import { AuthContext } from '../../Context/ReducerUserContext';
import { useNavigate } from 'react-router-dom';

function UserInfo() {
    const context = useContext(AuthContext);
    const navigate = useNavigate(); // Initialize useNavigate for navigation

    if (!context) {
        throw new Error("UserInfo must be used within an AuthProvider");
    }

    const { state, dispatch } = context;

    const handleLogout = () => {
        dispatch({ type: 'LOGOUT' }); // Clear the user state
        navigate('/login'); // Redirect to the login page
    };

    return (
        <>
            {state.user && (
                <div>
                    <h1>Welcome, {state.user.username}</h1>
                    <button className="btn btn-outline-dark" onClick={handleLogout}>Logout</button>
                </div>
            )}
        </>
    );
}

export default UserInfo;
