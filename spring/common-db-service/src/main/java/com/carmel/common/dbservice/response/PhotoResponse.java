package com.carmel.common.dbservice.response;

import com.carmel.common.dbservice.model.DTO.AlbumDTO;
import com.carmel.common.dbservice.model.DTO.PhotoDTO;
import com.carmel.common.dbservice.model.Photo;

import java.util.ArrayList;
import java.util.List;

public class PhotoResponse {
    private PhotoDTO photo;
    private List<PhotoDTO> photoList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public PhotoDTO getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = new PhotoDTO(photo);
    }

    public List<PhotoDTO> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = new ArrayList<>();
        photoList.forEach(photo1 -> {
            this.photoList.add(new PhotoDTO(photo1));
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
