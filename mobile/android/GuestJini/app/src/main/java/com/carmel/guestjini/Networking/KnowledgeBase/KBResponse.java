package com.carmel.guestjini.Networking.KnowledgeBase;

import java.util.List;

public class KBResponse {
    private KB kb;
    private List<KB> kbList;
    private int totalPages;
    private int totalRecords;
    private int currentRecords;
    private Boolean success;
    private String error;

    public KB getKb() {
        return kb;
    }

    public void setKb(KB kb) {
        this.kb = kb;
    }

    public List<KB> getKbList() {
        return kbList;
    }

    public void setKbList(List<KB> kbList) {
        this.kbList = kbList;
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
