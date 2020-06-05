package com.carmel.common.dbservice.model.DTO.LinkedIn;

import com.carmel.common.dbservice.model.LinkedIn.LinkedIn;

import java.util.Date;

public class LinkedInDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String linkedInId;
    private String fullName;
    private String email;
    private String profileURL;
    private String firstName;
    private String lastName;
    private String title;
    private String avatar;
    private String location;
    private String address;
    private Date birthday;
    private String summary;
    private int followers;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public LinkedInDTO() {
    }

    public LinkedInDTO(LinkedIn linkedIn) {
        this.id = linkedIn.getId();
        this.clientId = linkedIn.getClientId();
        this.orgId = linkedIn.getOrgId();
        this.linkedInId = linkedIn.getLinkedInId();
        this.fullName = linkedIn.getFullName();
        this.email = linkedIn.getEmail();
        this.profileURL = linkedIn.getProfileURL();
        this.firstName = linkedIn.getFirstName();
        this.lastName = linkedIn.getLastName();
        this.title = linkedIn.getTitle();
        this.avatar = linkedIn.getAvatar();
        this.location = linkedIn.getLocation();
        this.address = linkedIn.getAddress();
        this.birthday = linkedIn.getBirthday();
        this.summary = linkedIn.getSummary();
        this.followers = linkedIn.getFollowers();
        this.createdBy = linkedIn.getCreatedBy();
        this.creationTime = linkedIn.getCreationTime();
        this.lastModifiedBy = linkedIn.getLastModifiedBy();
        this.lastModifiedTime = linkedIn.getLastModifiedTime();
        this.isDeleted = linkedIn.getIsDeleted();
        this.deletedBy = linkedIn.getDeletedBy();
        this.deletedTime = linkedIn.getDeletedTime();
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
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
