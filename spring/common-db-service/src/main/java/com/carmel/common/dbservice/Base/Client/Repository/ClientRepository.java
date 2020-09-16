package com.carmel.common.dbservice.Base.Client.Repository;

import com.carmel.common.dbservice.Base.Client.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {


}
