package com.carmel.guestjini.Networking.KnowledgeBase;

import java.util.List;

public class KBRatingResponse {
    private KBRating kbRating;
    private List<KBRating> kbRatingList;
    private int totalPages;
    private int totalRecords;
    private int currentRecords;
    private Boolean success;
    private String error;

    public KBRating getKbRating() {
        return kbRating;
    }

    public void setKbRating(KBRating kbRating) {
        this.kbRating = kbRating;
    }

    public List<KBRating> getKbRatingList() {
        return kbRatingList;
    }

    public void setKbRatingList(List<KBRating> kbRatingList) {
        this.kbRatingList = kbRatingList;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getCurrentRecords() {
        return currentRecords;
    }

    public void setCurrentRecords(int currentRecords) {
        this.currentRecords = currentRecords;
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
