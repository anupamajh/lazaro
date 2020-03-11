package com.carmel.guesture.crmdashboard.response;



import com.carmel.guesture.crmdashboard.models.CRMTasks;

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
