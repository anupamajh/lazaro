package com.carmel.guestjini.booking.response;

import com.carmel.guestjini.booking.model.DTO.User;

public class UserResponse {
    private User user;
    private boolean success;
    private String error;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
