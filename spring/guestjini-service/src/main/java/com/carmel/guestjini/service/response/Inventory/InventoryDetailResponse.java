package com.carmel.guestjini.service.response.Inventory;

import com.carmel.guestjini.service.model.DTO.Inventory.InventoryDetailDTO;
import com.carmel.guestjini.service.model.Inventory.InventoryDetail;

import java.util.ArrayList;
import java.util.List;

public class InventoryDetailResponse {
    private InventoryDetailDTO inventoryDetail;
    private List<InventoryDetailDTO> inventoryDetailList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public InventoryDetailDTO getInventoryDetail() {
        return inventoryDetail;
    }

    public void setInventoryDetail(InventoryDetail inventoryDetail) {
        this.inventoryDetail = new InventoryDetailDTO(inventoryDetail);
    }

    public List<InventoryDetailDTO> getInventoryDetailList() {
        return inventoryDetailList;
    }

    public void setInventoryDetailList(List<InventoryDetail> inventoryDetailList) {
        this.inventoryDetailList = new ArrayList<>();
        inventoryDetailList.forEach(inventoryDetail1 -> {
            this.inventoryDetailList.add(new InventoryDetailDTO(inventoryDetail1));
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
