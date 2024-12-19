import React, { useContext, useEffect } from 'react'
import ViewTable from '../DumbComponent/ViewTable'
import { TicketContext } from '../../../Context/TicketContext'
import { AuthContext } from '../../../Context/ReducerUserContext';

function ViewTickets() {
  const ticketContext = useContext(TicketContext);
  const authContext = useContext(AuthContext);

  if(!ticketContext) {
    throw new Error("useTicketContext must be used within a TicketProvider")
  }
  if(!authContext) {
    throw new Error("useAuthContext must be used within an AuthProvider")
  }

  const { tickets, getTickets } = ticketContext;
  const { state } = authContext;

  //get the user id
  
  const userId = state.user?.userId;

  useEffect(() => {
    //fetch the tickets from the server
    async function fetchTickets() {
      try {
        const response = await fetch(`http://localhost:8080/users/${userId}/tickets`);
        if(!response.ok) {
          throw new Error('Failed to fetch tickets');
        }

        const tickets = await response.json();
        getTickets(tickets);
      } catch(error) {
        console.error('Failed to fetch tickets', error);
      }
    }

    fetchTickets();
  }, [userId, getTickets]);
  
  return (
    <div>
      <h1>My Tickets</h1>
      {tickets.length > 0 ? (
        <ViewTable tickets={tickets}/>
      ) : (<p>No Tickets Found</p>)}
    </div>
  );
}

export default ViewTickets;