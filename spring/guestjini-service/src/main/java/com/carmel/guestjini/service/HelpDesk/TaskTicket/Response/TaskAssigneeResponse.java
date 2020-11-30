package com.carmel.guestjini.service.HelpDesk.TaskTicket.Response;

import com.carmel.guestjini.service.HelpDesk.TaskTicket.DTO.TaskAssigneeDTO;
import com.carmel.guestjini.service.HelpDesk.TicketFeedback.DTO.TicketFeedBackDTO;
import com.carmel.guestjini.service.model.DTO.Common.UserDTO;
import com.carmel.guestjini.service.model.HelpDesk.TaskForceGroup;

import java.util.Date;
import java.util.List;

public class TaskAssigneeResponse {
    private TaskAssigneeDTO taskAssignee;
    private List<TaskAssigneeDTO> taskAssigneeList;
    private String ticketId;
    private String taskForceGroupId;
    private String userId;
    private TaskForceGroup taskForceGroup;
    private UserDTO userDTO;
    private UserDTO createdBy;
    private UserDTO modifiedBy;
    private Date creationTime;
    private Date lastModifiedTime;
    private int isDeleted;
    private Date deletedTime;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public TaskAssigneeDTO getTaskAssignee() {
        return taskAssignee;
    }

    public void setTaskAssignee(TaskAssigneeDTO taskAssignee) {
        this.taskAssignee = taskAssignee;
    }

    public List<TaskAssigneeDTO> getTaskAssigneeList() {
        return taskAssigneeList;
    }

    public void setTaskAssigneeList(List<TaskAssigneeDTO> taskAssigneeList) {
        this.taskAssigneeList = taskAssigneeList;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTaskForceGroupId() {
        return taskForceGroupId;
    }

    public void setTaskForceGroupId(String taskForceGroupId) {
        this.taskForceGroupId = taskForceGroupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public TaskForceGroup getTaskForceGroup() {
        return taskForceGroup;
    }

    public void setTaskForceGroup(TaskForceGroup taskForceGroup) {
        this.taskForceGroup = taskForceGroup;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public UserDTO getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserDTO createdBy) {
        this.createdBy = createdBy;
    }

    public UserDTO getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(UserDTO modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(Date deletedTime) {
        this.deletedTime = deletedTime;
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
