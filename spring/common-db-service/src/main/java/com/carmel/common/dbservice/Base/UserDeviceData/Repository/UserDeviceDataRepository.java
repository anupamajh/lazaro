package com.carmel.common.dbservice.Base.UserDeviceData.Repository;

import com.carmel.common.dbservice.Base.UserDeviceData.Model.UserDeviceData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDeviceDataRepository extends JpaRepository<UserDeviceData, String> {
    Optional<UserDeviceData> findByDeviceIdentifier(String deviceIdentifier);
}
