package com.carmel.guestjini.Models.Group;

public class Group {
    private String id;
    private String clientId;
    private String orgId;
    private String interestId;
    private String interestCategoryId;
    private String interestCategoryName;
    private String groupOwnerId;
    private int groupType;
    private String name;
    private String description;
    private String creationTime;
    private String subscribedDate;
    private int isSubscribed;
    private int isInvited;
    private int isMatchingInterest;
    private int cardType;

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

    public String getInterestId() {
        return interestId;
    }

    public void setInterestId(String interestId) {
        this.interestId = interestId;
    }

    public String getInterestCategoryId() {
        return interestCategoryId;
    }

    public void setInterestCategoryId(String interestCategoryId) {
        this.interestCategoryId = interestCategoryId;
    }

    public String getInterestCategoryName() {
        return interestCategoryName;
    }

    public void setInterestCategoryName(String interestCategoryName) {
        this.interestCategoryName = interestCategoryName;
    }

    public String getGroupOwnerId() {
        return groupOwnerId;
    }

    public void setGroupOwnerId(String groupOwnerId) {
        this.groupOwnerId = groupOwnerId;
    }

    public int getGroupType() {
        return groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType = groupType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getSubscribedDate() {
        return subscribedDate;
    }

    public void setSubscribedDate(String subscribedDate) {
        this.subscribedDate = subscribedDate;
    }

    public int getIsSubscribed() {
        return isSubscribed;
    }

    public void setIsSubscribed(int isSubscribed) {
        this.isSubscribed = isSubscribed;
    }

    public int getIsInvited() {
        return isInvited;
    }

    public void setIsInvited(int isInvited) {
        this.isInvited = isInvited;
    }

    public int getIsMatchingInterest() {
        return isMatchingInterest;
    }

    public void setIsMatchingInterest(int isMatchingInterest) {
        this.isMatchingInterest = isMatchingInterest;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }
}
