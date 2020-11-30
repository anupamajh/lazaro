package com.carmel.guestjini.Networking.Tickets;

import java.util.List;

public class TaskForceGroupResponse {
    private TaskForceGroup taskForceGroup;
    private List<TaskForceGroup> taskForceGroupList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public TaskForceGroup getTaskForceGroup() {
        return taskForceGroup;
    }

    public void setTaskForceGroup(TaskForceGroup taskForceGroup) {
        this.taskForceGroup = taskForceGroup;
    }

    public List<TaskForceGroup> getTaskForceGroupList() {
        return taskForceGroupList;
    }

    public void setTaskForceGroupList(List<TaskForceGroup> taskForceGroupList) {
        this.taskForceGroupList = taskForceGroupList;
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
