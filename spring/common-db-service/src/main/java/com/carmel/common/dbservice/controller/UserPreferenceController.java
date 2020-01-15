package com.carmel.common.dbservice.controller;

import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.model.UserPreference;
import com.carmel.common.dbservice.response.AccountHeadResponse;
import com.carmel.common.dbservice.response.UserPrefrenceResponse;
import com.carmel.common.dbservice.services.UserPreferenceService;
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
    UserInformation userInformation;

    @Autowired
    UserPreferenceService userPreferenceService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public UserPrefrenceResponse save(@Valid @RequestBody UserPreference userPreference) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        UserPrefrenceResponse userPrefrenceResponse = new UserPrefrenceResponse();
        try{
            if(userPreference.getId() == null){
                userPreference.setId("");
            }
            logger.trace("Data:{}", objectMapper.writeValueAsString(userInfo));

            Optional<UserPreference> optionalUserPreference = userPreferenceService.findByUserIdAndPreferenceType(userInfo.getId(), userPreference.getPreferenceType());
            UserPreference tempUserPreference = userPreference;

            if(optionalUserPreference.isPresent()){
                tempUserPreference = optionalUserPreference.get();
                logger.trace("Data:{}", objectMapper.writeValueAsString(tempUserPreference));

                tempUserPreference.setPreferenceType(userPreference.getPreferenceType());
                tempUserPreference.setIsHidden(userPreference.getIsHidden());
            }else{
                tempUserPreference.setUserId(userInfo.getId());
                tempUserPreference.setPreferenceType(userPreference.getPreferenceType());
                tempUserPreference.setIsHidden(userPreference.getIsHidden());

            }
            userPrefrenceResponse.setUserPreference(userPreferenceService.save(tempUserPreference));
            userPrefrenceResponse.setSuccess(true);
            userPrefrenceResponse.setError("");
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            userPrefrenceResponse.setSuccess(false);
            userPrefrenceResponse.setError(ex.getMessage());
        }

        return userPrefrenceResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public UserPrefrenceResponse getAll() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        UserPrefrenceResponse userPrefrenceResponse = new UserPrefrenceResponse();
        try {
            userPrefrenceResponse.setUserPreferenceList(userPreferenceService.findAllByUserId(userInfo.getId()));
            userPrefrenceResponse.setSuccess(true);
            userPrefrenceResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            userPrefrenceResponse.setSuccess(true);
            userPrefrenceResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return userPrefrenceResponse;
    }

}
