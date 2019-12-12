package com.carmel.guestjini.inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "g_inventory_location")
public class InventoryLocation {

    @Id
    @Column(name = "id")
    @Length(max = 40)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "google_place_id")
    @Length(max = 255, min = 1, message = "Google Place Id length should be between 1 and 255")
    private String googlePlaceId;

    @Column(name = "postal_address", columnDefinition = "TEXT")
    private String postalAddress;

    @Column(name = "lat")
    @Length(max = 255, min = 1, message = "Latitude length should be between 1 and 255")
    private String lat;

    @Column(name = "lon")
    @Length(max = 255, min = 1, message = "Longitude length should be between 1 and 255")
    private String lon;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inventory_id", nullable = false)
    @JsonIgnore
    private Inventory inventory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGooglePlaceId() {
        return googlePlaceId;
    }

    public void setGooglePlaceId(String googlePlaceId) {
        this.googlePlaceId = googlePlaceId;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
