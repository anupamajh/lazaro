package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.UserPreference;
import com.carmel.common.dbservice.repository.UserPreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPreferenceServiceImpl implements UserPreferenceService {

    @Autowired
    UserPreferenceRepository userPreferenceRepository;

    @Override
    public Optional<UserPreference> findByUserIdAndPreferenceType(String id, int preferenceType) {
        return userPreferenceRepository.findByUserIdAndPreferenceType(id, preferenceType);
    }

    @Override
    public UserPreference save(UserPreference userPreference) {
        return userPreferenceRepository.save(userPreference);
    }

    @Override
    public List<UserPreference> findAllByUserId(String userId) {
        return userPreferenceRepository.findAllByUserId(userId);
    }
}
