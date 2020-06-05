package com.carmel.common.dbservice.model.DTO.LinkedIn;

import com.carmel.common.dbservice.model.LinkedIn.LinkedInSkill;

import java.util.Date;

public class LinkedInSkillDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String linkedInId;
    private String skillTitle;
    private int rating;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public LinkedInSkillDTO() {
    }

    public LinkedInSkillDTO(LinkedInSkill linkedInSkill) {
        this.id = linkedInSkill.getId();
        this.clientId = linkedInSkill.getClientId();
        this.orgId = linkedInSkill.getOrgId();
        this.linkedInId = linkedInSkill.getLinkedInId();
        this.skillTitle = linkedInSkill.getSkillTitle();
        this.rating = linkedInSkill.getRating();
        this.createdBy = linkedInSkill.getCreatedBy();
        this.creationTime = linkedInSkill.getCreationTime();
        this.lastModifiedBy = linkedInSkill.getLastModifiedBy();
        this.lastModifiedTime = linkedInSkill.getLastModifiedTime();
        this.isDeleted = linkedInSkill.getIsDeleted();
        this.deletedBy = linkedInSkill.getDeletedBy();
        this.deletedTime = linkedInSkill.getDeletedTime();
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

    public String getSkillTitle() {
        return skillTitle;
    }

    public void setSkillTitle(String skillTitle) {
        this.skillTitle = skillTitle;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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
