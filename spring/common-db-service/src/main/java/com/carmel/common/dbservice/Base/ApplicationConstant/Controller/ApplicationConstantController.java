package com.carmel.common.dbservice.Base.ApplicationConstant.Controller;

import com.carmel.common.dbservice.Base.ApplicationConstant.Response.ApplicationConstantResponse;
import com.carmel.common.dbservice.Base.ApplicationConstant.Service.ApplicationConstantService;
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
    Logger logger = LoggerFactory.getLogger(ApplicationConstantController.class);

    @Autowired
    ApplicationConstantService applicationConstantService;

    @RequestMapping(value = "/get-by-key", method = RequestMethod.POST)
    public ApplicationConstantResponse getValueByKey(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        ApplicationConstantResponse applicationConstantResponse = new ApplicationConstantResponse();
        try {
            applicationConstantResponse = applicationConstantService
                    .getValueByKey(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            applicationConstantResponse.setSuccess(true);
            applicationConstantResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return applicationConstantResponse;
    }


}
