package com.carmel.common.dbservice.Base.Organization.DTO;

import com.carmel.common.dbservice.Base.Client.DTO.ClientDTO;
import com.carmel.common.dbservice.Base.Client.Model.Client;
import com.carmel.common.dbservice.Base.Organization.Model.Organization;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrganizationDTO {
    private String id;
    private String orgName;
    private String orgDomain;
    private String description;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;
    private OrganizationDTO parent;
    private List<OrganizationDTO> childrens = new ArrayList<>();
    private ClientDTO client;

    public OrganizationDTO() {
    }

    public OrganizationDTO(Organization organization){
        this.id = organization.getId();
        this.orgName = organization.getOrgName();
        this.orgDomain = organization.getOrgDomain();
        this.description = organization.getDescription();
        this.createdBy = organization.getCreatedBy();
        this.creationTime = organization.getCreationTime();
        this.lastModifiedBy = organization.getLastModifiedBy();
        this.lastModifiedTime = organization.getLastModifiedTime();
        this.isDeleted = organization.getIsDeleted();
        this.deletedBy = organization.getDeletedBy();
        this.deletedTime = organization.getDeletedTime();
        if(organization.getParent() != null) {
            this.parent = OrganizationDTO.getSimple(organization.getParent());
        }
        this.childrens = new ArrayList<>();
        if(!organization.getChildrens().isEmpty()) {
            organization.getChildrens().forEach(organization1 -> {
                this.childrens.add(getSimple(organization1));
            });
        }
        this.client = ClientDTO.getSimple(organization.getClient());
    }

    public static OrganizationDTO getSimple(Organization organization) {
        OrganizationDTO retValue = new OrganizationDTO();
        retValue.setId(organization.getId());
        retValue.setOrgName(organization.getOrgName());
        retValue.setOrgDomain(organization.getOrgDomain());
        retValue.setDescription(organization.getDescription());
        retValue.setCreatedBy(organization.getCreatedBy());
        retValue.setCreationTime(organization.getCreationTime());
        retValue.setLastModifiedBy(organization.getLastModifiedBy());
        retValue.setLastModifiedTime(organization.getLastModifiedTime());
        retValue.setIsDeleted(organization.getIsDeleted());
        retValue.setDeletedBy(organization.getDeletedBy());
        retValue.setDeletedTime(organization.getDeletedTime());
        return retValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgDomain() {
        return orgDomain;
    }

    public void setOrgDomain(String orgDomain) {
        this.orgDomain = orgDomain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public OrganizationDTO getParent() {
        return parent;
    }

    public void setParent(Organization parent) {
        this.parent = OrganizationDTO.getSimple(parent);
    }

    public List<OrganizationDTO> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<OrganizationDTO> childrens) {
        this.childrens = childrens;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = ClientDTO.getSimple(client);
    }
}


