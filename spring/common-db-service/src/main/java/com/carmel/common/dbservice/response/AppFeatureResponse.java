package com.carmel.common.dbservice.response;

import com.carmel.common.dbservice.model.AppFeatures;

public class AppFeatureResponse {

    private AppFeatures appFeatures;
    private boolean success;
    private String error;

    public AppFeatures getAppFeatures() {
        return appFeatures;
    }

    public void setAppFeatures(AppFeatures appFeatures) {
        this.appFeatures = appFeatures;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
