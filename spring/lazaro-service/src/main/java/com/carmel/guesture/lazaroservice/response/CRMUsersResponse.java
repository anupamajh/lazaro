package com.carmel.guesture.lazaroservice.response;

import com.carmel.guesture.lazaroservice.model.CRMUser;

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
