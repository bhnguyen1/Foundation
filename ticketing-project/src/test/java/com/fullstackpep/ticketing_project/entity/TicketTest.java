package com.fullstackpep.ticketing_project.entity;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class TicketTest {

    @Test
    void testParameterizedConstructor() {
        Ticket ticket = new Ticket(1, 100, "description");
        
        assertNull(ticket.getTicketId(), "Ticket ID should be null for this constructor");
        assertEquals(100, ticket.getAmount(), "Amount should be set correctly");
        assertEquals("description", ticket.getDescription(), "Description should be set correctly");
    }

    @Test
    void testFullConstructor() {
        Ticket ticket = new Ticket(1, 100, "description");

        // Assert
        assertEquals(null, ticket.getTicketId(), "Ticket ID should be set correctly");
        assertEquals(100, ticket.getAmount(), "Amount should be set correctly");
        assertEquals("description", ticket.getDescription(), "Description should be set correctly");
    }

    @Test
    void shouldSetandGetCorrectFields() {
        Ticket ticket = new Ticket();
        ticket.setTicketId(1);
        ticket.setAmount(100);
        ticket.setDescription("description");

        // Assert
        assertEquals(1, ticket.getTicketId(), "Ticket ID should be set correctly");
        assertEquals(100, ticket.getAmount(), "Amount should be set correctly");
        assertEquals("description", ticket.getDescription(), "Description should be set correctly");
    }

    @Test
    void testToString() {
        Ticket ticket = new Ticket(1, 100, "description");
        assertEquals("Ticket{ticketId=null, amount=100, description='description', status=PENDING}", ticket.toString());
    }

    @Test
    void equalsTest() {
        Ticket ticket1 = new Ticket(1, 100, "description");
        Ticket ticket2 = new Ticket(1, 100, "description");
        assertEquals(ticket1, ticket2);
    }
    

}
