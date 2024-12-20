import React, { useState } from 'react';
import UserManagement from '../SmartComponent/UserManagement';
import RegisterManagement from '../SmartComponent/RegisterManagement';

function FormToggle() {
    const [isRegistering, setIsRegistering] = useState(false);

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    {isRegistering ? (
                        <>
                            <RegisterManagement switchToLogin={() => setIsRegistering(false)} />
                            <div className="d-flex justify-content-center mt-3">
                                <button className="btn btn-primary" onClick={() => setIsRegistering(false)}>
                                    Switch to Login
                                </button>
                            </div>
                        </>
                    ) : (
                        <>
                            <UserManagement />
                            <div className="d-flex justify-content-center mt-3">
                                <button className="btn btn-primary" onClick={() => setIsRegistering(true)}>
                                    Switch to Register
                                </button>
                            </div>
                        </>
                    )}
                </div>
            </div>
        </div>
    );
}

export default FormToggle;
