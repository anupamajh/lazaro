package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.UserDeviceData;

import java.util.List;
import java.util.Optional;

public interface UserDeviceDataService {
    UserDeviceData save(UserDeviceData userDeviceData);

    Optional<UserDeviceData> findByDeviceIdentifier(String deviceIdentifier);
}
