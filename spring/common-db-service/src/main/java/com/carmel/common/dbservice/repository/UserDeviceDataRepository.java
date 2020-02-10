package com.carmel.common.dbservice.repository;

import com.carmel.common.dbservice.model.UserDeviceData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDeviceDataRepository extends JpaRepository<UserDeviceData, String> {
    Optional<UserDeviceData> findByDeviceIdentifier(String deviceIdentifier);
}
