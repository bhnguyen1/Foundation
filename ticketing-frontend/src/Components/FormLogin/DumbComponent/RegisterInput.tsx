
type RegisterInputProps = {username: string, setUsername: React.Dispatch<React.SetStateAction<string>>,
    password: string, setPassword: React.Dispatch<React.SetStateAction<string>>, handleSubmit: any};

  function RegisterInput({ username, setUsername, password, setPassword, handleSubmit }: RegisterInputProps) {
    return (
      <form onSubmit={handleSubmit} className="p-3 border rounded">
          <h3 className="text-center">Register</h3>
          <div className="form-group">
              <label>Username:</label>
              <input
                  type="text"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                  placeholder="Enter your username"
                  className="form-control"
              />
          </div>
          <div className="form-group">
              <label>Password:</label>
              <input
                  type="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  placeholder="Enter your password"
                  className="form-control"
              />
          </div>
          <div className="d-flex justify-content-between mt-3">
              <button type="submit" className="btn btn-success">Register</button>
              <button type="reset" className="btn btn-secondary">Reset</button>
          </div>
      </form>
    );
  }

export default RegisterInput