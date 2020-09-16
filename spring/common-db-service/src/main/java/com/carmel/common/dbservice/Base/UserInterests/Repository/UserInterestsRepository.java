package com.carmel.common.dbservice.Base.UserInterests.Repository;

import com.carmel.common.dbservice.Base.UserInterests.Model.UserInterests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserInterestsRepository extends JpaRepository<UserInterests, String> {
    Optional<UserInterests> findByInterestIdAndUserId(String interestId, String id);

    List<UserInterests> findByUserId(String id);
}
