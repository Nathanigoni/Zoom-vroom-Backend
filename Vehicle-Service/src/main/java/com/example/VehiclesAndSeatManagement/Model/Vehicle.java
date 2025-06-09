package com.example.VehiclesAndSeatManagement.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "vehicles")

public class Vehicle {

    @Id
    private String vehicleId;
    private String vehicleName;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int maxSeats;
    private List<Integer> bookedSeats; // List of booked seat numbers

    // Getters and setters
    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
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

    public int getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    public List<Integer> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<Integer> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    // Method to calculate available seats
    public int getAvailableSeats() {
        return maxSeats - (bookedSeats != null ? bookedSeats.size() : 0);
    }
    public void bookSeat(int seatNumber) {
        if (bookedSeats.contains(seatNumber)) {
            throw new RuntimeException("Seat already booked.");
        }

        if (seatNumber < 1 || seatNumber > maxSeats) {
            throw new RuntimeException("Invalid seat number.");
        }

        bookedSeats.add(seatNumber);

        maxSeats = maxSeats - 1; // Decrease maxSeats by 1
    }
    private String vehicleType;

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

}
