package com.carmel.common.dbservice.Base.UserInterests.Controller;

import com.carmel.common.dbservice.Base.UserInterests.Model.UserInterests;
import com.carmel.common.dbservice.Base.UserInterests.Responce.UserInterestsResponse;
import com.carmel.common.dbservice.Base.UserInterests.Service.UserInterestsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user-interests")
public class UserInterestsController {

    Logger logger = LoggerFactory.getLogger(UserInterestsController.class);

    @Autowired
    UserInterestsService userInterestsService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public UserInterestsResponse save(@Valid @RequestBody UserInterests userInterests) {
        logger.trace("Entering");
        UserInterestsResponse userInterestsResponse = new UserInterestsResponse();
        try {
            userInterestsResponse = userInterestsService.saveUserInterests(userInterests);
        } catch (Exception ex) {
            userInterestsResponse.setSuccess(true);
            userInterestsResponse.setError(ex.getMessage());
        }
        return userInterestsResponse;
    }

    @RequestMapping(value = "/get-user-interests", method = RequestMethod.POST)
    public UserInterestsResponse getUserInterests() {
        logger.trace("Entering");
        UserInterestsResponse userInterestsResponse = new UserInterestsResponse();
        try {
            userInterestsResponse = userInterestsService.getUserInterests();
        } catch (Exception ex) {
            userInterestsResponse.setSuccess(true);
            userInterestsResponse.setError(ex.getMessage());
        }
        return userInterestsResponse;

    }


}
