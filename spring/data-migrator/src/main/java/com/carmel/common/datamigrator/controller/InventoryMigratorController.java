package com.carmel.common.datamigrator.controller;

import com.carmel.common.datamigrator.model.Inventory;
import com.carmel.common.datamigrator.model.InventoryAttributes;
import com.carmel.common.datamigrator.model.InventoryGroup;
import com.carmel.common.datamigrator.repository.InventoryGroupRepository;
import com.carmel.common.datamigrator.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/migration/inventory")
public class InventoryMigratorController {
    Logger logger = LoggerFactory.getLogger(InventoryMigratorController.class);

    private static String clientId = "21e43c55-28ef-478a-ae65-dc896e5eaa34";
    private static String orgId = "21e43c55-28ef-478a-ae65-dc896e5eaa35";

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    InventoryGroupRepository inventoryGroupRepository;


    @RequestMapping(value = "/migrate", method = RequestMethod.POST)
    public ResponseEntity<?> migrate() {
        try {
            this.getExistingInventoryByParentId(null, null);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Import Complete", HttpStatus.OK);
    }

    @Transactional(rollbackFor = Exception.class)
    void getExistingInventoryByParentId(String parentId, Inventory parent) throws Exception {
        try {
            List<InventoryGroup> inventoryGroups = inventoryGroupRepository.findAllByParentId(parentId);
            inventoryGroups.forEach(inventoryGroup -> {
                Inventory inventory = new Inventory(inventoryGroup);
                inventory.setClientId(clientId);
                inventory.setParent(parent);
                Inventory savedInventory = inventoryRepository.save(inventory);
                InventoryAttributes inventoryAttributes = new InventoryAttributes();
                inventoryAttributes.setHasAttachedBalcony(0);
                inventoryAttributes.setHasAttachedBathroom(0);
                inventoryAttributes.setInventory(savedInventory);
                savedInventory.setInventoryAttributes(inventoryAttributes);
                savedInventory = inventoryRepository.save(savedInventory);
                logger.info(savedInventory.getTitle() + " Created");
                try {
                    this.getExistingInventoryByParentId(inventoryGroup.getId(), savedInventory);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception ex) {
            throw ex;
        }
    }
}
