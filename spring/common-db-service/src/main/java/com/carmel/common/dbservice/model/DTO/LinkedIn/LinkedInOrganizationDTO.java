package com.carmel.common.dbservice.model.DTO.LinkedIn;

import com.carmel.common.dbservice.model.LinkedIn.LinkedInOrganization;

import java.util.Date;

public class LinkedInOrganizationDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String linkedInId;
    private String oraganization;
    private String organizationTitle;
    private String organizationStart;
    private String organizationEnd;
    private String organizationDescription;
    private String organizationLocation;
    private String organizationLiURL;
    private String organizationLiTitle;
    private String organizationWWW;
    private String organizationDomain;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public LinkedInOrganizationDTO() {
    }

    public LinkedInOrganizationDTO(LinkedInOrganization linkedInOrganization) {
        this.id = linkedInOrganization.getId();
        this.clientId = linkedInOrganization.getClientId();
        this.orgId = linkedInOrganization.getOrgId();
        this.linkedInId = linkedInOrganization.getLinkedInId();
        this.oraganization = linkedInOrganization.getOraganization();
        this.organizationTitle = linkedInOrganization.getOrganizationTitle();
        this.organizationStart = linkedInOrganization.getOrganizationStart();
        this.organizationEnd = linkedInOrganization.getOrganizationEnd();
        this.organizationDescription = linkedInOrganization.getOrganizationDescription();
        this.organizationLocation = linkedInOrganization.getOrganizationLocation();
        this.organizationLiURL = linkedInOrganization.getOrganizationLiURL();
        this.organizationLiTitle = linkedInOrganization.getOrganizationLiTitle();
        this.organizationWWW = linkedInOrganization.getOrganizationWWW();
        this.organizationDomain = linkedInOrganization.getOrganizationDomain();
        this.createdBy = linkedInOrganization.getCreatedBy();
        this.creationTime = linkedInOrganization.getCreationTime();
        this.lastModifiedBy = linkedInOrganization.getLastModifiedBy();
        this.lastModifiedTime = linkedInOrganization.getLastModifiedTime();
        this.isDeleted = linkedInOrganization.getIsDeleted();
        this.deletedBy = linkedInOrganization.getDeletedBy();
        this.deletedTime = linkedInOrganization.getDeletedTime();
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

    public String getOraganization() {
        return oraganization;
    }

    public void setOraganization(String oraganization) {
        this.oraganization = oraganization;
    }

    public String getOrganizationTitle() {
        return organizationTitle;
    }

    public void setOrganizationTitle(String organizationTitle) {
        this.organizationTitle = organizationTitle;
    }

    public String getOrganizationStart() {
        return organizationStart;
    }

    public void setOrganizationStart(String organizationStart) {
        this.organizationStart = organizationStart;
    }

    public String getOrganizationEnd() {
        return organizationEnd;
    }

    public void setOrganizationEnd(String organizationEnd) {
        this.organizationEnd = organizationEnd;
    }

    public String getOrganizationDescription() {
        return organizationDescription;
    }

    public void setOrganizationDescription(String organizationDescription) {
        this.organizationDescription = organizationDescription;
    }

    public String getOrganizationLocation() {
        return organizationLocation;
    }

    public void setOrganizationLocation(String organizationLocation) {
        this.organizationLocation = organizationLocation;
    }

    public String getOrganizationLiURL() {
        return organizationLiURL;
    }

    public void setOrganizationLiURL(String organizationLiURL) {
        this.organizationLiURL = organizationLiURL;
    }

    public String getOrganizationLiTitle() {
        return organizationLiTitle;
    }

    public void setOrganizationLiTitle(String organizationLiTitle) {
        this.organizationLiTitle = organizationLiTitle;
    }

    public String getOrganizationWWW() {
        return organizationWWW;
    }

    public void setOrganizationWWW(String organizationWWW) {
        this.organizationWWW = organizationWWW;
    }

    public String getOrganizationDomain() {
        return organizationDomain;
    }

    public void setOrganizationDomain(String organizationDomain) {
        this.organizationDomain = organizationDomain;
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
