package com.carmel.guestjini.Models.Ticket;

import java.util.List;

public class TicketResponse {
    private Ticket taskTicket;
    private List<Ticket> taskTicketList;
    private int totalPages;
    private int totalRecords;
    private int currentRecords;
    private boolean success;
    private String error;

    public Ticket getTaskTicket() {
        return taskTicket;
    }

    public void setTaskTicket(Ticket taskTicket) {
        this.taskTicket = taskTicket;
    }

    public List<Ticket> getTaskTicketList() {
        return taskTicketList;
    }

    public void setTaskTicketList(List<Ticket> taskTicketList) {
        this.taskTicketList = taskTicketList;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getCurrentRecords() {
        return currentRecords;
    }

    public void setCurrentRecords(int currentRecords) {
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
