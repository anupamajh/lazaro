package com.carmel.guestjini.service.response.HelpDesk;

import com.carmel.guestjini.service.model.DTO.HelpDesk.TaskForceDTO;

import java.util.List;

public class TaskForceResponse {
    private TaskForceDTO taskForce;
    private List<TaskForceDTO> taskForceList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public TaskForceDTO getTaskForce() {
        return taskForce;
    }

    public void setTaskForce(TaskForceDTO taskForce) {
        this.taskForce = taskForce;
    }

    public List<TaskForceDTO> getTaskForceList() {
        return taskForceList;
    }

    public void setTaskForceList(List<TaskForceDTO> taskForceList) {
        this.taskForceList = taskForceList;
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
