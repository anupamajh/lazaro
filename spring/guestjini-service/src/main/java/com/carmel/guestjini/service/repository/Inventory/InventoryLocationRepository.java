package com.carmel.guestjini.service.repository.Inventory;

import com.carmel.guestjini.service.model.Inventory.InventoryLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryLocationRepository extends JpaRepository<InventoryLocation, String> {
}
