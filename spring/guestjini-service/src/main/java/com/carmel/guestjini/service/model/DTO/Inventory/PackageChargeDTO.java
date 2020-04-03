package com.carmel.guestjini.service.model.DTO.Inventory;


import com.carmel.guestjini.service.model.Inventory.PackageCharge;

import java.util.Date;

public class PackageChargeDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String title;
    private String narration;
    private double amount;
    private int billingCycle;
    private int chargeMethod;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;
    private PackageDTO aPackage;

    public PackageChargeDTO() {
    }

    public PackageChargeDTO(PackageCharge packageCharge) {
        this.id = packageCharge.getId();
        this.clientId = packageCharge.getClientId();
        this.orgId = packageCharge.getOrgId();
        this.title = packageCharge.getTitle();
        this.narration = packageCharge.getNarration();
        this.amount = packageCharge.getAmount();
        this.billingCycle = packageCharge.getBillingCycle();
        this.chargeMethod = packageCharge.getChargeMethod();
        this.createdBy = packageCharge.getCreatedBy();
        this.creationTime = packageCharge.getCreationTime();
        this.lastModifiedBy = packageCharge.getLastModifiedBy();
        this.isDeleted = packageCharge.getIsDeleted();
        this.deletedBy = packageCharge.getDeletedBy();
        this.deletedTime = packageCharge.getDeletedTime();
        this.aPackage = PackageDTO.getSimple(packageCharge.getaPackage());
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

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getBillingCycle() {
        return billingCycle;
    }

    public void setBillingCycle(int billingCycle) {
        this.billingCycle = billingCycle;
    }

    public int getChargeMethod() {
        return chargeMethod;
    }

    public void setChargeMethod(int chargeMethod) {
        this.chargeMethod = chargeMethod;
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

    public PackageDTO getaPackage() {
        return aPackage;
    }

    public void setaPackage(PackageDTO aPackage) {
        this.aPackage = aPackage;
    }
}
