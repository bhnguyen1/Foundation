import React , { useContext, useEffect } from 'react'
import QueryTable from '../DumbComponent/QueryTable'
import { TicketContext } from '../../../Context/TicketContext'
import { AuthContext } from '../../../Context/ReducerUserContext';

function QueryTickets() {
  const ticketContext = useContext(TicketContext);
  const authContext = useContext(AuthContext);

  if(!ticketContext) {
    throw new Error("useTicketContext must be used within a TicketProvider")
  }
  if(!authContext) {
    throw new Error("useAuthContext must be used within an AuthProvider")
  }

  const { tickets, getTickets } = ticketContext;

  async function refreshTickets() {
    try {
      const response = await fetch(`http://localhost:8080/tickets/PENDING`);
      if(!response.ok) {
        throw new Error('Failed to fetch tickets');
      }

      const tickets = await response.json();
      getTickets(tickets);
    } catch(error) {
      console.error('Failed to fetch tickets', error);
    }
  };

  useEffect(() => {
    refreshTickets();
  }, []);

  return (
    <div>
      {tickets.length > 0 ? (
        <QueryTable tickets={tickets}/>
      ) : (<p>No Tickets Found</p>)}
    </div>
  )
}

export default QueryTickets;