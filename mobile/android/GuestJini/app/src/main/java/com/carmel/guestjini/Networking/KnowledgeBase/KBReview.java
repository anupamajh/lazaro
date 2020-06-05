package com.carmel.guestjini.Networking.KnowledgeBase;

public class KBReview {
    private String id;
    private String clientId;
    private String orgId;
    private String kbId;
    private String reviewBy;
    private String reviewByName;
    private String reviewComment;
    private String reviewByLogoPath;
    private String creationTime;

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

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public String getReviewByLogoPath() {
        return reviewByLogoPath;
    }

    public void setReviewByLogoPath(String reviewByLogoPath) {
        this.reviewByLogoPath = reviewByLogoPath;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}
