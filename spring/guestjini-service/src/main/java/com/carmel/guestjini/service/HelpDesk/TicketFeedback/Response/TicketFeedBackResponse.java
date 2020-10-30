package com.carmel.guestjini.service.HelpDesk.TicketFeedback.Response;

import com.carmel.guestjini.service.HelpDesk.TicketFeedback.DTO.TicketFeedBackDTO;

import java.util.List;

public class TicketFeedBackResponse {
    private TicketFeedBackDTO ticketFeedBack;
    private List<TicketFeedBackDTO> ticketFeedBackList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public TicketFeedBackDTO getTicketFeedBack() {
        return ticketFeedBack;
    }

    public void setTicketFeedBack(TicketFeedBackDTO ticketFeedBack) {
        this.ticketFeedBack = ticketFeedBack;
    }

    public List<TicketFeedBackDTO> getTicketFeedBackList() {
        return ticketFeedBackList;
    }

    public void setTicketFeedBackList(List<TicketFeedBackDTO> ticketFeedBackList) {
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
