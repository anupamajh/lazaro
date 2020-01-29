package com.carmel.common.dbservice.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "g_group_people")
public class GroupPeople {
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

    @Column(name = "group_id")
    @Length(max = 40)
    private String groupId;

    @Column(name = "has_accepted_invitation")
    @Length(max = 40)
    private String hasAcceptedInvitation;


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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getHasAcceptedInvitation() {
        return hasAcceptedInvitation;
    }

    public void setHasAcceptedInvitation(String hasAcceptedInvitation) {
        this.hasAcceptedInvitation = hasAcceptedInvitation;
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
