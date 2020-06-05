package com.carmel.guestjini.Networking.Users;

import java.util.List;

public class InterestCategoryResponse {
    private InterestCategory interestCategory;
    private List<InterestCategory> interestCategoryList;
    private int totalPages;
    private int totalRecords;
    private int currentRecords;
    private boolean success;
    private String error;

    public InterestCategory getInterestCategory() {
        return interestCategory;
    }

    public void setInterestCategory(InterestCategory interestCategory) {
        this.interestCategory = interestCategory;
    }

    public List<InterestCategory> getInterestCategoryList() {
        return interestCategoryList;
    }

    public void setInterestCategoryList(List<InterestCategory> interestCategoryList) {
        this.interestCategoryList = interestCategoryList;
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
