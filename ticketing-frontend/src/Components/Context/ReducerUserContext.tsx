import { User } from './UserContext';
import React, { createContext, useReducer } from 'react'; 

//the point of this file is to create a context and a reducer for the user authentication
//manages complex state logic for the user authentication

interface AuthState {
    user: User | null;
}

type AuthAction = {type: 'LOGIN', payload: User} | {type: 'LOGOUT'};

const authReducer = (state: AuthState, action: AuthAction): AuthState => {
    switch(action.type) {
        case 'LOGIN':
            return {user: action.payload};
        case 'LOGOUT':
            return {user: null};
        default:
            throw new Error(`Unhandled Action Type: ${(action as AuthAction).type}`);
    }
}

//context type 
interface authContextType {
    state: AuthState;
    dispatch: React.Dispatch<AuthAction>;
}

export const AuthContext = createContext<authContextType | undefined>(undefined);

const initialState: AuthState = {user: null};

//provider component
interface AuthProviderProps {
    children: React.ReactNode;
}

export const AuthProvider: React.FC<AuthProviderProps> = ({children}) => {
    const [state, dispatch] = useReducer(authReducer, initialState);

    return (
        <AuthContext.Provider value={{state, dispatch}}>
            {children}
        </AuthContext.Provider>
    )
}
