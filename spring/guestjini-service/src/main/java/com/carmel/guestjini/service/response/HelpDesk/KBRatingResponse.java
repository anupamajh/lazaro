package com.carmel.guestjini.service.response.HelpDesk;



import com.carmel.guestjini.service.model.DTO.HelpDesk.KBRatingDTO;
import com.carmel.guestjini.service.model.HelpDesk.KBRating;

import java.util.ArrayList;
import java.util.List;

public class KBRatingResponse {
    private KBRatingDTO kbRating;
    private List<KBRatingDTO> kbRatingList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public KBRatingDTO getKbRating() {
        return kbRating;
    }

    public void setKbRating(KBRating kbRating) {
        this.kbRating = new KBRatingDTO(kbRating);
    }

    public List<KBRatingDTO> getKbRatingList() {
        return kbRatingList;
    }

    public void setKbRatingList(List<KBRating> kbRatingList) {
        this.kbRatingList = new ArrayList<>();
        kbRatingList.forEach(kbRating1 -> {
            this.kbRatingList.add(new KBRatingDTO(kbRating1));
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
