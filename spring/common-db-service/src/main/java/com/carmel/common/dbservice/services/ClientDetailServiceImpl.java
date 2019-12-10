package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.Client;
import com.carmel.common.dbservice.model.ClientDetails;
import com.carmel.common.dbservice.repository.ClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientDetailServiceImpl implements ClientDetailService {

    @Autowired
    ClientDetailsRepository clientDetailsRepository;

    @Override
    public List<ClientDetails> findAllByEmail(String email) {
        return clientDetailsRepository.findAllByEmail(email);
    }

    @Override
    public List<ClientDetails> findAllByEmailAndClientIsNot(String email, Client client) {
        return clientDetailsRepository.findAllByEmailAndClientIsNot(email, client);
    }


}
