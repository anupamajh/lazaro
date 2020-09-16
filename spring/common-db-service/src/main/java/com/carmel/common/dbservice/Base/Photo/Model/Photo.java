package com.carmel.common.dbservice.Base.Photo.Model;

import com.carmel.common.dbservice.Base.Album.Model.Album;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "g_common_photo")
@Audited
public class Photo {

    @Id
    @Column(name = "id")
    @Length(max = 40)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "type")
    @Length(min = 1, max = 255, message = "Type length should be between 1 and 255")
    @NotNull(message = "Type Cannot be null")
    @NotBlank(message = "Type cannot be blank")
    @NotEmpty(message = "Type cannot be empty")
    private String type;

    @Column(name = "path", columnDefinition = "TEXT")
    @NotNull(message = "Path Cannot be null")
    @NotBlank(message = "Path cannot be blank")
    @NotEmpty(message = "Path cannot be empty")
    private String path;

    @Column(name = "name", columnDefinition = "TEXT")
    @NotNull(message = "Name Cannot be null")
    @NotBlank(message = "Name cannot be blank")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Column(name = "error")
    private int error;

    @Column(name = "size")
    private int size;

    @Column(name = "created_by")
    @Length(max = 40)
    private String createdBy;

    @Column(name = "creation_time")
    private Date creationTime;

    @Column(name = "last_modified_by")
    @Length(max = 40)
    private String lastModifiedBy;

    @Column(name = "last_Modified_time")
    private Date lastModifiedTime;

    @Column(name = "is_deleted")
    private int isDeleted;

    @Column(name = "deleted_by")
    @Length(max = 40)
    private String deletedBy;

    @Column(name = "deleted_time")
    private Date deletedTime;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "album_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Album album;


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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

}
