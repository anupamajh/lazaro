package com.carmel.common.dbservice.Base.Album.Responce;

import com.carmel.common.dbservice.Base.Album.Model.Album;
import com.carmel.common.dbservice.Base.Album.DTO.AlbumDTO;

import java.util.ArrayList;
import java.util.List;

public class AlbumResponse {

    private AlbumDTO album;
    private List<AlbumDTO> albumList;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public AlbumDTO getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = new AlbumDTO(album);
    }

    public List<AlbumDTO> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = new ArrayList<>();
        albumList.forEach(album1 -> {
            this.albumList.add(new AlbumDTO(album1));
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
