package com.carmel.guesture.lazaroservice.services;

import com.carmel.guesture.lazaroservice.model.Phoned;
import com.carmel.guesture.lazaroservice.repository.PhonedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhonedServiceImpl implements PhonedService {

    @Autowired
    PhonedRepository phonedRepository;

    @Override
    public Phoned save(Phoned phoned) {
        return phonedRepository.save(phoned);
    }

    @Override
    public List<Phoned> findAllBySyncStatusIsNot(int isSynced) {
        return phonedRepository.findAllByIsSyncedIsNot(isSynced);
    }
}
