package com.example.Ticket.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "tickets")
public class Ticket {
    @Id
    private String ticketId;
    private String seatNumber;
    private String userId;
    private String location;
    private LocalDateTime bookingTimestamp;
        private LocalDateTime travelTime;
    private LocalDateTime expiryTime;
    private TicketStatus status;
    private LocalDateTime savedTimestamp;
    private double price;


    public Ticket(String seatNumber, String location, LocalDateTime travelTime) {
        this.ticketId = UUID.randomUUID().toString();
        this.seatNumber = seatNumber;
        this.location = location;
        this.travelTime = travelTime;
        this.bookingTimestamp = LocalDateTime.now();
        this.expiryTime = travelTime.plusMinutes(720);
        this.status = TicketStatus.CREATED;
        this.userId = null;
    }

    public String getTicketId() { return ticketId; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalDateTime getBookingTimestamp() { return bookingTimestamp; }
    public void setBookingTimestamp(LocalDateTime bookingTimestamp) {
        this.bookingTimestamp = bookingTimestamp;
    }

    public LocalDateTime getTravelTime() { return travelTime; }
    public void setTravelTime(LocalDateTime travelTime) { this.travelTime = travelTime; }

    public LocalDateTime getExpiryTime() { return expiryTime; }
    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    public TicketStatus getStatus() { return status; }
    public void setStatus(TicketStatus status) { this.status = status; }

    public LocalDateTime getSavedTimestamp() { return savedTimestamp; }
    public void setSavedTimestamp(LocalDateTime savedTimestamp) {
        this.savedTimestamp = savedTimestamp;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", userId='" + userId + '\'' +
                ", location='" + location + '\'' +
                ", bookingTimestamp=" + bookingTimestamp +
                ", travelTime=" + travelTime +
                ", expiryTime=" + expiryTime +
                ", status=" + status +
                ", savedTimestamp=" + savedTimestamp +
                '}';
    }
}
