package com.carmel.common.dbservice.Base.ApplicationConstant.Service;

import com.carmel.common.dbservice.Base.ApplicationConstant.Model.ApplicationConstant;
import com.carmel.common.dbservice.Base.ApplicationConstant.Repository.ApplicationConstantRepository;
import com.carmel.common.dbservice.Base.ApplicationConstant.Response.ApplicationConstantResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ApplicationConstantServiceImpl implements ApplicationConstantService {

    private Logger logger = LoggerFactory.getLogger(ApplicationConstantServiceImpl.class);

    @Autowired
    ApplicationConstantRepository applicationConstantRepository;

    @Override
    public Optional<ApplicationConstant> findByKey(String key) {
        return applicationConstantRepository.findByKey(key);
    }

    @Override
    public ApplicationConstantResponse getValueByKey(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        ApplicationConstantResponse applicationConstantResponse =
                new ApplicationConstantResponse();
        try {
            String key = formData.get("key");
            Optional<ApplicationConstant> optionalApplicationConstant = applicationConstantRepository.findByKey(key);
            if (optionalApplicationConstant.isPresent()) {
                applicationConstantResponse
                        .setApplicationConstant(optionalApplicationConstant.get());
                applicationConstantResponse.setSuccess(true);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return applicationConstantResponse;
    }
}
