package com.carmel.common.dbservice.model.DTO.LinkedIn;

import com.carmel.common.dbservice.model.LinkedIn.LinkedInPhone;

import java.util.Date;

public class LinkedInPhoneDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String linkedInId;
    private String phone;
    private String phoneType;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public LinkedInPhoneDTO() {
    }

    public LinkedInPhoneDTO(LinkedInPhone linkedInPhone) {
        this.id = linkedInPhone.getId();
        this.clientId = linkedInPhone.getClientId();
        this.orgId = linkedInPhone.getOrgId();
        this.linkedInId = linkedInPhone.getLinkedInId();
        this.phone = linkedInPhone.getPhone();
        this.phoneType = linkedInPhone.getPhoneType();
        this.createdBy = linkedInPhone.getCreatedBy();
        this.creationTime = linkedInPhone.getCreationTime();
        this.lastModifiedBy = linkedInPhone.getLastModifiedBy();
        this.lastModifiedTime = linkedInPhone.getLastModifiedTime();
        this.isDeleted = linkedInPhone.getIsDeleted();
        this.deletedBy = linkedInPhone.getDeletedBy();
        this.deletedTime = linkedInPhone.getDeletedTime();
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
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
