//interface to define the shape of the context value
//helps to avoid typos and makes it easier to see what the context value should look like
import { createContext, useState, ReactNode } from 'react';

export interface User {
    userId: number;
    username: string;
    role: 'EMPLOYEE' | 'MANAGER';
}

interface UserContextType {
    user: User | null;
    login: (userData: User) => void;
    logout: () => void;
}

export const UserContext = createContext< UserContextType | undefined>(undefined);

interface UserProviderProps {
    children: ReactNode;
}

export const UserProvider: React.FC<UserProviderProps> = ({children}) => {
    const [user, setUser] = useState<User | null>(null);

    const login = (userData: User) => {
        setUser(userData);
    }

    const logout = () => {
        setUser(null);
    }

    return (
        <UserContext.Provider value={{user, login, logout}}>
            {children}
        </UserContext.Provider>
    )
}

