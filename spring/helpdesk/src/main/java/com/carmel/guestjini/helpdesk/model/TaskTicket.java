package com.carmel.guestjini.helpdesk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "g_task_ticket")
@Audited
public class TaskTicket {
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

    @Column(name = "ticket_category_id")
    @Length(max = 40)
    private String ticketCategoryId;

    @Column(name = "parent_id")
    @Length(max = 40)
    private String parentId;

    @Column(name = "predecessor_id")
    @Length(max = 40)
    private String predecessorId;

    @Column(name = "ticket_no", columnDefinition = "TEXT")
    private String ticketNo;

    @Column(name = "ticket_title")
    @Length(min = 10, max = 255, message = "Ticket title length should be between 10 and 255")
    @NotNull(message = "Ticket title cannot be null")
    @NotEmpty(message = "Ticket title cannot be empty")
    private String ticketTitle;

    @Column(name = "ticket_narration", columnDefinition = "TEXT")
    private String ticketNarration;

    @Column(name = "estimated_duration")
    private double estimatedDuration;

    @Column(name = "estimated_unit")
    private int estimatedUnit;

    @Column(name = "planned_start_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date plannedStartDate;

    @Column(name = "planned_end_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date plannedEndDate;

    @Column(name = "actual_start_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date actualStartDate;

    @Column(name = "actual_end_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date actualEndDate;

    @Column(name = "ticket_status")
    private int ticketStatus;

    @Column(name = "requester_type")
    private int requesterType;

    @Column(name = "requester_id")
    @Length(max = 40)
    private String requesterId;

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

    public String getTicketCategoryId() {
        return ticketCategoryId;
    }

    public void setTicketCategoryId(String ticketCategoryId) {
        this.ticketCategoryId = ticketCategoryId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPredecessorId() {
        return predecessorId;
    }

    public void setPredecessorId(String predecessorId) {
        this.predecessorId = predecessorId;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getTicketTitle() {
        return ticketTitle;
    }

    public void setTicketTitle(String ticketTitle) {
        this.ticketTitle = ticketTitle;
    }

    public String getTicketNarration() {
        return ticketNarration;
    }

    public void setTicketNarration(String ticketNarration) {
        this.ticketNarration = ticketNarration;
    }

    public double getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(double estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public int getEstimatedUnit() {
        return estimatedUnit;
    }

    public void setEstimatedUnit(int estimatedUnit) {
        this.estimatedUnit = estimatedUnit;
    }

    public Date getPlannedStartDate() {
        return plannedStartDate;
    }

    public void setPlannedStartDate(Date plannedStartDate) {
        this.plannedStartDate = plannedStartDate;
    }

    public Date getPlannedEndDate() {
        return plannedEndDate;
    }

    public void setPlannedEndDate(Date plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
    }

    public Date getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(Date actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public Date getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(Date actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public int getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(int ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public int getRequesterType() {
        return requesterType;
    }

    public void setRequesterType(int requesterType) {
        this.requesterType = requesterType;
    }

    public String getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
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
