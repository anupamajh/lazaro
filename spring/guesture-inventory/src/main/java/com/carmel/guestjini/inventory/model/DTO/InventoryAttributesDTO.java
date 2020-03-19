package com.carmel.guestjini.inventory.model.DTO;

import com.carmel.guestjini.inventory.model.InventoryAttributes;

public class InventoryAttributesDTO {
    private String id;
    private Integer hasAttachedBalcony;
    private Integer hasAttachedBathroom;

    public InventoryAttributesDTO() {
    }

    public InventoryAttributesDTO(InventoryAttributes inventoryAttributes) {
        this.id = inventoryAttributes.getId();
        this.hasAttachedBalcony = inventoryAttributes.getHasAttachedBalcony();
        this.hasAttachedBathroom = inventoryAttributes.getHasAttachedBathroom();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getHasAttachedBalcony() {
        return hasAttachedBalcony;
    }

    public void setHasAttachedBalcony(Integer hasAttachedBalcony) {
        this.hasAttachedBalcony = hasAttachedBalcony;
    }

    public Integer getHasAttachedBathroom() {
        return hasAttachedBathroom;
    }

    public void setHasAttachedBathroom(Integer hasAttachedBathroom) {
        this.hasAttachedBathroom = hasAttachedBathroom;
    }
}
