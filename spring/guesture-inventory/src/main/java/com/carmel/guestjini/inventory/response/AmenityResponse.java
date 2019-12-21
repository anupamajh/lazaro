package com.carmel.guestjini.inventory.response;

import com.carmel.guestjini.inventory.components.PhotoInformation;
import com.carmel.guestjini.inventory.model.Amenity;
import com.carmel.guestjini.inventory.model.DTO.AmenityDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AmenityResponse {

    public static PhotoInformation photoInformation;

    private AmenityDTO amenity;
    private List<AmenityDTO> amenityList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public AmenityDTO getAmenity() {
        return amenity;
    }

    public void setAmenity(Amenity amenity) {
        this.amenity = new AmenityDTO(amenity);
    }

    public List<AmenityDTO> getAmenityList() {
        return amenityList;
    }

    public void setAmenityList(List<Amenity> amenityList) {
        AmenityDTO.photoInformation = photoInformation;
        this.amenityList = new ArrayList<>();
        amenityList.forEach(amenity1 -> {
            this.amenityList.add(new AmenityDTO(amenity1));
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
