package com.carmel.guesture.lazaroservice.repository;

import com.carmel.guesture.lazaroservice.model.Website;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebsiteRepository  extends JpaRepository<Website, String> {
    List<Website> findAllByIsSyncedIsNot(int isSynced);
}
