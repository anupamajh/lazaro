package com.carmel.guestjini.service.model.DTO.Inventory;

import com.carmel.guestjini.service.model.Inventory.InventoryDetail;

import java.util.Date;

public class InventoryDetailDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String title;
    private String inventoryId;
    private String propertyId;
    private String blockId;
    private String floorId;
    private String flatId;
    private String roomId;
    private String podId;
    private  int hasBalcony;
    private  int hasBathroom;
    private  int isTrial;
    private InventoryTypeDTO inventoryType;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private  int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public InventoryDetailDTO() {
    }

    public InventoryDetailDTO(InventoryDetail inventoryDetail) {
        this.id = inventoryDetail.getId();
        this.clientId = inventoryDetail.getClientId();
        this.orgId = inventoryDetail.getOrgId();
        this.title = inventoryDetail.getTitle();
        this.inventoryId = inventoryDetail.getInventoryId();
        this.propertyId = inventoryDetail.getPropertyId();
        this.blockId = inventoryDetail.getBlockId();
        this.floorId = inventoryDetail.getFloorId();
        this.flatId = inventoryDetail.getFlatId();
        this.roomId = inventoryDetail.getRoomId();
        this.podId = inventoryDetail.getPodId();
        this.hasBalcony = inventoryDetail.getHasBalcony();
        this.hasBathroom = inventoryDetail.getHasBathroom();
        this.isTrial = inventoryDetail.getIsTrial();
        this.inventoryType = new InventoryTypeDTO(inventoryDetail.getInventoryType());
        this.createdBy = inventoryDetail.getCreatedBy();
        this.creationTime = inventoryDetail.getCreationTime();
        this.lastModifiedBy = inventoryDetail.getLastModifiedBy();
        this.lastModifiedTime = inventoryDetail.getLastModifiedTime();
        this.isDeleted = inventoryDetail.getIsDeleted();
        this.deletedBy = inventoryDetail.getDeletedBy();
        this.deletedTime = inventoryDetail.getDeletedTime();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getFlatId() {
        return flatId;
    }

    public void setFlatId(String flatId) {
        this.flatId = flatId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getPodId() {
        return podId;
    }

    public void setPodId(String podId) {
        this.podId = podId;
    }

    public int getHasBalcony() {
        return hasBalcony;
    }

    public void setHasBalcony(int hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    public int getHasBathroom() {
        return hasBathroom;
    }

    public void setHasBathroom(int hasBathroom) {
        this.hasBathroom = hasBathroom;
    }

    public int getIsTrial() {
        return isTrial;
    }

    public void setIsTrial(int isTrial) {
        this.isTrial = isTrial;
    }

    public InventoryTypeDTO getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(InventoryTypeDTO inventoryType) {
        this.inventoryType = inventoryType;
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
