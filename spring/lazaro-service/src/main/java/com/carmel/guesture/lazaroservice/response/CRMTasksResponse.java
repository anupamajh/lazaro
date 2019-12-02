package com.carmel.guesture.lazaroservice.response;

import com.carmel.guesture.lazaroservice.model.CRMTasks;

import java.util.List;

public class CRMTasksResponse {

    List<CRMTasks> data;

    public List<CRMTasks> getData() {
        return data;
    }

    public void setData(List<CRMTasks> data) {
        this.data = data;
    }
}
