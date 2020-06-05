package com.carmel.common.dbservice.model.DTO.LinkedIn;

import com.carmel.common.dbservice.model.LinkedIn.LinkedInWebSite;

import java.util.Date;

public class LinkedInWebSiteDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String linkedInId;
    private String website;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public LinkedInWebSiteDTO() {
    }

    public LinkedInWebSiteDTO(LinkedInWebSite linkedInWebSite) {
        this.id = linkedInWebSite.getId();
        this.clientId = linkedInWebSite.getClientId();
        this.orgId = linkedInWebSite.getOrgId();
        this.linkedInId = linkedInWebSite.getLinkedInId();
        this.website = linkedInWebSite.getWebsite();
        this.createdBy = linkedInWebSite.getCreatedBy();
        this.creationTime = linkedInWebSite.getCreationTime();
        this.lastModifiedBy = linkedInWebSite.getLastModifiedBy();
        this.lastModifiedTime = linkedInWebSite.getLastModifiedTime();
        this.isDeleted = linkedInWebSite.getIsDeleted();
        this.deletedBy = linkedInWebSite.getDeletedBy();
        this.deletedTime = linkedInWebSite.getDeletedTime();
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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
