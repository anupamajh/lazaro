package com.carmel.guesture.lazaroservice.repository;

import com.carmel.guesture.lazaroservice.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent, String> {
    List<Agent> findAllByNameAndPhoneNumber(String agentName, String phoneNumber);
}
