package com.carmel.common.dbservice.Base.InterestCategory.Responce;

import com.carmel.common.dbservice.Base.InterestCategory.DTO.InterestCategoryDTO;
import com.carmel.common.dbservice.Base.InterestCategory.Model.InterestCategory;

import java.util.ArrayList;
import java.util.List;

public class InterestCategoryResponse {
    private InterestCategoryDTO interestCategory;
    private List<InterestCategoryDTO> interestCategoryList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public InterestCategoryDTO getInterestCategory() {
        return interestCategory;
    }

    public void setInterestCategory(InterestCategory interestCategory) {
        this.interestCategory = new InterestCategoryDTO(interestCategory);
    }

    public List<InterestCategoryDTO> getInterestCategoryList() {
        return interestCategoryList;
    }

    public void setInterestCategoryList(List<InterestCategory> interestCategoryList) {
        this.interestCategoryList = new ArrayList<>();
        interestCategoryList.forEach(interestCategory1 -> {
            this.interestCategoryList.add(new InterestCategoryDTO(interestCategory1));
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
