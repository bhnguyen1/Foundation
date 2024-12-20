import React from 'react'
import { Ticket } from '../../../Context/TicketContext'

interface QueryTableProps {
  tickets: Ticket[]; 
}

function QueryTable({tickets}: QueryTableProps) { //will need to inlcude props later
  return (
    <div className="table-container">
      <table className="ticket-table">
        <thead>
          <tr>
            <th>Ticket ID</th>
            <th>Description</th>
            <th>Amount</th>
          </tr>
        </thead>
        <tbody>
          {tickets.map((ticket) => (
            <tr key={ticket.ticketId}>
              <td>{ticket.ticketId}</td>
              <td>{ticket.description}</td>
              <td>${ticket.amount}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default QueryTable