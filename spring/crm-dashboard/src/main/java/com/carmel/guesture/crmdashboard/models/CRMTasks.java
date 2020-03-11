package com.carmel.guesture.crmdashboard.models;

import java.util.UUID;

public class CRMTasks {

    private String type;
    private String id;
    private CRMTasksAttributes attributes;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CRMTasksAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(CRMTasksAttributes attributes) {
        this.attributes = attributes;
    }
}
