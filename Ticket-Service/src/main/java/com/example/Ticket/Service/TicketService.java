package com.example.Ticket.Service;

import com.example.Ticket.Model.Ticket;
import com.example.Ticket.Model.TicketStatus;
import com.example.Ticket.Repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(TicketRequest request) {
        LocalDateTime now = LocalDateTime.now();

        if (request.getTravelTime() == null) {
            request.setTravelTime(now.plusMinutes(20));
            logger.info("No travelTime provided. Defaulting to {}", request.getTravelTime());
        }

        if (Duration.between(now, request.getTravelTime()).toMinutes() < 10) {
            throw new IllegalArgumentException("Cannot book less than 10 minutes to departure.");
        }

        // Optional: check for seat conflict by seatNumber + travelTime (if you want some kind of duplicate check)
        List<Ticket> existingTickets = ticketRepository.findByStatus(TicketStatus.CONFIRMED);
        boolean seatTaken = existingTickets.stream().anyMatch(t ->
                t.getSeatNumber() != null &&
                        t.getSeatNumber().equalsIgnoreCase(request.getSeatNumber()) &&
                        t.getTravelTime().equals(request.getTravelTime())
        );

        if (seatTaken) {
            throw new IllegalArgumentException("This seat is already booked.");
        }

        Ticket ticket = new Ticket(
                request.getSeatNumber(),
                request.getLocation(),
                request.getTravelTime()
        );

        ticket.setUserId(request.getUserId());
        ticket.setPrice(request.getPrice());

        return ticketRepository.save(ticket);
    }


    public Ticket getTicket(String ticketId) {
        return ticketRepository.findByTicketId(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found with ID: " + ticketId));
    }

    public Ticket bookTicket(String ticketId) {
        Ticket ticket = getTicket(ticketId);

        if (ticket.getStatus() != TicketStatus.CREATED) {
            throw new IllegalStateException("Only CREATED tickets can be booked.");
        }

        if (ticket.getExpiryTime().isBefore(LocalDateTime.now())) {
            ticket.setStatus(TicketStatus.EXPIRED);
            ticketRepository.save(ticket);
            throw new IllegalStateException("Ticket expired before booking.");
        }

        ticket.setStatus(TicketStatus.CONFIRMED);
        return ticketRepository.save(ticket);
    }

    public BookedTicketResponse bookTicketAndReturnSummary(String ticketId) {
        Ticket ticket = bookTicket(ticketId);
        return new BookedTicketResponse(
                ticket.getSeatNumber(),
                ticket.getLocation(),
                ticket.getExpiryTime(),
                ticket.getTravelTime(),
                ticket.getStatus(),
                ticket.getPrice()
        );
    }

    public void cancelTicket(String ticketId) {
        Ticket ticket = getTicket(ticketId);

        if (Duration.between(LocalDateTime.now(), ticket.getTravelTime()).toMinutes() < 3) {
            throw new IllegalStateException("Cannot cancel less than 3 minutes to departure.");
        }

        ticket.setStatus(TicketStatus.CANCELLED);
        ticketRepository.save(ticket);
    }

    public Ticket findNextAvailableTicket() {
        List<Ticket> tickets = ticketRepository.findByStatusOrderByBookingTimestampAsc(TicketStatus.CREATED);
        return tickets.isEmpty() ? null : tickets.get(0);
    }

    public Ticket bookNextAvailableTicket() {
        Ticket ticket = findNextAvailableTicket();
        if (ticket == null) {
            throw new RuntimeException("No available tickets to book.");
        }
        return bookTicket(ticket.getTicketId());
    }

    public List<Ticket> getUserTickets(String userId) {
        return ticketRepository.findByUserId(userId);
    }

    public List<PublicTicketResponse> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(ticket -> new PublicTicketResponse(
                        ticket.getTicketId(),
                        ticket.getSeatNumber(),
                        ticket.getLocation(),
                        ticket.getBookingTimestamp(),
                        ticket.getTravelTime(),
                        ticket.getExpiryTime(),
                        ticket.getPrice()
                ))
                .collect(Collectors.toList());
    }
}
