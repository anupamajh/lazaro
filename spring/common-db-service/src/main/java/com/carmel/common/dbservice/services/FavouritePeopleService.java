package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.FavouritePeople;

import java.util.List;
import java.util.Optional;

public interface FavouritePeopleService {
    Optional<FavouritePeople> findByUserIdAndOtherUserId(String userId, String otherUserId);

    FavouritePeople save(FavouritePeople favouritePeople);

    List<FavouritePeople> findByUserIdAndIsFavourite(String userId, int isFavourite);
}
