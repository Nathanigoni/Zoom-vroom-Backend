package com.example.Ticket.Controller;

import com.example.Ticket.Model.Ticket;
import com.example.Ticket.Service.BookedTicketResponse;
import com.example.Ticket.Service.PublicTicketResponse;
import com.example.Ticket.Service.TicketRequest;
import com.example.Ticket.Service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    private TicketService ticketService;

    @PostMapping("/create")
    public ResponseEntity<Ticket> createTicket(@RequestBody @Validated TicketRequest request) {
        Ticket ticket = ticketService.createTicket(request);
        return ResponseEntity.ok(ticket);
    }

    @PostMapping("/book/{ticketId}")
    public ResponseEntity<BookedTicketResponse> bookTicket(@PathVariable String ticketId) {
        BookedTicketResponse response = ticketService.bookTicketAndReturnSummary(ticketId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Ticket>> getUserTickets(@PathVariable String userId) {
        List<Ticket> tickets = ticketService.getUserTickets(userId);
        return ResponseEntity.ok(tickets);
    }

    @PostMapping("/cancel/{ticketId}")
    public ResponseEntity<String> cancelTicket(@PathVariable String ticketId) {
        ticketService.cancelTicket(ticketId);
        return ResponseEntity.ok("Ticket cancelled successfully.");
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicket(@PathVariable String ticketId) {
        Ticket ticket = ticketService.getTicket(ticketId);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PublicTicketResponse>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }
}
