package com.carmel.guestjini.service.service.Inventory;

import com.carmel.guestjini.service.model.Inventory.InventoryDetail;
import com.carmel.guestjini.service.repository.Inventory.InventoryDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryDetailServiceImpl implements InventoryDetailService{

    @Autowired
    InventoryDetailRepository inventoryDetailRepository;

    @Override
    public InventoryDetail save(InventoryDetail inventoryDetail) {
        return inventoryDetailRepository.save(inventoryDetail);
    }

    @Override
    public List<InventoryDetail> findAllByInventoryId(String inventoryId) {
        return inventoryDetailRepository.findAllByInventoryId(inventoryId);
    }

    @Override
    public List<InventoryDetail> findAll(Specification<InventoryDetail> filterInventoryDetailByAvailability) {
        return inventoryDetailRepository.findAll(filterInventoryDetailByAvailability);
    }
}
