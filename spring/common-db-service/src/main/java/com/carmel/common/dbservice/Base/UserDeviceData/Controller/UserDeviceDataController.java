package com.carmel.common.dbservice.Base.UserDeviceData.Controller;

import com.carmel.common.dbservice.Base.Group.Controller.GroupController;
import com.carmel.common.dbservice.Base.UserDeviceData.Model.UserDeviceData;
import com.carmel.common.dbservice.Base.UserDeviceData.Response.UserDeviceDataResponse;
import com.carmel.common.dbservice.Base.UserDeviceData.Service.UserDeviceDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user-device-data")
public class UserDeviceDataController {
    Logger logger = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    UserDeviceDataService userDeviceDataService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public UserDeviceDataResponse save(@RequestBody UserDeviceData userDeviceData) {
        logger.trace("Entering");
        UserDeviceDataResponse userDeviceDataResponse = new UserDeviceDataResponse();
        try {
            userDeviceDataResponse = userDeviceDataService
                    .saveUserDeviceData(userDeviceData);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            userDeviceDataResponse.setSuccess(true);
            userDeviceDataResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return userDeviceDataResponse;
    }


}
