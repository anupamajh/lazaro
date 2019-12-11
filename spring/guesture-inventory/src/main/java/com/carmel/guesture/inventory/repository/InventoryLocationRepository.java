package com.carmel.guesture.inventory.repository;

import com.carmel.guesture.inventory.model.InventoryLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryLocationRepository extends JpaRepository<InventoryLocation, String> {
}
