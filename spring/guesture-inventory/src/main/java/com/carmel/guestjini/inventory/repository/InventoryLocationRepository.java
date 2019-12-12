package com.carmel.guestjini.inventory.repository;

import com.carmel.guestjini.inventory.model.InventoryLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryLocationRepository extends JpaRepository<InventoryLocation, String> {
}
