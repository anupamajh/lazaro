package com.carmel.common.datamigrator.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "g_package")
public class Package {

    @Id
    @Column(name = "id")
    @Length(max = 40)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "photo_id")
    private String photoId;


    @Column(name = "package_title")
    @Length(max = 255, min = 1, message = "Package title length should be between 1 and 255")
    @NotBlank(message = "Package title cannot be blank")
    @NotNull(message = "Package title cannot be null")
    private String packageTitle;

    @Column(name = "package_narration")
    @Length(max = 1000, min = 1, message = "Package narration length should be between 1 and 1000")
    private String packageNarration;

    @Column(name = "rent")
     private double rent;

    @Column(name = "rent_cycle")
    private int rentCycle;

    @Column(name = "status")
    private int packageStatus;

    @Column(name = "stay_length")
    private int stayLength;

    @Column(name = "stay_length_unit")
    private int stayLengthUnit;

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
    private Integer isDeleted;

    @Column(name = "deleted_by")
    @Length(max = 40)
    private String deletedBy;

    @Column(name = "deleted_time")
    private Date deletedTime;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "aPackage")
    private List<PackageCharge> packageCharges;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "g_package_amenities",
            joinColumns = {
                    @JoinColumn(name = "package_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "amenity_id", referencedColumnName = "id")
            }
    )
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Amenity> amenities;


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

    public String getPackageTitle() {
        return packageTitle;
    }

    public void setPackageTitle(String packageTitle) {
        this.packageTitle = packageTitle;
    }

    public String getPackageNarration() {
        return packageNarration;
    }

    public void setPackageNarration(String packageNarration) {
        this.packageNarration = packageNarration;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public int getRentCycle() {
        return rentCycle;
    }

    public void setRentCycle(int rentCycle) {
        this.rentCycle = rentCycle;
    }

    public int getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(int packageStatus) {
        this.packageStatus = packageStatus;
    }

    public int getStayLength() {
        return stayLength;
    }

    public void setStayLength(int stayLength) {
        this.stayLength = stayLength;
    }

    public int getStayLengthUnit() {
        return stayLengthUnit;
    }

    public void setStayLengthUnit(int stayLengthUnit) {
        this.stayLengthUnit = stayLengthUnit;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
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

    public List<PackageCharge> getPackageCharges() {
        return packageCharges;
    }

    public void setPackageCharges(List<PackageCharge> packageCharges) {
        this.packageCharges = packageCharges;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }
}
