package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.UserDeviceData;
import com.carmel.common.dbservice.repository.UserDeviceDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDeviceDataServiceImpl implements UserDeviceDataService {

    @Autowired
    UserDeviceDataRepository userDeviceDataRepository;

    @Override
    public UserDeviceData save(UserDeviceData userDeviceData) {
        return userDeviceDataRepository.save(userDeviceData);
    }

    @Override
    public Optional<UserDeviceData> findByDeviceIdentifier(String deviceIdentifier) {
        return userDeviceDataRepository.findByDeviceIdentifier(deviceIdentifier);
    }
}
