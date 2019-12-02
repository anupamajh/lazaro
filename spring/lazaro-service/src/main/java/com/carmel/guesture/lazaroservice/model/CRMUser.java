package com.carmel.guesture.lazaroservice.model;

public class CRMUser {
    private String type;
    private String id;
    private CRMUserAttributes attributes;

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

    public CRMUserAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(CRMUserAttributes attributes) {
        this.attributes = attributes;
    }
}
