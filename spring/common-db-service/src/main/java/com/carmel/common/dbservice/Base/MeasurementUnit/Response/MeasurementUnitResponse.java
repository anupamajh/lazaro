package com.carmel.common.dbservice.Base.MeasurementUnit.Response;

import com.carmel.common.dbservice.Base.MeasurementUnit.DTO.MeasurementUnitDTO;
import com.carmel.common.dbservice.Base.MeasurementUnit.Model.MeasurementUnit;

import java.util.ArrayList;
import java.util.List;

public class MeasurementUnitResponse {
    private MeasurementUnitDTO measurementUnit;
    private List<MeasurementUnitDTO> measurementUnitList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public MeasurementUnitDTO getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        this.measurementUnit = new MeasurementUnitDTO(measurementUnit);
    }

    public List<MeasurementUnitDTO> getMeasurementUnitList() {
        return measurementUnitList;
    }

    public void setMeasurementUnitList(List<MeasurementUnit> measurementUnitList) {
        this.measurementUnitList = new ArrayList<>();
        measurementUnitList.forEach(measurementUnit1 -> {
            this.measurementUnitList.add(new MeasurementUnitDTO(measurementUnit1));
        });
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public long getCurrentRecords() {
        return currentRecords;
    }

    public void setCurrentRecords(long currentRecords) {
        this.currentRecords = currentRecords;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
