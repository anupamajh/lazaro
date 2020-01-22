package com.carmel.guestjini.helpdesk.model.DTO;

import com.carmel.guestjini.helpdesk.model.KBReview;

import java.util.Date;

public class KBReviewDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String kbId;
    private String reviewBy;
    private String reviewByName;
    private String reviewComment;
    private String reviewByLogoPath;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public KBReviewDTO() {
    }

    public KBReviewDTO(KBReview kbReview) {
        this.id = kbReview.getId();
        this.clientId = kbReview.getClientId();
        this.orgId = kbReview.getOrgId();
        this.kbId = kbReview.getKbId();
        this.reviewBy = kbReview.getReviewBy();
        this.reviewByName = kbReview.getReviewByName();
        this.reviewByLogoPath = kbReview.getReviewByLogoPath();
        this.reviewComment = kbReview.getReviewComment();
        this.createdBy = kbReview.getCreatedBy();
        this.creationTime = kbReview.getCreationTime();
        this.lastModifiedBy = kbReview.getLastModifiedBy();
        this.lastModifiedTime = kbReview.getLastModifiedTime();
        this.isDeleted = kbReview.getIsDeleted();
        this.deletedBy = kbReview.getDeletedBy();
        this.deletedTime = kbReview.getDeletedTime();
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

    public String getKbId() {
        return kbId;
    }

    public void setKbId(String kbId) {
        this.kbId = kbId;
    }

    public String getReviewBy() {
        return reviewBy;
    }

    public void setReviewBy(String reviewBy) {
        this.reviewBy = reviewBy;
    }

    public String getReviewByName() {
        return reviewByName;
    }

    public void setReviewByName(String reviewByName) {
        this.reviewByName = reviewByName;
    }

    public String getReviewByLogoPath() {
        return reviewByLogoPath;
    }

    public void setReviewByLogoPath(String reviewByLogoPath) {
        this.reviewByLogoPath = reviewByLogoPath;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
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
