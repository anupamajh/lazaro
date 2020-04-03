package com.carmel.guestjini.service.request.HelpDesk;

public class TicketAttachmentRequest {
    private String ticketId;
    private String fileName;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
