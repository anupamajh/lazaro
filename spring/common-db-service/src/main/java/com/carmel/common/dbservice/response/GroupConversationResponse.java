package com.carmel.common.dbservice.response;

import com.carmel.common.dbservice.model.DTO.GroupConversationDTO;
import com.carmel.common.dbservice.model.GroupConversation;

import java.util.ArrayList;
import java.util.List;

public class GroupConversationResponse {
    private GroupConversationDTO groupConversation;
    private List<GroupConversationDTO> groupConversationList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public GroupConversationDTO getGroupConversation() {
        return groupConversation;
    }

    public void setGroupConversation(GroupConversation groupConversation) {
        this.groupConversation = new GroupConversationDTO(groupConversation);
    }

    public List<GroupConversationDTO> getGroupConversationList() {
        return groupConversationList;
    }

    public void setGroupConversationList(List<GroupConversation> groupConversationList) {
        this.groupConversationList = new ArrayList<>();
        groupConversationList.forEach(groupConversation1 -> {
            this.groupConversationList.add(new GroupConversationDTO(groupConversation1));

        });
    }

    public void setGroupConversationListDTO(List<GroupConversationDTO> groupConversationList) {
        this.groupConversationList = groupConversationList;
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
