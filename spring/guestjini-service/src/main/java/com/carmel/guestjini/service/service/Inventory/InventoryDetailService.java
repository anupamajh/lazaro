package com.carmel.guestjini.service.service.Inventory;

import com.carmel.guestjini.service.model.Inventory.InventoryDetail;

import java.util.List;

public interface InventoryDetailService {
    List<InventoryDetail> findAllByInventoryId(String inventoryId);

    InventoryDetail save(InventoryDetail inventoryDetail);
}
