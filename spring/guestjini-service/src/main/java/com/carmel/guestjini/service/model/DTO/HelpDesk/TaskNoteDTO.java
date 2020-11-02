package com.carmel.guestjini.service.model.DTO.HelpDesk;



import com.carmel.guestjini.service.model.HelpDesk.TaskNote;

import java.util.Date;

public class TaskNoteDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String userId;
    private String ticketId;
    private String notes;
    private int isRead;
    private int isMine;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private String userName;


    public TaskNoteDTO() {
    }


    public TaskNoteDTO(TaskNote taskNote) {
        this.id = taskNote.getId();
        this.clientId = taskNote.getClientId();
        this.orgId = taskNote.getOrgId();
        this.userId = taskNote.getUserId();
        this.ticketId = taskNote.getTicketId();
        this.notes = taskNote.getNotes();
        this.isRead = taskNote.getIsRead();
        this.createdBy = taskNote.getCreatedBy();
        this.creationTime = taskNote.getCreationTime();
        this.lastModifiedBy = taskNote.getLastModifiedBy();
        this.lastModifiedTime = taskNote.getLastModifiedTime();
        this.isDeleted = taskNote.getIsDeleted();
        this.deletedBy = taskNote.getDeletedBy();
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getIsMine() {
        return isMine;
    }

    public void setIsMine(int isMine) {
        this.isMine = isMine;
    }
}
