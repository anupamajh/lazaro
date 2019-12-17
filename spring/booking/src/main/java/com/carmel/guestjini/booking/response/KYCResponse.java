package com.carmel.guestjini.booking.response;

import com.carmel.guestjini.booking.model.DTO.KYCDTO;
import com.carmel.guestjini.booking.model.KYC;

import java.util.ArrayList;
import java.util.List;

public class KYCResponse {

    private KYCDTO kyc;
    private List<KYCDTO> kycList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public KYCDTO getKyc() {
        return kyc;
    }

    public void setKyc(KYC kyc) {
        this.kyc = new KYCDTO(kyc);
    }

    public List<KYCDTO> getKycList() {
        return kycList;
    }

    public void setKycList(List<KYC> kycList) {
        this.kycList = new ArrayList<>();
        kycList.forEach(kyc1 -> {
            this.kycList.add(new KYCDTO(kyc1));
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
