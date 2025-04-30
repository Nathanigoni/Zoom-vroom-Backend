package com.example.VehiclesAndSeatManagement.Repository;

import com.example.VehiclesAndSeatManagement.Model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;


public interface VehicleRepository extends MongoRepository<Vehicle, String> {
//    List<Vehicle> findByVehicleType(String vehicleType);




}
