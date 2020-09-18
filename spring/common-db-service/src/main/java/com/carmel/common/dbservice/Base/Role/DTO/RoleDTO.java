package com.carmel.common.dbservice.Base.Role.DTO;

import com.carmel.common.dbservice.Base.AppFeature.DTO.AppFeaturesDTO;
import com.carmel.common.dbservice.Base.AppFeature.Model.AppFeatures;
import com.carmel.common.dbservice.Base.Client.DTO.ClientDTO;
import com.carmel.common.dbservice.Base.Client.Model.Client;
import com.carmel.common.dbservice.Base.Photo.DTO.PhotoDTO;
import com.carmel.common.dbservice.Base.Photo.Model.Photo;
import com.carmel.common.dbservice.Base.Role.Model.Role;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoleDTO {
    private String id;
    private String roleName;
    private String homePage;
    private String description;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;
    private List<AppFeaturesDTO> appFeatures;
    private ClientDTO client;

    public RoleDTO() {

    }

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.roleName = role.getRoleName();
        this.homePage = role.getHomePage();
        this.description = role.getDescription();
        this.createdBy = role.getCreatedBy();
        this.creationTime = role.getCreationTime();
        this.lastModifiedBy = role.getLastModifiedBy();
        this.lastModifiedTime = role.getLastModifiedTime();
        this.isDeleted = role.getIsDeleted();
        this.deletedBy = role.getDeletedBy();
        this.deletedTime = role.getDeletedTime();
        this.client = ClientDTO.getSimple(role.getClient());
        this.appFeatures = new ArrayList<>();
        if (role.getAppFeatures() != null){
            role.getAppFeatures().forEach(appFeature -> {
                this.appFeatures.add(AppFeaturesDTO.getSimple(appFeature));
            });
        }
    }

    public static RoleDTO getSimple(Role role) {
        RoleDTO roleDTO = new RoleDTO(role);
        roleDTO.id = role.getId();
        roleDTO.roleName = role.getRoleName();
        roleDTO.homePage = role.getHomePage();
        roleDTO.description = role.getDescription();
        roleDTO.createdBy = role.getCreatedBy();
        roleDTO.creationTime = role.getCreationTime();
        roleDTO.lastModifiedBy = role.getLastModifiedBy();
        roleDTO.lastModifiedTime = role.getLastModifiedTime();
        roleDTO.isDeleted = role.getIsDeleted();
        roleDTO.deletedBy = role.getDeletedBy();
        roleDTO.deletedTime = role.getDeletedTime();
        return roleDTO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = ClientDTO.getSimple(client);
    }

    public List<AppFeaturesDTO> getAppFeatures() {
        return appFeatures;
    }

    public void setAppFeatures(List<AppFeatures> appFeatures) {
        this.appFeatures = new ArrayList<>();

        appFeatures.forEach(appFeature -> {
            this.appFeatures.add(new AppFeaturesDTO());
                }

        );
    }


}
