package com.carmel.common.dbservice.Base.UserPreference.Service;

import com.carmel.common.dbservice.Base.UserPreference.Model.UserPreference;
import com.carmel.common.dbservice.Base.UserPreference.Response.UserPrefrenceResponse;

import java.util.List;
import java.util.Optional;

public interface UserPreferenceService {

    UserPreference save(UserPreference userPreference) throws Exception;

    Optional<UserPreference> findByUserIdAndPreferenceType(String id, int preferenceType) throws Exception;

    UserPrefrenceResponse saveUserPreference(UserPreference userPreference) throws Exception;

    UserPrefrenceResponse getAll() throws Exception;

    List<UserPreference> findAllByUserId(String id) throws Exception;
}
