package com.carmel.common.dbservice.response;

import com.carmel.common.dbservice.model.Organization;

public class OrganizationResponse {

    private Organization organization;
    private boolean success;
    private String error;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
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
