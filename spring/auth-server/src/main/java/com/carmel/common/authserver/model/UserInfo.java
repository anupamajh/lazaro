package com.carmel.common.authserver.model;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;

public class UserInfo implements Serializable {
    private String id;
    private String fullName;
    private String userName;
    private String phone;
    private String email;
    private Date lastLogin;
    private String lastLoginFrom;
    private int accountStatus;
    private int isGod;
    private String clientId;

    private Principal principal;

    public UserInfo() {

    }

    public UserInfo(User user) {
        this.id = user.getId();
        this.fullName = user.getUserName();
        this.userName = user.getUserName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.lastLogin = user.getLastLogin();
        this.accountStatus = user.getAccountStatus();
        this.isGod = user.getIsGod();
        this.clientId = user.getClient().getClientId();
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

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsGod() {
        return isGod;
    }

    public void setIsGod(int isGod) {
        this.isGod = isGod;
    }
}