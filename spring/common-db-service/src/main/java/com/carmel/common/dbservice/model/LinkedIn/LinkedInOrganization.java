package com.carmel.common.dbservice.model.LinkedIn;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "g_linked_in_organization")
@Audited
public class LinkedInOrganization {
    @Id
    @Column(name = "id")
    @Length(max = 40)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "client_id")
    @Length(max = 40)
    private String clientId;

    @Column(name = "org_id")
    @Length(max = 40)
    private String orgId;

    @Column(name = "linked_in_id")
    @Length(max = 150)
    private String linkedInId;

    @Column(name = "organization")
    @Length(max = 350)
    private String oraganization;

    @Column(name = "organization_title")
    @Length(max = 350)
    private String organizationTitle;

    @Column(name = "organization_start")
    @Length(max = 350)
    private String organizationStart;

    @Column(name = "organization_end")
    @Length(max = 350)
    private String organizationEnd;

    @Column(name = "organization_description")
    @Length(max = 350)
    private String organizationDescription;

    @Column(name = "organization_location")
    @Length(max = 350)
    private String organizationLocation;

    @Column(name = "organization_li_url")
    @Length(max = 350)
    private String organizationLiURL;

    @Column(name = "organization_li_title")
    @Length(max = 350)
    private String organizationLiTitle;

    @Column(name = "organization_www")
    @Length(max = 350)
    private String organizationWWW;

    @Column(name = "organization_domain")
    @Length(max = 350)
    private String organizationDomain;

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

    public String getLinkedInId() {
        return linkedInId;
    }

    public void setLinkedInId(String linkedInId) {
        this.linkedInId = linkedInId;
    }

    public String getOraganization() {
        return oraganization;
    }

    public void setOraganization(String oraganization) {
        this.oraganization = oraganization;
    }

    public String getOrganizationTitle() {
        return organizationTitle;
    }

    public void setOrganizationTitle(String organizationTitle) {
        this.organizationTitle = organizationTitle;
    }

    public String getOrganizationStart() {
        return organizationStart;
    }

    public void setOrganizationStart(String organizationStart) {
        this.organizationStart = organizationStart;
    }

    public String getOrganizationEnd() {
        return organizationEnd;
    }

    public void setOrganizationEnd(String organizationEnd) {
        this.organizationEnd = organizationEnd;
    }

    public String getOrganizationDescription() {
        return organizationDescription;
    }

    public void setOrganizationDescription(String organizationDescription) {
        this.organizationDescription = organizationDescription;
    }

    public String getOrganizationLocation() {
        return organizationLocation;
    }

    public void setOrganizationLocation(String organizationLocation) {
        this.organizationLocation = organizationLocation;
    }

    public String getOrganizationLiURL() {
        return organizationLiURL;
    }

    public void setOrganizationLiURL(String organizationLiURL) {
        this.organizationLiURL = organizationLiURL;
    }

    public String getOrganizationLiTitle() {
        return organizationLiTitle;
    }

    public void setOrganizationLiTitle(String organizationLiTitle) {
        this.organizationLiTitle = organizationLiTitle;
    }

    public String getOrganizationWWW() {
        return organizationWWW;
    }

    public void setOrganizationWWW(String organizationWWW) {
        this.organizationWWW = organizationWWW;
    }

    public String getOrganizationDomain() {
        return organizationDomain;
    }

    public void setOrganizationDomain(String organizationDomain) {
        this.organizationDomain = organizationDomain;
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
}
