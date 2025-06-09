package com.example.TicketApp.Controllers;

import com.example.TicketApp.dto.CreateTicketDto;
import com.example.TicketApp.Models.Ticket;
import com.example.TicketApp.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class Controller {

    @Autowired
    private TicketService ticketService;

    // POST /ticket/book – Book a ticket
    @PostMapping("/book")
    public Ticket bookTicket(@RequestBody CreateTicketDto dto) {
        return ticketService.bookTicket(dto);
    }

    // POST /ticket/cancel/{ticketId} – Cancel a ticket
    @PostMapping("/cancel/{ticketId}")
    public Ticket cancelTicket(@PathVariable String ticketId) {
        return ticketService.cancelTicket(ticketId);
    }

    // GET /ticket/{ticketId} – View ticket details
    @GetMapping("/{ticketId}")
    public Ticket getTicket(@PathVariable String ticketId) {
        return ticketService.getTicket(ticketId);
    }
}
