package com.example.TicketApp.Services;

import com.example.TicketApp.dto.CreateTicketDto;
import com.example.TicketApp.Models.Ticket;
import com.example.TicketApp.Models.TicketStatus;
import com.example.TicketApp.Repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    // Book a new ticket
    public Ticket bookTicket(CreateTicketDto dto) {
        // 1. Check travel time is in the future
        if (dto.getTravelTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Travel time must be in the future");
        }

        // 2. Enforce 10-minute rule
        if (dto.getTravelTime().isBefore(LocalDateTime.now().plusMinutes(10))) {
            throw new RuntimeException("Cannot book less than 10 minutes to departure");
        }

        // 3. Check if seat is already booked for the same travel time
        if (ticketRepository.existsByTravelTimeAndSeatNumber(dto.getTravelTime(), dto.getSeatNumber())) {
            throw new RuntimeException("Seat is already booked");
        }

        // 4. Create ticket with CREATED status
        Ticket ticket = new Ticket(dto.getUserId(), dto.getDestination(), dto.getTravelTime());
        ticket.setSeatNumber(dto.getSeatNumber());
        ticket.setStatus(TicketStatus.CREATED);
        ticket.setBookingTimestamp(LocalDateTime.now());
        ticket.setCancelled(false);
        ticket.setExpiryTime(dto.getTravelTime().plusHours(2)); // Optional

        ticket = ticketRepository.save(ticket); // Save with CREATED

        // 5. Simulate payment
        if (!mockPayment(dto.getUserId())) {
            throw new RuntimeException("Payment failed");
        }

        // 6. Update status to PAID and then ACTIVE
        ticket.setStatus(TicketStatus.PAID);
        ticket = ticketRepository.save(ticket); // Optional

        ticket.setStatus(TicketStatus.ACTIVE);
        return ticketRepository.save(ticket);
    }


    // Cancel ticket
    public Ticket cancelTicket(String ticketId) {
        Optional<Ticket> optional = ticketRepository.findById(ticketId);
        if (optional.isEmpty()) throw new RuntimeException("Ticket not found");

        Ticket ticket = optional.get();

        if (ticket.getStatus() == TicketStatus.EXPIRED)
            throw new RuntimeException("Ticket already expired");

        if (ticket.isCancelled())
            throw new RuntimeException("Ticket already cancelled");

        if (ticket.getTravelTime().isBefore(LocalDateTime.now().plusMinutes(3))) {
            throw new RuntimeException("Cannot cancel less than 3 minutes to departure");
        }

        ticket.setStatus(TicketStatus.CANCELLED);
        ticket.setCancelled(true);
        return ticketRepository.save(ticket);
    }

    // Expire tickets after travel time
    public void expireOldTickets() {
        List<Ticket> activeTickets = ticketRepository.findByStatus(TicketStatus.CONFIRMED);
        for (Ticket ticket : activeTickets) {
            if (ticket.getTravelTime().isBefore(LocalDateTime.now())) {
                ticket.setStatus(TicketStatus.EXPIRED);
                ticketRepository.save(ticket);
            }
        }
    }

    // Mock payment logic
    private boolean mockPayment(String userId) {
        return true; // Simulate success
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicket(String id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }
}
