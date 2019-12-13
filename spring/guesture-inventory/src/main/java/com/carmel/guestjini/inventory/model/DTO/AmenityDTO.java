package com.carmel.guestjini.inventory.model.DTO;

import com.carmel.guestjini.inventory.components.PhotoInformation;
import com.carmel.guestjini.inventory.model.Amenity;
import com.carmel.guestjini.inventory.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AmenityDTO {

    public static PhotoInformation photoInformation;
    private String id;
    private String clientId;
    private String orgId;
    private String photoId;
    private String title;
    private String narration;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    private Photo photo;

    public AmenityDTO() {

    }

    public AmenityDTO(Amenity amenity) {
        this.id = amenity.getId();
        this.clientId = amenity.getClientId();
        this.orgId = amenity.getOrgId();
        this.photoId = amenity.getPhotoId();
        this.title = amenity.getTitle();
        this.narration = amenity.getNarration();
        this.createdBy = amenity.getCreatedBy();
        this.creationTime = amenity.getCreationTime();
        this.lastModifiedBy = amenity.getLastModifiedBy();
        this.lastModifiedTime = amenity.getLastModifiedTime();
        this.isDeleted = amenity.getIsDeleted();
        this.deletedBy = amenity.getDeletedBy();
        this.deletedTime = amenity.getDeletedTime();
        if (this.photoId == null) {
            this.photoId = "";
        }
        if (this.photoId != "") {
            this.photo = photoInformation.getPhoto(this.photoId);
        }

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

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
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

    public Photo getPhoto() {
        if (this.photoId == null) {
            this.photoId = "";
        }
        if (this.photoId != "") {
            this.photo = photoInformation.getPhoto(this.photoId);
        }
        return photo;
    }
}
