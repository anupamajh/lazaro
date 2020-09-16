package com.carmel.common.dbservice.model;

import com.carmel.common.dbservice.Base.AppFeature.Model.AppFeatures;
import com.carmel.common.dbservice.Base.Client.Model.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "g_client_details")
@Audited
public class ClientDetails {

    @Id
    @Column(name = "id")
    @Length(max = 40)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;


    @Column(name = "client_name")
    @NotNull
    @Length(min = 1, max = 255, message = "Client name length should be between 1 and 255")
    @NotBlank(message = "Client Name cannot be blank")
    private String clientName;

    @Column(name = "client_address")
    @Length(min = 0, max = 8000, message = "Client name length cannot exceed 8000")
    private String clientAddress;

    @Column(name = "company_url")
    @Length(min = 0, max = 8000, message = "Company URL length cannot exceed 8000")
    private String companyURL;


    @Column(name = "company_logo_url")
    @Length(min = 0, max = 8000, message = "Company Logo URL  length cannot exceed 8000")
    private String companyLogoUrl;

    @Column(name = "email")
    @NotNull
    @Length(min = 1, max = 100, message = "Email length should be between 1 and 100")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "g_client_app_features",
            joinColumns = {
                    @JoinColumn(name = "client_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "app_feature_id", referencedColumnName = "id")
            }
    )
    private List<AppFeatures> appFeatures;

    @Column(name = "created_by")
    @Length(max = 40)
    private String createdBy;

    @Column(name = "creation_time")
    private Date creationTime;

    @Column(name = "last_modified_by")
    @Length(max = 40)
    private String lastModifiedBy;

    @Column(name = "last_Modified_time")
    private Date lastModifiedTime;

    @Column(name = "is_deleted")
    private int isDeleted;

    @Column(name = "deleted_by")
    @Length(max = 40)
    private String deletedBy;

    @Column(name = "deleted_time")
    private Date deletedTime;


    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private Client client;

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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<AppFeatures> getAppFeatures() {
        return appFeatures;
    }

    public void setAppFeatures(List<AppFeatures> appFeatures) {
        this.appFeatures = appFeatures;
    }
}
