import React, { useContext, useState, FormEvent }from 'react'
import { TicketContext } from '../../../Context/TicketContext'
import { AuthContext } from '../../../Context/ReducerUserContext';
import ApproveInput from '../DumbComponent/ApproveInput'
import QueryTickets from './QueryTickets'

function ApproveTickets() {
  const ticketContext = useContext(TicketContext);
  const authContext = useContext(AuthContext);

  if(!ticketContext) {
    throw new Error("useTicketContext must be used within a TicketProvider")
  }
  if(!authContext) {
    throw new Error("useAuthContext must be used within an AuthProvider")
  }

  const { getTickets } = ticketContext;

  const [ticketId, setTicketId] = useState('');

  async function handleAction(action: 'APPROVED' | 'DENIED') {
 
    if (!ticketId) {
      alert('Please enter a Ticket ID!');
      return;
    }

    try {
      const response = await fetch(`http://localhost:8080/tickets/${ticketId}`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ status: action }),
      });

      if (!response.ok) {
        throw new Error('Failed to update ticket!');
      }

      alert(`Ticket ${ticketId} has been ${action.toLowerCase()}.`);
      setTicketId('');
      await refreshTickets(); // Refresh the ticket list after updating

    } catch (error) {
      console.error('Failed to submit ticket', error);
      alert('Error updating ticket.');
    }
  }

  async function refreshTickets() {
    try {
      const response = await fetch(`http://localhost:8080/tickets/PENDING`);
      if (!response.ok) {
        throw new Error('Failed to fetch tickets');
      }

      const tickets = await response.json();
      getTickets(tickets);
    } catch (error) {
      console.error('Failed to fetch tickets', error);
    }
  }

  return (
    <>
        <ApproveInput ticketId={ticketId} setTicketId={setTicketId} 
        handleApprove={() => handleAction('APPROVED')} handleDeny={() => handleAction('DENIED')}/>
        <QueryTickets />
    </>
  )
}

export default ApproveTickets