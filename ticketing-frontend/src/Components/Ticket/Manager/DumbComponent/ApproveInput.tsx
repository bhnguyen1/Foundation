import React from 'react'
interface ApproveInputProps {
    ticketId: string; setTicketId: React.Dispatch<React.SetStateAction<string>>;
    handleApprove: () => void;
    handleDeny: () => void;
}
function ApproveInput({ticketId, setTicketId, handleApprove, handleDeny}: ApproveInputProps) {
  return (
    // the input field will take in the ticket ID and the manager will be able to approve or deny the ticket
    // the approve and deny buttons will be displayed next to the input field
    <form>
        <label>
            Ticket ID:
            <input type="text" name="ticketId" value={ticketId} onChange={(e) => setTicketId(e.target.value)}/>
        </label>
        <br />
        <button type="button" onClick={handleApprove}>
        Approve
      </button>
      <button type="button" onClick={handleDeny}>
        Deny
      </button>
    </form>
  )
}

export default ApproveInput