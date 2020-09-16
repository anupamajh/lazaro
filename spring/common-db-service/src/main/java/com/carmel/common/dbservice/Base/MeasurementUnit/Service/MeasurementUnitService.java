package com.carmel.common.dbservice.Base.MeasurementUnit.Service;

import com.carmel.common.dbservice.Base.MeasurementUnit.Model.MeasurementUnit;
import com.carmel.common.dbservice.Base.MeasurementUnit.Service.Responce.MeasurementUnitResponse;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MeasurementUnitService {
    MeasurementUnit save(MeasurementUnit measurementUnit);

    Optional<MeasurementUnit> findById(String id) throws Exception;

    MeasurementUnitResponse saveMeasurementUnit(MeasurementUnit measurementUnit) throws Exception;

    MeasurementUnitResponse moveToTrash(Map<String, String> formData) throws Exception;

    MeasurementUnitResponse get(Map<String, String> formData) throws Exception;

    MeasurementUnitResponse getDeleted() throws Exception;

    MeasurementUnitResponse getAll() throws Exception;

    MeasurementUnitResponse getPaginated(Map<String, String> formData) throws Exception;

    MeasurementUnitResponse searchPaginated(Map<String, String> formData) throws Exception;

    MeasurementUnitResponse search(SearchRequest searchRequest) throws Exception;

    MeasurementUnitResponse findAllByIsDeletedAndClientId(int i, String clientId) throws Exception;

    MeasurementUnitResponse  findAllByClientIdAndIsDeleted(String clientId, int i, Pageable pageable) throws Exception;

    MeasurementUnitResponse  findAll(Specification<MeasurementUnit> textInAllColumns, Pageable pageable) throws Exception;

    MeasurementUnitResponse  findAllByClientIdAndTitle(String clientId, String title) throws Exception;

    MeasurementUnitResponse  findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id) throws Exception;
}
