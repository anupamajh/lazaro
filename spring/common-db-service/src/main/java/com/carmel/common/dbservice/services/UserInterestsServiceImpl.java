package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.UserInterests;
import com.carmel.common.dbservice.repository.UserInterestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInterestsServiceImpl implements UserInterestsService {

    @Autowired
    UserInterestsRepository userInterestsRepository;

    @Override
    public Optional<UserInterests> findByInterestIdAndUserId(String interestId, String id) {
        return userInterestsRepository.findByInterestIdAndUserId(interestId, id);
    }

    @Override
    public UserInterests save(UserInterests userInterests) {
        return userInterestsRepository.save(userInterests);
    }

    @Override
    public List<UserInterests> findByUserId(String id) {
        return userInterestsRepository.findByUserId(id);
    }
}
