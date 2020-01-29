package com.carmel.common.dbservice.model.DTO;

import com.carmel.common.dbservice.model.InterestCategory;
import com.carmel.common.dbservice.model.UserInterests;

import java.util.Date;

public class UserInterestsDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String userId;
    private String interestId;
    private int isInterested;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;
    private String interestCategoryName;
    private String interestCategoryId;
    private String interestName;
    private InterestCategory interestCategory;


    public UserInterestsDTO() {
    }

    public UserInterestsDTO(UserInterests userInterests) {
        this.id = userInterests.getId();
        this.clientId = userInterests.getClientId();
        this.orgId = userInterests.getOrgId();
        this.userId = userInterests.getUserId();
        this.interestId = userInterests.getInterestId();
        this.isInterested = userInterests.getIsInterested();
        this.createdBy = userInterests.getCreatedBy();
        this.creationTime = userInterests.getCreationTime();
        this.lastModifiedBy = userInterests.getLastModifiedBy();
        this.lastModifiedTime = userInterests.getLastModifiedTime();
        this.isDeleted = userInterests.getIsDeleted();
        this.deletedBy = userInterests.getDeletedBy();
        this.deletedTime = userInterests.getDeletedTime();
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

    public String getInterestId() {
        return interestId;
    }

    public void setInterestId(String interestId) {
        this.interestId = interestId;
    }

    public int getIsInterested() {
        return isInterested;
    }

    public void setIsInterested(int isInterested) {
        this.isInterested = isInterested;
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

    public String getInterestCategoryName() {
        return interestCategoryName;
    }

    public void setInterestCategoryName(String interestCategoryName) {
        this.interestCategoryName = interestCategoryName;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

    public String getInterestCategoryId() {
        return interestCategoryId;
    }

    public void setInterestCategoryId(String interestCategoryId) {
        this.interestCategoryId = interestCategoryId;
    }

    public InterestCategory getInterestCategory() {
        return interestCategory;
    }

    public void setInterestCategory(InterestCategory interestCategory) {
        this.interestCategory = interestCategory;
    }
}
