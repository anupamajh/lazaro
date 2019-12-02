package com.carmel.guesture.lazaroservice.services;

import com.carmel.guesture.lazaroservice.model.Phoned;

import java.util.List;

public interface PhonedService {

    Phoned save(Phoned phoned);

    List<Phoned> findAllBySyncStatusIsNot(int isSynced);
}
