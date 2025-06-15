package com.example.Ticket.Service;

import com.example.Ticket.Model.Ticket;
import com.example.Ticket.Model.TicketStatus;
import com.example.Ticket.Repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
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
        }

        if (Duration.between(now, request.getTravelTime()).toMinutes() < 10) {
            throw new IllegalArgumentException("Cannot book less than 10 minutes to departure.");
        }

        Ticket ticket = new Ticket(null, request.getLocation(), request.getTravelTime());
        return ticketRepository.save(ticket);
    }

    public PublicTicketResponse getTicketPublic(String ticketId) {
        Ticket ticket = ticketRepository.findByTicketId(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found with ID: " + ticketId));

        return new PublicTicketResponse(
                ticket.getTicketId(),
                ticket.getSeatNumber(),
                ticket.getLocation(),
                ticket.getBookingTimestamp(),
                ticket.getTravelTime(),
                ticket.getExpiryTime()
        );
    }


    public List<PublicTicketResponse> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(ticket -> new PublicTicketResponse(
                        ticket.getTicketId(),
                        ticket.getSeatNumber(),
                        ticket.getLocation(),
                        ticket.getBookingTimestamp(),
                        ticket.getTravelTime(),
                        ticket.getExpiryTime()
                ))
                .collect(Collectors.toList());
    }


    public List<UserTicketResponse> getUserTickets(String userId) {
        return ticketRepository.findByUserId(userId).stream()
                .map(ticket -> new UserTicketResponse(
                        ticket.getTicketId(),
                        ticket.getSeatNumber(),
                        ticket.getLocation(),
                        ticket.getTravelTime(),
                        ticket.getExpiryTime(),
                        ticket.getBookingTimestamp(),
                        ticket.getStatus()
                ))
                .collect(Collectors.toList());
    }

    public BookedTicketResponse bookTicketAndReturnSummary(String ticketId, String seatNumber, String userId) {
        Ticket templateTicket = ticketRepository.findByTicketId(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Template ticket not found with ID: " + ticketId));

        if (templateTicket.getStatus() != TicketStatus.CREATED) {
            throw new IllegalStateException("Only template tickets (CREATED) can be used to book.");
        }

        if (templateTicket.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Ticket template has expired.");
        }

        boolean seatTaken = ticketRepository.existsBySeatNumberAndTravelTimeAndStatus(
                seatNumber, templateTicket.getTravelTime(), TicketStatus.CONFIRMED);

        if (seatTaken) {
            throw new IllegalArgumentException("Seat already taken for this travel time.");
        }

        Ticket bookedTicket = new Ticket(null, templateTicket.getLocation(), templateTicket.getTravelTime());
        bookedTicket.setUserId(userId);
        bookedTicket.setSeatNumber(seatNumber);
        bookedTicket.setStatus(TicketStatus.CONFIRMED);
        bookedTicket.setBookingTimestamp(LocalDateTime.now());
        bookedTicket.setExpiryTime(templateTicket.getTravelTime());

        ticketRepository.save(bookedTicket);

        return new BookedTicketResponse(
                bookedTicket.getSeatNumber(),
                bookedTicket.getLocation(),
                bookedTicket.getExpiryTime(),
                bookedTicket.getTravelTime(),
                bookedTicket.getStatus()
        );
    }

    public void cancelTicket(String ticketId, String userId) {
        Ticket ticket = ticketRepository.findByTicketId(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found with ID: " + ticketId));

        // Optional: Uncomment to restrict users from canceling others' tickets
        // if (!userId.equals(ticket.getUserId())) {
        //     throw new IllegalStateException("You can only cancel your own ticket.");
        // }

        if (Duration.between(LocalDateTime.now(), ticket.getTravelTime()).toMinutes() < 3) {
            throw new IllegalStateException("Cannot cancel less than 3 minutes to departure.");
        }

        ticket.setStatus(TicketStatus.CREATED);
        ticket.setUserId(null);
        ticket.setSeatNumber(null);
        ticketRepository.save(ticket);
    }

    public void saveTicket(String ticketId, String userId) {
        Ticket ticket = ticketRepository.findByTicketId(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found with ID: " + ticketId));

        if (ticket.getStatus() != TicketStatus.CREATED) {
            throw new IllegalStateException("Only CREATED tickets can be saved.");
        }

        ticket.setUserId(userId);
        ticket.setStatus(TicketStatus.SAVED);
        ticket.setSavedTimestamp(LocalDateTime.now());
        ticketRepository.save(ticket);
    }

    public void expireStaleSavedTickets() {
        List<Ticket> savedTickets = ticketRepository.findByStatus(TicketStatus.SAVED);
        LocalDateTime now = LocalDateTime.now();

        for (Ticket ticket : savedTickets) {
            if (ticket.getSavedTimestamp() != null &&
                    Duration.between(ticket.getSavedTimestamp(), now).toMinutes() > 360) {

                ticket.setStatus(TicketStatus.CREATED);
                ticket.setUserId(null);
                ticket.setSavedTimestamp(null);
                ticketRepository.save(ticket);

                logger.info("Reverted ticket {} from SAVED to CREATED due to inactivity.", ticket.getTicketId());
            }
        }
    }

    @Scheduled(fixedRate = 60000) // every 1 minute
    public void autoExpireSavedTickets() {
        expireStaleSavedTickets();
    }
}
