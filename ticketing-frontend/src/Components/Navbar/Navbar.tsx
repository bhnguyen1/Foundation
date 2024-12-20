import React, { useContext } from 'react'
import { Link } from 'react-router-dom'
import { AuthContext } from '../Context/ReducerUserContext'

function Navbar() {
    const context = useContext(AuthContext);

    if (!context) {
        throw new Error("You probably forgot to put <AuthProvider> in your component tree.");
    }

    const { state } = context;

    if(!state.user) {
        return null;
    }

    const role = state.user?.role;
    
    return (
        <>
            <nav className="navbar navbar-expand-lg bg-body-tertiary">
                <div className="container-fluid">
                    <Link className="navbar-brand" to="/home">TicketingApp</Link>
                    <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav">
                        {role === 'EMPLOYEE' && (
                            <>
                                <li className="nav-item">
                                    <Link className="nav-link" aria-current="page" to="/view-tickets">View Tickets</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/submit-tickets">Submit Ticket</Link>
                                </li>
                            </>
                        )}
                        {role === 'MANAGER' && (
                            <>
                                <li className="nav-item">
                                    <Link className="nav-link" aria-current="page" to="/approve-tickets">Approve Tickets</Link>
                                </li>
                            </>
                        )}
                    </ul>
                    </div>
                </div>
            </nav>
        </>
    )
}

export default Navbar