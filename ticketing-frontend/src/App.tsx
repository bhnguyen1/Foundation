import React from 'react';
// import logo from './logo.svg';
import './App.css';
import { Routes, Route, Navigate } from 'react-router-dom';
import UserManagement from './Components/FormLogin/SmartComponent/UserManagement';
import Navbar from './Components/Navbar/Navbar';
import { AuthProvider } from './Components/Context/ReducerUserContext';
import FormToggle from './Components/FormLogin/FormToggle/FormToggle';
import ViewTickets from './Components/Ticket/Employee/SmartComponent/ViewTickets';
import SubmitTickets from './Components/Ticket/Employee/SmartComponent/SubmitTickets';
import ApproveTickets from './Components/Ticket/Manager/ApproveTickets';
import QueryTickets from './Components/Ticket/Manager/QueryTickets';
import { TicketProvider } from './Components/Context/TicketContext';




function App() {
  return (
    <div className="App">
      <AuthProvider>
        <TicketProvider>
          <Navbar/>
          <Routes>
            <Route path="/" element={<Navigate to="/login" replace />}/>
            <Route path="/login" element={<FormToggle />} />
            <Route path="/view-tickets" element={<ViewTickets />} />
            <Route path="/submit-tickets" element={<SubmitTickets />} />
            <Route path="/approve-tickets" element={<ApproveTickets />} />
            <Route path="/query-tickets" element={<QueryTickets />} />
          </Routes>
        </TicketProvider>
      </AuthProvider>
    </div>
  );
}

export default App;
