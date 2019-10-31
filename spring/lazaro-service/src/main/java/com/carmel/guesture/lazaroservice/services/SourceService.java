package com.carmel.guesture.lazaroservice.services;

import com.carmel.guesture.lazaroservice.model.Source;

import java.util.List;

public interface SourceService {
    Source save(Source source);

    List<Source> findAllByMediumAndPoint(String medium, String point);
}
