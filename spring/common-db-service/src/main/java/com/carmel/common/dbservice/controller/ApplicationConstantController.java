package com.carmel.common.dbservice.controller;

import com.carmel.common.dbservice.model.ApplicationConstant;
import com.carmel.common.dbservice.response.ApplicationConstantResponse;
import com.carmel.common.dbservice.services.ApplicationConstantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/application-constant")
public class ApplicationConstantController {
    Logger logger = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    ApplicationConstantService applicationConstantService;

    @RequestMapping(value = "/get-by-key", method = RequestMethod.POST)
    public ApplicationConstantResponse getValueByKey(@RequestBody Map<String, String> formData) {
        ApplicationConstantResponse applicationConstantResponse =
                new ApplicationConstantResponse();
        try {
            String key = formData.get("key");
            Optional<ApplicationConstant> optionalApplicationConstant = applicationConstantService.findByKey(key);
            if(optionalApplicationConstant.isPresent()){
                applicationConstantResponse
                        .setApplicationConstant(optionalApplicationConstant.get());
                applicationConstantResponse.setSuccess(true);
            }
        } catch (Exception ex) {
            applicationConstantResponse.setSuccess(false);
            applicationConstantResponse.setError(ex.getMessage());
        }
        return applicationConstantResponse;
    }


}
