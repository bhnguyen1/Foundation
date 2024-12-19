import React, {createContext, useState, ReactNode} from 'react'
    
export interface Ticket {
    ticketId?: number;
    submittedBy: number;
    amount: number;
    description: string;
    status?: 'PENDING' | 'APPROVED' | 'DENIED';
}

interface TicketContextType {
    tickets: Ticket[];
    submitTicket: (ticketData: Ticket) => void;
    //what should i return here? if i don't need anything else besides to look at the tickets in an async function
    getTickets: (tickets: Ticket[]) => void;
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

    const getTickets = (tickets: Ticket[]) => {
        setTickets(tickets);
    }

    return (
        <TicketContext.Provider value={{tickets, submitTicket, getTickets}}>
            {children}
        </TicketContext.Provider>
    )
}