package com.carmel.common.dbservice.repository.Facebook;

import com.carmel.common.dbservice.model.Facebook.Facebook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacebookRepository extends JpaRepository<Facebook, String> {
    Optional<Facebook> findByTitleURL(String facebookTitle);
}
