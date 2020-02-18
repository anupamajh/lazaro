package com.carmel.common.dbservice.response;

import com.carmel.common.dbservice.model.DTO.UserDeviceDataDTO;
import com.carmel.common.dbservice.model.UserDeviceData;

import java.util.ArrayList;
import java.util.List;

public class UserDeviceDataResponse {

    private UserDeviceDataDTO userDeviceData;
    private List<UserDeviceDataDTO> userDeviceDataList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public UserDeviceDataDTO getUserDeviceData() {
        return userDeviceData;
    }

    public void setUserDeviceData(UserDeviceData userDeviceData) {
        this.userDeviceData = new UserDeviceDataDTO(userDeviceData);
    }

    public List<UserDeviceDataDTO> getUserDeviceDataList() {
        return userDeviceDataList;
    }

    public void setUserDeviceDataList(List<UserDeviceData> userDeviceDataList) {
        this.userDeviceDataList = new ArrayList<>();
        userDeviceDataList.forEach(userDeviceData1 -> {
            this.userDeviceDataList.add(new UserDeviceDataDTO(userDeviceData1));
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
