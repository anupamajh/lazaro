package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> findAll();

    Client save(Client client);

    Optional<Client> findById(String clientId);
}
