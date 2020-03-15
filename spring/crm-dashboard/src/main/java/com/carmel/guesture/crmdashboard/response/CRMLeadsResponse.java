package com.carmel.guesture.crmdashboard.response;



import com.carmel.guesture.crmdashboard.models.CRMLead;

import java.util.List;

public class CRMLeadsResponse {

    private boolean success;
    private long totalRecords;
    private long totalPages;

    private List<CRMLead> data;


    public List<CRMLead> getData() {
        return data;
    }

    public void setData(List<CRMLead> data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }
}
