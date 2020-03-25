package com.carmel.guestjini.inventory.services;

import com.carmel.guestjini.inventory.model.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    List<Inventory> findAllByClientIdAndTitle(String clientId, String title);

    List<Inventory> findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id);

    Inventory save(Inventory inventory);

    Optional<Inventory> findById(String id);

    List<Inventory> findAllByIsDeletedAndClientId(int isDeleted, String clientId);

    Page<Inventory> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<Inventory> findAll(Specification<Inventory> textInAllColumns, Pageable pageable);

    List<Inventory> getAllParents(String id);

    List<Inventory> findAll(Specification<Inventory> filterInventoryByPackage);
}
