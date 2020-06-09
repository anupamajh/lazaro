package com.carmel.common.dbservice.repository.Twitter;

import com.carmel.common.dbservice.model.Twitter.Twitter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TwitterRepository extends JpaRepository<Twitter, String> {
    Optional<Twitter> findByTwitterId(String twitterId);
}
