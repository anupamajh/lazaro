package com.carmel.guestjini.inventory.repository;

import com.carmel.guestjini.inventory.model.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, String> {
    List<Inventory> findAllByClientIdAndTitleAndIsDeleted(String clientId, String title, int isDeleted);

    List<Inventory> findAllByClientIdAndTitleAndIdIsNotAndIsDeleted(String clientId, String title, String id, int isDeleted);

    List<Inventory> findAllByIsDeletedAndClientId(int isDeleted, String clientId);

    Page<Inventory> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<Inventory> findAll(Specification<Inventory> textInAllColumns, Pageable pageable);
}