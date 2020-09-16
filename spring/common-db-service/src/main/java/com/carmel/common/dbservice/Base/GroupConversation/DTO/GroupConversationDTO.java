package com.carmel.common.dbservice.Base.GroupConversation.DTO;

import com.carmel.common.dbservice.Base.GroupConversation.Model.GroupConversation;

import java.util.Date;

public class GroupConversationDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String groupId;
    private String userId;
    private String displayName;
    private String message;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;
    private int isItMe;


    public GroupConversationDTO() {
    }

    public GroupConversationDTO(GroupConversation groupConversation) {
        this.id = groupConversation.getId();
        this.clientId = groupConversation.getClientId();
        this.orgId = groupConversation.getOrgId();
        this.groupId = groupConversation.getGroupId();
        this.userId = groupConversation.getUserId();
        this.displayName = groupConversation.getDisplayName();
        this.message = groupConversation.getMessage();
        this.createdBy = groupConversation.getCreatedBy();
        this.creationTime = groupConversation.getCreationTime();
        this.lastModifiedBy = groupConversation.getLastModifiedBy();
        this.lastModifiedTime = groupConversation.getLastModifiedTime();
        this.isDeleted = groupConversation.getIsDeleted();
        this.deletedBy = groupConversation.getDeletedBy();
        this.deletedTime = groupConversation.getDeletedTime();
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public int getIsItMe() {
        return isItMe;
    }

    public void setIsItMe(int isItMe) {
        this.isItMe = isItMe;
    }
}
