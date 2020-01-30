package com.carmel.common.dbservice.repository;

import com.carmel.common.dbservice.model.FavouritePeople;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavouritePeopleRepository extends JpaRepository<FavouritePeople, String> {
    Optional<FavouritePeople> findByUserIdAndOtherUserId(String userId, String otherUserId);

    List<FavouritePeople> findByUserId(String id);

    List<FavouritePeople> findByUserIdAndIsFavourite(String userId, int isFavourite);
}
