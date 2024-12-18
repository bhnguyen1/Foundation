import React from 'react';
// import logo from './logo.svg';
import './App.css';
import { Routes, Route, Navigate } from 'react-router-dom';
import UserManagement from './Components/FormLogin/SmartComponent/UserManagement';
import Navbar from './Components/Navbar/Navbar';
import { AuthProvider } from './Components/Context/ReducerUserContext';
import FormToggle from './Components/FormLogin/FormToggle/FormToggle';



function App() {
  return (
    <div className="App">
      <AuthProvider>
        <Navbar/>
        <Routes>
          <Route path="/" element={<Navigate to="/login" replace />}/>
          <Route path="/login" element={<FormToggle />} />
        </Routes>
      </AuthProvider>
    </div>
  );
}

export default App;
