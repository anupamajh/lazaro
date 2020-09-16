package com.carmel.common.dbservice.Base.Album.DTO;

import com.carmel.common.dbservice.Base.Album.Model.Album;
import com.carmel.common.dbservice.Base.Photo.DTO.PhotoDTO;
import com.carmel.common.dbservice.Base.Photo.Model.Photo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlbumDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String title;
    private String narration;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;
    private List<PhotoDTO> photos;

    public AlbumDTO() {
    }

    public AlbumDTO(Album album) {
        this.id = album.getId();
        this.clientId = album.getClientId();
        this.orgId = album.getOrgId();
        this.title = album.getTitle();
        this.narration = album.getNarration();
        this.createdBy = album.getCreatedBy();
        this.creationTime = album.getCreationTime();
        this.lastModifiedBy = album.getLastModifiedBy();
        this.lastModifiedTime = album.getLastModifiedTime();
        this.isDeleted = album.getIsDeleted();
        this.deletedBy = album.getDeletedBy();
        this.deletedTime = album.getDeletedTime();
        this.photos = new ArrayList<>();
        if (album.getPhotos() != null) {
            album.getPhotos().forEach(photo -> {
                this.photos.add(PhotoDTO.getSimple(photo));
            });
        }

    }

    public static AlbumDTO getSimple(Album album) {
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.id = album.getId();
        albumDTO.clientId = album.getClientId();
        albumDTO.orgId = album.getOrgId();
        albumDTO.title = album.getTitle();
        albumDTO.narration = album.getNarration();
        albumDTO.createdBy = album.getCreatedBy();
        albumDTO.creationTime = album.getCreationTime();
        albumDTO.lastModifiedBy = album.getLastModifiedBy();
        albumDTO.lastModifiedTime = album.getLastModifiedTime();
        albumDTO.isDeleted = album.getIsDeleted();
        albumDTO.deletedBy = album.getDeletedBy();
        albumDTO.deletedTime = album.getDeletedTime();
        return albumDTO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(Date deletedTime) {
        this.deletedTime = deletedTime;
    }

    public List<PhotoDTO> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = new ArrayList<>();

        photos.forEach(photo -> {
            this.photos.add(new PhotoDTO(photo));
        });
    }
}
