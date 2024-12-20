import React, { useContext } from 'react';
import { AuthContext } from '../../Context/ReducerUserContext';
import { useNavigate } from 'react-router-dom';
import ticketingLogo from '../../../ticketingLogo.png';

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
                    <img src={ticketingLogo} alt="Ticketing Logo" width="250px" height="250px" style={{ marginBottom: "20px" }}/>
                    <br />
                    <button className="btn btn-outline-dark" onClick={handleLogout}>Logout</button>
                </div>
            )}
        </>
    );
}

export default UserInfo;
