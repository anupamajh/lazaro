package com.carmel.guestjini.service.response.Inventory;


import com.carmel.guestjini.service.model.DTO.Inventory.InventoryTypeDTO;
import com.carmel.guestjini.service.model.Inventory.InventoryType;

import java.util.ArrayList;
import java.util.List;

public class InventoryTypeResponse {
    private InventoryTypeDTO inventoryType;
    private List<InventoryTypeDTO> inventoryTypeList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public InventoryTypeDTO getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(InventoryType inventoryType) {
        this.inventoryType = new InventoryTypeDTO(inventoryType);
    }

    public List<InventoryTypeDTO> getInventoryTypeList() {
        return inventoryTypeList;
    }

    public void setInventoryTypeList(List<InventoryType> inventoryTypeList) {
        this.inventoryTypeList = new ArrayList<>();
        inventoryTypeList.forEach(inventoryType1 -> {
            this.inventoryTypeList.add(new InventoryTypeDTO(inventoryType1));
        });
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
