package com.carmel.guestjini.helpdesk.model.DTO;

import com.carmel.guestjini.helpdesk.model.TaskTicketCategories;

import java.util.Date;

public class TaskTicketCategoriesDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String parentId;
    private String title;
    private String description;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public TaskTicketCategoriesDTO() {
    }

    public TaskTicketCategoriesDTO(TaskTicketCategories taskTicketCategories) {
        this.id = taskTicketCategories.getId();
        this.clientId = taskTicketCategories.getClientId();
        this.orgId = taskTicketCategories.getOrgId();
        this.parentId = taskTicketCategories.getParentId();
        this.title = taskTicketCategories.getTitle();
        this.description = taskTicketCategories.getDescription();
        this.createdBy = taskTicketCategories.getCreatedBy();
        this.creationTime = taskTicketCategories.getCreationTime();
        this.lastModifiedBy = taskTicketCategories.getLastModifiedBy();
        this.lastModifiedTime = taskTicketCategories.getLastModifiedTime();
        this.isDeleted = taskTicketCategories.getIsDeleted();
        this.deletedBy = taskTicketCategories.getDeletedBy();
        this.deletedTime = taskTicketCategories.getDeletedTime();
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
