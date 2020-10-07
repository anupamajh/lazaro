package com.carmel.common.dbservice.Base.AppFeature.DTO;

import com.carmel.common.dbservice.Base.AppFeature.Model.AppFeatures;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppFeaturesDTO {
    private String id;
    private String featureName;
    private String toolTip;
    private String systemRole;
    private String url;
    private Integer group;
    private Integer sequence;
    private Integer isSystem;
    private String parentId;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;
    private List<AppFeaturesDTO> childrens = new ArrayList<>();

    public AppFeaturesDTO() {

    }


    public AppFeaturesDTO(AppFeatures appFeatures) {
        this.id = appFeatures.getId();
        this.featureName = appFeatures.getFeatureName();
        this.toolTip = appFeatures.getToolTip();
        this.systemRole = appFeatures.getSystemRole();
        this.url = appFeatures.getUrl();
        this.group = appFeatures.getGroup();
        this.sequence = appFeatures.getSequence();
        this.isSystem = appFeatures.getIsSystem();
        this.createdBy = appFeatures.getCreatedBy();
        this.creationTime = appFeatures.getCreationTime();
        this.lastModifiedBy = appFeatures.getLastModifiedBy();
        this.lastModifiedTime = appFeatures.getLastModifiedTime();
        this.isDeleted = appFeatures.getIsDeleted();
        this.deletedBy = appFeatures.getDeletedBy();
        this.deletedTime = appFeatures.getDeletedTime();
        if (appFeatures.getParent() != null) {
            this.parentId = appFeatures.getParent().getId();
        }
        this.childrens = new ArrayList<>();
        appFeatures.getChildrens().forEach(appFeatures1 -> {
            this.childrens.add(getSimple(appFeatures1));
        });
    }

    public AppFeaturesDTO(AppFeatures appFeatures, Boolean isSimple) {
        this.id = appFeatures.getId();
        this.featureName = appFeatures.getFeatureName();
        this.toolTip = appFeatures.getToolTip();
        this.systemRole = appFeatures.getSystemRole();
        this.url = appFeatures.getUrl();
        this.group = appFeatures.getGroup();
        this.sequence = appFeatures.getSequence();
        this.isSystem = appFeatures.getIsSystem();
        this.createdBy = appFeatures.getCreatedBy();
        this.creationTime = appFeatures.getCreationTime();
        this.lastModifiedBy = appFeatures.getLastModifiedBy();
        this.lastModifiedTime = appFeatures.getLastModifiedTime();
        this.isDeleted = appFeatures.getIsDeleted();
        this.deletedBy = appFeatures.getDeletedBy();
        this.deletedTime = appFeatures.getDeletedTime();
        if (appFeatures.getParent() != null) {
            this.parentId = appFeatures.getParent().getId();
        }
        this.childrens = new ArrayList<>();
        if (!isSimple) {
            appFeatures.getChildrens().forEach(appFeatures1 -> {
                this.childrens.add(getSimple(appFeatures1));
            });
        }
    }

    public static AppFeaturesDTO getSimple(AppFeatures appFeatures) {
        AppFeaturesDTO retValue = new AppFeaturesDTO();
        retValue.setId(appFeatures.getId());
        retValue.setFeatureName(appFeatures.getFeatureName());
        retValue.setToolTip(appFeatures.getToolTip());
        retValue.setSystemRole(appFeatures.getSystemRole());
        retValue.setUrl(appFeatures.getUrl());
        retValue.setGroup(appFeatures.getGroup());
        retValue.setSequence(appFeatures.getSequence());
        retValue.setIsSystem(appFeatures.getIsSystem());
        retValue.setCreatedBy(appFeatures.getCreatedBy());
        retValue.setCreationTime(appFeatures.getCreationTime());
        retValue.setLastModifiedBy(appFeatures.getLastModifiedBy());
        retValue.setLastModifiedTime(appFeatures.getLastModifiedTime());
        retValue.setIsDeleted(appFeatures.getIsDeleted());
        retValue.setDeletedBy(appFeatures.getDeletedBy());
        retValue.setDeletedTime(appFeatures.getDeletedTime());
        return retValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getToolTip() {
        return toolTip;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }

    public String getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(String systemRole) {
        this.systemRole = systemRole;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Integer isSystem) {
        this.isSystem = isSystem;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public List<AppFeaturesDTO> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<AppFeaturesDTO> childrens) {
        this.childrens = childrens;
    }
}
