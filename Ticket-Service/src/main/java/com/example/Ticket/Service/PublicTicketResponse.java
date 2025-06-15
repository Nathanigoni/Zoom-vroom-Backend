package com.example.Ticket.Service;

import com.example.Ticket.Model.TicketStatus;

import java.time.LocalDateTime;

public class PublicTicketResponse {
    private String ticketId;
    private String seatNumber;
    private String location;
    private LocalDateTime bookingTimestamp;
    private LocalDateTime travelTime;
    private LocalDateTime expiryTime;

    public PublicTicketResponse(String ticketId, String seatNumber,
                                String location,
                                LocalDateTime bookingTimestamp,
                                LocalDateTime travelTime,
                                LocalDateTime expiryTime) {
        this.ticketId = ticketId;
        this.seatNumber = seatNumber;
        this.location = location;
        this.bookingTimestamp = bookingTimestamp;
        this.travelTime = travelTime;
        this.expiryTime = expiryTime;
    }

    public String getTicketId() { return ticketId; }
    public String getSeatNumber() { return seatNumber; }
    public String getLocation() { return location; }
    public LocalDateTime getBookingTimestamp() { return bookingTimestamp; }
    public LocalDateTime getTravelTime() { return travelTime; }
    public LocalDateTime getExpiryTime() { return expiryTime; }
}
