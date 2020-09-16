package com.carmel.common.dbservice.Base.ApplicationConstant.Response;

import com.carmel.common.dbservice.Base.ApplicationConstant.Model.ApplicationConstant;
import com.carmel.common.dbservice.Base.ApplicationConstant.DTO.ApplicationConstantDTO;

import java.util.ArrayList;
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

    public void setApplicationConstant(ApplicationConstant applicationConstant) {
        this.applicationConstant = new ApplicationConstantDTO(applicationConstant);
    }

    public List<ApplicationConstantDTO> getApplicationConstantList() {
        return applicationConstantList;
    }

    public void setApplicationConstantList(List<ApplicationConstant> applicationConstantList) {
        this.applicationConstantList = new ArrayList<>();
        applicationConstantList.forEach(applicationConstant1 -> {
            this.applicationConstantList.add(new ApplicationConstantDTO(applicationConstant1));
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
