package com.example.VehiclesAndSeatManagement.Controller;

import com.example.VehiclesAndSeatManagement.Model.SeatBookingRequest;
import com.example.VehiclesAndSeatManagement.Model.Vehicle;
import com.example.VehiclesAndSeatManagement.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // Endpoint to book a seat
    @PostMapping("/book-seat")
    public ResponseEntity<String> bookSeat(@RequestBody SeatBookingRequest request) {
        vehicleService.bookSeat(request.getVehicleId(), request.getSeatNumber());
        return ResponseEntity.ok("Seat booked successfully.");
    }

    // Endpoint to get available seats
    @GetMapping("/{vehicleId}/available-seats")
    public ResponseEntity<Integer> getAvailableSeats(@PathVariable String vehicleId) {
        int availableSeats = vehicleService.getAvailableSeats(vehicleId);
        return ResponseEntity.ok(availableSeats);
    }

    // Endpoint to get all vehicles
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    // Endpoint to get a specific vehicle by ID
    @GetMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable String vehicleId) {
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        return ResponseEntity.ok(vehicle);
    }
    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        Vehicle savedVehicle = vehicleService.saveVehicle(vehicle);
        return ResponseEntity.ok(savedVehicle);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleBookingException(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // 400 Bad Request
    }

//    @GetMapping("/vehicles")
//    public List<Vehicle> getVehiclesByType(@RequestParam String type) {
//        return vehicleService.getVehiclesByType(type);
//    }

}
