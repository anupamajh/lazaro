package com.carmel.guestjini.inventory.controller;

import com.carmel.guestjini.inventory.response.AmenityResponse;
import com.carmel.guestjini.inventory.services.AmenityService;
import com.carmel.guestjini.inventory.specifications.AmenitySpecification;
import com.carmel.guestjini.inventory.components.UserInformation;
import com.carmel.guestjini.inventory.model.Amenity;
import com.carmel.guestjini.inventory.model.Principal.UserInfo;
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
@RequestMapping(value = "/amenity")
public class AmenityController {
    Logger logger = LoggerFactory.getLogger(AmenityController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    AmenityService amenityService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AmenityResponse save(@Valid @RequestBody Amenity amenity) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AmenityResponse amenityResponse = new AmenityResponse();
        try {
            if (amenity.getId() == null) {
                amenity.setId("");
            }
            if(amenity.getOrgId() == null || amenity.getOrgId().isEmpty()){
                amenity.setOrgId(userInfo.getDefaultOrganization().getId());
            }
            if (amenity.getId().equals("")) {
                amenity.setCreatedBy(userInfo.getId());
                amenity.setCreationTime(new Date());
            } else {
                amenity.setLastModifiedBy(userInfo.getId());
                amenity.setLastModifiedTime(new Date());
            }
            amenity.setClientId(userInfo.getClient().getClientId());
            if (checkDuplicate(amenity)) {
                amenityResponse.setAmenity(amenity);
                amenityResponse.setSuccess(false);
                amenityResponse.setError("Duplicate Amenity Name name!");
            } else {
                amenityResponse.setAmenity(amenityService.save(amenity));
                amenityResponse.setSuccess(true);
                amenityResponse.setError("");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            amenityResponse.setSuccess(false);
            amenityResponse.setError(ex.getMessage());
        }
        return amenityResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public AmenityResponse moveToTrash(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AmenityResponse amenityResponse = new AmenityResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Amenity> optionalAmenity = amenityService.findById(formData.get("id"));
            if (optionalAmenity.isPresent()) {
                Amenity amenity = optionalAmenity.get();
                amenity.setIsDeleted(1);
                amenity.setDeletedBy(userInfo.getId());
                amenity.setDeletedTime(new Date());
                amenityResponse.setSuccess(true);
                amenityResponse.setError("");
                amenityResponse.setAmenity(amenityService.save(amenity));
            } else {
                amenityResponse.setSuccess(false);
                amenityResponse.setError("Error occurred while moving amenity to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            amenityResponse.setSuccess(false);
            amenityResponse.setError(ex.getMessage());
        }
        return amenityResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public AmenityResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AmenityResponse amenityResponse = new AmenityResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Amenity> optionalAmenity = amenityService.findById(formData.get("id"));
            if (optionalAmenity.isPresent()) {
                Amenity amenity = optionalAmenity.get();
                amenityResponse.setSuccess(true);
                amenityResponse.setError("");
                amenityResponse.setAmenity(amenity);
            } else {
                amenityResponse.setSuccess(false);
                amenityResponse.setError("Error occurred while Fetching amenity!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            amenityResponse.setSuccess(false);
            amenityResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return amenityResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public AmenityResponse getDeleted() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AmenityResponse amenityResponse = new AmenityResponse();
        try {
            amenityResponse.setAmenityList(amenityService.findAllByIsDeletedAndClientId(1, userInfo.getClient().getClientId()));
            amenityResponse.setSuccess(true);
            amenityResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            amenityResponse.setSuccess(true);
            amenityResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return amenityResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public AmenityResponse getAll() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AmenityResponse amenityResponse = new AmenityResponse();
        try {
            amenityResponse.setAmenityList(amenityService.findAllByIsDeletedAndClientId(0, userInfo.getClient().getClientId()));
            amenityResponse.setSuccess(true);
            amenityResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            amenityResponse.setSuccess(true);
            amenityResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return amenityResponse;
    }

    @RequestMapping(value = "/get-amenities", method = RequestMethod.POST)
    public AmenityResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AmenityResponse amenityResponse = new AmenityResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<Amenity> page = amenityService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            amenityResponse.setTotalRecords(page.getTotalElements());
            amenityResponse.setTotalPages(page.getTotalPages());
            amenityResponse.setAmenityList(page.getContent());
            amenityResponse.setCurrentRecords(amenityResponse.getAmenityList().size());
            amenityResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            amenityResponse.setSuccess(false);
            amenityResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return amenityResponse;
    }

    @RequestMapping(value = "/search-amenities", method = RequestMethod.POST)
    public AmenityResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AmenityResponse amenityResponse = new AmenityResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<Amenity> page;
            if (searchText == null) {
                page = amenityService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(),0,pageable);
            } else {
                page = amenityService.findAll(AmenitySpecification.textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
            }
            amenityResponse.setTotalRecords(page.getTotalElements());
            amenityResponse.setTotalPages(page.getTotalPages());
            amenityResponse.setAmenityList(page.getContent());
            amenityResponse.setCurrentRecords(amenityResponse.getAmenityList().size());
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            amenityResponse.setSuccess(false);
            amenityResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return amenityResponse;
    }

    private boolean checkDuplicate(Amenity amenity) {
        List<Amenity> amenities;
        if (amenity.getId().equals("")) {
            amenities = amenityService.findAllByClientIdAndTitle(amenity.getClientId(), amenity.getTitle());
        } else {
            amenities = amenityService.findAllByClientIdAndTitleAndIdIsNot(
                    amenity.getClientId(), amenity.getTitle(), amenity.getId());
        }
        if (amenities.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
