package com.carmel.guestjini.inventory.response;

import com.carmel.guestjini.inventory.model.DTO.InventoryDTO;
import com.carmel.guestjini.inventory.model.Inventory;

import java.util.ArrayList;
import java.util.List;

public class InventoryResponse {
    private InventoryDTO inventory;
    private List<InventoryDTO> inventoryList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public InventoryDTO getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = new InventoryDTO(inventory);
    }

    public List<InventoryDTO> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<Inventory> inventoryList) {
        this.inventoryList = new ArrayList<>();
        inventoryList.forEach(inventory->{
            this.inventoryList.add(new InventoryDTO(inventory));
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
