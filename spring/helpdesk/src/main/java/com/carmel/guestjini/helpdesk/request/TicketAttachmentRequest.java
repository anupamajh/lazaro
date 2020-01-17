package com.carmel.guestjini.helpdesk.request;

import org.springframework.web.multipart.MultipartFile;

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
