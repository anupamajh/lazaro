package com.carmel.guestjini.service.response.HelpDesk;



import com.carmel.guestjini.service.model.DTO.HelpDesk.KBDTO;
import com.carmel.guestjini.service.model.HelpDesk.KB;

import java.util.ArrayList;
import java.util.List;

public class KBResponse {
    private KBDTO kb;
    private List<KBDTO> kbList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public KBDTO getKb() {
        return kb;
    }

    public void setKb(KB kb) {
        this.kb = new KBDTO(kb);
    }

    public List<KBDTO> getKbList() {
        return kbList;
    }

    public void setKbList(List<KB> kbList) {
        this.kbList = new ArrayList<>();
        kbList.forEach(kb1 -> {
            this.kbList.add(new KBDTO(kb1));
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
