package com.carmel.common.dbservice.model.DTO;

import com.carmel.common.dbservice.model.AddressBook;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Blob;
import java.util.Date;

public class AddressBookDTO {
    private String id;
    private String orgId;
    private String typeId;
    private String userId;
    private String parentId;
    private String displayName;
    private String address;
    private String place;
    private String pinCode;
    private String phone1;
    private String phone2;
    private String email1;
    private String email2;
    private String website;
    private Date dateOfBirth;
    private String logoPath;
    private Blob logoBlob;
    private String geoLat;
    private String geoLon;
    private String createdBy;
    private Date creationTime;
    private String lastModifiedBy;
    private Date lastModifiedTime;
    private  int isDeleted;
    private String deletedBy;
    private Date deletedTime;
    private int isInvited;
    private int isMe;
    private int hasAcceptedInvitation;

    public AddressBookDTO() {
    }

    public AddressBookDTO(AddressBook addressBook) {
        this.id = addressBook.getId();
        this.orgId = addressBook.getOrgId();
        this.typeId = addressBook.getTypeId();
        this.userId = addressBook.getUserId();
        this.parentId = addressBook.getParentId();
        this.displayName = addressBook.getDisplayName();
        this.address = addressBook.getAddress();
        this.place = addressBook.getPlace();
        this.pinCode = addressBook.getPinCode();
        this.phone1 = addressBook.getPhone1();
        this.phone2 = addressBook.getPhone2();
        this.email1 = addressBook.getEmail1();
        this.email2 = addressBook.getEmail2();
        this.website = addressBook.getWebsite();
        this.dateOfBirth = addressBook.getDateOfBirth();
        this.logoPath = addressBook.getLogoPath();
        this.logoBlob = addressBook.getLogoBlob();
        this.geoLat = addressBook.getGeoLat();
        this.geoLon = addressBook.getGeoLon();
        this.createdBy = addressBook.getCreatedBy();
        this.creationTime = addressBook.getCreationTime();
        this.lastModifiedBy = addressBook.getLastModifiedBy();
        this.lastModifiedTime = addressBook.getLastModifiedTime();
        this.isDeleted = addressBook.getIsDeleted();
        this.deletedBy = addressBook.getDeletedBy();
        this.deletedTime = addressBook.getDeletedTime();
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

    public int getIsInvited() {
        return isInvited;
    }

    public void setIsInvited(int isInvited) {
        this.isInvited = isInvited;
    }

    public int getHasAcceptedInvitation() {
        return hasAcceptedInvitation;
    }

    public void setHasAcceptedInvitation(int hasAcceptedInvitation) {
        this.hasAcceptedInvitation = hasAcceptedInvitation;
    }

    public int getIsMe() {
        return isMe;
    }

    public void setIsMe(int isMe) {
        this.isMe = isMe;
    }
}
