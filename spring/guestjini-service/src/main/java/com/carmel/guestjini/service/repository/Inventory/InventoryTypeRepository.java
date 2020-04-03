package com.carmel.guestjini.service.repository.Inventory;

import com.carmel.guestjini.service.model.Inventory.InventoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryTypeRepository extends JpaRepository<InventoryType, String> {
    List<InventoryType> findAllByClientIdAndTitleAndIsDeleted(String clientId, String title, int i);

    List<InventoryType> findAllByClientIdAndTitleAndIdIsNotAndIsDeleted(String clientId, String title, String id, int i);

    List<InventoryType> findAllByIsDeletedAndClientId(int isDeleted, String clientId);

    Page<InventoryType> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<InventoryType> findAll(Specification<InventoryType> textInAllColumns, Pageable pageable);
}
