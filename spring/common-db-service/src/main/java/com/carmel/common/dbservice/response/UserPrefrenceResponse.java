package com.carmel.common.dbservice.response;

import com.carmel.common.dbservice.model.UserPreference;

import java.util.List;

public class UserPrefrenceResponse {
    private List<UserPreference> userPreferenceList;
    private UserPreference userPreference;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public List<UserPreference> getUserPreferenceList() {
        return userPreferenceList;
    }

    public void setUserPreferenceList(List<UserPreference> userPreferenceList) {
        this.userPreferenceList = userPreferenceList;
    }

    public UserPreference getUserPreference() {
        return userPreference;
    }

    public void setUserPreference(UserPreference userPreference) {
        this.userPreference = userPreference;
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
