package com.fullstackpep.ticketing_project.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fullstackpep.ticketing_project.repository.TicketRepository;
import com.fullstackpep.ticketing_project.repository.UserRepository;
import com.fullstackpep.ticketing_project.entity.Ticket;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {
    
    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test 
    void shouldThrowExceptionWhenUserDoesNotExist() {
        Ticket ticket = new Ticket(1, 100, "description");
        when(userRepository.existsById(1)).thenReturn(false);

       assertThrows(NoSuchElementException.class, () -> ticketService.submitTicket(ticket));
       verify(ticketRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsEmpty() {
        Ticket ticket = new Ticket(1, 100, "");
        when(userRepository.existsById(1)).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> ticketService.submitTicket(ticket));
        verify(ticketRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenAmountIsNegative() {
        Ticket ticket = new Ticket(1, -100, "description");
        when(userRepository.existsById(1)).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> ticketService.submitTicket(ticket));
        verify(ticketRepository, never()).save(any());
    }

    @Test
    void shouldSetStatusToPendingWhenStatusIsNotProvided() {
        Ticket ticket = new Ticket(1, 100, "description");
        when(userRepository.existsById(1)).thenReturn(true);

        ticketService.submitTicket(ticket);
        assertEquals(Ticket.Status.PENDING, ticket.getStatus());
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    void shouldThrowExceptionWhenTicketDoesNotExist() {
        when(ticketRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> ticketService.changeTicketStatus(1, Ticket.Status.APPROVED));
        verify(ticketRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenStatusIsTheSame() {
        Ticket ticket = new Ticket(1, 100, "description");
        ticket.setStatus(Ticket.Status.PENDING);
        when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));

        assertThrows(IllegalArgumentException.class, () -> ticketService.changeTicketStatus(1, Ticket.Status.PENDING));
        verify(ticketRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenChangingStatusToPending() {
        Ticket ticket = new Ticket(1, 100, "description");
        ticket.setStatus(Ticket.Status.APPROVED);
        when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));

        assertThrows(IllegalArgumentException.class, () -> ticketService.changeTicketStatus(1, Ticket.Status.PENDING));
        verify(ticketRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenSwitchingStatusBackToDenied() {
        Ticket ticket = new Ticket(1, 100, "description");
        ticket.setStatus(Ticket.Status.APPROVED);
        when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));

        assertThrows(IllegalArgumentException.class, () -> ticketService.changeTicketStatus(1, Ticket.Status.DENIED));
        verify(ticketRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenSwitchingStatusBackToApproved() {
        Ticket ticket = new Ticket(1, 100, "description");
        ticket.setStatus(Ticket.Status.DENIED);
        when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));

        assertThrows(IllegalArgumentException.class, () -> ticketService.changeTicketStatus(1, Ticket.Status.APPROVED));
        verify(ticketRepository, never()).save(any());
    }

}
