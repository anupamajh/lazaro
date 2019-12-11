package com.carmel.guesture.inventory.model.DTO;

import com.carmel.guesture.inventory.model.InventoryType;

import java.util.Date;

public class InventoryTypeDTO {
    private String id;
    private String clientId;
    private String title;
    private String narration;
    private int systemConstant;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private  int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public InventoryTypeDTO() {
    }

    public InventoryTypeDTO(InventoryType inventoryType) {
        this.id = inventoryType.getId();
        this.clientId = inventoryType.getClientId();
        this.title = inventoryType.getTitle();
        this.narration = inventoryType.getNarration();
        this.systemConstant = inventoryType.getSystemConstant();
        this.createdBy = inventoryType.getCreatedBy();
        this.creationTime = inventoryType.getCreationTime();
        this.lastModifiedBy = inventoryType.getLastModifiedBy();
        this.lastModifiedTime = inventoryType.getLastModifiedTime();
        this.isDeleted = inventoryType.getIsDeleted();
        this.deletedBy = inventoryType.getDeletedBy();
        this.deletedTime = inventoryType.getDeletedTime();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public int getSystemConstant() {
        return systemConstant;
    }

    public void setSystemConstant(int systemConstant) {
        this.systemConstant = systemConstant;
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
