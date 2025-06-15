package com.example.Ticket.Service;

import com.example.Ticket.Model.TicketStatus;

import java.time.LocalDateTime;

public class BookedTicketResponse {
    private String seatNumber;
    private String location;
    private LocalDateTime expiryTime;
    private LocalDateTime travelTime;
    private TicketStatus status;

    public BookedTicketResponse(String seatNumber, String location, LocalDateTime expiryTime,
                                LocalDateTime travelTime, TicketStatus status) {
        this.seatNumber = seatNumber;
        this.location = location;
        this.expiryTime = expiryTime;
        this.travelTime = travelTime;
        this.status = status;
    }

    public String getSeatNumber() { return seatNumber; }
    public String getLocation() { return location; }
    public LocalDateTime getExpiryTime() { return expiryTime; }
    public LocalDateTime getTravelTime() { return travelTime; }
    public TicketStatus getStatus() { return status; }
}
