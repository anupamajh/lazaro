package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.ApplicationConstant;

import java.util.Optional;

public interface ApplicationConstantService {
    Optional<ApplicationConstant> findByKey(String key);
}
