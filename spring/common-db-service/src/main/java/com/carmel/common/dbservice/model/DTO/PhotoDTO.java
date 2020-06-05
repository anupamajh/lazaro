package com.carmel.common.dbservice.model.DTO;

import com.carmel.common.dbservice.model.Album;
import com.carmel.common.dbservice.model.Photo;

import java.util.Date;

public class PhotoDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String type;
    private String path;
    private String name;
    private int error;
    private int size;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;
    private AlbumDTO album;

    public PhotoDTO() {
    }

    public PhotoDTO(Photo photo) {
        this.id = photo.getId();
        this.clientId = photo.getClientId();
        this.orgId = photo.getOrgId();
        this.type = photo.getType();
        this.path = photo.getPath();
        this.name = photo.getName();
        this.error = photo.getError();
        this.size = photo.getSize();
        this.createdBy = photo.getCreatedBy();
        this.creationTime = photo.getCreationTime();
        this.lastModifiedBy = photo.getLastModifiedBy();
        this.lastModifiedTime = photo.getLastModifiedTime();
        this.isDeleted = photo.getIsDeleted();
        this.deletedBy = photo.getDeletedBy();
        this.deletedTime = photo.getDeletedTime();
        this.album = AlbumDTO.getSimple(photo.getAlbum());
    }

    public static PhotoDTO getSimple(Photo photo) {
        PhotoDTO photoDTO = new PhotoDTO(photo);
        return photoDTO;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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

    public AlbumDTO getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = AlbumDTO.getSimple(album);
    }
}
