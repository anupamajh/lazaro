package com.carmel.common.dbservice.Base.Client.Service;

import com.carmel.common.dbservice.Base.Client.Model.Client;
import com.carmel.common.dbservice.Base.Client.Responce.ClientResponse;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> findAll() throws Exception;

    Client save(Client client)  throws Exception;

    Optional<Client> findById(String clientId) throws Exception;

    ClientResponse register(Client client) throws Exception;

    ClientResponse getAll() throws Exception;
}
