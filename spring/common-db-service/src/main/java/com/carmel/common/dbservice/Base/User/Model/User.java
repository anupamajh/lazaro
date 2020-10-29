package com.carmel.common.dbservice.Base.User.Model;

import com.carmel.common.dbservice.Base.Client.Model.Client;
import com.carmel.common.dbservice.Base.Organization.Model.Organization;
import com.carmel.common.dbservice.Base.Role.Model.Role;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "g_user")
@Audited
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @Length(max = 40)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @Column(name = "full_name")
    @Length(max = 100, min = 1, message = "Full name length should be between 1 and 100")
    @NotEmpty(message = "Full Name cannot be empty")
    @NotBlank(message = "Full Name cannot be blank")
    @NotNull(message = "Full Name cannot be empty")
    private String fullName;

    @Column(name = "user_name")
    @Length(max = 100, min = 1, message = "User name length should be between 1 and 100")
    private String userName;

    @Column(name = "password")
    @Length(max = 1000)
    private String password;

    @Column(name = "phone")
    @Length(max = 100)
    private String phone;

    @Column(name = "email")
    @Length(max = 100)
    private String email;

    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "last_login_from")
    @Length(max = 100)
    private String lastLoginFrom;

    @Column(name = "account_status")
    private int accountStatus;

    @Column(name = "is_god", columnDefinition = "int default 0")
    private int isGod;

    @Column(name = "gender")
    private int gender;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "g_role_accounts",
            joinColumns = {
                    @JoinColumn(name = "account_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")
            }
    )
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "g_user_orgs",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "org_id", referencedColumnName = "id")
            }
    )
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Organization> organizations;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization defaultOrganization;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public User() {
    }

    public User(Role role) {
        this.roles = new ArrayList<>();
        this.roles.add(role);
    }

    public User(User user) {
        this.id = user.getId();
        this.client = user.getClient();
        this.fullName = user.getFullName();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.accountStatus = user.getAccountStatus();
        this.isGod = user.getIsGod();
        this.isDeleted = user.getIsDeleted();
        this.roles = user.roles;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public Organization getDefaultOrganization() {
        return defaultOrganization;
    }

    public void setDefaultOrganization(Organization defaultOrganization) {
        this.defaultOrganization = defaultOrganization;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
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
