package com.carmel.guestjini.service.response.HelpDesk;



import com.carmel.guestjini.service.model.DTO.HelpDesk.TaskAttachmentDTO;
import com.carmel.guestjini.service.model.HelpDesk.TaskAttachment;

import java.util.ArrayList;
import java.util.List;

public class TaskAttachmentResponse {
    private TaskAttachmentDTO taskAttachment;
    private List<TaskAttachmentDTO> taskAttachmentList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public TaskAttachmentDTO getTaskAttachment() {
        return taskAttachment;
    }

    public void setTaskAttachment(TaskAttachment taskAttachment) {
        this.taskAttachment = new TaskAttachmentDTO(taskAttachment);
    }

    public List<TaskAttachmentDTO> getTaskAttachmentList() {
        return taskAttachmentList;
    }

    public void setTaskAttachmentList(List<TaskAttachment> taskAttachmentList) {
        this.taskAttachmentList = new ArrayList<>();
        taskAttachmentList.forEach(taskAttachment1 -> {
            this.taskAttachmentList.add(new TaskAttachmentDTO(taskAttachment1));
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
