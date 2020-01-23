package com.carmel.common.dbservice.response;

import com.carmel.common.dbservice.model.DTO.InterestDTO;
import com.carmel.common.dbservice.model.Interest;

import java.util.ArrayList;
import java.util.List;

public class InterestResponse {
    private InterestDTO interest;
    private List<InterestDTO> interestList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public InterestDTO getInterest() {
        return interest;
    }

    public void setInterest(Interest interest) {
        this.interest = new InterestDTO(interest);
    }

    public List<InterestDTO> getInterestList() {
        return interestList;
    }

    public void setInterestList(List<Interest> interestList) {
        this.interestList = new ArrayList<>();
        interestList.forEach(interest1 -> {
            this.interestList.add(new InterestDTO(interest1));
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
