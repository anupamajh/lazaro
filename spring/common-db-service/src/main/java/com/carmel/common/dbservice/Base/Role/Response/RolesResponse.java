package com.carmel.common.dbservice.Base.Role.Response;

import com.carmel.common.dbservice.Base.Role.DTO.RoleDTO;
import com.carmel.common.dbservice.Base.Role.Model.Role;

import java.util.ArrayList;
import java.util.List;

public class RolesResponse {

    private RoleDTO role;
    private List<RoleDTO> roleList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = new RoleDTO((role));
    }

    public List<RoleDTO> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = new ArrayList<>();
        roleList.forEach(role1 ->{
            this.roleList.add(new RoleDTO(role1));
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
