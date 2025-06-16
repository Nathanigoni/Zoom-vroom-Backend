package com.example.Ticket.Repository;

import com.example.Ticket.Model.Ticket;
import com.example.Ticket.Model.TicketStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {
    List<Ticket> findByStatus(TicketStatus status);
    List<Ticket> findByStatusOrderByBookingTimestampAsc(TicketStatus status);
    Ticket findFirstByStatusOrderByBookingTimestampAsc(TicketStatus status);
    Optional<Ticket> findByTicketId(String ticketId);
    List<Ticket> findByUserIdAndStatus(String userId, TicketStatus status);
    List<Ticket> findByUserId(String userId);
}
