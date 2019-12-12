package com.carmel.guestjini.inventory.response;

import com.carmel.guestjini.inventory.model.InventoryType;

import java.util.List;

public class InventoryTypeResponse {
    private InventoryType inventoryType;
    private List<InventoryType> inventoryTypeList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public InventoryType getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(InventoryType inventoryType) {
        this.inventoryType = inventoryType;
    }

    public List<InventoryType> getInventoryTypeList() {
        return inventoryTypeList;
    }

    public void setInventoryTypeList(List<InventoryType> inventoryTypeList) {
        this.inventoryTypeList = inventoryTypeList;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public long getCurrentRecords() {
        return currentRecords;
    }

    public void setCurrentRecords(long currentRecords) {
        this.currentRecords = currentRecords;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
