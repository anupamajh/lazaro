package com.carmel.common.dbservice.model.DTO;

import com.carmel.common.dbservice.model.InterestCategory;

import java.util.Date;

public class InterestCategoryDTO {
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

    public InterestCategoryDTO() {
    }

    public InterestCategoryDTO(InterestCategory interestCategory) {
        this.id = interestCategory.getId();
        this.clientId = interestCategory.getClientId();
        this.orgId = interestCategory.getOrgId();
        this.name = interestCategory.getName();
        this.description = interestCategory.getDescription();
        this.createdBy = interestCategory.getCreatedBy();
        this.creationTime = interestCategory.getCreationTime();
        this.lastModifiedBy = interestCategory.getLastModifiedBy();
        this.lastModifiedTime = interestCategory.getLastModifiedTime();
        this.isDeleted = interestCategory.getIsDeleted();
        this.deletedBy = interestCategory.getDeletedBy();
        this.deletedTime = interestCategory.getDeletedTime();

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
