package com.carmel.guestjini.service.model.DTO.HelpDesk;

import com.carmel.guestjini.service.model.HelpDesk.TaskForceGroup;

import java.util.Date;

public class TaskForceGroupDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String name;
    private String description;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public TaskForceGroupDTO() {
    }

    public TaskForceGroupDTO(TaskForceGroup taskForceGroup) {
        this.id = taskForceGroup.getId();
        this.clientId = taskForceGroup.getClientId();
        this.orgId = taskForceGroup.getOrgId();
        this.name = taskForceGroup.getName();
        this.description = taskForceGroup.getDescription();
        this.createdBy = taskForceGroup.getCreatedBy();
        this.creationTime = taskForceGroup.getCreationTime();
        this.lastModifiedBy = taskForceGroup.getLastModifiedBy();
        this.lastModifiedTime = taskForceGroup.getLastModifiedTime();
        this.isDeleted = taskForceGroup.getIsDeleted();
        this.deletedBy = taskForceGroup.getDeletedBy();
        this.deletedTime = taskForceGroup.getDeletedTime();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
