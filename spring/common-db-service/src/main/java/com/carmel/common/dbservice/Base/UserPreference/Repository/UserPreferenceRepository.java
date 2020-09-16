package com.carmel.common.dbservice.Base.UserPreference.Repository;

import com.carmel.common.dbservice.Base.UserPreference.Model.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, String> {
    Optional<UserPreference> findByUserIdAndPreferenceType(String id, int preferenceType);

    List<UserPreference> findAllByUserId(String userId);
}
