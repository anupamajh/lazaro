package com.carmel.common.dbservice.Base.UserInterests.Responce;

import com.carmel.common.dbservice.Base.UserInterests.DTO.UserInterestsDTO;
import com.carmel.common.dbservice.Base.UserInterests.Model.UserInterests;

import java.util.ArrayList;
import java.util.List;

public class UserInterestsResponse {
    private UserInterestsDTO userInterests;
    private List<UserInterestsDTO> userInterestsList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public UserInterestsDTO getUserInterests() {
        return userInterests;
    }

    public void setUserInterests(UserInterests userInterests) {
        this.userInterests = new UserInterestsDTO(userInterests);
    }

    public List<UserInterestsDTO> getUserInterestsList() {
        return userInterestsList;
    }

    public void setUserInterestsList(List<UserInterests> userInterestsList) {
        this.userInterestsList = new ArrayList<>();
        userInterestsList.forEach(userInterests1 -> {
            this.userInterestsList.add(new UserInterestsDTO(userInterests1));
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
