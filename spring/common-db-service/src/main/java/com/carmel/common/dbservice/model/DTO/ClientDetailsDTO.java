package com.carmel.common.dbservice.model.DTO;

import com.carmel.common.dbservice.model.AppFeatures;
import com.carmel.common.dbservice.model.ClientDetails;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class ClientDetailsDTO {
    private String id;
    private String clientName;
    private String clientAddress;
    private String companyURL;
    private String companyLogoUrl;
    private String email;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;
    private List<AppFeatures> appFeatures;

    public ClientDetailsDTO() {
    }

    public ClientDetailsDTO(ClientDetails clientDetails) {
        this.id = clientDetails.getId();
        this.clientName = clientDetails.getClientName();
        this.clientAddress = clientDetails.getClientAddress();
        this.companyURL = clientDetails.getCompanyURL();
        this.companyLogoUrl = clientDetails.getCompanyLogoUrl();
        this.email = clientDetails.getEmail();
        this.createdBy = clientDetails.getCreatedBy();
        this.creationTime = clientDetails.getCreationTime();
        this.lastModifiedBy = clientDetails.getLastModifiedBy();
        this.lastModifiedTime = clientDetails.getLastModifiedTime();
        this.isDeleted = clientDetails.getIsDeleted();
        this.deletedBy = clientDetails.getDeletedBy();
        this.deletedTime = clientDetails.getDeletedTime();
        this.appFeatures = clientDetails.getAppFeatures();
    }

    public static ClientDetailsDTO getSimple(ClientDetails clientDetails) {
        return new ClientDetailsDTO(clientDetails);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getCompanyURL() {
        return companyURL;
    }

    public void setCompanyURL(String companyURL) {
        this.companyURL = companyURL;
    }

    public String getCompanyLogoUrl() {
        return companyLogoUrl;
    }

    public void setCompanyLogoUrl(String companyLogoUrl) {
        this.companyLogoUrl = companyLogoUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<AppFeatures> getAppFeatures() {
        return appFeatures;
    }

    public void setAppFeatures(List<AppFeatures> appFeatures) {
        this.appFeatures = appFeatures;
    }
}
