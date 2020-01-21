package com.carmel.guestjini.helpdesk.response;

import com.carmel.guestjini.helpdesk.model.DTO.TaskNoteDTO;
import com.carmel.guestjini.helpdesk.model.TaskNote;

import java.util.ArrayList;
import java.util.List;

public class TaskNoteResponse {

    private TaskNoteDTO taskNote;
    private List<TaskNoteDTO> taskNoteList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public TaskNoteDTO getTaskNote() {
        return taskNote;
    }

    public void setTaskNote(TaskNote taskNote) {
        this.taskNote = new TaskNoteDTO(taskNote);
    }

    public List<TaskNoteDTO> getTaskNoteList() {
        return taskNoteList;
    }

    public void setTaskNoteList(List<TaskNote> taskNoteList) {
        this.taskNoteList = new ArrayList<>();
        taskNoteList.forEach(taskNote1 -> {
            this.taskNoteList.add(new TaskNoteDTO(taskNote1));
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
