package com.carmel.guestjini.service.model.DTO.HelpDesk;



import com.carmel.guestjini.service.model.HelpDesk.TaskTicket;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import java.util.Date;

public class TaskTicketDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String ticketCategoryId;
    private String parentId;
    private String predecessorId;
    private String ticketNo;
    private String ticketTitle;
    private String ticketNarration;
    private double estimatedDuration;
    private int estimatedUnit;
    private Date plannedStartDate;
    private Date plannedEndDate;
    private Date actualStartDate;
    private Date actualEndDate;
    private int ticketStatus;
    private int requesterType;
    private String requesterId;
    private String requesterName;
    private String requesterInventoryId;
    private String requesterInventoryTitle;
    private String taskRunnerId;
    private String taskForceGroupId;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public TaskTicketDTO() {

    }

    public TaskTicketDTO(TaskTicket taskTicket) {
        this.id = taskTicket.getId();
        this.clientId = taskTicket.getClientId();
        this.orgId = taskTicket.getOrgId();
        this.ticketCategoryId = taskTicket.getTicketCategoryId();
        this.parentId = taskTicket.getParentId();
        this.predecessorId = taskTicket.getPredecessorId();
        this.ticketNo = taskTicket.getTicketNo();
        this.ticketTitle = taskTicket.getTicketTitle();
        this.ticketNarration = taskTicket.getTicketNarration();
        this.estimatedDuration = taskTicket.getEstimatedDuration();
        this.estimatedUnit = taskTicket.getEstimatedUnit();
        this.plannedStartDate = taskTicket.getPlannedStartDate();
        this.plannedEndDate = taskTicket.getPlannedEndDate();
        this.actualStartDate = taskTicket.getActualStartDate();
        this.actualEndDate = taskTicket.getActualEndDate();
        this.ticketStatus = taskTicket.getTicketStatus();
        this.requesterType = taskTicket.getRequesterType();
        this.requesterId = taskTicket.getRequesterId();
        this.requesterName = taskTicket.getRequesterName();
        this.requesterInventoryId = taskTicket.getRequesterInventoryId();
        this.requesterInventoryTitle = taskTicket.getRequesterInventoryTitle();
        this.taskRunnerId = taskTicket.getTaskRunnerId();
        this.taskForceGroupId = taskTicket.getTaskForceGroupId();
        this.createdBy = taskTicket.getCreatedBy();
        this.creationTime = taskTicket.getCreationTime();
        this.lastModifiedBy = taskTicket.getLastModifiedBy();
        this.lastModifiedTime = taskTicket.getLastModifiedTime();
        this.isDeleted = taskTicket.getIsDeleted();
        this.deletedBy = taskTicket.getDeletedBy();
        this.deletedTime = taskTicket.getDeletedTime();
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

    public String getTaskRunnerId() {
        return taskRunnerId;
    }

    public void setTaskRunnerId(String taskRunnerId) {
        this.taskRunnerId = taskRunnerId;
    }

    public String getTaskForceGroupId() {
        return taskForceGroupId;
    }

    public void setTaskForceGroupId(String taskForceGroupId) {
        this.taskForceGroupId = taskForceGroupId;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getRequesterInventoryId() {
        return requesterInventoryId;
    }

    public void setRequesterInventoryId(String requesterInventoryId) {
        this.requesterInventoryId = requesterInventoryId;
    }

    public String getRequesterInventoryTitle() {
        return requesterInventoryTitle;
    }

    public void setRequesterInventoryTitle(String requesterInventoryTitle) {
        this.requesterInventoryTitle = requesterInventoryTitle;
    }
}
