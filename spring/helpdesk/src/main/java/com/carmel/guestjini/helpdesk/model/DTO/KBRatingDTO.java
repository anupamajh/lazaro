package com.carmel.guestjini.helpdesk.model.DTO;

import com.carmel.guestjini.helpdesk.model.KBRating;

import java.util.Date;

public class KBRatingDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String kbId;
    private String ratingBy;
    private int isLiked;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public KBRatingDTO() {
    }

    public KBRatingDTO(KBRating kbRating) {
        this.id = kbRating.getId();
        this.clientId = kbRating.getClientId();
        this.orgId = kbRating.getOrgId();
        this.kbId = kbRating.getKbId();
        this.ratingBy = kbRating.getRatingBy();
        this.isLiked = kbRating.getIsLiked();
        this.createdBy = kbRating.getCreatedBy();
        this.creationTime = kbRating.getCreationTime();
        this.lastModifiedBy = kbRating.getLastModifiedBy();
        this.lastModifiedTime = kbRating.getLastModifiedTime();
        this.isDeleted = kbRating.getIsDeleted();
        this.deletedBy = kbRating.getDeletedBy();
        this.deletedTime = kbRating.getDeletedTime();
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

    public String getRatingBy() {
        return ratingBy;
    }

    public void setRatingBy(String ratingBy) {
        this.ratingBy = ratingBy;
    }

    public int getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(int isLiked) {
        this.isLiked = isLiked;
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
