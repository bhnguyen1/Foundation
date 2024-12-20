import React from 'react'

type SubmitInputProps = {description: string, setDescription: React.Dispatch<React.SetStateAction<string>>, 
amount: number, setAmount: React.Dispatch<React.SetStateAction<number>>, handleSubmit: any};

function SubmitInput({description, setDescription, amount, setAmount, handleSubmit}: SubmitInputProps) {
  
  return (
    <form onSubmit={handleSubmit}>
        <h2>Submit Ticket</h2>
        <div className="mb-3">
          <label>Description: </label>
          <textarea
            id="description"
            className="form-control"
            value={description}
            onChange={(e: React.ChangeEvent<HTMLTextAreaElement>) => setDescription(e.target.value)}
            placeholder="Enter the description"
            rows={3}
            style={{width: '50%', margin: '0 auto', display: 'block'}}
          ></textarea>
        </div>
        <br/>
        <div className="mb-3">
          <label className="form-label">Amount: </label>
          <input type="number" className="form-control" id="exampleFormControlInput1" style={{width: '45%', margin: '0 auto', display: 'block'}} 
          placeholder="Enter the amount in $00.00" value={amount} onChange={(e: React.ChangeEvent<HTMLInputElement>) => setAmount(Number(e.target.value))}/>
        </div>
        <br/>
        <button type="submit" className="btn btn-outline-dark">Submit</button>
    </form>
  )
}

export default SubmitInput