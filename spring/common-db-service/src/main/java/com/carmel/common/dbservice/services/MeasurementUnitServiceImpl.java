package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.MeasurementUnit;
import com.carmel.common.dbservice.repository.MeasurementUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementUnitServiceImpl implements MeasurementUnitService {
    @Autowired
    MeasurementUnitRepository measurementUnitRepository;

    @Override
    public MeasurementUnit save(MeasurementUnit measurementUnit) {
        return measurementUnitRepository.save(measurementUnit);
    }

    @Override
    public Optional<MeasurementUnit> findById(String id) {
        return measurementUnitRepository.findById(id);
    }

    @Override
    public List<MeasurementUnit> findAllByIsDeletedAndClientId(int isDeleted, String clientId) {
        return measurementUnitRepository.findAllByIsDeletedAndClientId(isDeleted, clientId);
    }

    @Override
    public Page<MeasurementUnit> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return measurementUnitRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted, pageable);
    }

    @Override
    public Page<MeasurementUnit> findAll(Specification<MeasurementUnit> textInAllColumns, Pageable pageable) {
        return measurementUnitRepository.findAll(textInAllColumns, pageable);
    }

    @Override
    public List<MeasurementUnit> findAllByClientIdAndTitle(String clientId, String title) {
        return measurementUnitRepository.findAllByClientIdAndTitleAndIsDeleted(clientId, title, 0);
    }

    @Override
    public List<MeasurementUnit> findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id) {
        return measurementUnitRepository.findAllByClientIdAndTitleAndIdIsNotAndIsDeleted(clientId, title, id, 0);
    }
}
