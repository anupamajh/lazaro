package com.carmel.common.datamigrator.repository;

import com.carmel.common.datamigrator.model.InventoryType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryTypeRepository extends JpaRepository<InventoryType, String> {
}
