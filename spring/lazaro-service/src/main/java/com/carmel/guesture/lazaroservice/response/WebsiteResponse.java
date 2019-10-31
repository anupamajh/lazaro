package com.carmel.guesture.lazaroservice.response;

import com.carmel.guesture.lazaroservice.model.Website;

public class WebsiteResponse {
    
    private Website website;
    private Boolean success;
    private String error;

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
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
