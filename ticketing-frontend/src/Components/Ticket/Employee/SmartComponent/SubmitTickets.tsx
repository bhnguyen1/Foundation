import React from 'react'
import SubmitInput from '../DumbComponent/SubmitInput'
import { TicketContext } from '../../../Context/TicketContext'
import { FormEvent, useState, useContext } from 'react'
import { AuthContext } from '../../../Context/ReducerUserContext'

function SubmitTickets() {
  const [description, setDescription] = useState('');
  const [amount, setAmount] = useState(0);

  const ticketContext = useContext(TicketContext);
  const authContext = useContext(AuthContext);

  if(!ticketContext) {
    throw new Error("SubmitTickets must be used within a TicketProvider");
  }
  if(!authContext) {
    throw new Error("SubmitTickets must be used within an AuthProvider");
  }

  const { submitTicket } = ticketContext;
  const { state } = authContext;
  // console.log(state)

  async function handleSubmit(event: FormEvent) {
    event.preventDefault();
    //some kind of validation of maybe
  
    const ticketData = {
      submittedBy: state.user?.userId, //some kind of user id from the 
      amount,
      description
    }

    try {
      const response = await fetch('http://localhost:8080/tickets', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json'},
        body: JSON.stringify(ticketData)
      });

      if(response.status === 401) {
        alert('You must be logged in to submit a ticket');
        return;
      }
      if(response.status === 400) {
        alert('Please enter a valid amount or description');
        return;
      }
      if(!response.ok) {
        throw new Error('Failed to submit ticket!');
      }

      const savedTicket = await response.json();
      submitTicket(savedTicket);
      setAmount(0);
      setDescription('');
      //print message to the user that the ticket was submitted
      alert('Ticket submitted successfully');
    } catch(error) {
      console.error('Failed to submit ticket', error);
    }
  }

  return (
    <SubmitInput amount={amount} setAmount={setAmount} description={description} 
     setDescription={setDescription} handleSubmit={handleSubmit}/>
  )
}

export default SubmitTickets