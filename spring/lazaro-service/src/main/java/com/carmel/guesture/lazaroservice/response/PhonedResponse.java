package com.carmel.guesture.lazaroservice.response;

import com.carmel.guesture.lazaroservice.model.Phoned;

public class PhonedResponse {

    private Phoned phoned;
    private Boolean success;
    private String error;

    public Phoned getPhoned() {
        return phoned;
    }

    public void setPhoned(Phoned phoned) {
        this.phoned = phoned;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
