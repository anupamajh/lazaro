package com.carmel.common.dbservice.repository;

import com.carmel.common.dbservice.model.ApplicationConstant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationConstantRepository extends JpaRepository<ApplicationConstant, String> {
    Optional<ApplicationConstant> findByKey(String key);
}
