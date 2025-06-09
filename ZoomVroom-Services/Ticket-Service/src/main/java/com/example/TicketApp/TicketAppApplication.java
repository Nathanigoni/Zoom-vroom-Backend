package com.example.TicketApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableMongoRepositories
public class TicketAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketAppApplication.class, args);
	}

}
