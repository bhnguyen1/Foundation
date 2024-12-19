package com.fullstackpep.ticketing_project.repository;

import com.fullstackpep.ticketing_project.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

import java.util.List;

/*
 * Point of this repository interface is to provide a way to interact with the database 
 * to perform CRUD operations on the Ticket entity
 * Table name: ticket
 * ticketId (primary key) : type int
 * submittedBy (foreign key) : type int
 * amount : type int
 * description : type varchar (255)
 * status : type varchar (255)
 * 
 * things to check for:
 * see all tickets made by a user
 * see all tickets
 * see all tickets by status
*/

public interface TicketRepository extends JpaRepository<Ticket, Integer>{
    ///checks to see all tickets made by a user
    List<Ticket> findTicketsBySubmittedBy(Integer userId);

    //checks to see all tickets by a certain status
    List<Ticket> findTicketsByStatus(Ticket.Status status);
}
