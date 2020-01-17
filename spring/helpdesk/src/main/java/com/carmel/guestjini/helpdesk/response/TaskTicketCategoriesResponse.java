package com.carmel.guestjini.helpdesk.response;

import com.carmel.guestjini.helpdesk.model.DTO.TaskTicketCategoriesDTO;
import com.carmel.guestjini.helpdesk.model.TaskTicketCategories;

import java.util.ArrayList;
import java.util.List;

public class TaskTicketCategoriesResponse {
    private TaskTicketCategoriesDTO taskTicketCategories;
    private List<TaskTicketCategoriesDTO> taskTicketCategoriesList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public TaskTicketCategoriesDTO getTaskTicketCategories() {
        return taskTicketCategories;
    }

    public void setTaskTicketCategories(TaskTicketCategories taskTicketCategories) {
        this.taskTicketCategories = new TaskTicketCategoriesDTO(taskTicketCategories);
    }

    public List<TaskTicketCategoriesDTO> getTaskTicketCategoriesList() {
        return taskTicketCategoriesList;
    }

    public void setTaskTicketCategoriesList(List<TaskTicketCategories> taskTicketCategoriesList) {
        this.taskTicketCategoriesList = new ArrayList<>();
        taskTicketCategoriesList.forEach(taskTicketCategories1 -> {
            this.taskTicketCategoriesList.add(new TaskTicketCategoriesDTO(taskTicketCategories1));
        });
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
