package com.carmel.common.dbservice.repository.Instagram;

import com.carmel.common.dbservice.model.Instagram.Instagram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstagramRepository extends JpaRepository<Instagram, String> {
    Optional<Instagram> findByInstagramId(String instagramId);
}
