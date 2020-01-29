package com.carmel.common.dbservice.repository;

import com.carmel.common.dbservice.model.FavouritePeople;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavouritePeopleRepository extends JpaRepository<FavouritePeople, String> {
    Optional<FavouritePeople> findByUserIdAndOtherUserId(String userId, String otherUserId);
}
