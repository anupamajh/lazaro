package com.carmel.guesture.lazaroservice.services;

import com.carmel.guesture.lazaroservice.model.Website;
import com.carmel.guesture.lazaroservice.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsiteServiceImpl implements WebsiteService{

    @Autowired
    WebsiteRepository websiteRepository;

    @Override
    public Website save(Website website) {
        return websiteRepository.save(website);
    }

    @Override
    public List<Website> findAllBySyncStatusIsNot(int isSynced) {
        return websiteRepository.findAllByIsSyncedIsNot(isSynced);
    }
}
