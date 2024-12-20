package com.fullstackpep.ticketing_project.service;

import com.fullstackpep.ticketing_project.entity.Ticket;
import com.fullstackpep.ticketing_project.repository.UserRepository;
import com.fullstackpep.ticketing_project.repository.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/*
 * what logic should be in this service class?
 * submit (add) a ticket
 * cases:
 * can't be blank description
 * can't be negative amount
 * default status is pending
 * has to have an existing user id
 * ------------------------------------------------------------
 * change status of a ticket
 * cases:
 * can't change status to the same status
 * can't change status to pending
 * can't change status to denied if it's already approved
 * can't change status to approved if it's already denied
 * ------------------------------------------------------------
 * view all tickets by a user
 * ------------------------------------------------------------
 * view all tickets by a status
 * 
 * 
 * 
*/

@Service
public class TicketService {
    TicketRepository ticketRepository;
    UserRepository userRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public int changeTicketStatus(int ticketId, Ticket.Status newStatus) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        if(!ticket.isPresent()) {
            System.out.println("Ticket could not be found");
            throw new NoSuchElementException("Ticket does not exist!");
        }
        if(ticket.get().getStatus() == newStatus) {
            System.out.println("Ticket is already " + newStatus);
            throw new IllegalArgumentException("Ticket is already " + newStatus);
        }
        if(newStatus == Ticket.Status.PENDING) {
            System.out.println("Can't change status back to PENDING!");
            throw new IllegalArgumentException("Can't change status to PENDING!");
        }
        if(ticket.get().getStatus() == Ticket.Status.APPROVED && newStatus == Ticket.Status.DENIED) {
            System.out.println("Can't change status to DENIED if it's already APPROVED!");
            throw new IllegalArgumentException("Can't change status to DENIED if it's already APPROVED!");
        }
        if(ticket.get().getStatus() == Ticket.Status.DENIED && newStatus == Ticket.Status.APPROVED) {
            System.out.println("Can't change status to APPROVED if it's already DENIED!");
            throw new IllegalArgumentException("Can't change status to APPROVED if it's already DENIED!");
        }
        System.out.println("Changing status of ticket");
        Ticket changedTicket = ticket.get();
        changedTicket.setStatus(newStatus);
        ticketRepository.save(changedTicket);
        System.out.println("Status of ticket changed");
        return 1;
    }

    public List<Ticket> viewTicketsByStatus(Ticket.Status status) {
        return ticketRepository.findTicketsByStatus(status);
    }

    public List<Ticket> viewTicketsById(Integer userId) {
        return ticketRepository.findTicketsBySubmittedBy(userId);
    }

    @Transactional
    public Ticket submitTicket(Ticket ticket) {
        if(!userRepository.existsById(ticket.getSubmittedBy())) {
            System.out.println("User could not be found");
            throw new NoSuchElementException("User does not exist!");
        }
        if(ticket.getDescription().isEmpty() || ticket.getAmount() <= 0) {
            System.out.println("User could not submit ticket");
            throw new IllegalArgumentException("Ticket does not follow the guidelines!");
        }
        if(ticket.getStatus() == null) {
            System.out.println("Setting status to PENDING");
            ticket.setStatus(Ticket.Status.PENDING);
            System.out.println("Status set to PENDING");
        }
    
        return ticketRepository.save(ticket);
    }

}
