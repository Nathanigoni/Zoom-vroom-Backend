package com.example.TicketApp.Models;

import java.time.LocalDateTime;

public class Ticket {

    private String ticketId;
    private String seatNumber;
    private String vehicleId;
    private String userId;
    private String destination;
    private LocalDateTime bookingTimestamp;
    private LocalDateTime travelTime;
    private LocalDateTime expiryTime;
    private TicketStatus status;
    private boolean cancelled;

    // ✅ Default constructor (required by Spring/Jackson)
    public Ticket() {
    }

    // Full constructor
    public Ticket(String ticketId, String seatNumber, String vehicleId, String userId,
                  String destination, LocalDateTime bookingTimestamp, LocalDateTime travelTime,
                  LocalDateTime expiryTime, TicketStatus status, boolean cancelled) {
        this.ticketId = ticketId;
        this.seatNumber = seatNumber;
        this.vehicleId = vehicleId;
        this.userId = userId;
        this.destination = destination;
        this.bookingTimestamp = bookingTimestamp;
        this.travelTime = travelTime;
        this.expiryTime = expiryTime;
        this.status = status;
        this.cancelled = cancelled;
    }

    // Constructor with userId, destination, travelTime, cancelled
    public Ticket(String userId, String destination, LocalDateTime travelTime, boolean cancelled) {
        this.userId = userId;
        this.destination = destination;
        this.travelTime = travelTime;
        this.cancelled = cancelled;
    }

    // Constructor used when booking a new ticket
    public Ticket(String userId, String destination, LocalDateTime travelTime) {
        this.userId = userId;
        this.destination = destination;
        this.travelTime = travelTime;
        this.bookingTimestamp = LocalDateTime.now(); // time of booking
        this.status = TicketStatus.CREATED;
        this.cancelled = false;
        this.expiryTime = travelTime.plusHours(2); // example expiry rule
    }

    // Getters and Setters

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getBookingTimestamp() {
        return bookingTimestamp;
    }

    public void setBookingTimestamp(LocalDateTime bookingTimestamp) {
        this.bookingTimestamp = bookingTimestamp;
    }

    public LocalDateTime getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(LocalDateTime travelTime) {
        this.travelTime = travelTime;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
