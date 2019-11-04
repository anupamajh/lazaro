package com.carmel.common.dbservice.response;

import com.carmel.common.dbservice.model.Role;

public class RoleResponse {

    private Role role;
    private boolean success;
    private String error;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
