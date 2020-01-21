package com.carmel.guestjini.helpdesk.response;

import com.carmel.guestjini.helpdesk.model.DTO.KBDocDTO;
import com.carmel.guestjini.helpdesk.model.KBDoc;

import java.util.ArrayList;
import java.util.List;

public class KBDocResponse {
    private KBDocDTO kbDoc;
    private List<KBDocDTO> kbDocList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public KBDocDTO getKbDoc() {
        return kbDoc;
    }

    public void setKbDoc(KBDoc kbDoc) {
        this.kbDoc = new KBDocDTO(kbDoc);
    }

    public List<KBDocDTO> getKbDocList() {
        return kbDocList;
    }

    public void setKbDocList(List<KBDoc> kbDocList) {
        this.kbDocList = new ArrayList<>();
        kbDocList.forEach(kbDoc1 -> {
            this.kbDocList.add(new KBDocDTO(kbDoc1));
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
