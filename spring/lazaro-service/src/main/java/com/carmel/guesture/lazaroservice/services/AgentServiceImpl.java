package com.carmel.guesture.lazaroservice.services;

import com.carmel.guesture.lazaroservice.model.Agent;
import com.carmel.guesture.lazaroservice.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    AgentRepository agentRepository;

    @Override
    public Agent save(Agent agent) {
        return agentRepository.save(agent);
    }

    @Override
    public List<Agent> findAllByNameAndPhoneNumber(String agentName, String phoneNumber) {
        return agentRepository.findAllByNameAndPhoneNumber(agentName, phoneNumber);
    }
}
