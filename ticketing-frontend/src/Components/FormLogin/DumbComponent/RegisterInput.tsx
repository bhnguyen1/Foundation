
type RegisterInputProps = {username: string, setUsername: React.Dispatch<React.SetStateAction<string>>,
    password: string, setPassword: React.Dispatch<React.SetStateAction<string>>, handleSubmit: any};

function RegisterInput({username, setUsername, password, setPassword, handleSubmit}: RegisterInputProps) {
  return (
    <form onSubmit={handleSubmit}>
        <h2>Register</h2>
        <label>Username: </label>
        <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} placeholder="Enter your username" />
        <br />
        <label>Password: </label>
        <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Enter your password" />
        <br />
        <button type="submit">Register</button>
    </form>
  )
}

export default RegisterInput