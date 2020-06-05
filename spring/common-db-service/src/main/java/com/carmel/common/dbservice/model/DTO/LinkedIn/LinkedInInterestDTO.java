package com.carmel.common.dbservice.model.DTO.LinkedIn;

import com.carmel.common.dbservice.model.LinkedIn.LinkedInInterest;

import java.util.Date;

public class LinkedInInterestDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String linkedInId;
    private String interest;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public LinkedInInterestDTO() {
    }

    public LinkedInInterestDTO(LinkedInInterest linkedInInterest) {
        this.id = linkedInInterest.getId();
        this.clientId = linkedInInterest.getClientId();
        this.orgId = linkedInInterest.getOrgId();
        this.linkedInId = linkedInInterest.getLinkedInId();
        this.interest = linkedInInterest.getInterest();
        this.createdBy = linkedInInterest.getCreatedBy();
        this.creationTime = linkedInInterest.getCreationTime();
        this.lastModifiedBy = linkedInInterest.getLastModifiedBy();
        this.lastModifiedTime = linkedInInterest.getLastModifiedTime();
        this.isDeleted = linkedInInterest.getIsDeleted();
        this.deletedBy = linkedInInterest.getDeletedBy();
        this.deletedTime = linkedInInterest.getDeletedTime();
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

    public String getLinkedInId() {
        return linkedInId;
    }

    public void setLinkedInId(String linkedInId) {
        this.linkedInId = linkedInId;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
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
