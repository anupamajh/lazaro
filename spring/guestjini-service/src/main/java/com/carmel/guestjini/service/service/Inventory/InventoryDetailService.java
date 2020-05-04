package com.carmel.guestjini.service.service.Inventory;

import com.carmel.guestjini.service.model.Inventory.InventoryDetail;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface InventoryDetailService {
    List<InventoryDetail> findAllByInventoryId(String inventoryId);

    InventoryDetail save(InventoryDetail inventoryDetail);

    List<InventoryDetail> findAll(Specification<InventoryDetail> filterInventoryDetailByAvailability);
}
