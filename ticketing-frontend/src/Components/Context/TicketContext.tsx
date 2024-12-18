import React, {createContext, useState, ReactNode} from 'react'
    
export interface Ticket {
    submitter: number;
    amount: number;
    description: string;
}

interface TicketContextType {
    tickets: Ticket[];
    submitTicket: (ticketData: Ticket) => void;
    // include other functions to query the tickets, modify tickets, and fetch tickets based on submitter
}

export const TicketContext = createContext<TicketContextType | undefined>(undefined);

interface TicketProviderProps {
    children: ReactNode;
}

export const TicketProvider: React.FC<TicketProviderProps> = ({children}) => {
    const [tickets, setTickets] = useState<Ticket[]>([]);

    const submitTicket = (ticketData: Ticket) => {
        setTickets(existingTicket => [...existingTicket, ticketData]); 
    }

    return (
        <TicketContext.Provider value={{tickets, submitTicket}}>
            {children}
        </TicketContext.Provider>
    )
}