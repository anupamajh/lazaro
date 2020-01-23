package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.UserInterests;

import java.util.List;
import java.util.Optional;

public interface UserInterestsService {
    Optional<UserInterests> findByInterestIdAndUserId(String interestId, String id);

    UserInterests save(UserInterests userInterests);

    List<UserInterests> findByUserId(String id);
}
