package com.carmel.common.dbservice.controller;

import com.carmel.common.dbservice.common.Search.SearchBuilder;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.AppFeatures;
import com.carmel.common.dbservice.model.DTO.AppFeatureTreeDTO;
import com.carmel.common.dbservice.model.Role;
import com.carmel.common.dbservice.model.User;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.response.AppFeaturesResponse;
import com.carmel.common.dbservice.services.AppFeaturesService;
import com.carmel.common.dbservice.services.UserService;
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

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

import static com.carmel.common.dbservice.specifications.AppFeatureSpecification.textInAllColumns;

@RestController
@RequestMapping(value = "/app-features")
public class AppFeaturesController {

    Logger logger = LoggerFactory.getLogger(AppFeaturesController.class);

    @Autowired
    AppFeaturesService appFeaturesService;

    @Autowired
    UserService userService;

    @Autowired
    UserInformation userInformation;

    @Autowired
    EntityManager entityManager;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AppFeaturesResponse save(@Valid @RequestBody AppFeatures appFeatures) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AppFeaturesResponse appFeatureResponse = new AppFeaturesResponse();
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
                if (appFeatures.getId() != null) {
                    if (!appFeatures.getId().trim().equals("")) {
                        appFeatures.setLastModifiedBy(userInfo.getId());
                        appFeatures.setLastModifiedTime(new Date());
                    } else {
                        appFeatures.setCreatedBy(userInfo.getId());
                        appFeatures.setCreationTime(new Date());
                    }
                } else {
                    appFeatures.setCreatedBy(userInfo.getId());
                    appFeatures.setCreationTime(new Date());
                }
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
    public AppFeaturesResponse moveToTrash(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        AppFeaturesResponse appFeatureResponse = new AppFeaturesResponse();
        try {
            Optional<AppFeatures> optionalAppFeature = appFeaturesService.findById(formData.get("id"));
            if (optionalAppFeature != null) {
                AppFeatures appFeatures = optionalAppFeature.get();
                appFeatures.setIsDeleted(1);
                appFeatures.setDeletedBy(userInfo.getId());
                appFeatures.setDeletedTime(new Date());
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
    public AppFeaturesResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AppFeaturesResponse appFeatureResponse = new AppFeaturesResponse();
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

    @RequestMapping(value = "/get-app-features", method = RequestMethod.POST)
    public AppFeaturesResponse getPaginated(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("featureName"));
            Page<AppFeatures> page = appFeaturesService.findAllByIsDeleted(0, pageable);
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

    @RequestMapping(value = "/search-app-features", method = RequestMethod.POST)
    public AppFeaturesResponse searchPaginated(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("featureName"));
            Page<AppFeatures> page;
            if (searchText == null) {
                page = appFeaturesService.findAll(pageable);
            } else {
                page = appFeaturesService.findAll(textInAllColumns(searchText), pageable);
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

    @RequestMapping(value = "/get-deep-app-features", method = RequestMethod.POST)
    public AppFeaturesResponse getDeepAppFeatures(Map<String, String> formData) {
        List<AppFeatureTreeDTO> treeData;
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            String parentId = formData.get("parentId");
            treeData = appFeaturesService.getTreeData(parentId);
            appFeaturesResponse.setTreeData(treeData);
            appFeaturesResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            appFeaturesResponse.setSuccess(false);
            appFeaturesResponse.setError(ex.getMessage());
        }
        return appFeaturesResponse;
    }

    @RequestMapping(value = "/get-user-app-features", method = RequestMethod.POST)
    public AppFeaturesResponse getUserAppFeatures() {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            Optional<User> optionalUser = userService.findById(userInfo.getId());
            optionalUser.orElseThrow(() ->
                    new Exception("User record not found")
            );
            List<Role> roles = optionalUser.get().getRoles();
            List<String> ids = new ArrayList<>();
            roles.forEach(role -> {
                ids.addAll(role.getAppFeatures().stream().map(AppFeatures::getId).collect(Collectors.toList()));
            });
            List<AppFeatures> appFeaturesList = appFeaturesService.findAllByIdInAndIsDeleted(ids, 0);
            appFeaturesResponse.setSimpleAppFeaturesList(appFeaturesList);
            appFeaturesResponse.setSuccess(true);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            appFeaturesResponse.setSuccess(false);
            appFeaturesResponse.setError(ex.getMessage());
        }
        return appFeaturesResponse;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public AppFeaturesResponse search(@RequestBody SearchRequest searchRequest) {
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<AppFeatures> criteriaQuery = criteriaBuilder.createQuery(AppFeatures.class);
            Root<AppFeatures> root = criteriaQuery.from(AppFeatures.class);
            criteriaQuery = SearchBuilder.buildSearch(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root,
                    AppFeatures.class,
                    searchRequest
            );
            long totalRecords = SearchBuilder.getTotalRecordCount(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root
            );
            TypedQuery<AppFeatures> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setFirstResult((searchRequest.getCurrentPage() - 1) * searchRequest.getPageSize());
            typedQuery.setMaxResults(searchRequest.getPageSize());
            List<AppFeatures> appFeaturesList = typedQuery.getResultList();
            appFeaturesResponse.setCurrentRecords(appFeaturesList.size());
            appFeaturesResponse.setTotalRecords(totalRecords);
            appFeaturesResponse.setSuccess(true);
            appFeaturesResponse.setError("");
            appFeaturesResponse.setAppFeaturesList(appFeaturesList);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.toString(), ex);
            appFeaturesResponse.setSuccess(false);
            appFeaturesResponse.setError(ex.getMessage());
        }
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
