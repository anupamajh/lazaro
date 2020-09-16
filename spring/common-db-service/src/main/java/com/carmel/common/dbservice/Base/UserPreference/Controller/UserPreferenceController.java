package com.carmel.common.dbservice.Base.UserPreference.Controller;

import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.Base.UserPreference.Model.UserPreference;
import com.carmel.common.dbservice.Base.UserPreference.Response.UserPrefrenceResponse;
import com.carmel.common.dbservice.Base.UserPreference.Service.UserPreferenceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user-preference")
public class UserPreferenceController {

    Logger logger = LoggerFactory.getLogger(UserPreferenceController.class);

    @Autowired
    UserPreferenceService userPreferenceService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public UserPrefrenceResponse save(@Valid @RequestBody UserPreference userPreference) {
        logger.trace("Entering");
        UserPrefrenceResponse userPrefrenceResponse = new UserPrefrenceResponse();
        try {
            userPrefrenceResponse = userPreferenceService
                    .saveUserPreference(userPreference);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            userPrefrenceResponse.setSuccess(true);
            userPrefrenceResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return userPrefrenceResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public UserPrefrenceResponse getAll() {
        logger.trace("Entering");
        UserPrefrenceResponse userPrefrenceResponse = new UserPrefrenceResponse();
        try {
            userPrefrenceResponse = userPreferenceService
                    .getAll();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            userPrefrenceResponse.setSuccess(true);
            userPrefrenceResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return userPrefrenceResponse;
    }

}
