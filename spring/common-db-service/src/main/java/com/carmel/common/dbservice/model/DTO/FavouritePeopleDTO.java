package com.carmel.common.dbservice.model.DTO;

import com.carmel.common.dbservice.model.FavouritePeople;

import java.util.Date;

public class FavouritePeopleDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String userId;
    private String otherUserId;
    private int isFavourite;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public FavouritePeopleDTO() {
    }

    public FavouritePeopleDTO(FavouritePeople favouritePeople) {
        this.id = favouritePeople.getId();
        this.clientId = favouritePeople.getClientId();
        this.orgId = favouritePeople.getOrgId();
        this.userId = favouritePeople.getUserId();
        this.otherUserId = favouritePeople.getOtherUserId();
        this.isFavourite = favouritePeople.getIsFavourite();
        this.createdBy = favouritePeople.getCreatedBy();
        this.creationTime = favouritePeople.getCreationTime();
        this.lastModifiedBy = favouritePeople.getLastModifiedBy();
        this.lastModifiedTime = favouritePeople.getLastModifiedTime();
        this.isDeleted = favouritePeople.getIsDeleted();
        this.deletedBy = favouritePeople.getDeletedBy();
        this.deletedTime = favouritePeople.getDeletedTime();
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(String otherUserId) {
        this.otherUserId = otherUserId;
    }

    public int getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(int isFavourite) {
        this.isFavourite = isFavourite;
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
