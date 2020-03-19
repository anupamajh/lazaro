package com.carmel.guestjini.inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "g_inventory_attributes")
@Audited
public class InventoryAttributes {

    @Id
    @Column(name = "id")
    @Length(max = 40)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inventory_id", nullable = false)
    @JsonIgnore
    private Inventory inventory;

    private int hasAttachedBalcony;

    private int hasAttachedBathroom;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getHasAttachedBalcony() {
        return hasAttachedBalcony;
    }

    public void setHasAttachedBalcony(int hasAttachedBalcony) {
        this.hasAttachedBalcony = hasAttachedBalcony;
    }

    public int getHasAttachedBathroom() {
        return hasAttachedBathroom;
    }

    public void setHasAttachedBathroom(int hasAttachedBathroom) {
        this.hasAttachedBathroom = hasAttachedBathroom;
    }
}
