package com.carmel.common.dbservice.response;

import com.carmel.common.dbservice.model.AppFeatures;

import java.util.List;

public class AppFeaturesResponse {
    private List<AppFeatures> appFeaturesList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public List<AppFeatures> getAppFeaturesList() {
        return appFeaturesList;
    }

    public void setAppFeaturesList(List<AppFeatures> appFeaturesList) {
        this.appFeaturesList = appFeaturesList;
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
