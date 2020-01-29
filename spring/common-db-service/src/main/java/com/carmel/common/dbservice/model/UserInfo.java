package com.carmel.common.dbservice.model;

import com.carmel.common.dbservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserInfo {

    @Autowired
    UserRepository repository;

    private String id;
    private String fullName;
    private String userName;
    private String phone;
    private Date lastLogin;
    private String lastLoginFrom;
    private int accountStatus;
    private int isOperator;
    private int gender;
    private  Client client;
    private  Organization defaultOrganization;
    private List<Organization> organizations;
    private AddressBook addressBook;
    private List<UserPreference> userPreferences;

    private Principal principal;

    public UserInfo(){

    }

    public UserInfo(User user) {
        this.id = user.getId();
        this.fullName =user.getUserName();
        this.userName = user.getUserName();
        this.phone = user.getPhone();
        this.lastLogin = user.getLastLogin();
        this.accountStatus = user.getAccountStatus();
        this.isOperator = user.getIsOperator();
        this.client = user.getClient();
        this.defaultOrganization = user.getDefaultOrganization();
        this.organizations = user.getOrganizations();
        this.gender = user.getGender();
        this.userPreferences = new ArrayList<>();
        this.addressBook = new AddressBook();
    }

    public UserInfo(String userName){
        Optional<User> optionalUser = repository.findByUserName(userName);
        optionalUser.orElseThrow(() ->
                new UsernameNotFoundException("Cannot find the logged in principle, Please contact administrator"));
        User user = optionalUser.get();
        this.id = user.getId();
        this.fullName =user.getUserName();
        this.userName = user.getUserName();
        this.phone = user.getPhone();
        this.lastLogin = user.getLastLogin();
        this.accountStatus = user.getAccountStatus();
        this.isOperator = user.getIsOperator();
        this.client = user.getClient();
        this.defaultOrganization = user.getDefaultOrganization();
        this.organizations = user.getOrganizations();
        this.gender = user.getGender();
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Organization getDefaultOrganization() {
        return defaultOrganization;
    }

    public void setDefaultOrganization(Organization defaultOrganization) {
        this.defaultOrganization = defaultOrganization;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public AddressBook getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public List<UserPreference> getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(List<UserPreference> userPreferences) {
        this.userPreferences = userPreferences;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
