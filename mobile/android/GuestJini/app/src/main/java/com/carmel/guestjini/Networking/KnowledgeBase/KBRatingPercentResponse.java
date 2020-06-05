package com.carmel.guestjini.Networking.KnowledgeBase;

public class KBRatingPercentResponse {
    public Double likedPercent;
    public Double disLikedPercent;
    public Integer likedCount;
    public Integer disLikedCount;
    private Boolean success;
    private String error;

    public Double getLikedPercent() {
        return likedPercent;
    }

    public void setLikedPercent(Double likedPercent) {
        this.likedPercent = likedPercent;
    }

    public Double getDisLikedPercent() {
        return disLikedPercent;
    }

    public void setDisLikedPercent(Double disLikedPercent) {
        this.disLikedPercent = disLikedPercent;
    }

    public Integer getLikedCount() {
        return likedCount;
    }

    public void setLikedCount(Integer likedCount) {
        this.likedCount = likedCount;
    }

    public Integer getDisLikedCount() {
        return disLikedCount;
    }

    public void setDisLikedCount(Integer disLikedCount) {
        this.disLikedCount = disLikedCount;
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
