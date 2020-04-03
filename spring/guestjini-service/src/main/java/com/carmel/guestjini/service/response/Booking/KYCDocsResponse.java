package com.carmel.guestjini.service.response.Booking;


import com.carmel.guestjini.service.model.Booking.KYCDocs;
import com.carmel.guestjini.service.model.DTO.Booking.KYCDocsDTO;

import java.util.ArrayList;
import java.util.List;

public class KYCDocsResponse {
    private KYCDocsDTO kycDocs;
    private List<KYCDocsDTO> kycDocsList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public KYCDocsDTO getKycDocs() {
        return kycDocs;
    }

    public void setKycDocs(KYCDocs kycDocs) {
        this.kycDocs = new KYCDocsDTO(kycDocs);
    }

    public List<KYCDocsDTO> getKycDocsList() {
        return kycDocsList;
    }

    public void setKycDocsList(List<KYCDocs> kycDocsList) {
        this.kycDocsList =  new ArrayList<>();
        kycDocsList.forEach(kycDocs1 -> {
            this.kycDocsList.add(new KYCDocsDTO(kycDocs1));
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
