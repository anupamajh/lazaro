package com.carmel.common.datamigrator.controller;

import com.carmel.common.datamigrator.model.*;
import com.carmel.common.datamigrator.model.Package;
import com.carmel.common.datamigrator.repository.InventoryGroupRepository;
import com.carmel.common.datamigrator.repository.InventoryRepository;
import com.carmel.common.datamigrator.repository.InventoryTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/migration/inventory")
public class InventoryMigratorController {
    Logger logger = LoggerFactory.getLogger(InventoryMigratorController.class);

    private static String clientId = "21e43c55-28ef-478a-ae65-dc896e5eaa34";
    private static String orgId = "21e43c55-28ef-478a-ae65-dc896e5eaa35";

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    InventoryTypeRepository inventoryTypeRepository;

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

    @RequestMapping(value = "/assign-packages", method = RequestMethod.POST)
    public ResponseEntity<?> assignPackages() {
        try {
            this.assignPackagesToInventory();
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Import Complete", HttpStatus.OK);
    }

    private void assignPackagesToInventory() throws Exception {
        List<Package> packages = new ArrayList<>();
        Package aPackage = new Package();
        aPackage.setId("c2c81dca-029a-4bad-ba22-064de303a2b1");
        aPackage.setIsDeleted(0);
        packages.add(aPackage);
        aPackage = new Package();
        aPackage.setId("e08f9c6c-a7a5-4f37-863e-89e46cac460a");
        aPackage.setIsDeleted(0);
        packages.add(aPackage);
        aPackage = new Package();
        aPackage.setId("3f444cb2-e50d-4d77-b36f-437cf31f8560");
        aPackage.setIsDeleted(0);
        packages.add(aPackage);
        aPackage = new Package();
        aPackage.setId("74df04e3-74bc-4df3-bee0-fdf9cf295f61");
        aPackage.setIsDeleted(0);
        packages.add(aPackage);
        aPackage = new Package();
        aPackage.setId("acf578d1-ed0e-4f73-b2d8-53ed72cea846");
        aPackage.setIsDeleted(0);
        packages.add(aPackage);
        aPackage = new Package();
        aPackage.setId("b838a75f-683a-4d41-bd7e-751bab8c044f");
        aPackage.setIsDeleted(0);
        packages.add(aPackage);
        aPackage = new Package();
        aPackage.setIsDeleted(0);
        aPackage.setId("6040edcf-a768-4bb7-aa56-d7157325c496");
        packages.add(aPackage);
        aPackage = new Package();
        aPackage.setId("8dc5bbd8-fdb0-4b8d-a98a-82a9377b2441");
        aPackage.setIsDeleted(0);
        packages.add(aPackage);
        aPackage = new Package();
        aPackage.setId("db86c15d-0cf6-4863-8d16-0e378cd1d3fb");
        aPackage.setIsDeleted(0);
        packages.add(aPackage);
        aPackage = new Package();
        aPackage.setId("a6ea4896-1323-4d54-9968-73778f64be80");
        aPackage.setIsDeleted(0);
        packages.add(aPackage);
        aPackage = new Package();
        aPackage.setId("ea3c81c2-e5b4-4a46-8e80-c5056de8e0f5");
        aPackage.setIsDeleted(0);
        packages.add(aPackage);
        try {
            Optional<InventoryType> optionalInventoryType = inventoryTypeRepository.findById("500");
            if (optionalInventoryType.isPresent()) {
                List<Inventory> inventories = inventoryRepository.findAllByIsDeleted(0);
                for (Inventory inventory : inventories) {
                    if (
                            inventory.getInventoryType().getId().equals("500") ||
                                    inventory.getInventoryType().getId().equals("600")
                    ) {
                        inventory.setPackages(packages);
                        inventoryRepository.save(inventory);
                    }
                }
            }
        } catch (Exception ex) {
            throw ex;
        }


    }

}


