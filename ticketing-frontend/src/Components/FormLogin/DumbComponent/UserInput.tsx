
type UserInputProps = {username: string, setUsername: React.Dispatch<React.SetStateAction<string>>, 
    password: string, setPassword: React.Dispatch<React.SetStateAction<string>>, handleSubmit: any};

function UserInput({ username, setUsername, password, setPassword, handleSubmit }: UserInputProps) {
  return (
    <form onSubmit={handleSubmit} className="p-3 border rounded">
        <h3 className="text-center">Login</h3>
        <div className="form-group">
            <label>Username:</label>
            <input
                type="text"
                value={username}
                onChange={(e: any) => setUsername(e.target.value)}
                placeholder="Enter your username"
                className="form-control"
            />
        </div>
        <div className="form-group">
            <label>Password:</label>
            <input
                type="password"
                value={password}
                onChange={(e: any) => setPassword(e.target.value)}
                placeholder="Enter your password"
                className="form-control"
            />
        </div>
        <div className="d-flex justify-content-between mt-3">
            <button type="submit" className="btn btn-success">Login</button>
            <button type="reset" className="btn btn-secondary">Reset</button>
        </div>
    </form>
  );
}

export default UserInput