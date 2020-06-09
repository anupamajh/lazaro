package com.carmel.common.dbservice.model.DTO.Instagram;

import com.carmel.common.dbservice.model.Instagram.Instagram;

import java.util.Date;

public class InstagramDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String instagramId;
    private String userName;
    private String fullName;
    private String userProfile;
    private boolean isFollowedByUser;
    private boolean isRequestedByUser;
    private boolean isUserFollowedBy;
    private String profilePicUrl;
    private boolean isVerified;
    private String profilePicUrlHD;
    private boolean isPrivate;
    private int followsCount;
    private int followedByCount;
    private int followingTagCount;
    private int mediaCount;
    private String biography;
    private boolean isBusiness;
    private int userTags;
    private int mutualFollowersCount;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public InstagramDTO() {
    }

    public InstagramDTO(Instagram instagram) {
        this.id = instagram.getId();
        this.clientId = instagram.getClientId();
        this.orgId = instagram.getOrgId();
        this.instagramId = instagram.getInstagramId();
        this.userName = instagram.getUserName();
        this.fullName = instagram.getFullName();
        this.userProfile = instagram.getUserProfile();
        this.isFollowedByUser = instagram.isFollowedByUser();
        this.isRequestedByUser = instagram.isRequestedByUser();
        this.isUserFollowedBy = instagram.isUserFollowedBy();
        this.profilePicUrl = instagram.getProfilePicUrl();
        this.isVerified = instagram.isVerified();
        this.profilePicUrlHD = instagram.getProfilePicUrlHD();
        this.isPrivate = instagram.isPrivate();
        this.followsCount = instagram.getFollowsCount();
        this.followedByCount = instagram.getFollowedByCount();
        this.followingTagCount = instagram.getFollowingTagCount();
        this.mediaCount = instagram.getMediaCount();
        this.biography = instagram.getBiography();
        this.isBusiness = instagram.isBusiness();
        this.userTags = instagram.getUserTags();
        this.mutualFollowersCount = instagram.getMutualFollowersCount();
        this.createdBy = instagram.getCreatedBy();
        this.creationTime = instagram.getCreationTime();
        this.lastModifiedBy = instagram.getLastModifiedBy();
        this.lastModifiedTime = instagram.getLastModifiedTime();
        this.isDeleted = instagram.getIsDeleted();
        this.deletedBy = instagram.getDeletedBy();
        this.deletedTime = instagram.getDeletedTime();
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

    public String getInstagramId() {
        return instagramId;
    }

    public void setInstagramId(String instagramId) {
        this.instagramId = instagramId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public boolean isFollowedByUser() {
        return isFollowedByUser;
    }

    public void setFollowedByUser(boolean followedByUser) {
        isFollowedByUser = followedByUser;
    }

    public boolean isRequestedByUser() {
        return isRequestedByUser;
    }

    public void setRequestedByUser(boolean requestedByUser) {
        isRequestedByUser = requestedByUser;
    }

    public boolean isUserFollowedBy() {
        return isUserFollowedBy;
    }

    public void setUserFollowedBy(boolean userFollowedBy) {
        isUserFollowedBy = userFollowedBy;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getProfilePicUrlHD() {
        return profilePicUrlHD;
    }

    public void setProfilePicUrlHD(String profilePicUrlHD) {
        this.profilePicUrlHD = profilePicUrlHD;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public int getFollowsCount() {
        return followsCount;
    }

    public void setFollowsCount(int followsCount) {
        this.followsCount = followsCount;
    }

    public int getFollowedByCount() {
        return followedByCount;
    }

    public void setFollowedByCount(int followedByCount) {
        this.followedByCount = followedByCount;
    }

    public int getFollowingTagCount() {
        return followingTagCount;
    }

    public void setFollowingTagCount(int followingTagCount) {
        this.followingTagCount = followingTagCount;
    }

    public int getMediaCount() {
        return mediaCount;
    }

    public void setMediaCount(int mediaCount) {
        this.mediaCount = mediaCount;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public boolean isBusiness() {
        return isBusiness;
    }

    public void setBusiness(boolean business) {
        isBusiness = business;
    }

    public int getUserTags() {
        return userTags;
    }

    public void setUserTags(int userTags) {
        this.userTags = userTags;
    }

    public int getMutualFollowersCount() {
        return mutualFollowersCount;
    }

    public void setMutualFollowersCount(int mutualFollowersCount) {
        this.mutualFollowersCount = mutualFollowersCount;
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
