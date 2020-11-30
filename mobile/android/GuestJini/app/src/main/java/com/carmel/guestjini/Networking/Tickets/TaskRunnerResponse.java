package com.carmel.guestjini.Networking.Tickets;

import java.util.List;

public class TaskRunnerResponse {
    private TaskRunner taskRunner;
    private List<TaskRunner> taskRunnerList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public TaskRunner getTaskRunner() {
        return taskRunner;
    }

    public void setTaskRunner(TaskRunner taskRunner) {
        this.taskRunner = taskRunner;
    }

    public List<TaskRunner> getTaskRunnerList() {
        return taskRunnerList;
    }

    public void setTaskRunnerList(List<TaskRunner> taskRunnerList) {
        this.taskRunnerList = taskRunnerList;
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
