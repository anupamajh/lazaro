package com.carmel.guestjini.service.response.Common;


import com.carmel.guestjini.service.model.DTO.Common.ApplicationConstantDTO;

import java.util.List;

public class ApplicationConstantResponse {
    private ApplicationConstantDTO applicationConstant;
    private List<ApplicationConstantDTO> applicationConstantList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public ApplicationConstantDTO getApplicationConstant() {
        return applicationConstant;
    }

    public void setApplicationConstant(ApplicationConstantDTO applicationConstant) {
        this.applicationConstant = applicationConstant;
    }

    public List<ApplicationConstantDTO> getApplicationConstantList() {
        return applicationConstantList;
    }

    public void setApplicationConstantList(List<ApplicationConstantDTO> applicationConstantList) {
        this.applicationConstantList = applicationConstantList;
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
