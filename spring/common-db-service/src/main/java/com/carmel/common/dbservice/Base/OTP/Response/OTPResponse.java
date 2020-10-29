package com.carmel.common.dbservice.Base.OTP.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OTPResponse {
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Details")
    private String Details;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}
