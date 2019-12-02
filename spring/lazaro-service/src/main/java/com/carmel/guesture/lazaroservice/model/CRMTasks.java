package com.carmel.guesture.lazaroservice.model;

import java.util.UUID;

public class CRMTasks {

    private String type;
    private String id;
    private CRMTasksAttributes attributes;

    public CRMTasks(Phoned phoned, CRMLead crmLead) {
        this.id = UUID.randomUUID().toString();
        this.type = "Task";
        this.attributes = new CRMTasksAttributes(phoned, crmLead);
    }

    public CRMTasks(Website website, CRMLead crmLead) {
        this.id = UUID.randomUUID().toString();
        this.type = "Task";
        this.attributes = new CRMTasksAttributes(website, crmLead);
    }

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
