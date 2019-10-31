package com.carmel.guesture.lazaroservice.services;

import com.carmel.guesture.lazaroservice.model.Phoned;
import com.carmel.guesture.lazaroservice.repository.PhonedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhonedServiceImpl implements PhonedService {

    @Autowired
    PhonedRepository phonedRepository;

    @Override
    public Phoned save(Phoned phoned) {
        return phonedRepository.save(phoned);
    }
}
