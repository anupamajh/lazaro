package com.carmel.guestjini.Networking.Tickets;

import com.carmel.guestjini.Networking.Users.User;

import java.util.Date;
import java.util.List;

public class TaskAssigneeResponse {
    private TaskAssignee taskAssignee;
    private List<TaskAssignee> taskAssigneeList;
    private String ticketId;
    private String taskForceGroupId;
    private String userId;
    private TaskForceGroup taskForceGroup;
    private User userDTO;
    private User createdBy;
    private User modifiedBy;
    private Date creationTime;
    private Date lastModifiedTime;
    private int isDeleted;
    private Date deletedTime;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public TaskAssignee getTaskAssignee() {
        return taskAssignee;
    }

    public void setTaskAssignee(TaskAssignee taskAssignee) {
        this.taskAssignee = taskAssignee;
    }

    public List<TaskAssignee> getTaskAssigneeList() {
        return taskAssigneeList;
    }

    public void setTaskAssigneeList(List<TaskAssignee> taskAssigneeList) {
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

    public User getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(User userDTO) {
        this.userDTO = userDTO;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
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
