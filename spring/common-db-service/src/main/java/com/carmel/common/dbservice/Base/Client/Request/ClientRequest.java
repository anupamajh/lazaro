package com.carmel.common.dbservice.Base.Client.Request;

import com.carmel.common.dbservice.Base.Client.DTO.ClientDTO;
import com.carmel.common.dbservice.Base.Client.Model.Client;

public class ClientRequest {
    private Client client;
    private String superAdminUserName;
    private String superAdminPassword;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getSuperAdminUserName() {
        return superAdminUserName;
    }

    public void setSuperAdminUserName(String superAdminUserName) {
        this.superAdminUserName = superAdminUserName;
    }

    public String getSuperAdminPassword() {
        return superAdminPassword;
    }

    public void setSuperAdminPassword(String superAdminPassword) {
        this.superAdminPassword = superAdminPassword;
    }
}
