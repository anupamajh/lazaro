package com.carmel.common.dbservice.Base.ApplicationConstant.DTO;

import com.carmel.common.dbservice.Base.ApplicationConstant.Model.ApplicationConstant;

public class ApplicationConstantDTO {
    private String id;
    private String clientId;
    private String key;
    private String value;

    public ApplicationConstantDTO() {
    }

    public ApplicationConstantDTO(ApplicationConstant applicationConstant) {
        this.id = applicationConstant.getId();
        this.clientId = applicationConstant.getClientId();
        this.key = applicationConstant.getKey();
        this.value = applicationConstant.getValue();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
