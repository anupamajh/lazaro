package com.carmel.guestjini.service.model.DTO.Common;

import com.carmel.guestjini.service.model.Booking.Guest;
import com.carmel.guestjini.service.model.Principal.Client;
import com.carmel.guestjini.service.model.Principal.Organization;

import java.util.Date;

public class UserDTO {
    private String id;
    private String fullName;
    private String userName;
    private String password;
    private String phone;
    private Date lastLogin;
    private String lastLoginFrom;
    private int isOperator;
    private int accountStatus;
    private int gender;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;
    private Organization defaultOrganization;
    private Client client;

    public UserDTO(){

    }

    public UserDTO(Guest guest) {
        this.fullName = guest.getFullName();
        this.userName = guest.getEmail();
        this.phone = guest.getPhone();
        this.isOperator = 0;
        Client client = new Client();
        client.setClientId(guest.getClientId());
        this.client = client;
        this.accountStatus = 1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastLoginFrom() {
        return lastLoginFrom;
    }

    public void setLastLoginFrom(String lastLoginFrom) {
        this.lastLoginFrom = lastLoginFrom;
    }

    public int getIsOperator() {
        return isOperator;
    }

    public void setIsOperator(int isOperator) {
        this.isOperator = isOperator;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
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

    public Organization getDefaultOrganization() {
        return defaultOrganization;
    }

    public void setDefaultOrganization(Organization defaultOrganization) {
        this.defaultOrganization = defaultOrganization;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
