package com.fullstackpep.ticketing_project.controller;

//import entities and services here
import com.fullstackpep.ticketing_project.entity.*;
import com.fullstackpep.ticketing_project.service.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;

//import java libraries as need here
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class TicketingController {
    private UserService userService;
    private TicketService ticketService;

    @Autowired
    public TicketingController(UserService userService, TicketService ticketService) {
        this.userService = userService;
        this.ticketService = ticketService;
    }

    @PatchMapping("/tickets/{ticketId}")
    public @ResponseBody ResponseEntity<Integer> patchTicket(@PathVariable int ticketId, @RequestBody Ticket ticket) {
        try {
            int updatedTicket = ticketService.changeTicketStatus(ticketId, ticket.getStatus());
            return ResponseEntity.ok(updatedTicket);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    //get ticket by status
    //could be wrong path variable parameter - check error log to make sure 
    @GetMapping("/tickets/{status}")
    public @ResponseBody List<Ticket> getTicketsByStatus(@PathVariable Ticket.Status status) {
        return ticketService.viewTicketsByStatus(status);
    }


    @GetMapping("/users/{userId}/tickets")
    public @ResponseBody List<Ticket> getUsersTickets(@PathVariable Integer userId) {
        return ticketService.viewTicketsById(userId);
    }

    @PostMapping("/tickets")
    public @ResponseBody ResponseEntity<Ticket> postTicket(@RequestBody Ticket ticket) {
        try {
            Ticket newTicket = ticketService.submitTicket(ticket);
            return ResponseEntity.ok(newTicket);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(400).body(null);
        } 
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null);
        }
    }
    

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<User> postLogin(@RequestBody User user) {
        try {
            User loggedInUser = userService.login(user.getUsername(), user.getPassword());
            return ResponseEntity.ok(loggedInUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<User> postRegister(@RequestBody User user) {
        try {
            User newUser = userService.addUser(user);
            return ResponseEntity.ok(newUser);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    
}
