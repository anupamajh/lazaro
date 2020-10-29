package com.carmel.guestjini.Networking.Tickets;

import java.util.List;

public class TicketCategoryResponse {
    private TicketCategory taskTicketCategories;
    private List<TicketCategory> taskTicketCategoriesList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public TicketCategory getTaskTicketCategories() {
        return taskTicketCategories;
    }

    public void setTaskTicketCategories(TicketCategory taskTicketCategories) {
        this.taskTicketCategories = taskTicketCategories;
    }

    public List<TicketCategory> getTaskTicketCategoriesList() {
        return taskTicketCategoriesList;
    }

    public void setTaskTicketCategoriesList(List<TicketCategory> taskTicketCategoriesList) {
        this.taskTicketCategoriesList = taskTicketCategoriesList;
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