package com.carmel.common.dbservice.Base.UserInterests.Service;

import com.carmel.common.dbservice.Base.UserInterests.Model.UserInterests;
import com.carmel.common.dbservice.Base.UserInterests.Responce.UserInterestsResponse;

import java.util.Optional;

public interface UserInterestsService {
    Optional<UserInterests> findByInterestIdAndUserId(String interestId, String id);

    UserInterests save(UserInterests userInterests) throws Exception;

    UserInterestsResponse saveUserInterests(UserInterests userInterests) throws Exception;

    UserInterestsResponse getUserInterests() throws Exception;

    UserInterestsResponse findByUserId(String id) throws Exception;

    UserInterestsResponse findAll() throws Exception;
}
