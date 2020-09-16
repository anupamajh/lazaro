package com.carmel.common.dbservice.Base.UserDeviceData.Service;

import com.carmel.common.dbservice.Base.UserDeviceData.Model.UserDeviceData;
import com.carmel.common.dbservice.Base.UserDeviceData.Response.UserDeviceDataResponse;

import java.util.Optional;

public interface UserDeviceDataService {
    UserDeviceData save(UserDeviceData userDeviceData) throws Exception;

    Optional<UserDeviceData> findByDeviceIdentifier(String deviceIdentifier) throws Exception;

    UserDeviceDataResponse saveUserDeviceData(UserDeviceData userDeviceData) throws Exception;
}
