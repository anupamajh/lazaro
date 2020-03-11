package com.carmel.guesture.crmdashboard.models;

import com.carmel.guesture.crmdashboard.common.DateUtil;

import java.util.Date;

public class CRMTasksAttributes {
    private String name;
    private String description;
    private String parent_type;
    private String parent_id;
    private String priority;
    private Date date_start;
    private Date date_entered;
    private Date date_due;
    private String status;
    private String assigned_user_id;
    private String contact_name;
    private String contact_phone;
    private String contact_email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParent_type() {
        return parent_type;
    }

    public void setParent_type(String parent_type) {
        this.parent_type = parent_type;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDate_start() {
        return DateUtil.getFormattedDateTime("dd-MMM-yyyy hh:mm a",date_start);
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public String getDate_due() {
        return DateUtil.getFormattedDateTime("dd-MMM-yyyy hh:mm a",date_due);
    }

    public void setDate_due(Date date_due) {
        this.date_due = date_due;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssigned_user_id() {
        return assigned_user_id;
    }

    public void setAssigned_user_id(String assigned_user_id) {
        this.assigned_user_id = assigned_user_id;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    public String getDate_entered() {
        return DateUtil.getFormattedDate("",date_entered);
    }

    public void setDate_entered(Date date_entered) {
        this.date_entered = date_entered;
    }
}
