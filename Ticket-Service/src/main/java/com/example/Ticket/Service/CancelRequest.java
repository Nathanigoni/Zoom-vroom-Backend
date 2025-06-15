package com.example.Ticket.Service;

public class CancelRequest {
    private String ticketId;
    private String userId;

    public String getTicketId() { return ticketId; }
    public void setTicketId(String ticketId) { this.ticketId = ticketId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
}
