package com.carmel.common.dbservice.model;

import com.carmel.common.dbservice.repository.UserRepository;
import com.carmel.common.dbservice.services.UserService;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.security.Principal;
import java.util.Date;
import java.util.Optional;

public class UserInfo {

    @Autowired
    UserRepository repository;

    private String id;
    private String orgId;
    private String fullName;
    private String userName;
    private String phone;
    private Date lastLogin;
    private String lastLoginFrom;
    private int accountStatus;
    private int isOperator;

    private Principal principal;

    public UserInfo(){

    }

    public UserInfo(User user) {
        this.id = user.getId();
        this.orgId = user.getOrgId();
        this.fullName =user.getUserName();
        this.userName = user.getUserName();
        this.phone = user.getPhone();
        this.lastLogin = user.getLastLogin();
        this.accountStatus = user.getAccountStatus();
        this.isOperator = user.getIsOperator();
    }

    public UserInfo(String userName){
        Optional<User> optionalUser = repository.findByUserName(userName);
        optionalUser.orElseThrow(() ->
                new UsernameNotFoundException("Cannot find the logged in principle, Please contact administrator"));
        User user = optionalUser.get();
        this.id = user.getId();
        this.orgId = user.getOrgId();
        this.fullName =user.getUserName();
        this.userName = user.getUserName();
        this.phone = user.getPhone();
        this.lastLogin = user.getLastLogin();
        this.accountStatus = user.getAccountStatus();
        this.isOperator = user.getIsOperator();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
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

    public int getIsOperator() {
        return isOperator;
    }

    public void setIsOperator(int isOperator) {
        this.isOperator = isOperator;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }
}
