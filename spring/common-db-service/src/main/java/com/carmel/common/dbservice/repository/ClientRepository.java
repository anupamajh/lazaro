package com.carmel.common.dbservice.repository;

import com.carmel.common.dbservice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {


}
