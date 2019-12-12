package com.carmel.guestjini.inventory.model.DTO;

import com.carmel.guestjini.inventory.model.Package;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PackageDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String packageTitle;
    private String packageNarration;
    private double rent;
    private int rentCycle;
    private int packageStatus;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;
    private List<PackageChargeDTO> packageCharges;

    public PackageDTO() {
    }

    public PackageDTO(Package aPackage) {
        this.id = aPackage.getId();
        this.clientId = aPackage.getClientId();
        this.orgId = aPackage.getOrgId();
        this.packageTitle = aPackage.getPackageTitle();
        this.packageNarration = aPackage.getPackageNarration();
        this.rent = aPackage.getRent();
        this.rentCycle = aPackage.getRentCycle();
        this.packageStatus = aPackage.getPackageStatus();
        this.createdBy = aPackage.getCreatedBy();
        this.creationTime = aPackage.getCreationTime();
        this.lastModifiedBy = aPackage.getLastModifiedBy();
        this.lastModifiedTime = aPackage.getLastModifiedTime();
        this.isDeleted = aPackage.getIsDeleted();
        this.deletedBy = aPackage.getDeletedBy();
        this.deletedTime = aPackage.getDeletedTime();
        this.packageCharges = new ArrayList<>();
        aPackage.getPackageCharges().forEach(packageCharge -> {
            this.packageCharges.add(new PackageChargeDTO(packageCharge));
        });
    }

    public static PackageDTO getSimple(Package aPackage) {
        PackageDTO packageDTO = new PackageDTO();
        packageDTO.id = aPackage.getId();
        packageDTO.clientId = aPackage.getClientId();
        packageDTO.orgId = aPackage.getOrgId();
        packageDTO.packageTitle = aPackage.getPackageTitle();
        packageDTO.packageNarration = aPackage.getPackageNarration();
        packageDTO.rent = aPackage.getRent();
        packageDTO.rentCycle = aPackage.getRentCycle();
        packageDTO.packageStatus = aPackage.getPackageStatus();
        packageDTO.createdBy = aPackage.getCreatedBy();
        packageDTO.creationTime = aPackage.getCreationTime();
        packageDTO.lastModifiedBy = aPackage.getLastModifiedBy();
        packageDTO.lastModifiedTime = aPackage.getLastModifiedTime();
        packageDTO.isDeleted = aPackage.getIsDeleted();
        packageDTO.deletedBy = aPackage.getDeletedBy();
        packageDTO.deletedTime = aPackage.getDeletedTime();
        packageDTO.packageCharges = new ArrayList<>();

        return packageDTO;
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

    public String getPackageTitle() {
        return packageTitle;
    }

    public void setPackageTitle(String packageTitle) {
        this.packageTitle = packageTitle;
    }

    public String getPackageNarration() {
        return packageNarration;
    }

    public void setPackageNarration(String packageNarration) {
        this.packageNarration = packageNarration;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public int getRentCycle() {
        return rentCycle;
    }

    public void setRentCycle(int rentCycle) {
        this.rentCycle = rentCycle;
    }

    public int getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(int packageStatus) {
        this.packageStatus = packageStatus;
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

    public List<PackageChargeDTO> getPackageCharges() {
        return packageCharges;
    }

    public void setPackageCharges(List<PackageChargeDTO> packageCharges) {
        this.packageCharges = packageCharges;
    }
}
