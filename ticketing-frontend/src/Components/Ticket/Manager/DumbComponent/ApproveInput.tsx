import React from 'react';

interface ApproveInputProps {
  ticketId: string;
  setTicketId: React.Dispatch<React.SetStateAction<string>>;
  handleApprove: () => void;
  handleDeny: () => void;
}

function ApproveInput({ ticketId, setTicketId, handleApprove, handleDeny }: ApproveInputProps) {
  return (
    // Using Bootstrap's grid system for alignment
    <form className="row align-items-center mb-4">
      <h2 className="mb-3">Approving Tickets</h2>
      <div className="col-auto">
        <label htmlFor="ticketID" className="visually-hidden">Ticket ID</label>
        <input
          type="text"
          className="form-control"
          id="ticketID"
          value={ticketId}
          onChange={(e) => setTicketId(e.target.value)}
          placeholder="Enter Ticket ID"
        />
      </div>
      <div className="col-auto">
        <button type="button" className="btn btn-success me-2" onClick={handleApprove}>
          Approve
        </button>
        <button type="button" className="btn btn-danger" onClick={handleDeny}>
          Deny
        </button>
      </div>
    </form>
  );
}

export default ApproveInput;
