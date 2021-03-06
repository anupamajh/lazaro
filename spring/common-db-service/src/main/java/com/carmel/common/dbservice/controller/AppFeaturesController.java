package com.carmel.common.dbservice.controller;

import com.carmel.common.dbservice.model.AppFeatures;
import com.carmel.common.dbservice.response.AppFeatureResponse;
import com.carmel.common.dbservice.response.AppFeaturesResponse;
import com.carmel.common.dbservice.services.AppFeaturesService;
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

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/app-features")
public class AppFeaturesController {

    Logger logger = LoggerFactory.getLogger(AppFeaturesController.class);

    @Autowired
    AppFeaturesService appFeaturesService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AppFeatureResponse save(@RequestBody AppFeatures appFeatures) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AppFeatureResponse appFeatureResponse = new AppFeatureResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(appFeatures));
            if (appFeatures.getParent() != null) {
                Optional<AppFeatures> optionalAppFeature = appFeaturesService.findById(appFeatures.getParent().getId());
                appFeatures.setParent(optionalAppFeature.get());
            }
            if (checkDuplicate(appFeatures)) {
                appFeatureResponse.setAppFeatures(appFeatures);
                appFeatureResponse.setSuccess(false);
                appFeatureResponse.setError("Duplicate application feature name!");
            } else {
                appFeatureResponse.setAppFeatures(appFeaturesService.save(appFeatures));
                appFeatureResponse.setSuccess(true);
                appFeatureResponse.setError("");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            appFeatureResponse.setSuccess(false);
            appFeatureResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return appFeatureResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public AppFeatureResponse moveToTrash(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AppFeatureResponse appFeatureResponse = new AppFeatureResponse();
        try {
            Optional<AppFeatures> optionalAppFeature = appFeaturesService.findById(formData.get("id"));
            if (optionalAppFeature != null) {
                AppFeatures appFeatures = optionalAppFeature.get();
                appFeatures.setIsDeleted(1);
                appFeatureResponse.setSuccess(true);
                appFeatureResponse.setError("");
                appFeatureResponse.setAppFeatures(appFeaturesService.save(appFeatures));
            } else {
                appFeatureResponse.setSuccess(false);
                appFeatureResponse.setError("Error occurred while moving application feature to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            appFeatureResponse.setSuccess(false);
            appFeatureResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return appFeatureResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public AppFeatureResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AppFeatureResponse appFeatureResponse = new AppFeatureResponse();
        try {
            Optional<AppFeatures> optionalAppFeature = appFeaturesService.findById(formData.get("id"));
            if (optionalAppFeature != null) {
                AppFeatures appFeatures = optionalAppFeature.get();
                appFeatureResponse.setSuccess(true);
                appFeatureResponse.setError("");
                appFeatureResponse.setAppFeatures(appFeaturesService.save(appFeatures));
            } else {
                appFeatureResponse.setSuccess(false);
                appFeatureResponse.setError("Error occurred while fetching application feature!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            appFeatureResponse.setSuccess(false);
            appFeatureResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return appFeatureResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public AppFeaturesResponse getAll() {
        logger.trace("Entering");
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            appFeaturesResponse.setAppFeaturesList(appFeaturesService.findAllByIsDeleted(0));
            appFeaturesResponse.setSuccess(true);
            appFeaturesResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            appFeaturesResponse.setSuccess(false);
            appFeaturesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return appFeaturesResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.GET)
    public AppFeaturesResponse getDeleted() {
        logger.trace("Entering");
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            appFeaturesResponse.setAppFeaturesList(appFeaturesService.findAllByIsDeleted(1));
            appFeaturesResponse.setSuccess(true);
            appFeaturesResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            appFeaturesResponse.setSuccess(false);
            appFeaturesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return appFeaturesResponse;
    }

    @RequestMapping(value = "/get-app-features", method = RequestMethod.GET)
    public AppFeaturesResponse getPaginated(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("featureName"));
            Page<AppFeatures> page = appFeaturesService.findAllByIsDeleted(0,pageable);
            appFeaturesResponse.setTotalRecords(page.getTotalElements());
            appFeaturesResponse.setTotalPages(page.getTotalPages());
            appFeaturesResponse.setAppFeaturesList(page.getContent());
            appFeaturesResponse.setCurrentRecords(appFeaturesResponse.getAppFeaturesList().size());
            appFeaturesResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            appFeaturesResponse.setSuccess(false);
            appFeaturesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return appFeaturesResponse;
    }

    @RequestMapping(value = "/search-app-features", method = RequestMethod.GET)
    public AppFeaturesResponse searchPaginated(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String featureName = formData.get("feature_name") == null ? null : String.valueOf(formData.get("feature_name"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("featureName"));
            Page<AppFeatures> page;
            if (featureName == null ) {
                page = appFeaturesService.findAll(pageable);
            } else {
                page = appFeaturesService.findAllByFeatureNameContaining(featureName, pageable);
            }
            appFeaturesResponse.setTotalRecords(page.getTotalElements());
            appFeaturesResponse.setTotalPages(page.getTotalPages());
            appFeaturesResponse.setAppFeaturesList(page.getContent());
            appFeaturesResponse.setCurrentRecords(appFeaturesResponse.getAppFeaturesList().size());
            appFeaturesResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            appFeaturesResponse.setSuccess(false);
            appFeaturesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return appFeaturesResponse;
    }

    private boolean checkDuplicate(AppFeatures appFeatures) {
        List<AppFeatures> appFeaturesList;
        String parentId = null;
        if (appFeatures.getParent() != null) {
            parentId = appFeatures.getParent().getId();
        }
        if (appFeatures.getId() == null) {
            appFeatures.setId("");
        }
        if (appFeatures.getId().isEmpty()) {
            appFeaturesList = appFeaturesService.findAllByFeatureNameAndParentIdIs(
                    appFeatures.getFeatureName(), parentId
            );
        } else {
            appFeaturesList = appFeaturesService.findAllByFeatureNameAndParentIdIsAndIdIsNot(
                    appFeatures.getFeatureName(), parentId, appFeatures.getId()
            );
        }

        if (appFeaturesList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
