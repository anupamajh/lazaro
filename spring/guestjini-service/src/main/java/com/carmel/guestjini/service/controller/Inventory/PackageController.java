package com.carmel.guestjini.service.controller.Inventory;

import com.carmel.guestjini.service.components.UserInformation;
import com.carmel.guestjini.service.config.PackageConstants;
import com.carmel.guestjini.service.model.Inventory.Package;
import com.carmel.guestjini.service.model.Inventory.PackageCharge;
import com.carmel.guestjini.service.model.Principal.UserInfo;
import com.carmel.guestjini.service.response.Inventory.PackageResponse;
import com.carmel.guestjini.service.service.Inventory.AmenityService;
import com.carmel.guestjini.service.service.Inventory.PackageChargeService;
import com.carmel.guestjini.service.service.Inventory.PackageService;
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

import static com.carmel.guestjini.service.specification.Inventory.PackageSpecification.textInAllColumns;

@RestController
@RequestMapping(value = "/package")
public class PackageController {

    Logger logger = LoggerFactory.getLogger(PackageController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    PackageService packageService;

    @Autowired
    PackageChargeService packageChargeService;

    @Autowired
    AmenityService amenityService;

    @Autowired
    PackageConstants packageConstants;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public PackageResponse save(@Valid @RequestBody Package aPackage) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        PackageResponse packageResponse = new PackageResponse();
        try {
            if (aPackage.getId() == null) {
                aPackage.setId("");
            }
            if(aPackage.getOrgId() == null || aPackage.getOrgId().isEmpty()){
                if(userInfo.getDefaultOrganization() != null) {
                    aPackage.setOrgId(userInfo.getDefaultOrganization().getId());
                }
            }
            if (aPackage.getId().equals("")) {
                aPackage.setCreatedBy(userInfo.getId());
                aPackage.setCreationTime(new Date());
            } else {
                aPackage.setLastModifiedBy(userInfo.getId());
                aPackage.setLastModifiedTime(new Date());
            }
            aPackage.setClientId(userInfo.getClient().getClientId());
//            if(aPackage.getAmenities()!=null){
//                List<Amenity> amenities = new ArrayList<>();
//                aPackage.getAmenities().forEach(amenity -> {
//                    Optional<Amenity> optionalAmenity =  amenityService.findById(amenity.getId());
//                    if(optionalAmenity.isPresent()){
//                        amenities.add(optionalAmenity.get());
//                    }
//                });
//                aPackage.setAmenities(amenities);
//            }

            if (checkDuplicate(aPackage)) {
                packageResponse.setPackage(aPackage);
                packageResponse.setSuccess(false);
                packageResponse.setError("Duplicate Package Name name!");
            } else {
                List<PackageCharge> packageCharges = aPackage.getPackageCharges();
                aPackage.setPackageCharges(null);
                Package savedPackage = packageService.save(aPackage);
                if(packageCharges != null){
                    for(int i = 0; i< packageCharges.size(); i++){
                        packageCharges.get(i).setaPackage(savedPackage);
                        packageCharges.get(i).setClientId(savedPackage.getClientId());
                        packageCharges.get(i).setOrgId(savedPackage.getOrgId());
                    }
                }
                savedPackage.setPackageCharges(packageCharges);
                packageResponse.setPackage(packageService.save(savedPackage));
                packageResponse.setSuccess(true);
                packageResponse.setError("");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            packageResponse.setSuccess(false);
            packageResponse.setError(ex.getMessage());
        }
        return packageResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public PackageResponse moveToTrash(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        PackageResponse packageResponse = new PackageResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Package> optionalPackage = packageService.findById(formData.get("id"));
            if (optionalPackage != null) {
                Package aPackage = optionalPackage.get();
                aPackage.setIsDeleted(1);
                aPackage.setDeletedBy(userInfo.getId());
                aPackage.setDeletedTime(new Date());
                packageResponse.setSuccess(true);
                packageResponse.setError("");
                packageResponse.setPackage(packageService.save(aPackage));
            } else {
                packageResponse.setSuccess(false);
                packageResponse.setError("Error occurred while moving package to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            packageResponse.setSuccess(false);
            packageResponse.setError(ex.getMessage());
        }
        return packageResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public PackageResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        PackageResponse packageResponse = new PackageResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Package> optionalPackage = packageService.findById(formData.get("id"));
            if (optionalPackage.isPresent()) {
                Package aPackage = optionalPackage.get();
                packageResponse.setSuccess(true);
                packageResponse.setError("");
                packageResponse.setPackage(aPackage);
            } else {
                packageResponse.setSuccess(false);
                packageResponse.setError("Error occurred while Fetching inventory!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            packageResponse.setSuccess(false);
            packageResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return packageResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public PackageResponse getDeleted() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        PackageResponse packageResponse = new PackageResponse();
        try {
            packageResponse.setPackageList(packageService.findAllByIsDeletedAndClientId(1, userInfo.getClient().getClientId()));
            packageResponse.setSuccess(true);
            packageResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            packageResponse.setSuccess(true);
            packageResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return packageResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public PackageResponse getAll() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        PackageResponse packageResponse = new PackageResponse();
        try {
            packageResponse.setPackageList(packageService.findAllByIsDeletedAndClientId(0, userInfo.getClient().getClientId()));
            packageResponse.setSuccess(true);
            packageResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            packageResponse.setSuccess(true);
            packageResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return packageResponse;
    }

    @RequestMapping(value = "/get-packages", method = RequestMethod.POST)
    public PackageResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        PackageResponse packageResponse = new PackageResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("packageTitle"));
            Page<Package> page = packageService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            packageResponse.setTotalRecords(page.getTotalElements());
            packageResponse.setTotalPages(page.getTotalPages());
            packageResponse.setPackageList(page.getContent());
            packageResponse.setCurrentRecords(packageResponse.getPackageList().size());
            packageResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            packageResponse.setSuccess(false);
            packageResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return packageResponse;
    }

    @RequestMapping(value = "/search-packages", method = RequestMethod.POST)
    public PackageResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        PackageResponse packageResponse = new PackageResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("packageTitle"));
            Page<Package> page;
            if (searchText == null) {
                page = packageService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(),0,pageable);
            } else {
                page = packageService.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
            }
            packageResponse.setTotalRecords(page.getTotalElements());
            packageResponse.setTotalPages(page.getTotalPages());
            packageResponse.setPackageList(page.getContent());
            packageResponse.setCurrentRecords(packageResponse.getPackageList().size());
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            packageResponse.setSuccess(false);
            packageResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return packageResponse;
    }

    @RequestMapping(value = "/find-package-by-name", method = RequestMethod.POST)
    public PackageResponse findPackageByName(@RequestBody Map<String, String> formData){
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        String id = "";
        if(packageConstants
                .getPackageConstants()
                .containsKey(formData.get("packageName"))
        ){
            id = packageConstants
                    .getPackageConstants().get(formData.get("packageName"));
        }
         PackageResponse packageResponse = new PackageResponse();
        try {
            if(id.trim().equals("")){
                throw new Exception("Unable to find package with name " + formData.get("packageName"));
            }
            Optional<Package> optionalPackage = packageService.findById(id);
            if (optionalPackage.isPresent()) {
                Package aPackage = optionalPackage.get();
                packageResponse.setSuccess(true);
                packageResponse.setError("");
                packageResponse.setPackage(aPackage);
            } else {
                packageResponse.setSuccess(false);
                packageResponse.setError("Error occurred while Fetching inventory!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            packageResponse.setSuccess(false);
            packageResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return packageResponse;
    }

    private boolean checkDuplicate(Package aPackage) {
        List<Package> packages;
        if (aPackage.getId().equals("")) {
            packages = packageService.findAllByClientIdAndPackageTitle(aPackage.getClientId(), aPackage.getPackageTitle());
        } else {
            packages = packageService.findAllByClientIdAndPackageTitleAndIdIsNot(
                    aPackage.getClientId(), aPackage.getPackageTitle(), aPackage.getId());
        }
        if (packages.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

}