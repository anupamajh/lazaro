package com.carmel.guesture.lazaroservice.repository;

import com.carmel.guesture.lazaroservice.model.Website;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebsiteRepository  extends JpaRepository<Website, String> {
}
