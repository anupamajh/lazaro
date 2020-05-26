package com.carmel.guestjini.Networking.Users;

import java.util.List;

public class UserInterestsResponse {
    private UserInterests userInterests;
    private List<UserInterests> userInterestsList;
    private int totalPages;
    private int totalRecords;
    private int currentRecords;
    private boolean success;
    private String error;

    public UserInterests getUserInterests() {
        return userInterests;
    }

    public void setUserInterests(UserInterests userInterests) {
        this.userInterests = userInterests;
    }

    public List<UserInterests> getUserInterestsList() {
        return userInterestsList;
    }

    public void setUserInterestsList(List<UserInterests> userInterestsList) {
        this.userInterestsList = userInterestsList;
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
