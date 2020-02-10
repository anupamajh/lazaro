package com.carmel.common.dbservice.model.DTO;

import com.carmel.common.dbservice.model.UserDeviceData;

import java.util.Date;

public class UserDeviceDataDTO {
    private String id;
    private String userId;
    private String fullName;
    private String userName;
    private int deviceType;
    private String deviceIdentifier;
    private String deviceToken;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public UserDeviceDataDTO() {
    }

    public UserDeviceDataDTO(UserDeviceData userDeviceData) {
        this.id = userDeviceData.getId();
        this.userId = userDeviceData.getUserId();
        this.fullName = userDeviceData.getFullName();
        this.userName = userDeviceData.getUserName();
        this.deviceType = userDeviceData.getDeviceType();
        this.deviceIdentifier = userDeviceData.getDeviceIdentifier();
        this.deviceToken = userDeviceData.getDeviceToken();
        this.createdBy = userDeviceData.getCreatedBy();
        this.creationTime = userDeviceData.getCreationTime();
        this.lastModifiedBy = userDeviceData.getLastModifiedBy();
        this.lastModifiedTime = userDeviceData.getLastModifiedTime();
        this.isDeleted = userDeviceData.getIsDeleted();
        this.deletedBy = userDeviceData.getDeletedBy();
        this.deletedTime = userDeviceData.getDeletedTime();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceIdentifier() {
        return deviceIdentifier;
    }

    public void setDeviceIdentifier(String deviceIdentifier) {
        this.deviceIdentifier = deviceIdentifier;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
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
