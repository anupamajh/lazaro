package com.carmel.guestjini.service.model.DTO.HelpDesk;

public class TicketCountDTO {
    private Integer activeTicketCount;
    private Integer draftTicketCount;
    private Integer archiveTicketCount;
    private boolean success;
    private String error;

    public TicketCountDTO() {
        this.activeTicketCount = 0;
        this.draftTicketCount = 0;
        this.archiveTicketCount = 0;
    }

    public Integer getActiveTicketCount() {
        return activeTicketCount;
    }

    public void setActiveTicketCount(Integer activeTicketCount) {
        this.activeTicketCount = activeTicketCount;
    }

    public Integer getDraftTicketCount() {
        return draftTicketCount;
    }

    public void setDraftTicketCount(Integer draftTicketCount) {
        this.draftTicketCount = draftTicketCount;
    }

    public Integer getArchiveTicketCount() {
        return archiveTicketCount;
    }

    public void setArchiveTicketCount(Integer archiveTicketCount) {
        this.archiveTicketCount = archiveTicketCount;
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
