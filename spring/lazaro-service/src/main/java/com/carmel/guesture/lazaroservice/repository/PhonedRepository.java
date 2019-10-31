package com.carmel.guesture.lazaroservice.repository;

import com.carmel.guesture.lazaroservice.model.Phoned;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhonedRepository extends JpaRepository<Phoned, String> {
}
