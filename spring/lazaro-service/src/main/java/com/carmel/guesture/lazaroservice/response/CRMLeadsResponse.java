package com.carmel.guesture.lazaroservice.response;

import com.carmel.guesture.lazaroservice.model.CRMLead;

import java.util.List;

public class CRMLeadsResponse {

    private List<CRMLead> data;

    public List<CRMLead> getData() {
        return data;
    }

    public void setData(List<CRMLead> data) {
        this.data = data;
    }
}
