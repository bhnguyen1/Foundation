import React from 'react'

type SubmitInputProps = {description: string, setDescription: React.Dispatch<React.SetStateAction<string>>, 
amount: number, setAmount: React.Dispatch<React.SetStateAction<number>>, handleSubmit: any};

function SubmitInput({description, setDescription, amount, setAmount, handleSubmit}: SubmitInputProps) {
  
  return (
    <form onSubmit={handleSubmit}>
        <h2>Submit Ticket</h2>
        <label>Description: </label>
        <input type="text" value={description} onChange={(e: any) => setDescription(e.target.value)} placeholder="Enter the description"/>
        <br/>
        <label>Amount: </label>
        <input type="number" value={amount} onChange={(e: any) => setAmount(e.target.value)} placeholder="Enter the amount"/>
        <br/>
        <button type="submit">Submit</button>
    </form>
  )
}

export default SubmitInput