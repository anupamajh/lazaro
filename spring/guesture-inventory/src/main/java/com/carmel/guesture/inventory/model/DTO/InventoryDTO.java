package com.carmel.guesture.inventory.model.DTO;

import com.carmel.guesture.inventory.model.Inventory;
import com.carmel.guesture.inventory.model.InventoryType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InventoryDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String title;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private  int isDeleted;
    private String deletedBy;
    private Date deletedTime;
    private Inventory parent;
    private List<InventoryDTO> childrens = new ArrayList<>();
    private InventoryTypeDTO inventoryType;
    private InventoryLocationDTO inventoryLocation;

    public InventoryDTO() {
    }

    public InventoryDTO(Inventory inventory) {
        this.id = inventory.getId();
        this.clientId = inventory.getClientId();
        this.orgId = inventory.getOrgId();
        this.title = inventory.getTitle();
        this.createdBy = inventory.getCreatedBy();
        this.creationTime = inventory.getCreationTime();
        this.lastModifiedBy = inventory.getLastModifiedBy();
        this.lastModifiedTime = inventory.getLastModifiedTime();
        this.isDeleted = inventory.getIsDeleted();
        this.deletedBy = inventory.getDeletedBy();
        this.childrens =  new ArrayList<>();
        this.inventoryType = new InventoryTypeDTO(inventory.getInventoryType());
        this.inventoryLocation = new InventoryLocationDTO(inventory.getInventoryLocation());

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

    public Inventory getParent() {
        return parent;
    }

    public void setParent(Inventory parent) {
        this.parent = parent;
    }

    public List<InventoryDTO> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<InventoryDTO> childrens) {
        this.childrens = childrens;
    }

    public InventoryTypeDTO getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(InventoryType inventoryType) {
        this.inventoryType = new InventoryTypeDTO(inventoryType);
    }

    public InventoryLocationDTO getInventoryLocation() {
        return inventoryLocation;
    }

    public void setInventoryLocation(InventoryLocationDTO inventoryLocation) {
        this.inventoryLocation = inventoryLocation;
    }
}
