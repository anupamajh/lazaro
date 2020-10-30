package com.carmel.guestjini.service.response.HelpDesk;



import com.carmel.guestjini.service.model.DTO.HelpDesk.TaskAttachmentDTO;
import com.carmel.guestjini.service.model.DTO.HelpDesk.TaskTicketCategoriesDTO;
import com.carmel.guestjini.service.model.DTO.HelpDesk.TaskTicketDTO;
import com.carmel.guestjini.service.model.HelpDesk.TaskAttachment;
import com.carmel.guestjini.service.model.HelpDesk.TaskTicket;
import com.carmel.guestjini.service.model.HelpDesk.TaskTicketCategories;

import java.util.ArrayList;
import java.util.List;

public class TaskTicketResponse {
    private TaskTicketDTO taskTicket;
    private List<TaskTicketDTO> taskTicketList;
    private List<TaskAttachmentDTO> taskAttachments;
    private List<TaskTicketCategoriesDTO> taskTicketCategories;
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

    public List<TaskAttachmentDTO> getTaskAttachments() {
        return taskAttachments;
    }

    public void setTaskAttachments(List<TaskAttachment> taskAttachments) {
        this.taskAttachments = new ArrayList<>();
        taskAttachments.forEach(taskAttachment -> {
            this.taskAttachments.add(new TaskAttachmentDTO(taskAttachment));
        });
    }

    public List<TaskTicketCategoriesDTO> getTaskTicketCategories() {
        return taskTicketCategories;
    }

    public void setTaskTicketCategories(List<TaskTicketCategories> taskTicketCategories) {
        this.taskTicketCategories = new ArrayList<>();
        taskTicketCategories.forEach(taskTicketCategories1 -> {
            this.taskTicketCategories.add(new TaskTicketCategoriesDTO(taskTicketCategories1));
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
