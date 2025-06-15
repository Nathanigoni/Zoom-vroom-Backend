package com.example.Ticket.Service;

import com.example.Ticket.Model.TicketStatus;

import java.time.LocalDateTime;

public class UserTicketResponse {
    private String ticketId;
    private String seatNumber;
    private String location;
    private LocalDateTime travelTime;
    private LocalDateTime expiryTime;
    private LocalDateTime bookingTimestamp;
    private TicketStatus status;

    public UserTicketResponse(String ticketId, String seatNumber, String location,
                              LocalDateTime travelTime, LocalDateTime expiryTime, LocalDateTime bookingTimestamp,TicketStatus status) {
        this.ticketId = ticketId;
        this.seatNumber = seatNumber;
        this.location = location;
        this.travelTime = travelTime;
        this.expiryTime = expiryTime;
        this.bookingTimestamp = bookingTimestamp;
        this.status = status;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getLocation() {
        return location;
    }


    public LocalDateTime getTravelTime() {
        return travelTime;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public LocalDateTime getBookingTimestamp() {
        return bookingTimestamp;


    }
}
