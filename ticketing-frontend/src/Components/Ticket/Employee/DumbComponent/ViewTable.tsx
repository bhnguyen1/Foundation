import React from 'react'
import { Ticket } from '../../../Context/TicketContext'

interface ViewTableProps {
  tickets: Ticket[];
}

function ViewTable({ tickets }: ViewTableProps) {
  return (
    <div className="table-container">
      <table className="ticket-table">
        <thead>
          <tr>
            <th>Ticket ID</th>
            <th>Description</th>
            <th>Amount</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          {tickets.map((ticket) => (
            <tr key={ticket.ticketId}>
              <td>{ticket.ticketId}</td>
              <td>{ticket.description}</td>
              <td>${ticket.amount}</td>
              <td>{ticket.status}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}


export default ViewTable