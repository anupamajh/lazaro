package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.Client;
import com.carmel.common.dbservice.model.ClientDetails;

import java.util.List;

public interface ClientDetailService {

    List<ClientDetails> findAllByEmail(String email);

    List<ClientDetails> findAllByEmailAndClientIsNot(String email, Client client);
}
