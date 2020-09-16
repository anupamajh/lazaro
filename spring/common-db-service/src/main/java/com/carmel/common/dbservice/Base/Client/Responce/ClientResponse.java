package com.carmel.common.dbservice.Base.Client.Responce;

import com.carmel.common.dbservice.Base.Client.Model.Client;
import com.carmel.common.dbservice.Base.Client.DTO.ClientDTO;

import java.util.ArrayList;
import java.util.List;

public class ClientResponse {

    private ClientDTO client;
    private boolean success;
    private String error;
    private List<ClientDTO> clientList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = new ClientDTO(client);
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

    public void setClient(ClientDTO client) {
        this.client = client;
    }

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
}
