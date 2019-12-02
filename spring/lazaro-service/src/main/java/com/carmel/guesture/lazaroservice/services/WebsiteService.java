package com.carmel.guesture.lazaroservice.services;

import com.carmel.guesture.lazaroservice.model.Website;

import java.util.List;

public interface WebsiteService {
    Website save(Website website);

    List<Website> findAllBySyncStatusIsNot(int isSynced);
}
