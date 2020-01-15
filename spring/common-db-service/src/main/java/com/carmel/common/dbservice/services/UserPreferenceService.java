package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.UserPreference;

import java.util.List;
import java.util.Optional;

public interface UserPreferenceService {
    
    Optional<UserPreference> findByUserIdAndPreferenceType(String id, int preferenceType);

    UserPreference save(UserPreference userPreference);

    List<UserPreference> findAllByUserId(String id);
}
