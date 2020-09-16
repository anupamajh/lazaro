package com.carmel.common.dbservice.Base.UserDeviceData.Service;

import com.carmel.common.dbservice.Base.UserDeviceData.Model.UserDeviceData;
import com.carmel.common.dbservice.Base.UserDeviceData.Repository.UserDeviceDataRepository;
import com.carmel.common.dbservice.Base.UserDeviceData.Response.UserDeviceDataResponse;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserDeviceDataServiceImpl implements UserDeviceDataService {

    private Logger logger = LoggerFactory.getLogger(UserDeviceDataServiceImpl.class);

    @Autowired
    UserDeviceDataRepository userDeviceDataRepository;

    @Autowired
    UserInformation userInformation;

    @Override
    public UserDeviceData save(UserDeviceData userDeviceData) {
        return userDeviceDataRepository.save(userDeviceData);
    }

    @Override
    public Optional<UserDeviceData> findByDeviceIdentifier(String deviceIdentifier) {
        return userDeviceDataRepository.findByDeviceIdentifier(deviceIdentifier);
    }

    @Override
    public UserDeviceDataResponse saveUserDeviceData(UserDeviceData userDeviceData) throws Exception {
        UserInfo userInfo = null;
        try {
            userInfo = userInformation.getUserInfo();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
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
            Optional<UserDeviceData> optionalUserDeviceData = this.findByDeviceIdentifier(userDeviceData.getDeviceIdentifier());
            optionalUserDeviceData.ifPresent(deviceData -> userDeviceData.setId(deviceData.getId()));
            userDeviceDataResponse.setUserDeviceData(this.save(userDeviceData));
            userDeviceDataResponse.setSuccess(true);
            userDeviceDataResponse.setError("");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        return userDeviceDataResponse;
    }
}
