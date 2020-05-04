package com.carmel.guestjini.service.repository.Inventory;

import com.carmel.guestjini.service.model.Inventory.InventoryDetail;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryDetailRepository extends JpaRepository<InventoryDetail, String> {
    List<InventoryDetail> findAllByInventoryId(String inventoryId);

    List<InventoryDetail> findAll(Specification<InventoryDetail> filterInventoryDetailByAvailability);
}
