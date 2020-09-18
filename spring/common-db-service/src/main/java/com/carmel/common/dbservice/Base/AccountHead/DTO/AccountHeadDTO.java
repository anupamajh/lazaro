package com.carmel.common.dbservice.Base.AccountHead.DTO;
import com.carmel.common.dbservice.Base.AccountHead.Model.AccountHead;

import java.util.Date;

public class AccountHeadDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String title;
    private String narration;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private String deletedBy;
    private Date deletedTime;


    public AccountHeadDTO(AccountHead accountHead) {
        this.id = accountHead.getId();
        this.clientId = accountHead.getClientId();
        this.orgId = accountHead.getOrgId();
        this.title = accountHead.getTitle();
        this.narration = accountHead.getNarration();
        this.createdBy = accountHead.getCreatedBy();
        this.creationTime = accountHead.getCreationTime();
        this.lastModifiedBy = accountHead.getLastModifiedBy();
        this.deletedBy = accountHead.getDeletedBy();
        this.deletedTime = accountHead.getDeletedTime();
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

