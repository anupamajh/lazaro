package com.carmel.common.dbservice.response;

import com.carmel.common.dbservice.model.DTO.FavouritePeopleDTO;
import com.carmel.common.dbservice.model.FavouritePeople;

import java.util.ArrayList;
import java.util.List;

public class FavouritePeopleResponse {
    private FavouritePeopleDTO favouritePeople;
    private List<FavouritePeopleDTO> favouritePeopleList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public FavouritePeopleDTO getFavouritePeople() {
        return favouritePeople;
    }

    public void setFavouritePeople(FavouritePeople favouritePeople) {
        this.favouritePeople = new FavouritePeopleDTO(favouritePeople);
    }

    public List<FavouritePeopleDTO> getFavouritePeopleList() {
        return favouritePeopleList;
    }

    public void setFavouritePeopleList(List<FavouritePeople> favouritePeopleList) {
        this.favouritePeopleList = new ArrayList<>();
        favouritePeopleList.forEach(favouritePeople1 -> {
            this.favouritePeopleList.add(new FavouritePeopleDTO(favouritePeople1));
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
