package com.example.Ticket.Service;

import java.time.LocalDateTime;

public class TicketRequest {
    private LocalDateTime travelTime;
    private String location;


    public LocalDateTime getTravelTime() { return travelTime; }
    public void setTravelTime(LocalDateTime travelTime) { this.travelTime = travelTime; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
