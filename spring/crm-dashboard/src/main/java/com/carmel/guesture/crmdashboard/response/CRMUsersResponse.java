package com.carmel.guesture.crmdashboard.response;



import com.carmel.guesture.crmdashboard.models.CRMUser;

import java.util.List;

public class CRMUsersResponse {

    private List<CRMUser> data;

    public List<CRMUser> getData() {
        return data;
    }

    public void setData(List<CRMUser> data) {
        this.data = data;
    }
}
