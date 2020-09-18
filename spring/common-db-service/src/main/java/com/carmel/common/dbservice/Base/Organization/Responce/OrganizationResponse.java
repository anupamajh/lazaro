package com.carmel.common.dbservice.Base.Organization.Responce;

import com.carmel.common.dbservice.Base.Organization.DTO.OrganizationDTO;
import com.carmel.common.dbservice.Base.Organization.Model.Organization;

import java.util.ArrayList;
import java.util.List;


public class OrganizationResponse {
    private List<OrganizationDTO> organizationList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;
    private OrganizationDTO organization;


    public List<OrganizationDTO> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<Organization> organizationList) {
        this.organizationList = new ArrayList<>();
        organizationList.forEach(organization1 -> {
            this.organizationList.add(new OrganizationDTO(organization1));
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

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = new OrganizationDTO(organization);
    }
}
