package com.carmel.common.dbservice.response;

import com.carmel.common.dbservice.model.Client;
import com.carmel.common.dbservice.model.DTO.ClientDTO;

import java.util.ArrayList;
import java.util.List;

public class ClientsResponse {
    private List<ClientDTO> clientList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public List<ClientDTO> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = new ArrayList<>();
        clientList.forEach(client -> {
            this.clientList.add(new ClientDTO(client));
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
