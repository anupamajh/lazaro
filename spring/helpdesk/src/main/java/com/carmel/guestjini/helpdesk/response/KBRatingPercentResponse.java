package com.carmel.guestjini.helpdesk.response;

public class KBRatingPercentResponse {
    private double likedPercent;
    private double disLikedPercent;
    private int likedCount;
    private int disLikedCount;
    private boolean success;
    private String error;

    public double getLikedPercent() {
        return likedPercent;
    }

    public void setLikedPercent(double likedPercent) {
        this.likedPercent = likedPercent;
    }

    public double getDisLikedPercent() {
        return disLikedPercent;
    }

    public void setDisLikedPercent(double disLikedPercent) {
        this.disLikedPercent = disLikedPercent;
    }

    public int getLikedCount() {
        return likedCount;
    }

    public void setLikedCount(int likedCount) {
        this.likedCount = likedCount;
    }

    public int getDisLikedCount() {
        return disLikedCount;
    }

    public void setDisLikedCount(int disLikedCount) {
        this.disLikedCount = disLikedCount;
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
