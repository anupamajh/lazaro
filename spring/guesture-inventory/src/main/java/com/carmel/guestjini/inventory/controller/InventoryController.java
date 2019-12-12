package com.carmel.guestjini.inventory.controller;

import com.carmel.guestjini.inventory.components.UserInformation;
import com.carmel.guestjini.inventory.model.Inventory;
import com.carmel.guestjini.inventory.model.InventoryLocation;
import com.carmel.guestjini.inventory.model.Principal.UserInfo;
import com.carmel.guestjini.inventory.response.InventoryResponse;
import com.carmel.guestjini.inventory.services.InventoryLocationService;
import com.carmel.guestjini.inventory.services.InventoryService;
import com.carmel.guestjini.inventory.specifications.InventorySpecification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(value = "/inventory")
public class InventoryController {
    Logger logger = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    InventoryService inventoryService;

    @Autowired
    InventoryLocationService inventoryLocationService;

    @Autowired
    UserInformation userInformation;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public InventoryResponse save(@Valid @RequestBody Inventory inventory) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InventoryResponse inventoryResponse = new InventoryResponse();
        if (inventory.getId() == null) {
            inventory.setId("");
        }

        if(inventory.getOrgId() == null || inventory.getOrgId().isEmpty()){
            inventory.setOrgId(userInfo.getDefaultOrganization().getId());
        }

        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(inventory));
            if (inventory.getParent() != null) {
                Optional<Inventory> optionalInventory = inventoryService.findById(inventory.getParent().getId());
                inventory.setParent(optionalInventory.get());
            }
            inventory.setClientId(userInfo.getClient().getClientId());
            if (checkDuplicate(inventory)) {
                inventoryResponse.setInventory(inventory);
                inventoryResponse.setSuccess(false);
                inventoryResponse.setError("Duplicate Inventory!!");
            } else {
                InventoryLocation inventoryLocation = inventory.getInventoryLocation();
                Inventory savedInventory;
                if (inventory.getId().equals("")) {
                    inventory.setCreatedBy(userInfo.getId());
                    inventory.setCreationTime(new Date());
                    inventory.setInventoryLocation(null);
                    savedInventory = inventoryService.save(inventory);
                    inventoryLocation.setInventory(inventory);
                    savedInventory.setInventoryLocation(inventoryLocation);
                    inventoryResponse.setInventory(inventoryService.save(savedInventory));
                } else {
                    Optional<Inventory> optionalInventory = inventoryService.findById(inventory.getId());
                    savedInventory = optionalInventory.get();
                    inventory.setLastModifiedBy(userInfo.getId());
                    inventory.setLastModifiedTime(new Date());
                    inventoryLocation.setId(savedInventory.getInventoryLocation().getId());
                    inventoryLocation.setInventory(inventory);
                    inventory.setInventoryLocation(inventoryLocation);
                    savedInventory = inventoryService.save(inventory);
                    inventoryResponse.setInventory(inventoryService.save(savedInventory));
                }
                inventoryResponse.setSuccess(true);
                inventoryResponse.setError("");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            inventoryResponse.setSuccess(false);
            inventoryResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return inventoryResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public InventoryResponse moveToTrash(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InventoryResponse inventoryResponse = new InventoryResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Inventory> optionalInventory = inventoryService.findById(formData.get("id"));
            if (optionalInventory != null) {
                Inventory inventory = optionalInventory.get();
                inventory.setIsDeleted(1);
                inventory.setDeletedBy(userInfo.getId());
                inventory.setDeletedTime(new Date());
                inventoryResponse.setSuccess(true);
                inventoryResponse.setError("");
                inventoryResponse.setInventory(inventoryService.save(inventory));
            } else {
                inventoryResponse.setSuccess(false);
                inventoryResponse.setError("Error occurred while moving inventory to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            inventoryResponse.setSuccess(false);
            inventoryResponse.setError(ex.getMessage());
        }
        return inventoryResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public InventoryResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InventoryResponse inventoryResponse = new InventoryResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Inventory> optionalInventory = inventoryService.findById(formData.get("id"));
            if (optionalInventory.isPresent()) {
                Inventory inventory = optionalInventory.get();
                inventoryResponse.setSuccess(true);
                inventoryResponse.setError("");
                inventoryResponse.setInventory(inventory);
            } else {
                inventoryResponse.setSuccess(false);
                inventoryResponse.setError("Error occurred while fetching Inventory!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            inventoryResponse.setSuccess(false);
            inventoryResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return inventoryResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public InventoryResponse getDeleted() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InventoryResponse inventoryResponse = new InventoryResponse();
        try {
            inventoryResponse.setInventoryList(inventoryService.findAllByIsDeletedAndClientId(1, userInfo.getClient().getClientId()));
            inventoryResponse.setSuccess(true);
            inventoryResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            inventoryResponse.setSuccess(true);
            inventoryResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return inventoryResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public InventoryResponse getAll() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InventoryResponse inventoryResponse = new InventoryResponse();
        try {
            inventoryResponse.setInventoryList(inventoryService.findAllByIsDeletedAndClientId(0, userInfo.getClient().getClientId()));
            inventoryResponse.setSuccess(true);
            inventoryResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            inventoryResponse.setSuccess(true);
            inventoryResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return inventoryResponse;
    }

    @RequestMapping(value = "/get-inventories", method = RequestMethod.POST)
    public InventoryResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InventoryResponse inventoryResponse = new InventoryResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<Inventory> page = inventoryService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            inventoryResponse.setTotalRecords(page.getTotalElements());
            inventoryResponse.setTotalPages(page.getTotalPages());
            inventoryResponse.setInventoryList(page.getContent());
            inventoryResponse.setCurrentRecords(inventoryResponse.getInventoryList().size());
            inventoryResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            inventoryResponse.setSuccess(false);
            inventoryResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return inventoryResponse;
    }

    @RequestMapping(value = "/search-inventories", method = RequestMethod.POST)
    public InventoryResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InventoryResponse inventoryResponse = new InventoryResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<Inventory> page;
            if (searchText == null) {
                page = inventoryService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(),0,pageable);
            } else {
                page = inventoryService.findAll(InventorySpecification.textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
            }
            inventoryResponse.setTotalRecords(page.getTotalElements());
            inventoryResponse.setTotalPages(page.getTotalPages());
            inventoryResponse.setInventoryList(page.getContent());
            inventoryResponse.setCurrentRecords(inventoryResponse.getInventoryList().size());
            inventoryResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            inventoryResponse.setSuccess(false);
            inventoryResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return inventoryResponse;
    }

    private boolean checkDuplicate(Inventory inventory) {
        List<Inventory> inventories;
        if (inventory.getId() == "") {
            inventories = inventoryService.findAllByClientIdAndTitle(inventory.getClientId(), inventory.getTitle());
        } else {
            inventories = inventoryService.findAllByClientIdAndTitleAndIdIsNot(inventory.getClientId(), inventory.getTitle(), inventory.getId());
        }
        if (inventories.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}
