package com.fullstackpep.ticketing_project.service;

import com.fullstackpep.ticketing_project.entity.Ticket;
import com.fullstackpep.ticketing_project.repository.UserRepository;
import com.fullstackpep.ticketing_project.repository.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Optional;

/*
 * what logic should be in this service class?
 * submit (add) a ticket
 * approve a ticket
 * deny a ticket
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

    
}
