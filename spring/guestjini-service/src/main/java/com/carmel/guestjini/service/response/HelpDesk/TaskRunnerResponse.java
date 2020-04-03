package com.carmel.guestjini.service.response.HelpDesk;



import com.carmel.guestjini.service.model.DTO.HelpDesk.TaskRunnerDTO;
import com.carmel.guestjini.service.model.HelpDesk.TaskRunner;

import java.util.ArrayList;
import java.util.List;

public class TaskRunnerResponse {
    private TaskRunnerDTO taskRunner;
    private List<TaskRunnerDTO> taskRunnerList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public TaskRunnerDTO getTaskRunner() {
        return taskRunner;
    }

    public void setTaskRunner(TaskRunner taskRunner) {
        this.taskRunner = new TaskRunnerDTO(taskRunner);
    }

    public List<TaskRunnerDTO> getTaskRunnerList() {
        return taskRunnerList;
    }

    public void setTaskRunnerList(List<TaskRunner> taskRunnerList) {
        this.taskRunnerList = new ArrayList<>();
        taskRunnerList.forEach(taskRunner1 -> {
            this.taskRunnerList.add(new TaskRunnerDTO(taskRunner1));
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
