package com.example.TicketApp.dto;

import java.time.LocalDateTime;

public class CreateTicketDto {
    private String userId;
    private String destination;
    private LocalDateTime travelTime;
    private String seatNumber;

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

    public LocalDateTime getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(LocalDateTime travelTime) {
        this.travelTime = travelTime;
    }



    public String getSeatNumber() {
        return seatNumber;
    }

}