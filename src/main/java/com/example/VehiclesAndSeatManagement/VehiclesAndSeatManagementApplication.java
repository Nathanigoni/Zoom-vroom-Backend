package com.example.VehiclesAndSeatManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication

public class VehiclesAndSeatManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehiclesAndSeatManagementApplication.class, args);
	}

}
