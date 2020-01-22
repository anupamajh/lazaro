package com.carmel.guestjini.helpdesk.model.DTO;

import com.carmel.guestjini.helpdesk.model.KB;

import java.util.Date;

public class KBDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String topicTitle;
    private String topicNarration;
    private String authorName;
    private String authorLogoPath;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public KBDTO() {
    }

    public KBDTO(KB kb) {
        this.id = kb.getId();
        this.clientId = kb.getClientId();
        this.orgId = kb.getOrgId();
        this.topicTitle = kb.getTopicTitle();
        this.topicNarration = kb.getTopicNarration();
        this.authorName = kb.getAuthorName();
        this.authorLogoPath = kb.getAuthorLogoPath();
        this.createdBy = kb.getCreatedBy();
        this.creationTime = kb.getCreationTime();
        this.lastModifiedBy = kb.getLastModifiedBy();
        this.lastModifiedTime = kb.getLastModifiedTime();
        this.isDeleted = kb.getIsDeleted();
        this.deletedBy = kb.getDeletedBy();
        this.deletedTime = kb.getDeletedTime();

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

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicNarration() {
        return topicNarration;
    }

    public void setTopicNarration(String topicNarration) {
        this.topicNarration = topicNarration;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorLogoPath() {
        return authorLogoPath;
    }

    public void setAuthorLogoPath(String authorLogoPath) {
        this.authorLogoPath = authorLogoPath;
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
