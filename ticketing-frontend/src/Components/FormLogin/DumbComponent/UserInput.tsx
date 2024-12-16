
type UserInputProps = {username: string, setUsername: React.Dispatch<React.SetStateAction<string>>, 
    password: string, setPassword: React.Dispatch<React.SetStateAction<string>>, handleSubmit: any};

function UserInput({username, setUsername, password, setPassword, handleSubmit}: UserInputProps) {

  return (
    <form onSubmit={handleSubmit}>
        <label>Username: </label>
        <input type="text" value={username} onChange={(e: any) => setUsername(e.target.value)} placeholder="Enter your username"/>
        <br/>
        <label>Password: </label>
        <input type="password" value={password} onChange={(e: any) => setPassword(e.target.value)} placeholder="Enter your password"/>
        <br/>
        <button type="submit">Submit</button>
    </form>
  )
}

export default UserInput