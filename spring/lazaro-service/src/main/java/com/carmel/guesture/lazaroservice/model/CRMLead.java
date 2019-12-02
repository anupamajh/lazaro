package com.carmel.guesture.lazaroservice.model;

import java.util.UUID;

public class CRMLead {
    private String type;
    private String id;
    private CRMLeadAttributes attributes;

    public CRMLead() {
    }

    public CRMLead(Person person, CRMUser crmUser) {
        this.id = UUID.randomUUID().toString();
        this.type = "Lead";
        this.attributes = new CRMLeadAttributes(person, crmUser);

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

    public CRMLeadAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(CRMLeadAttributes attributes) {
        this.attributes = attributes;
    }
}
