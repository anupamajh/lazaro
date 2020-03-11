package com.carmel.guesture.crmdashboard.response;



import com.carmel.guesture.crmdashboard.models.CRMLead;

import java.util.List;

public class CRMLeadsResponse {

    private boolean success;
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
}
