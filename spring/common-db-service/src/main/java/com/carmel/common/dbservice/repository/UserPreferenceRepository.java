package com.carmel.common.dbservice.repository;

import com.carmel.common.dbservice.model.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, String> {
    Optional<UserPreference> findByUserIdAndPreferenceType(String id, int preferenceType);

    List<UserPreference> findAllByUserId(String userId);
}
