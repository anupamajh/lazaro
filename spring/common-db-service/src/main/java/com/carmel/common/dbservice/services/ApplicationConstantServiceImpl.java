package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.ApplicationConstant;
import com.carmel.common.dbservice.repository.ApplicationConstantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationConstantServiceImpl implements ApplicationConstantService {

    @Autowired
    ApplicationConstantRepository applicationConstantRepository;

    @Override
    public Optional<ApplicationConstant> findByKey(String key) {
        return applicationConstantRepository.findByKey(key);
    }
}
