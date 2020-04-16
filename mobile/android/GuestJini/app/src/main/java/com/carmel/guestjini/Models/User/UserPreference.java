package com.carmel.guestjini.Models.User;

public class UserPreference {
    private String id;
    private String userId;
    private int preferenceType;
    private int isHidden;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getPreferenceType() {
        return preferenceType;
    }

    public void setPreferenceType(int preferenceType) {
        this.preferenceType = preferenceType;
    }

    public int getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(int isHidden) {
        this.isHidden = isHidden;
    }
}
