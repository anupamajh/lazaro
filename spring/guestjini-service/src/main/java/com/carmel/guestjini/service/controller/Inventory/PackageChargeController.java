package com.carmel.guestjini.service.controller.Inventory;

import com.carmel.guestjini.service.components.UserInformation;
import com.carmel.guestjini.service.model.Inventory.PackageCharge;
import com.carmel.guestjini.service.model.Principal.UserInfo;
import com.carmel.guestjini.service.response.Inventory.PackageChargeResponse;
import com.carmel.guestjini.service.service.Inventory.PackageChargeService;
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

import static com.carmel.guestjini.service.specification.Inventory.PackageChargeSpecification.textInAllColumns;

@RestController
@RequestMapping(value = "/package-charge")
public class PackageChargeController {
    Logger logger = LoggerFactory.getLogger(PackageController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    PackageChargeService packageChargeService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public PackageChargeResponse save(@Valid @RequestBody PackageCharge packageCharge) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        PackageChargeResponse packageChargeResponse = new PackageChargeResponse();
        try {
            if (packageCharge.getId() == null) {
                packageCharge.setId("");
            }
            if(packageCharge.getOrgId() == null || packageCharge.getOrgId().isEmpty()){
                if(userInfo.getDefaultOrganization() != null) {
                    packageCharge.setOrgId(userInfo.getDefaultOrganization().getId());
                }
            }
            if (packageCharge.getId().equals("")) {
                packageCharge.setCreatedBy(userInfo.getId());
                packageCharge.setCreationTime(new Date());
            } else {
                packageCharge.setLastModifiedBy(userInfo.getId());
                packageCharge.setLastModifiedTime(new Date());
            }
            packageCharge.setClientId(userInfo.getClient().getClientId());
            if (checkDuplicate(packageCharge)) {
                packageChargeResponse.setPackageCharge(packageCharge);
                packageChargeResponse.setSuccess(false);
                packageChargeResponse.setError("Duplicate Package Charge name!");
            } else {
                packageChargeResponse.setPackageCharge(packageChargeService.save(packageCharge));
                packageChargeResponse.setSuccess(true);
                packageChargeResponse.setError("");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            packageChargeResponse.setSuccess(false);
            packageChargeResponse.setError(ex.getMessage());
        }
        return packageChargeResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public PackageChargeResponse moveToTrash(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        PackageChargeResponse packageChargeResponse = new PackageChargeResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<PackageCharge> optionalPackageCharge = packageChargeService.findById(formData.get("id"));
            if (optionalPackageCharge != null) {
                PackageCharge packageCharge = optionalPackageCharge.get();
                packageCharge.setIsDeleted(1);
                packageCharge.setDeletedBy(userInfo.getId());
                packageCharge.setDeletedTime(new Date());
                packageChargeResponse.setSuccess(true);
                packageChargeResponse.setError("");
                packageChargeResponse.setPackageCharge(packageChargeService.save(packageCharge));
            } else {
                packageChargeResponse.setSuccess(false);
                packageChargeResponse.setError("Error occurred while moving package charge to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            packageChargeResponse.setSuccess(false);
            packageChargeResponse.setError(ex.getMessage());
        }
        return packageChargeResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public PackageChargeResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        PackageChargeResponse packageChargeResponse = new PackageChargeResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<PackageCharge> optionalPackageCharge = packageChargeService.findById(formData.get("id"));
            if (optionalPackageCharge.isPresent()) {
                PackageCharge packageCharge = optionalPackageCharge.get();
                packageChargeResponse.setSuccess(true);
                packageChargeResponse.setError("");
                packageChargeResponse.setPackageCharge(packageCharge);
            } else {
                packageChargeResponse.setSuccess(false);
                packageChargeResponse.setError("Error occurred while Fetching package charge!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            packageChargeResponse.setSuccess(false);
            packageChargeResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return packageChargeResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public PackageChargeResponse getDeleted() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        PackageChargeResponse packageChargeResponse = new PackageChargeResponse();
        try {
            packageChargeResponse.setPackageChargeList(packageChargeService.findAllByIsDeletedAndClientId(1, userInfo.getClient().getClientId()));
            packageChargeResponse.setSuccess(true);
            packageChargeResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            packageChargeResponse.setSuccess(true);
            packageChargeResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return packageChargeResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public PackageChargeResponse getAll() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        PackageChargeResponse packageChargeResponse = new PackageChargeResponse();
        try {
            packageChargeResponse.setPackageChargeList(packageChargeService.findAllByIsDeletedAndClientId(0, userInfo.getClient().getClientId()));
            packageChargeResponse.setSuccess(true);
            packageChargeResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            packageChargeResponse.setSuccess(true);
            packageChargeResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return packageChargeResponse;
    }

    @RequestMapping(value = "/get-package-charges", method = RequestMethod.POST)
    public PackageChargeResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        PackageChargeResponse packageChargeResponse = new PackageChargeResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<PackageCharge> page = packageChargeService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            packageChargeResponse.setTotalRecords(page.getTotalElements());
            packageChargeResponse.setTotalPages(page.getTotalPages());
            packageChargeResponse.setPackageChargeList(page.getContent());
            packageChargeResponse.setCurrentRecords(packageChargeResponse.getPackageChargeList().size());
            packageChargeResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            packageChargeResponse.setSuccess(false);
            packageChargeResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return packageChargeResponse;
    }

    @RequestMapping(value = "/search-package-charges", method = RequestMethod.POST)
    public PackageChargeResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        PackageChargeResponse packageChargeResponse = new PackageChargeResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<PackageCharge> page;
            if (searchText == null) {
                page = packageChargeService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(),0,pageable);
            } else {
                page = packageChargeService.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
            }
            packageChargeResponse.setTotalRecords(page.getTotalElements());
            packageChargeResponse.setTotalPages(page.getTotalPages());
            packageChargeResponse.setPackageChargeList(page.getContent());
            packageChargeResponse.setCurrentRecords(packageChargeResponse.getPackageChargeList().size());
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            packageChargeResponse.setSuccess(false);
            packageChargeResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return packageChargeResponse;
    }

    private boolean checkDuplicate(PackageCharge packageCharge) {
        List<PackageCharge> packageCharges;
        if (packageCharge.getId().equals("")) {
            packageCharges = packageChargeService.findAllByClientIdAndTitle(packageCharge.getClientId(), packageCharge.getTitle());
        } else {
            packageCharges = packageChargeService.findAllByClientIdAndPTitleAndIdIsNot(
                    packageCharge.getClientId(), packageCharge.getTitle(), packageCharge.getId());
        }
        if (packageCharges.size() > 0) {
            return true;
        } else {
            return false;
        }
    }


}
