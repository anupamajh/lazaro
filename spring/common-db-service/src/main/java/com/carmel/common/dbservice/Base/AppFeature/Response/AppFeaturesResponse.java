package com.carmel.common.dbservice.Base.AppFeature.Response;

import com.carmel.common.dbservice.Base.AppFeature.Model.AppFeatures;
import com.carmel.common.dbservice.Base.AppFeature.DTO.AppFeatureTreeDTO;
import com.carmel.common.dbservice.Base.AppFeature.DTO.AppFeaturesDTO;

import java.util.ArrayList;
import java.util.List;

public class AppFeaturesResponse {
    private AppFeaturesDTO appFeatures;
    private List<AppFeaturesDTO> appFeaturesList;
    private List<AppFeatureTreeDTO> treeData;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public AppFeaturesDTO getAppFeatures() {
        return appFeatures;
    }

    public void setAppFeatures(AppFeatures appFeatures) {
        this.appFeatures = new AppFeaturesDTO(appFeatures);
    }

    public List<AppFeaturesDTO> getAppFeaturesList() {
        return appFeaturesList;
    }

    public void setAppFeaturesList(List<AppFeatures> appFeaturesList) {
        this.appFeaturesList = new ArrayList<>();
        appFeaturesList.forEach(appFeatures1 -> {
            this.appFeaturesList.add(new AppFeaturesDTO(appFeatures1));
        });
    }

    public void setSimpleAppFeaturesList(List<AppFeatures> appFeaturesList) {
        this.appFeaturesList = new ArrayList<>();
        appFeaturesList.forEach(appFeatures1 -> {
            this.appFeaturesList.add(new AppFeaturesDTO(appFeatures1, true));
        });
    }

    public List<AppFeatureTreeDTO> getTreeData() {
        return treeData;
    }

    public void setTreeData(List<AppFeatureTreeDTO> treeData) {
        this.treeData = treeData;
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
