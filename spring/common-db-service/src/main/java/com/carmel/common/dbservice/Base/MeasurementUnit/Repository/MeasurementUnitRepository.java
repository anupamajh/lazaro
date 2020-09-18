package com.carmel.common.dbservice.Base.MeasurementUnit.Repository;

import com.carmel.common.dbservice.Base.MeasurementUnit.Model.MeasurementUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasurementUnitRepository extends JpaRepository<MeasurementUnit, String> {
    List<MeasurementUnit> findAllByIsDeletedAndClientId(int isDeleted, String clientId);

    Page<MeasurementUnit> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<MeasurementUnit> findAll(Specification<MeasurementUnit> textInAllColumns, Pageable pageable);

    List<MeasurementUnit> findAllByClientIdAndTitleAndIsDeleted(String clientId, String title, int i);

    List<MeasurementUnit> findAllByClientIdAndTitleAndIdIsNotAndIsDeleted(String clientId, String title, String id, int i);

    List<MeasurementUnit> findAllByClientIdAndTitle(String clientId, String title);

    List<MeasurementUnit>  findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id);
}
