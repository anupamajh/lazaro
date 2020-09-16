package com.carmel.common.dbservice.Base.ApplicationConstant.Repository;

import com.carmel.common.dbservice.Base.ApplicationConstant.Model.ApplicationConstant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationConstantRepository extends JpaRepository<ApplicationConstant, String> {
    Optional<ApplicationConstant> findByKey(String key);
}
