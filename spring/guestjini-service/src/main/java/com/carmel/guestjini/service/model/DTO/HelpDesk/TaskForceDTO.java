package com.carmel.guestjini.service.model.DTO.HelpDesk;

import com.carmel.guestjini.service.model.HelpDesk.TaskForce;

import java.util.Date;

public class TaskForceDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String userId;
    private String phone;
    private String groupId;
    private int isGroupAdmin;
    private int isSuperAdmin;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public TaskForceDTO() {
    }

    public TaskForceDTO(TaskForce taskForce) {
        this.id = taskForce.getId();
        this.clientId = taskForce.getClientId();
        this.orgId = taskForce.getOrgId();
        this.userId = taskForce.getUserId();
        this.phone = taskForce.getPhone();
        this.groupId = taskForce.getGroupId();
        this.isGroupAdmin = taskForce.getIsGroupAdmin();
        this.isSuperAdmin = taskForce.getIsSuperAdmin();
        this.createdBy = taskForce.getCreatedBy();
        this.creationTime = taskForce.getCreationTime();
        this.lastModifiedBy = taskForce.getLastModifiedBy();
        this.lastModifiedTime = taskForce.getLastModifiedTime();
        this.isDeleted = taskForce.getIsDeleted();
        this.deletedBy = taskForce.getDeletedBy();
        this.deletedTime = taskForce.getDeletedTime();
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getIsGroupAdmin() {
        return isGroupAdmin;
    }

    public void setIsGroupAdmin(int isGroupAdmin) {
        this.isGroupAdmin = isGroupAdmin;
    }

    public int getIsSuperAdmin() {
        return isSuperAdmin;
    }

    public void setIsSuperAdmin(int isSuperAdmin) {
        this.isSuperAdmin = isSuperAdmin;
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
