package com.carmel.common.dbservice.model.Facebook;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "g_facebook")
@Audited
public class Facebook {
    @Id
    @Column(name = "id")
    @Length(max = 40)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "client_id")
    @Length(max = 40)
    private String clientId;

    @Column(name = "org_id")
    @Length(max = 40)
    private String orgId;

    @Column(name = "fb_name")
    @Length(max = 150)
    private String fbName;

    @Column(name = "title_url")
    @Length(max = 1000)
    private String titleURL;

    @Column(name = "image_url", columnDefinition = "LONGTEXT")
    private String image;

    @Column(name = "company_name")
    @Length(max = 1000)
    private String companyName;

    @Column(name = "pac_url")
    @Length(max = 8000)
    private String pacURL;

    @Column(name = "designation")
    @Length(max = 1000)
    private String designation;

    @Column(name = "pac_url_2")
    @Length(max = 8000)
    private String pacURL2;

    @Column(name = "current_working")
    @Length(max = 1000)
    private String currentWorking;

    @Column(name = "live_at")
    @Length(max = 1000)
    private String liveAt;

    @Column(name = "52_eh_url")
    @Length(max = 8000)
    private String eh52URL;

    @Column(name = "52_eh_4")
    @Length(max = 8000)
    private String eh524;

    @Column(name = "followers")
    @Length(max = 1000)
    private String followers;

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

    public Facebook() {
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

    public String getFbName() {
        return fbName;
    }

    public void setFbName(String fbName) {
        this.fbName = fbName;
    }

    public String getTitleURL() {
        return titleURL;
    }

    public void setTitleURL(String titleURL) {
        this.titleURL = titleURL;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPacURL() {
        return pacURL;
    }

    public void setPacURL(String pacURL) {
        this.pacURL = pacURL;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPacURL2() {
        return pacURL2;
    }

    public void setPacURL2(String pacURL2) {
        this.pacURL2 = pacURL2;
    }

    public String getCurrentWorking() {
        return currentWorking;
    }

    public void setCurrentWorking(String currentWorking) {
        this.currentWorking = currentWorking;
    }

    public String getLiveAt() {
        return liveAt;
    }

    public void setLiveAt(String liveAt) {
        this.liveAt = liveAt;
    }

    public String getEh52URL() {
        return eh52URL;
    }

    public void setEh52URL(String eh52URL) {
        this.eh52URL = eh52URL;
    }

    public String getEh524() {
        return eh524;
    }

    public void setEh524(String eh524) {
        this.eh524 = eh524;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
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
}
