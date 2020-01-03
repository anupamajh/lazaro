package com.carmel.guestjini.helpdesk.response;

import com.carmel.guestjini.helpdesk.model.DTO.TaskTicketDTO;
import com.carmel.guestjini.helpdesk.model.TaskTicket;

import java.util.ArrayList;
import java.util.List;

public class TaskTicketResponse {
    private TaskTicketDTO taskTicket;
    private List<TaskTicketDTO> taskTicketList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public TaskTicketDTO getTaskTicket() {
        return taskTicket;
    }

    public void setTaskTicket(TaskTicket taskTicket) {
        this.taskTicket = new TaskTicketDTO(taskTicket);
    }

    public List<TaskTicketDTO> getTaskTicketList() {
        return taskTicketList;
    }

    public void setTaskTicketList(List<TaskTicket> taskTicketList) {
        this.taskTicketList = new ArrayList<>();
        taskTicketList.forEach(taskTicket -> {
            this.taskTicketList.add(new TaskTicketDTO(taskTicket));
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
