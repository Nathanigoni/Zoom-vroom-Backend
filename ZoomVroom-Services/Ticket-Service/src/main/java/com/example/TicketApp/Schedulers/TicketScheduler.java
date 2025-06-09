package com.example.TicketApp.Schedulers;

import com.example.TicketApp.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TicketScheduler {

    @Autowired
    private TicketService ticketService;

    @Scheduled(fixedRate = 60000) // Every 60 seconds
    public void checkAndExpireTickets() {
        ticketService.expireOldTickets();
    }
}
