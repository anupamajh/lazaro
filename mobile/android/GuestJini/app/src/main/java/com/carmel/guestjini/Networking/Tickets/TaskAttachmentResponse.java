package com.carmel.guestjini.Networking.Tickets;

import java.util.List;

public class TaskAttachmentResponse {
    private TaskAttachment taskAttachment;
    private List<TaskAttachment> taskAttachmentList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public TaskAttachment getTaskAttachment() {
        return taskAttachment;
    }

    public void setTaskAttachment(TaskAttachment taskAttachment) {
        this.taskAttachment = taskAttachment;
    }

    public List<TaskAttachment> getTaskAttachmentList() {
        return taskAttachmentList;
    }

    public void setTaskAttachmentList(List<TaskAttachment> taskAttachmentList) {
        this.taskAttachmentList = taskAttachmentList;
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
