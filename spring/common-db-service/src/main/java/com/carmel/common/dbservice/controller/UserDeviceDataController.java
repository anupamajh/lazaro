package com.carmel.common.dbservice.controller;

import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.UserDeviceData;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.response.UserDeviceDataResponse;
import com.carmel.common.dbservice.services.UserDeviceDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user-device-data")
public class UserDeviceDataController {
    Logger logger = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    UserDeviceDataService userDeviceDataService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public UserDeviceDataResponse save(@RequestBody UserDeviceData userDeviceData) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        UserDeviceDataResponse userDeviceDataResponse = new UserDeviceDataResponse();
        try {
            if (userDeviceData.getId() == null) {
                userDeviceData.setId("");
            }
            if (userDeviceData.getId().equals("")) {
                userDeviceData.setCreatedBy(userInfo.getId());
                userDeviceData.setCreationTime(new Date());
            } else {
                userDeviceData.setLastModifiedBy(userInfo.getId());
                userDeviceData.setLastModifiedTime(new Date());
            }
            userDeviceData.setUserId(userInfo.getId());
            userDeviceData.setFullName(userInfo.getFullName());
            userDeviceData.setUserName(userInfo.getUserName());
            Optional<UserDeviceData> optionalUserDeviceData = userDeviceDataService.findByDeviceIdentifier(userDeviceData.getDeviceIdentifier());
            optionalUserDeviceData.ifPresent(deviceData -> userDeviceData.setId(deviceData.getId()));
            userDeviceDataResponse.setUserDeviceData(userDeviceDataService.save(userDeviceData));
            userDeviceDataResponse.setSuccess(true);
            userDeviceDataResponse.setError("");
        } catch (
                Exception ex) {
            logger.error(ex.getMessage(), ex);
            userDeviceDataResponse.setSuccess(false);
            userDeviceDataResponse.setError(ex.getMessage());
        }
        return userDeviceDataResponse;
    }


}
