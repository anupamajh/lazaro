package com.carmel.guestjini.Models.Group;

import java.util.List;

public class GroupConversationResponse {
    private GroupConversation groupConversation;
    private List<GroupConversation> groupConversationList;
    private int totalPages;
    private int totalRecords;
    private int currentRecords;
    private Boolean success;
    private String error;

    public GroupConversation getGroupConversation() {
        return groupConversation;
    }

    public void setGroupConversation(GroupConversation groupConversation) {
        this.groupConversation = groupConversation;
    }

    public List<GroupConversation> getGroupConversationList() {
        return groupConversationList;
    }

    public void setGroupConversationList(List<GroupConversation> groupConversationList) {
        this.groupConversationList = groupConversationList;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getCurrentRecords() {
        return currentRecords;
    }

    public void setCurrentRecords(int currentRecords) {
        this.currentRecords = currentRecords;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
