package com.carmel.guesture.lazaroservice.repository;

import com.carmel.guesture.lazaroservice.model.Phoned;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhonedRepository extends JpaRepository<Phoned, String> {
    List<Phoned> findAllByIsSyncedIsNot(int isSynced);
}
