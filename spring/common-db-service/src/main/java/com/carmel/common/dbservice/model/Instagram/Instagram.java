package com.carmel.common.dbservice.model.Instagram;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "g_instagram")
@Audited
public class Instagram {
    @Id
    @Column(name = "id")
    @Length(max = 40)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "client_id")
    @Length(max = 40)
    private String clientId;

    @Column(name = "org_id")
    @Length(max = 40)
    private String orgId;

    @Column(name = "instagram_id")
    private String instagramId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "user_profile", columnDefinition = "LONGTEXT")
    private String userProfile;

    @Column(name = "is_followed_by_user")
    private boolean isFollowedByUser;

    @Column(name = "is_requested_by_user")
    private boolean isRequestedByUser;

    @Column(name = "is_user_followed_by")
    private boolean isUserFollowedBy;

    @Column(name = "profile_pic_url", columnDefinition = "LONGTEXT")
    private String profilePicUrl;

    @Column(name = "is_verified")
    private boolean isVerified;

    @Column(name = "profile_pic_url_hd", columnDefinition = "LONGTEXT")
    private String profilePicUrlHD;

    @Column(name = "is_private")
    private boolean isPrivate;

    @Column(name = "follows_count")
    private int followsCount;

    @Column(name = "followed_by_count")
    private int followedByCount;

    @Column(name = "following_tag_count")
    private int followingTagCount;

    @Column(name = "media_count")
    private int mediaCount;

    @Column(name = "biography", columnDefinition = "TEXT")
    private String biography;

    @Column(name = "is_business")
    private boolean isBusiness;

    @Column(name = "user_tags")
    private int userTags;

    @Column(name = "mutual_followers_count")
    private int mutualFollowersCount;

    @Column(name = "created_by")
    @Length(max = 40)
    private String createdBy;

    @Column(name = "creation_time")
    private Date creationTime;

    @Column(name = "last_modified_by")
    @Length(max = 40)
    private String lastModifiedBy;

    @Column(name = "last_Modified_time")
    private Date lastModifiedTime;

    @Column(name = "is_deleted")
    private int isDeleted;

    @Column(name = "deleted_by")
    @Length(max = 40)
    private String deletedBy;

    @Column(name = "deleted_time")
    private Date deletedTime;

    public Instagram() {
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
