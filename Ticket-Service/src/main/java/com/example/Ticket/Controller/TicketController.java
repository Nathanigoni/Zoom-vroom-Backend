package com.example.Ticket.Controller;

import com.example.Ticket.Service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTicket(@RequestBody TicketRequest request) {
        return ResponseEntity.ok(ticketService.createTicket(request));
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<PublicTicketResponse> getTicket(@PathVariable String ticketId) {
        return ResponseEntity.ok(ticketService.getTicketPublic(ticketId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserTicketResponse>> getUserTickets(@PathVariable String userId) {
        return ResponseEntity.ok(ticketService.getUserTickets(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PublicTicketResponse>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @PostMapping("/book/{ticketId}")
    public ResponseEntity<BookedTicketResponse> bookTicket(
            @PathVariable String ticketId,
            @RequestBody BookingRequest request
    ) {
        BookedTicketResponse response = ticketService.bookTicketAndReturnSummary(
                ticketId,
                request.getSeatNumber(),  // now required
                request.getUserId()
        );
        return ResponseEntity.ok(response);}

    @PostMapping("/cancel")
    public ResponseEntity<Void> cancelTicket(@RequestBody CancelRequest request) {
        ticketService.cancelTicket(request.getTicketId(), request.getUserId());
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/save")
    public ResponseEntity<String> saveTicket(@RequestBody BookingRequest request) {
        ticketService.saveTicket(request.getTicketId(), request.getUserId());
        return ResponseEntity.ok("Ticket saved successfully.");
    }

}
