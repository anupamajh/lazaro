package com.carmel.guestjini.service.service.Inventory;

import com.carmel.guestjini.service.model.Inventory.InventoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface InventoryTypeService {
    List<InventoryType> findAllByClientIdAndTitle(String clientId, String title);

    List<InventoryType> findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id);

    InventoryType save(InventoryType inventoryType);

    Optional<InventoryType> findById(String id);

    List<InventoryType> findAllByIsDeletedAndClientId(int isDeleted, String clientId);

    Page<InventoryType> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<InventoryType> findAll(Specification<InventoryType> textInAllColumns, Pageable pageable);
}
