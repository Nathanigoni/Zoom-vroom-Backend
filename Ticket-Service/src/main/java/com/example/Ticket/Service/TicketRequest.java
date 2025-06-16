package com.example.Ticket.Service;

import java.time.LocalDateTime;

public class TicketRequest {
    private String seatNumber;
    private String vehicleId;
    private String userId;
    private LocalDateTime travelTime;
    private String location;
    private double price;


    // Getters and Setters
    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public LocalDateTime getTravelTime() { return travelTime; }
    public void setTravelTime(LocalDateTime travelTime) { this.travelTime = travelTime; }

    public String getLocation() {return location;}
    public void setLocation(String location) {this.location = location;}

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
