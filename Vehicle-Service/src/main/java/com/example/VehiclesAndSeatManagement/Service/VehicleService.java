package com.example.VehiclesAndSeatManagement.Service;

import com.example.VehiclesAndSeatManagement.Model.Vehicle;
import com.example.VehiclesAndSeatManagement.Repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;


    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }


    // Booking a seat
    public void bookSeat(String vehicleId, int seatNumber) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        // Book the seat and update the vehicle
        vehicle.bookSeat(seatNumber);

        // Save the updated vehicle back to MongoDB
        vehicleRepository.save(vehicle);
    }

    // Get available seats
    public int getAvailableSeats(String vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        return vehicle.getAvailableSeats();
    }

    // Get all vehicles
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Get vehicle by ID
    public Vehicle getVehicleById(String id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    // Calculate travel duration (optional)
    public long getTravelDuration(String vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        return Duration.between(vehicle.getDepartureTime(), vehicle.getArrivalTime()).toMinutes();

    }
//    public List<Vehicle> getVehiclesByType(String vehicleType) {
//        return vehicleRepository.findByVehicleType(vehicleType);
//    }

}
