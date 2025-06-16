package com.example.Ticket.Service;

import java.time.LocalDateTime;

public class PublicTicketResponse {
    private String ticketId;
    private String seatNumber;
    private String location;
    private String vehicleId;
    private LocalDateTime bookingTimestamp;
    private LocalDateTime travelTime;
    private LocalDateTime expiryTime;
    private  double price;

    public PublicTicketResponse(String ticketId, String seatNumber, String location,
                                LocalDateTime bookingTimestamp, LocalDateTime travelTime, LocalDateTime expiryTime, double  price) {
        this.ticketId = ticketId;
        this.seatNumber = seatNumber;
        this.location = location;
        this.vehicleId = vehicleId;
        this.bookingTimestamp = bookingTimestamp;
        this.travelTime = travelTime;
        this.expiryTime = expiryTime;
        this.price = price;
    }

    // Getters only (no setters for immutability)
    public String getTicketId() { return ticketId; }
    public String getSeatNumber() { return seatNumber; }
    public String getLocation() { return location; }
    public String getVehicleId() { return vehicleId; }
    public LocalDateTime getBookingTimestamp() { return bookingTimestamp; }
    public LocalDateTime getTravelTime() { return travelTime; }
    public LocalDateTime getExpiryTime() { return expiryTime; }
    public double getPrice() {return price;}
}
