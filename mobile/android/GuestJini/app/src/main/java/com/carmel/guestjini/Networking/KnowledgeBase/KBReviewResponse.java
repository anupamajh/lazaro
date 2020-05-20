package com.carmel.guestjini.Networking.KnowledgeBase;

import java.util.List;

public class KBReviewResponse {
    private KBReview kbReview;
    private List<KBReview> kbReviewList;
    private int totalPages;
    private int totalRecords;
    private int currentRecords;
    private boolean success;
    private String error;

    public KBReview getKbReview() {
        return kbReview;
    }

    public void setKbReview(KBReview kbReview) {
        this.kbReview = kbReview;
    }

    public List<KBReview> getKbReviewList() {
        return kbReviewList;
    }

    public void setKbReviewList(List<KBReview> kbReviewList) {
        this.kbReviewList = kbReviewList;
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
