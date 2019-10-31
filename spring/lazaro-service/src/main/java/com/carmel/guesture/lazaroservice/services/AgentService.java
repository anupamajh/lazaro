package com.carmel.guesture.lazaroservice.services;

import com.carmel.guesture.lazaroservice.model.Agent;

import java.util.List;

public interface AgentService {
    Agent save(Agent agent);

    List<Agent> findAllByNameAndPhoneNumber(String agentName, String phoneNumber);

}
