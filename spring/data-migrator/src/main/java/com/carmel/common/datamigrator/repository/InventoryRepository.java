package com.carmel.common.datamigrator.repository;

import com.carmel.common.datamigrator.model.Inventory;
import com.carmel.common.datamigrator.model.InventoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, String> {
    List<Inventory> findAllByIsDeletedAndInventoryType(int i, InventoryType inventoryType);

    List<Inventory> findAllByIsDeleted(int i);

    Optional<Inventory> findByTitleAndParentIdAndIsDeleted(String title, String parentId, int status);
}
