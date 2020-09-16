package com.carmel.common.dbservice.Base.Group.DTO;

import com.carmel.common.dbservice.Base.Group.Model.Group;

import java.util.Date;

public class GroupDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String interestId;
    private String interestCategoryId;
    private String interestCategoryName;
    private String groupOwnerId;
    private String name;
    private String description;
    private int groupType;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;
    private Date subscribedDate;
    private int isSubscribed;
    private int isInvited;
    private int isMatchingInterest;
    private int cardType;


    public GroupDTO() {
    }


    public GroupDTO(Group group) {
        this.id = group.getId();
        this.clientId = group.getClientId();
        this.orgId = group.getOrgId();
        this.interestId = group.getInterestId();
        this.interestCategoryId = group.getInterestCategoryId();
        this.groupOwnerId = group.getGroupOwnerId();
        this.name = group.getName();
        this.description = group.getDescription();
        this.groupType = group.getGroupType();
        this.createdBy = group.getCreatedBy();
        this.lastModifiedBy = group.getLastModifiedBy();
        this.lastModifiedTime = group.getLastModifiedTime();
        this.isDeleted = group.getIsDeleted();
        this.deletedBy = group.getDeletedBy();
        this.deletedTime = group.getDeletedTime();
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

    public String getInterestId() {
        return interestId;
    }

    public void setInterestId(String interestId) {
        this.interestId = interestId;
    }

    public String getInterestCategoryId() {
        return interestCategoryId;
    }

    public void setInterestCategoryId(String interestCategoryId) {
        this.interestCategoryId = interestCategoryId;
    }

    public String getInterestCategoryName() {
        return interestCategoryName;
    }

    public void setInterestCategoryName(String interestCategoryName) {
        this.interestCategoryName = interestCategoryName;
    }

    public String getGroupOwnerId() {
        return groupOwnerId;
    }

    public void setGroupOwnerId(String groupOwnerId) {
        this.groupOwnerId = groupOwnerId;
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

    public int getGroupType() {
        return groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType = groupType;
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

    public int getIsSubscribed() {
        return isSubscribed;
    }

    public void setIsSubscribed(int isSubscribed) {
        this.isSubscribed = isSubscribed;
    }

    public int getIsInvited() {
        return isInvited;
    }

    public void setIsInvited(int isInvited) {
        this.isInvited = isInvited;
    }

    public int getIsMatchingInterest() {
        return isMatchingInterest;
    }

    public void setIsMatchingInterest(int isMatchingInterest) {
        this.isMatchingInterest = isMatchingInterest;
    }

    public Date getSubscribedDate() {
        return subscribedDate;
    }

    public void setSubscribedDate(Date subscribedDate) {
        this.subscribedDate = subscribedDate;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }
}
