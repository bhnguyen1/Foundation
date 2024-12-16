import React from 'react'
import { Link } from 'react-router-dom'

function Navbar() {
  return (
    <>
        <nav className="navbar navbar-expand-lg bg-body-tertiary">
            <div className="container-fluid">
                <Link className="navbar-brand" to="/">Navigation</Link>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNav">
                <ul className="navbar-nav">
                    <li className="nav-item">
                        <Link className="nav-link" aria-current="page" to="/login">Home</Link>
                    </li>
                    {/* <li className="nav-item">
                        <Link className="nav-link" to="#">Submit Ticket</Link> //Make sure to make this a link to the submit ticket page
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link" to="#">View Ticket History</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link disabled" aria-disabled="true">Ticket Submissions</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link disabled" aria-disabled="true">Ticket Approval</Link>
                    </li> */}
                </ul>
                </div>
            </div>
        </nav>
    </>
  )
}

export default Navbar