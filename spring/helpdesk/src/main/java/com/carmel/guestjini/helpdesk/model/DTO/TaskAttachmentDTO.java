package com.carmel.guestjini.helpdesk.model.DTO;

import com.carmel.guestjini.helpdesk.model.TaskAttachment;

import java.util.Date;

public class TaskAttachmentDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String ticketId;
    private String type;
    private String docTitle;
    private String path;
    private String name;
    private int error;
    private long size;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public TaskAttachmentDTO() {
    }

    public TaskAttachmentDTO(TaskAttachment taskAttachment) {
        this.id = taskAttachment.getId();
        this.clientId = taskAttachment.getClientId();
        this.orgId = taskAttachment.getOrgId();
        this.ticketId = taskAttachment.getTicketId();
        this.type = taskAttachment.getType();
        this.docTitle = taskAttachment.getDocTitle();
        this.path = taskAttachment.getPath();
        this.name = taskAttachment.getName();
        this.error = taskAttachment.getError();
        this.size = taskAttachment.getSize();
        this.createdBy = taskAttachment.getCreatedBy();
        this.creationTime = taskAttachment.getCreationTime();
        this.lastModifiedBy = taskAttachment.getLastModifiedBy();
        this.lastModifiedTime = taskAttachment.getLastModifiedTime();
        this.isDeleted = taskAttachment.getIsDeleted();
        this.deletedBy = taskAttachment.getDeletedBy();
        this.deletedTime = taskAttachment.getDeletedTime();
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

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
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
