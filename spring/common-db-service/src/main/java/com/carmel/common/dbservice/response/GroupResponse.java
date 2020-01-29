package com.carmel.common.dbservice.response;

import com.carmel.common.dbservice.model.DTO.GroupDTO;
import com.carmel.common.dbservice.model.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupResponse {
    private GroupDTO group;
    private List<GroupDTO> groupList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public GroupDTO getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = new GroupDTO(group);
    }

    public List<GroupDTO> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = new ArrayList<>();
        groupList.forEach(group1 -> {
            this.groupList.add(new GroupDTO(group1));
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
