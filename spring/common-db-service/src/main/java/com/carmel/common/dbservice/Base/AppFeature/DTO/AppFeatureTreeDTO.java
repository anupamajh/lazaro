package com.carmel.common.dbservice.Base.AppFeature.DTO;

import com.carmel.common.dbservice.Base.AppFeature.Model.AppFeatures;

import java.util.List;

public class AppFeatureTreeDTO {

    private String id;
    private String featureName;
    private String text;
    private String toolTip;
    private String systemRole;
    private String url;
    private Integer sequence;
    private Integer isSystem;
    private String parentId;
    private List<AppFeatureTreeDTO> children;

    public AppFeatureTreeDTO() {
    }

    public AppFeatureTreeDTO(AppFeatures appFeatures) {
        this.id = appFeatures.getId();
        this.featureName = appFeatures.getFeatureName();
        this.text = appFeatures.getFeatureName();
        this.toolTip = appFeatures.getToolTip();
        this.systemRole = appFeatures.getSystemRole();
        this.url = appFeatures.getUrl();
        this.sequence = appFeatures.getSequence();
        this.isSystem = appFeatures.getIsSystem();
        if(appFeatures.getParent() !=  null){
            this.parentId = appFeatures.getParent().getId();
        }
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public List<AppFeatureTreeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<AppFeatureTreeDTO> children) {
        this.children = children;
    }
}
