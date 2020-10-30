package com.carmel.guestjini.Networking.Tickets;

import java.util.List;

public class TicketFeedBackResponse {
    private TicketFeedBack ticketFeedBack;
    private List<TicketFeedBack> ticketFeedBackList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public TicketFeedBack getTicketFeedBack() {
        return ticketFeedBack;
    }

    public void setTicketFeedBack(TicketFeedBack ticketFeedBack) {
        this.ticketFeedBack = ticketFeedBack;
    }

    public List<TicketFeedBack> getTicketFeedBackList() {
        return ticketFeedBackList;
    }

    public void setTicketFeedBackList(List<TicketFeedBack> ticketFeedBackList) {
        this.ticketFeedBackList = ticketFeedBackList;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public long getCurrentRecords() {
        return currentRecords;
    }

    public void setCurrentRecords(long currentRecords) {
        this.currentRecords = currentRecords;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
