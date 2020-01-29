package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.FavouritePeople;

import java.util.Optional;

public interface FavouritePeopleService {
    Optional<FavouritePeople> findByUserIdAndOtherUserId(String userId, String otherUserId);

    FavouritePeople save(FavouritePeople favouritePeople);
}
