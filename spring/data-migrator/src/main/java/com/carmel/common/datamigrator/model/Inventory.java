package com.carmel.common.datamigrator.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "g_inventory_group")
public class Inventory {
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

    @Column(name = "title")
    @Length(max = 255, min = 1, message = "Inventory title length should be between 1 and 255")
    @NotBlank(message = "Inventory title cannot be blank")
    @NotNull(message = "Inventory title cannot be null")
    private String title;

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
    private  int isDeleted;

    @Column(name = "deleted_by")
    @Length(max = 40)
    private String deletedBy;

    @Column(name = "deleted_time")
    private Date deletedTime;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="parent_id")
    @JsonBackReference
    private Inventory parent;

    @OneToMany(mappedBy="parent")
    @JsonManagedReference
    private List<Inventory> childrens = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    private InventoryType inventoryType;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "inventory")
    private InventoryLocation inventoryLocation;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "g_inventory_packages",
            joinColumns = {
                    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "package_id", referencedColumnName = "id")
            }
    )
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Package> packages;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "g_inventory_amenities",
            joinColumns = {
                    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "amenity_id", referencedColumnName = "id")
            }
    )
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Amenity> amenities;

    @OneToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "inventory")
    private InventoryAttributes inventoryAttributes;

    public Inventory(InventoryGroup inventoryGroup) {
        this.orgId = inventoryGroup.getOrgId();
        this.title = inventoryGroup.getTitle();
        this.isDeleted = inventoryGroup.getIsDeleted();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Inventory getParent() {
        return parent;
    }

    public void setParent(Inventory parent) {
        this.parent = parent;
    }

    public List<Inventory> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<Inventory> childrens) {
        this.childrens = childrens;
    }

    public InventoryType getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(InventoryType inventoryType) {
        this.inventoryType = inventoryType;
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

    public InventoryLocation getInventoryLocation() {
        return inventoryLocation;
    }

    public void setInventoryLocation(InventoryLocation inventoryLocation) {
        this.inventoryLocation = inventoryLocation;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    public InventoryAttributes getInventoryAttributes() {
        return inventoryAttributes;
    }

    public void setInventoryAttributes(InventoryAttributes inventoryAttributes) {
        this.inventoryAttributes = inventoryAttributes;
    }
}