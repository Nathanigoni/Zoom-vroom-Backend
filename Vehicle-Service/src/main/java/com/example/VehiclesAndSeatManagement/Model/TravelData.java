package com.example.VehiclesAndSeatManagement.Model;


import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;


public class TravelData {
    @Id
    private String vehicleId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private long travelDuration;
    private String ticketId;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public long getTravelDuration() {
        return travelDuration;
    }

    public void setTravelDuration(long travelDuration) {
        this.travelDuration = travelDuration;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}

