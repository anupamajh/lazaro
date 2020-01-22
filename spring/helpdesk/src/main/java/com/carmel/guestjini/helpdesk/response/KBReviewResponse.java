package com.carmel.guestjini.helpdesk.response;

import com.carmel.guestjini.helpdesk.model.DTO.KBReviewDTO;
import com.carmel.guestjini.helpdesk.model.KBReview;

import java.util.ArrayList;
import java.util.List;

public class KBReviewResponse {

    private KBReviewDTO kbReview;
    private List<KBReviewDTO> kbReviewList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public KBReviewDTO getKbReview() {
        return kbReview;
    }

    public void setKbReview(KBReview kbReview) {
        this.kbReview = new KBReviewDTO(kbReview);
    }

    public List<KBReviewDTO> getKbReviewList() {
        return kbReviewList;
    }

    public void setKbReviewList(List<KBReview> kbReviewList) {
        this.kbReviewList = new ArrayList<>();
        kbReviewList.forEach(kbReview1 -> {
            this.kbReviewList.add(new KBReviewDTO(kbReview1));
        });
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public long getCurrentRecords() {
        return currentRecords;
    }

    public void setCurrentRecords(long currentRecords) {
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
