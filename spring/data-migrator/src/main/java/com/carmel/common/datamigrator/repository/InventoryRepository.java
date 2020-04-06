package com.carmel.common.datamigrator.repository;

import com.carmel.common.datamigrator.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, String> {
}
