package com.carmel.guestjini.service.HelpDesk.TicketFeedback.DTO;

import com.carmel.guestjini.service.HelpDesk.TicketFeedback.Model.TicketFeedBack;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

public class TicketFeedBackDTO {
    private String id;
    private String clientId;
    private String orgId;
    private String userId;
    private String ticketId;
    private int rating;
    private String feedback;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;

    public TicketFeedBackDTO() {
    }

    public TicketFeedBackDTO(TicketFeedBack ticketFeedBack) {
        this.id = ticketFeedBack.getId();
        this.clientId = ticketFeedBack.getClientId();
        this.orgId = ticketFeedBack.getOrgId();
        this.userId = ticketFeedBack.getUserId();
        this.ticketId = ticketFeedBack.getTicketId();
        this.rating = ticketFeedBack.getRating();
        this.feedback = ticketFeedBack.getFeedback();
        this.createdBy = ticketFeedBack.getCreatedBy();
        this.creationTime = ticketFeedBack.getCreationTime();
        this.lastModifiedBy = ticketFeedBack.getLastModifiedBy();
        this.lastModifiedTime = ticketFeedBack.getLastModifiedTime();
        this.isDeleted = ticketFeedBack.getIsDeleted();
        this.deletedBy = ticketFeedBack.getDeletedBy();
        this.deletedTime = ticketFeedBack.getDeletedTime();
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
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
