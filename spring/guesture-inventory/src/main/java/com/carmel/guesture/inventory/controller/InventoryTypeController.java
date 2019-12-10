package com.carmel.guesture.inventory.controller;

import com.carmel.guesture.inventory.components.UserInformation;
import com.carmel.guesture.inventory.model.InventoryType;
import com.carmel.guesture.inventory.model.Principal.UserInfo;
import com.carmel.guesture.inventory.response.InventoryTypeResponse;
import com.carmel.guesture.inventory.services.InventoryTypeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.carmel.guesture.inventory.specifications.InventoryTypeSpecification.textInAllColumns;

@RestController
@RequestMapping("/inventory-type")
public class InventoryTypeController {

    Logger logger = LoggerFactory.getLogger(InventoryTypeController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    InventoryTypeService inventoryTypeService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public InventoryTypeResponse save(@Valid @RequestBody InventoryType inventoryType) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InventoryTypeResponse inventoryTypeResponse = new InventoryTypeResponse();
        try {
            if (inventoryType.getId() == null) {
                inventoryType.setId("");
            }
            if (inventoryType.getId().equals("")) {
                inventoryType.setCreatedBy(userInfo.getId());
                inventoryType.setCreationTime(new Date());
            } else {
                inventoryType.setLastModifiedBy(userInfo.getId());
                inventoryType.setLastModifiedTime(new Date());
            }
            inventoryType.setClientId(userInfo.getClient().getClientId());
            if (checkDuplicate(inventoryType)) {
                inventoryTypeResponse.setInventoryType(inventoryType);
                inventoryTypeResponse.setSuccess(false);
                inventoryTypeResponse.setError("Duplicate Inventory Type Name name!");
            } else {
                inventoryTypeResponse.setInventoryType(inventoryTypeService.save(inventoryType));
                inventoryTypeResponse.setSuccess(true);
                inventoryTypeResponse.setError("");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            inventoryTypeResponse.setSuccess(false);
            inventoryTypeResponse.setError(ex.getMessage());
        }
        return inventoryTypeResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public InventoryTypeResponse moveToTrash(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InventoryTypeResponse inventoryTypeResponse = new InventoryTypeResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<InventoryType> optionalInventoryType = inventoryTypeService.findById(formData.get("id"));
            if (optionalInventoryType != null) {
                InventoryType inventoryType = optionalInventoryType.get();
                inventoryType.setIsDeleted(1);
                inventoryType.setDeletedBy(userInfo.getId());
                inventoryType.setDeletedTime(new Date());
                inventoryTypeResponse.setSuccess(true);
                inventoryTypeResponse.setError("");
                inventoryTypeResponse.setInventoryType(inventoryTypeService.save(inventoryType));
            } else {
                inventoryTypeResponse.setSuccess(false);
                inventoryTypeResponse.setError("Error occurred while moving inventory type to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            inventoryTypeResponse.setSuccess(false);
            inventoryTypeResponse.setError(ex.getMessage());
        }
        return inventoryTypeResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public InventoryTypeResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InventoryTypeResponse inventoryTypeResponse = new InventoryTypeResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<InventoryType> optionalInventoryType = inventoryTypeService.findById(formData.get("id"));
            if (optionalInventoryType.isPresent()) {
                InventoryType inventoryType = optionalInventoryType.get();
                inventoryTypeResponse.setSuccess(true);
                inventoryTypeResponse.setError("");
                inventoryTypeResponse.setInventoryType(inventoryType);
            } else {
                inventoryTypeResponse.setSuccess(false);
                inventoryTypeResponse.setError("Error occurred while moving organization to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            inventoryTypeResponse.setSuccess(false);
            inventoryTypeResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return inventoryTypeResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public InventoryTypeResponse getDeleted() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InventoryTypeResponse inventoryTypeResponse = new InventoryTypeResponse();
        try {
            inventoryTypeResponse.setInventoryTypeList(inventoryTypeService.findAllByIsDeletedAndClientId(1, userInfo.getClient().getClientId()));
            inventoryTypeResponse.setSuccess(true);
            inventoryTypeResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            inventoryTypeResponse.setSuccess(true);
            inventoryTypeResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return inventoryTypeResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public InventoryTypeResponse getAll() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InventoryTypeResponse inventoryTypeResponse = new InventoryTypeResponse();
        try {
            inventoryTypeResponse.setInventoryTypeList(inventoryTypeService.findAllByIsDeletedAndClientId(0, userInfo.getClient().getClientId()));
            inventoryTypeResponse.setSuccess(true);
            inventoryTypeResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            inventoryTypeResponse.setSuccess(true);
            inventoryTypeResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return inventoryTypeResponse;
    }

    @RequestMapping(value = "/get-inventory-types", method = RequestMethod.POST)
    public InventoryTypeResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InventoryTypeResponse inventoryTypeResponse = new InventoryTypeResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<InventoryType> page = inventoryTypeService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            inventoryTypeResponse.setTotalRecords(page.getTotalElements());
            inventoryTypeResponse.setTotalPages(page.getTotalPages());
            inventoryTypeResponse.setInventoryTypeList(page.getContent());
            inventoryTypeResponse.setCurrentRecords(inventoryTypeResponse.getInventoryTypeList().size());
            inventoryTypeResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            inventoryTypeResponse.setSuccess(false);
            inventoryTypeResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return inventoryTypeResponse;
    }

    @RequestMapping(value = "/search-inventory-types", method = RequestMethod.POST)
    public InventoryTypeResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InventoryTypeResponse inventoryTypeResponse = new InventoryTypeResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<InventoryType> page;
            if (searchText == null) {
                page = inventoryTypeService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(),0,pageable);
            } else {
                page = inventoryTypeService.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
            }
            inventoryTypeResponse.setTotalRecords(page.getTotalElements());
            inventoryTypeResponse.setTotalPages(page.getTotalPages());
            inventoryTypeResponse.setInventoryTypeList(page.getContent());
            inventoryTypeResponse.setCurrentRecords(inventoryTypeResponse.getInventoryTypeList().size());
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            inventoryTypeResponse.setSuccess(false);
            inventoryTypeResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return inventoryTypeResponse;
    }


    private boolean checkDuplicate(InventoryType inventoryType) {
        List<InventoryType> inventoryTypeList;
        if (inventoryType.getId().equals("")) {
            inventoryTypeList = inventoryTypeService.findAllByClientIdAndTitle(inventoryType.getClientId(), inventoryType.getTitle());
        } else {
            inventoryTypeList = inventoryTypeService.findAllByClientIdAndTitleAndIdIsNot(
                    inventoryType.getClientId(), inventoryType.getTitle(), inventoryType.getId());
        }
        if (inventoryTypeList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }


}
