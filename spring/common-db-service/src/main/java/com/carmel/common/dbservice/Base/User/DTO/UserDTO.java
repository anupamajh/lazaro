package com.carmel.common.dbservice.Base.User.DTO;

import com.carmel.common.dbservice.Base.Client.DTO.ClientDTO;
import com.carmel.common.dbservice.Base.Client.Model.Client;
import com.carmel.common.dbservice.Base.Organization.DTO.OrganizationDTO;
import com.carmel.common.dbservice.Base.Organization.Model.Organization;
import com.carmel.common.dbservice.Base.Role.DTO.RoleDTO;
import com.carmel.common.dbservice.Base.Role.Model.Role;
import com.carmel.common.dbservice.Base.User.Model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDTO {
    private String id;
    private String fullName;
    private String userName;
    private String password;
    private String phone;
    private String email;
    private Date lastLogin;
    private String lastLoginFrom;
    private int accountStatus;
    private int isGod;
    private int gender;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private int isDeleted;
    private String deletedBy;
    private Date deletedTime;
    private List<RoleDTO> roles;
    private List<OrganizationDTO> organizations;
    private OrganizationDTO defaultOrganization;
    private ClientDTO client;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.lastLogin = user.getLastLogin();
        this.lastLoginFrom = user.getLastLoginFrom();
        this.accountStatus = user.getAccountStatus();
        this.isGod = user.getIsGod();
        this.gender = user.getGender();
        this.createdBy = user.getCreatedBy();
        this.creationTime = user.getCreationTime();
        this.lastModifiedBy = user.getLastModifiedBy();
        this.lastModifiedTime = user.getLastModifiedTime();
        this.isDeleted = user.getIsDeleted();
        this.deletedBy = user.getDeletedBy();
        this.deletedTime = user.getDeletedTime();
        this.roles = new ArrayList<>();
        if (user.getRoles() != null) {
            user.getRoles().forEach(role -> {
                this.roles.add(RoleDTO.getSimple(role));
            });
        }
        this.organizations = new ArrayList<>();
        if (user.getOrganizations() != null) {
            user.getOrganizations().forEach(organization -> {
                this.organizations.add(OrganizationDTO.getSimple(organization));
            });
        }
        if (user.getDefaultOrganization() != null) {
            this.defaultOrganization = OrganizationDTO.getSimple(user.getDefaultOrganization());
        }
        this.client = ClientDTO.getSimple(user.getClient());
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

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
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

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = new ArrayList<>();

        roles.forEach(role -> {
            this.roles.add(new RoleDTO(role));
        });
    }

    public List<OrganizationDTO> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = new ArrayList<>();

        organizations.forEach(organization -> {
            this.organizations.add(new OrganizationDTO(organization));
        });
    }

    public OrganizationDTO getDefaultOrganization() {
        return defaultOrganization;
    }

    public void setDefaultOrganization(Organization defaultOrganization) {
        this.defaultOrganization = OrganizationDTO.getSimple(defaultOrganization);
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = ClientDTO.getSimple(client);
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
