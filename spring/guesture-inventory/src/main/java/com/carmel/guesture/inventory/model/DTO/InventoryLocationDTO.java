package com.carmel.guesture.inventory.model.DTO;

import com.carmel.guesture.inventory.model.InventoryLocation;

public class InventoryLocationDTO {

    private String id;
    private String googlePlaceId;
    private String postalAddress;
    private String lat;
    private String lon;

    public InventoryLocationDTO() {
    }

    public InventoryLocationDTO(InventoryLocation inventoryLocation) {
        this.id = inventoryLocation.getId();
        this.googlePlaceId = inventoryLocation.getGooglePlaceId();
        this.postalAddress = inventoryLocation.getPostalAddress();
        this.lat = inventoryLocation.getLat();
        this.lon = inventoryLocation.getLon();
    }

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
}
