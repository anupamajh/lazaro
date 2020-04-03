package com.carmel.guestjini.service.response.Inventory;

import com.carmel.guestjini.service.model.DTO.Inventory.PackageDTO;
import com.carmel.guestjini.service.model.Inventory.Package;

import java.util.ArrayList;
import java.util.List;

public class PackageResponse {
    private PackageDTO aPackage;
    private List<PackageDTO> packageList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public PackageDTO getPackage() {
        return aPackage;
    }

    public void setPackage(Package aPackage) {
        this.aPackage = new PackageDTO(aPackage);
    }

    public List<PackageDTO> getPackageList() {
        return packageList;
    }

    public void setPackageList(List<Package> packageList) {
        this.packageList = new ArrayList<>();
        packageList.forEach(pack->{
            this.packageList.add(new PackageDTO(pack));
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
