package com.carmel.guestjini.Networking;

public class GenericResponse {
    private Boolean success;
    private String error;

    public GenericResponse() {
        this.error = "";
        this.success = false;
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
