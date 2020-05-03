package com.carmel.guestjini.service.controller.Inventory;

import com.carmel.guestjini.service.components.UserInformation;
import com.carmel.guestjini.service.model.Inventory.Inventory;
import com.carmel.guestjini.service.model.Inventory.InventoryDetail;
import com.carmel.guestjini.service.service.Inventory.InventoryDetailService;
import com.carmel.guestjini.service.service.Inventory.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/inventory-detail")
public class InventoryDetailController {
    Logger logger = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    InventoryDetailService inventoryDetailService;

    @Autowired
    InventoryService inventoryService;

    @RequestMapping(value = "/populate-inventory-details", method = RequestMethod.POST)
    public void populateInventoryDetails() {
        try {
            String propertyId = "";
            String blockId = "";
            String floorId = "";
            String flatId = "";
            String roomId = "";
            String podId = "";
            List<Inventory> properties = inventoryService.findAllByParentIdAndIsDeleted(null, 0);
            for (Inventory property : properties) {
                propertyId = property.getId();
                if (!isDuplicateEntry(property)) {
                    inventoryDetailService.save(getInventoryDetail(
                            property,
                            propertyId
                    ));
                }
                List<Inventory> blocks = inventoryService.findAllByParentIdAndIsDeleted(property.getId(), 0);
                for (Inventory block : blocks) {
                    blockId = block.getId();
                    if (!isDuplicateEntry(block)) {
                        inventoryDetailService.save(getInventoryDetail(
                                block,
                                propertyId,
                                blockId
                        ));
                    }
                    List<Inventory> floors = inventoryService.findAllByParentIdAndIsDeleted(block.getId(), 0);
                    for (Inventory floor : floors) {
                        floorId = floor.getId();
                        if (!isDuplicateEntry(floor)) {
                            inventoryDetailService.save(getInventoryDetail(
                                    floor,
                                    propertyId,
                                    blockId,
                                    floorId
                            ));
                        }
                        List<Inventory> flats = inventoryService.findAllByParentIdAndIsDeleted(floor.getId(), 0);
                        for (Inventory flat : flats) {
                            flatId = flat.getId();
                            if (!isDuplicateEntry(flat)) {
                                inventoryDetailService.save(getInventoryDetail(
                                        flat,
                                        propertyId,
                                        blockId,
                                        floorId,
                                        flatId
                                ));
                            }
                            List<Inventory> rooms = inventoryService.findAllByParentIdAndIsDeleted(flat.getId(), 0);
                            for (Inventory room : rooms) {
                                roomId = room.getId();
                                if (!isDuplicateEntry(room)) {
                                    inventoryDetailService.save(getInventoryDetail(
                                            room,
                                            propertyId,
                                            blockId,
                                            floorId,
                                            flatId,
                                            roomId
                                    ));
                                }
                                List<Inventory> pods = inventoryService.findAllByParentIdAndIsDeleted(room.getId(), 0);
                                for (Inventory pod : pods) {
                                    podId = pod.getId();
                                    if (!isDuplicateEntry(pod)) {
                                        inventoryDetailService.save(getInventoryDetail(
                                                pod,
                                                propertyId,
                                                blockId,
                                                floorId,
                                                flatId,
                                                roomId,
                                                podId
                                        ));
                                    }
                                }
                            }
                        }
                    }
                }

            }
        } catch (Exception ex) {

        }
    }

    private InventoryDetail getInventoryDetail(
            Inventory inventory,
            String propertyId,
            String blockId,
            String floorId,
            String flatId,
            String roomId,
            String podId
    ) {
        InventoryDetail inventoryDetail =
                getInventoryDetail(inventory,propertyId, blockId, floorId,flatId, roomId);
        inventoryDetail.setPodId(podId);
        return inventoryDetail;
    }

    private InventoryDetail getInventoryDetail(
            Inventory inventory,
            String propertyId,
            String blockId,
            String floorId,
            String flatId,
            String roomId
    ) {
        InventoryDetail inventoryDetail =
                getInventoryDetail(inventory,propertyId, blockId, floorId,flatId);
        inventoryDetail.setRoomId(roomId);
        return inventoryDetail;
    }

    private InventoryDetail getInventoryDetail(
            Inventory inventory,
            String propertyId,
            String blockId,
            String floorId,
            String flatId
    ) {
        InventoryDetail inventoryDetail =
                getInventoryDetail(inventory,propertyId, blockId, floorId);
        inventoryDetail.setFlatId(flatId);
        return inventoryDetail;
    }

    private InventoryDetail getInventoryDetail(
            Inventory inventory,
            String propertyId,
            String blockId,
            String floorId) {

        InventoryDetail inventoryDetail =
                getInventoryDetail(inventory,propertyId, blockId);
        inventoryDetail.setFloorId(floorId);
        return inventoryDetail;
    }

    private InventoryDetail getInventoryDetail(
            Inventory inventory,
            String propertyId,
            String blockId
    ) {
        InventoryDetail inventoryDetail = getInventoryDetail(inventory, propertyId);
        inventoryDetail.setBlockId(blockId);
        return inventoryDetail;
    }

    private InventoryDetail getInventoryDetail(Inventory inventory, String propertyId) {
        InventoryDetail inventoryDetail = new InventoryDetail();
        inventoryDetail.setClientId(inventory.getClientId());
        inventoryDetail.setOrgId(inventory.getOrgId());
        inventoryDetail.setTitle(inventory.getTitle());
        inventoryDetail.setInventoryId(inventory.getId());
        inventoryDetail.setInventoryType(inventory.getInventoryType());
        inventoryDetail.setPropertyId(propertyId);
        inventoryDetail.setHasBalcony(inventory.getInventoryAttributes().getHasAttachedBalcony());
        inventoryDetail.setHasBathroom(inventory.getInventoryAttributes().getHasAttachedBathroom());
        inventoryDetail.setIsTrial(0);
        inventoryDetail.setCreatedBy(inventory.getCreatedBy());
        inventoryDetail.setCreationTime(inventory.getCreationTime());
        inventoryDetail.setLastModifiedBy(inventory.getLastModifiedBy());
        inventoryDetail.setLastModifiedTime(inventory.getLastModifiedTime());
        inventoryDetail.setIsDeleted(inventory.getIsDeleted());
        inventoryDetail.setDeletedBy(inventory.getDeletedBy());
        inventoryDetail.setDeletedTime(inventory.getDeletedTime());
        return inventoryDetail;
    }

    private Boolean isDuplicateEntry(Inventory inventory) {
        List<InventoryDetail> inventoryDetails =
                inventoryDetailService.findAllByInventoryId(inventory.getId());
        if (inventoryDetails.isEmpty()) {
            return false;
        }
        return true;
    }


}
