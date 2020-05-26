package com.carmel.guestjini.Networking.Users;

public class UserInterests {
    private String id;
    private String clientId;
    private String orgId;
    private String userId;
    private String interestId;
    private String interestName;
    private String interestCategoryName;
    private String getInterestCategoryId;
    private  int isInterested;
    private InterestCategory interestCategory;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInterestId() {
        return interestId;
    }

    public void setInterestId(String interestId) {
        this.interestId = interestId;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

    public String getInterestCategoryName() {
        return interestCategoryName;
    }

    public void setInterestCategoryName(String interestCategoryName) {
        this.interestCategoryName = interestCategoryName;
    }

    public String getGetInterestCategoryId() {
        return getInterestCategoryId;
    }

    public void setGetInterestCategoryId(String getInterestCategoryId) {
        this.getInterestCategoryId = getInterestCategoryId;
    }

    public int getIsInterested() {
        return isInterested;
    }

    public void setIsInterested(int isInterested) {
        this.isInterested = isInterested;
    }

    public InterestCategory getInterestCategory() {
        return interestCategory;
    }

    public void setInterestCategory(InterestCategory interestCategory) {
        this.interestCategory = interestCategory;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}
