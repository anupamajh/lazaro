package com.carmel.guestjini.service.response.Inventory;


import com.carmel.guestjini.service.model.DTO.Inventory.PackageChargeDTO;
import com.carmel.guestjini.service.model.Inventory.PackageCharge;

import java.util.ArrayList;
import java.util.List;

public class PackageChargeResponse {
    private PackageChargeDTO packageCharge;
    private List<PackageChargeDTO> packageChargeList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public PackageChargeDTO getPackageCharge() {
        return packageCharge;
    }

    public void setPackageCharge(PackageCharge packageCharge) {
        this.packageCharge = new PackageChargeDTO(packageCharge);
    }

    public List<PackageChargeDTO> getPackageChargeList() {
        return packageChargeList;
    }

    public void setPackageChargeList(List<PackageCharge> packageChargeList) {
        this.packageChargeList = new ArrayList<>();

        packageChargeList.forEach(packageCharge1 -> {
            this.packageChargeList.add(new PackageChargeDTO(packageCharge1));
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
