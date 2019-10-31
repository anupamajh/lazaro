package com.carmel.guesture.lazaroservice.repository;

import com.carmel.guesture.lazaroservice.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SourceRepository extends JpaRepository<Source, String> {
    List<Source> findAllByMediumAndPoint(String medium, String point);
}
