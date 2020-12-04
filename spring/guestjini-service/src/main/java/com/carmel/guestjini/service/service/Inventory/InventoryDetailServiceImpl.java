package com.carmel.guestjini.service.service.Inventory;

import com.carmel.guestjini.service.model.Inventory.InventoryDetail;
import com.carmel.guestjini.service.repository.Inventory.InventoryDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryDetailServiceImpl implements InventoryDetailService {

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

    @Override
    public Optional<InventoryDetail> findByInventoryId(String selectedInventoryId) {
        return inventoryDetailRepository.findByInventoryId(selectedInventoryId);
    }

    @Override
    public String getInventoryPath(String inventoryId) {
        Optional<InventoryDetail> optionalInventoryDetail = this.findByInventoryId(inventoryId);
        if (optionalInventoryDetail.isPresent()) {
            String inventoryPath = "";
            String delim = " ";
            InventoryDetail inventoryDetail = optionalInventoryDetail.get();

            String propertyId = inventoryDetail.getPropertyId();
            String blockId = inventoryDetail.getBlockId();
            String floorId = inventoryDetail.getFloorId();
            String flatId = inventoryDetail.getFlatId();
            String roomId = inventoryDetail.getRoomId();
            String podId = inventoryDetail.getPodId();
            inventoryPath = inventoryDetail.getTitle();
            if (roomId != null) {
                optionalInventoryDetail = this.findByInventoryId(roomId);
                inventoryDetail = optionalInventoryDetail.get();
                inventoryPath = inventoryDetail.getTitle() + " / " + inventoryPath;
            }
            if (flatId != null) {
                optionalInventoryDetail = this.findByInventoryId(flatId);
                inventoryDetail = optionalInventoryDetail.get();
                inventoryPath = inventoryDetail.getTitle() + " / " + inventoryPath;
            }
            if (floorId != null) {

                optionalInventoryDetail = this.findByInventoryId(floorId);
                inventoryDetail = optionalInventoryDetail.get();
                inventoryPath = inventoryDetail.getTitle() + " / " + inventoryPath;
            }
            if (blockId != null) {

                optionalInventoryDetail = this.findByInventoryId(blockId);
                inventoryDetail = optionalInventoryDetail.get();
                inventoryPath = inventoryDetail.getTitle() + " / " + inventoryPath;
            }
            if (propertyId != null) {
                optionalInventoryDetail = this.findByInventoryId(propertyId);
                inventoryDetail = optionalInventoryDetail.get();
                inventoryPath = inventoryDetail.getTitle() + " / " + inventoryPath;
            }
            return inventoryPath;

        } else {
            return "";
        }
    }
}
