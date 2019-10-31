package com.carmel.guesture.lazaroservice.services;

import com.carmel.guesture.lazaroservice.model.Source;
import com.carmel.guesture.lazaroservice.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceServiceImpl implements SourceService {

    @Autowired
    SourceRepository sourceRepository;

    @Override
    public Source save(Source source) {
        return sourceRepository.save(source);
    }

    @Override
    public List<Source> findAllByMediumAndPoint(String medium, String point) {
        return sourceRepository.findAllByMediumAndPoint(medium, point);
    }
}
