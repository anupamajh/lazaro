package com.carmel.guestjini.helpdesk.model.DTO;

import com.carmel.guestjini.helpdesk.model.KBDoc;

import java.util.Date;

public class KBDocDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String kbId;
    private String docTitle;
    private String path;
    private String name;
    private String type;
    private int error;
    private int size;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public KBDocDTO() {
    }

    public KBDocDTO(KBDoc kbDoc) {
        this.id = kbDoc.getId();
        this.clientId = kbDoc.getClientId();
        this.orgId = kbDoc.getOrgId();
        this.kbId = kbDoc.getKbId();
        this.docTitle = kbDoc.getDocTitle();
        this.path = kbDoc.getPath();
        this.name = kbDoc.getName();
        this.type = kbDoc.getType();
        this.error = kbDoc.getError();
        this.size = kbDoc.getSize();
        this.createdBy = kbDoc.getCreatedBy();
        this.creationTime = kbDoc.getCreationTime();
        this.lastModifiedBy = kbDoc.getLastModifiedBy();
        this.lastModifiedTime = kbDoc.getLastModifiedTime();
        this.isDeleted = kbDoc.getIsDeleted();
        this.deletedBy = kbDoc.getDeletedBy();
        this.deletedTime = kbDoc.getDeletedTime();
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

    public String getKbId() {
        return kbId;
    }

    public void setKbId(String kbId) {
        this.kbId = kbId;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
