package com.fullstackpep.ticketing_project.controller;

import com.fullstackpep.ticketing_project.entity.*;
import com.fullstackpep.ticketing_project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TicketingController.class)
public class TicketingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private TicketService ticketService;

    @Test
    public void postTicketSuccessful() throws Exception {
        // Arrange
        Ticket mockTicket = new Ticket(1, 100, "Test Ticket");
        mockTicket.setStatus(Ticket.Status.PENDING);
        when(ticketService.submitTicket(any(Ticket.class))).thenReturn(mockTicket);

        String json = "{\"submittedBy\":1,\"amount\":100,\"description\":\"Test Ticket\"}";

        // Act & Assert
        mockMvc.perform(post("/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Test Ticket"));
    }

    @Test
    public void postTicketWithInvalidUser() throws Exception {
        // Arrange
        when(ticketService.submitTicket(any(Ticket.class)))
                .thenThrow(new NoSuchElementException("User does not exist!"));

        String json = "{\"submittedBy\":999,\"amount\":100,\"description\":\"Invalid User Ticket\"}";

        // Act & Assert
        mockMvc.perform(post("/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void postTicketWithInvalidAmount() throws Exception {
        // Arrange
        when(ticketService.submitTicket(any(Ticket.class)))
                .thenThrow(new IllegalArgumentException("Amount must be greater than 0!"));

        String json = "{\"submittedBy\":1,\"amount\":-100,\"description\":\"Invalid Amount Ticket\"}";

        // Act & Assert
        mockMvc.perform(post("/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getTicketsByStatusSuccessful() throws Exception {
        // Arrange
        Ticket mockTicket = new Ticket(1, 100, "Test Ticket");
        mockTicket.setStatus(Ticket.Status.PENDING);
        when(ticketService.viewTicketsByStatus(Ticket.Status.PENDING))
                .thenReturn(Arrays.asList(mockTicket));

        // Act & Assert
        mockMvc.perform(get("/tickets/PENDING"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description").value("Test Ticket"));
    }

    @Test
    public void patchTicketStatusSuccessful() throws Exception {
        // Arrange
        when(ticketService.changeTicketStatus(eq(1), eq(Ticket.Status.APPROVED))).thenReturn(1);

        String json = "{\"status\":\"APPROVED\"}";

        // Act & Assert
        mockMvc.perform(patch("/tickets/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void patchTicketStatusInvalid() throws Exception {
        // Arrange
        when(ticketService.changeTicketStatus(eq(1), eq(Ticket.Status.PENDING)))
                .thenThrow(new IllegalArgumentException("Can't change status to PENDING!"));

        String json = "{\"status\":\"PENDING\"}";

        // Act & Assert
        mockMvc.perform(patch("/tickets/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test 
    public void patchTicketsNotAvailable() throws Exception {
        // Arrange
        when(ticketService.changeTicketStatus(eq(1), eq(Ticket.Status.APPROVED)))
                .thenThrow(new NoSuchElementException("Ticket does not exist!"));

        String json = "{\"status\":\"APPROVED\"}";

        // Act & Assert
        mockMvc.perform(patch("/tickets/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getUsersTicketsSuccessful() throws Exception {
        // Arrange
        Ticket mockTicket = new Ticket(1, 100, "Test Ticket");
        mockTicket.setStatus(Ticket.Status.PENDING);
        when(ticketService.viewTicketsById(1))
                .thenReturn(Arrays.asList(mockTicket));

        // Act & Assert
        mockMvc.perform(get("/users/1/tickets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description").value("Test Ticket"));
    }

    @Test
    public void postLoginSuccessful() throws Exception {
        // Arrange
        User mockUser = new User(1, "testUser", "testPassword", User.Role.EMPLOYEE);
        when(userService.login("testUser", "testPassword"))
                .thenReturn(mockUser);

        String json = "{\"username\":\"testUser\",\"password\":\"testPassword\"}";

        // Act & Assert
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser"));
    }

    @Test
    public void postLoginInvalid() throws Exception {
        // Arrange
        when(userService.login("invalidUser", "invalidPassword"))
                .thenThrow(new IllegalArgumentException("Invalid login!"));

        String json = "{\"username\":\"invalidUser\",\"password\":\"invalidPassword\"}";

        // Act & Assert
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void postRegisterSuccessful() throws Exception {
        // Arrange
        User mockUser = new User(1, "testUser", "testPassword", User.Role.EMPLOYEE);
        when(userService.addUser(any(User.class)))
                .thenReturn(mockUser);

        String json = "{\"username\":\"testUser\",\"password\":\"testPassword\"}";

        // Act & Assert
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser"));
    }

    @Test
    public void postRegisterUsernameExists() throws Exception {
        // Arrange
        when(userService.addUser(any(User.class)))
                .thenThrow(new IllegalStateException("Username already exists!"));

        String json = "{\"username\":\"testUser\",\"password\":\"testPassword\"}";

        // Act & Assert
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isConflict());
    }

    @Test
    public void postRegisterInvalid() throws Exception {
        // Arrange
        when(userService.addUser(any(User.class)))
                .thenThrow(new IllegalArgumentException("Invalid user!"));

        String json = "{\"username\":\"\",\"password\":\"\"}";

        // Act & Assert
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

}
