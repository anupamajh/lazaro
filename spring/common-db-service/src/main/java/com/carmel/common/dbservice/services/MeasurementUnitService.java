package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.MeasurementUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface MeasurementUnitService {
    MeasurementUnit save(MeasurementUnit measurementUnit);

    Optional<MeasurementUnit> findById(String id);

    List<MeasurementUnit> findAllByIsDeletedAndClientId(int i, String clientId);

    Page<MeasurementUnit> findAllByClientIdAndIsDeleted(String clientId, int i, Pageable pageable);

    Page<MeasurementUnit> findAll(Specification<MeasurementUnit> textInAllColumns, Pageable pageable);

    List<MeasurementUnit> findAllByClientIdAndTitle(String clientId, String title);

    List<MeasurementUnit> findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id);
}
