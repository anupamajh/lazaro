package com.carmel.guestjini.inventory.response;

import com.carmel.guestjini.inventory.model.DocumentTemplate;

import java.util.List;

public class DocumentTemplateResponse {
    private DocumentTemplate documentTemplate;
    private List<DocumentTemplate> documentTemplateList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public DocumentTemplate getDocumentTemplate() {
        return documentTemplate;
    }

    public void setDocumentTemplate(DocumentTemplate documentTemplate) {
        this.documentTemplate = documentTemplate;
    }

    public List<DocumentTemplate> getDocumentTemplateList() {
        return documentTemplateList;
    }

    public void setDocumentTemplateList(List<DocumentTemplate> documentTemplateList) {
        this.documentTemplateList = documentTemplateList;
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
