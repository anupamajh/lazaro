package com.carmel.common.dbservice.model.DTO.LinkedIn;

import com.carmel.common.dbservice.model.LinkedIn.LinkedInEducation;

import java.util.Date;

public class LinkedInEducationDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String linkedInId;
    private String education;
    private String educationDegree;
    private String educationFOS;
    private String educationGrade;
    private Date educationStart;
    private Date educationEnd;
    private String educationDescription;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public LinkedInEducationDTO() {
    }

    public LinkedInEducationDTO(LinkedInEducation linkedInEducation) {
        this.id = linkedInEducation.getId();
        this.clientId = linkedInEducation.getClientId();
        this.orgId = linkedInEducation.getOrgId();
        this.linkedInId = linkedInEducation.getLinkedInId();
        this.education = linkedInEducation.getEducation();
        this.educationDegree = linkedInEducation.getEducationDegree();
        this.educationFOS = linkedInEducation.getEducationFOS();
        this.educationGrade = linkedInEducation.getEducationGrade();
        this.educationStart = linkedInEducation.getEducationStart();
        this.educationEnd = linkedInEducation.getEducationEnd();
        this.educationDescription = linkedInEducation.getEducationDescription();
        this.createdBy = linkedInEducation.getCreatedBy();
        this.creationTime = linkedInEducation.getCreationTime();
        this.lastModifiedBy = linkedInEducation.getLastModifiedBy();
        this.lastModifiedTime = linkedInEducation.getLastModifiedTime();
        this.isDeleted = linkedInEducation.getIsDeleted();
        this.deletedBy = linkedInEducation.getDeletedBy();
        this.deletedTime = linkedInEducation.getDeletedTime();
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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEducationDegree() {
        return educationDegree;
    }

    public void setEducationDegree(String educationDegree) {
        this.educationDegree = educationDegree;
    }

    public String getEducationFOS() {
        return educationFOS;
    }

    public void setEducationFOS(String educationFOS) {
        this.educationFOS = educationFOS;
    }

    public String getEducationGrade() {
        return educationGrade;
    }

    public void setEducationGrade(String educationGrade) {
        this.educationGrade = educationGrade;
    }

    public Date getEducationStart() {
        return educationStart;
    }

    public void setEducationStart(Date educationStart) {
        this.educationStart = educationStart;
    }

    public Date getEducationEnd() {
        return educationEnd;
    }

    public void setEducationEnd(Date educationEnd) {
        this.educationEnd = educationEnd;
    }

    public String getEducationDescription() {
        return educationDescription;
    }

    public void setEducationDescription(String educationDescription) {
        this.educationDescription = educationDescription;
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
