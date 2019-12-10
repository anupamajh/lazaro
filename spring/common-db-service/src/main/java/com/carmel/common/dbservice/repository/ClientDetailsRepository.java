package com.carmel.common.dbservice.repository;

import com.carmel.common.dbservice.model.Client;
import com.carmel.common.dbservice.model.ClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientDetailsRepository extends JpaRepository<ClientDetails, String> {

    List<ClientDetails> findAllByEmail(String email);

    List<ClientDetails> findAllByEmailAndClientIsNot(String email, Client client);
}
