package com.carmel.common.dbservice.response;

import com.carmel.common.dbservice.model.Client;
import com.carmel.common.dbservice.model.DTO.ClientDTO;

public class ClientResponse {

    private ClientDTO client;
    private boolean success;
    private String error;

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
}
