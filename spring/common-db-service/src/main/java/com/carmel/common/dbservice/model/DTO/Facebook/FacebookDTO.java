package com.carmel.common.dbservice.model.DTO.Facebook;

import com.carmel.common.dbservice.model.Facebook.Facebook;

import java.util.Date;

public class FacebookDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String fbName;
    private String titleURL;
    private String image;
    private String companyName;
    private String pacURL;
    private String designation;
    private String pacURL2;
    private String currentWorking;
    private String liveAt;
    private String eh52URL;
    private String eh524;
    private String followers;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public FacebookDTO() {
    }

    public FacebookDTO(Facebook facebook) {
        this.id = facebook.getId();
        this.clientId = facebook.getClientId();
        this.orgId = facebook.getOrgId();
        this.fbName = facebook.getFbName();
        this.titleURL = facebook.getTitleURL();
        this.image = facebook.getImage();
        this.companyName = facebook.getCompanyName();
        this.pacURL = facebook.getPacURL();
        this.designation = facebook.getDesignation();
        this.pacURL2 = facebook.getPacURL2();
        this.currentWorking = facebook.getCurrentWorking();
        this.liveAt = facebook.getLiveAt();
        this.eh52URL = facebook.getEh52URL();
        this.eh524 = facebook.getEh524();
        this.followers = facebook.getFollowers();
        this.createdBy = facebook.getCreatedBy();
        this.creationTime = facebook.getCreationTime();
        this.lastModifiedBy = facebook.getLastModifiedBy();
        this.lastModifiedTime = facebook.getLastModifiedTime();
        this.isDeleted = facebook.getIsDeleted();
        this.deletedBy = facebook.getDeletedBy();
        this.deletedTime = facebook.getDeletedTime();
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

    public String getFbName() {
        return fbName;
    }

    public void setFbName(String fbName) {
        this.fbName = fbName;
    }

    public String getTitleURL() {
        return titleURL;
    }

    public void setTitleURL(String titleURL) {
        this.titleURL = titleURL;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPacURL() {
        return pacURL;
    }

    public void setPacURL(String pacURL) {
        this.pacURL = pacURL;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPacURL2() {
        return pacURL2;
    }

    public void setPacURL2(String pacURL2) {
        this.pacURL2 = pacURL2;
    }

    public String getCurrentWorking() {
        return currentWorking;
    }

    public void setCurrentWorking(String currentWorking) {
        this.currentWorking = currentWorking;
    }

    public String getLiveAt() {
        return liveAt;
    }

    public void setLiveAt(String liveAt) {
        this.liveAt = liveAt;
    }

    public String getEh52URL() {
        return eh52URL;
    }

    public void setEh52URL(String eh52URL) {
        this.eh52URL = eh52URL;
    }

    public String getEh524() {
        return eh524;
    }

    public void setEh524(String eh524) {
        this.eh524 = eh524;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
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
