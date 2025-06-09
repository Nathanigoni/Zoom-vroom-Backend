package com.example.TicketApp.Repositories;

import com.example.TicketApp.Models.Ticket;
import com.example.TicketApp.Models.TicketStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository extends MongoRepository<Ticket, String> {

    List<Ticket> findByStatus(TicketStatus ticketStatus);
    boolean existsByTravelTimeAndSeatNumber(LocalDateTime travelTime, String seatNumber);

}
