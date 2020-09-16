package com.carmel.common.dbservice.Base.AddressBook.Model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "g_addressbook")
@Audited
public class AddressBook {

    @Id
    @Column(name = "id")
    @Length(max = 40)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "org_id")
    @Length(max = 40)
    private String orgId;

    @Column(name = "type_id")
    @Length(max = 40)
    private String typeId;

    @Column(name = "user_id")
    @Length(max = 40)
    private String userId;

    @Column(name = "parent_id")
    @Length(max = 40)
    private String parentId;

    @Column(name = "display_name")
    @Length(max = 255)
    private String displayName;

    @Column(name = "address", columnDefinition = "TEXT")
    @Length(max = 40)
    private String address;

    @Column(name = "place")
    @Length(max = 255)
    private String place;

    @Column(name = "pincode")
    @Length(max = 40)
    private String pinCode;

    @Column(name = "phone_1")
    @Length(max = 20)
    private String phone1;

    @Column(name = "phone_2")
    @Length(max = 20)
    private String phone2;

    @Column(name = "email_1")
    @Length(max = 255)
    private String email1;

    @Column(name = "email_2")
    @Length(max = 255)
    private String email2;

    @Column(name = "website")
    @Length(max = 255)
    private String website;

    @Column(name = "dob")
    private Date dateOfBirth;

    @Column(name = "logo_path")
    @Length(max = 255)
    private String logoPath;

    @Column(name = "logo_blob")
    private Blob logoBlob;

    @Column(name = "geo_lat")
    @Length(max = 255)
    private String geoLat;

    @Column(name = "geo_lon")
    @Length(max = 255)
    private String geoLon;

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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public Blob getLogoBlob() {
        return logoBlob;
    }

    public void setLogoBlob(Blob logoBlob) {
        this.logoBlob = logoBlob;
    }

    public String getGeoLat() {
        return geoLat;
    }

    public void setGeoLat(String geoLat) {
        this.geoLat = geoLat;
    }

    public String getGeoLon() {
        return geoLon;
    }

    public void setGeoLon(String geoLon) {
        this.geoLon = geoLon;
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
