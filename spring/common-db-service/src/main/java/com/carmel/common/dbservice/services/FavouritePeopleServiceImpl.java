package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.FavouritePeople;
import com.carmel.common.dbservice.repository.FavouritePeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavouritePeopleServiceImpl implements FavouritePeopleService {
    @Autowired
    FavouritePeopleRepository favouritePeopleRepository;

    @Override
    public Optional<FavouritePeople> findByUserIdAndOtherUserId(String userId, String otherUserId) {
        return favouritePeopleRepository.findByUserIdAndOtherUserId(userId, otherUserId);
    }

    @Override
    public FavouritePeople save(FavouritePeople favouritePeople) {
        return favouritePeopleRepository.save(favouritePeople);
    }

    @Override
    public List<FavouritePeople> findByUserIdAndIsFavourite(String userId, int isFavourite) {
        return favouritePeopleRepository.findByUserIdAndIsFavourite(userId, isFavourite);
    }
}
