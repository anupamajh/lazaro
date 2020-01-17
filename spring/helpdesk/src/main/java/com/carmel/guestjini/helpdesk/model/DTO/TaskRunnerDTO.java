package com.carmel.guestjini.helpdesk.model.DTO;

import com.carmel.guestjini.helpdesk.model.TaskRunner;

import java.util.Date;

public class TaskRunnerDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String userId;
    private String ticketId;
    /**
     * 1-Completed2-Work in progress3-Not Started
     */
    private int taskStatus;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public TaskRunnerDTO() {
    }

    public TaskRunnerDTO(TaskRunner taskRunner) {
        this.id = taskRunner.getId();
        this.clientId = taskRunner.getClientId();
        this.orgId = taskRunner.getOrgId();
        this.userId = taskRunner.getUserId();
        this.ticketId = taskRunner.getTicketId();
        this.taskStatus = taskRunner.getTaskStatus();
        this.createdBy = taskRunner.getCreatedBy();
        this.creationTime = taskRunner.getCreationTime();
        this.lastModifiedBy = taskRunner.getLastModifiedBy();
        this.lastModifiedTime = taskRunner.getLastModifiedTime();
        this.isDeleted = taskRunner.getIsDeleted();
        this.deletedBy = taskRunner.getDeletedBy();
        this.deletedTime = taskRunner.getDeletedTime();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
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

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(Date deletedTime) {
        this.deletedTime = deletedTime;
    }
}
