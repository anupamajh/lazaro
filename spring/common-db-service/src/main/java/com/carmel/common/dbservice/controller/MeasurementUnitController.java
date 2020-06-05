package com.carmel.common.dbservice.controller;

import com.carmel.common.dbservice.common.Search.SearchBuilder;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.MeasurementUnit;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.response.MeasurementUnitResponse;
import com.carmel.common.dbservice.services.MeasurementUnitService;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.carmel.common.dbservice.specifications.MeasurementUnitSpecification.textInAllColumns;

@RestController
@RequestMapping(value = "/measurement-unit")
public class MeasurementUnitController {

    Logger logger = LoggerFactory.getLogger(MeasurementUnitController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    MeasurementUnitService measurementUnitService;

    @Autowired
    EntityManager entityManager;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public MeasurementUnitResponse save(@Valid @RequestBody MeasurementUnit measurementUnit) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        MeasurementUnitResponse measurementUnitResponse = new MeasurementUnitResponse();
        try {
            if (measurementUnit.getId() == null) {
                measurementUnit.setId("");
            }
            if (measurementUnit.getOrgId() == null || measurementUnit.getOrgId().isEmpty()) {
                if (userInfo.getDefaultOrganization() != null) {
                    measurementUnit.setOrgId(userInfo.getDefaultOrganization().getId());
                }
            }
            if (measurementUnit.getId().equals("")) {
                measurementUnit.setCreatedBy(userInfo.getId());
                measurementUnit.setCreationTime(new Date());
            } else {
                measurementUnit.setLastModifiedBy(userInfo.getId());
                measurementUnit.setLastModifiedTime(new Date());
            }
            measurementUnit.setClientId(userInfo.getClient().getClientId());
            if (checkDuplicate(measurementUnit)) {
                measurementUnitResponse.setMeasurementUnit(measurementUnit);
                measurementUnitResponse.setSuccess(false);
                measurementUnitResponse.setError("Duplicate measurement unit!");
            } else {
                measurementUnitResponse.setMeasurementUnit(measurementUnitService.save(measurementUnit));
                measurementUnitResponse.setSuccess(true);
                measurementUnitResponse.setError("");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            measurementUnitResponse.setSuccess(false);
            measurementUnitResponse.setError(ex.getMessage());
        }
        return measurementUnitResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public MeasurementUnitResponse moveToTrash(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        MeasurementUnitResponse measurementUnitResponse = new MeasurementUnitResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<MeasurementUnit> optionalMeasurementUnit = measurementUnitService.findById(formData.get("id"));
            if (optionalMeasurementUnit.isPresent()) {
                MeasurementUnit measurementUnit = optionalMeasurementUnit.get();
                measurementUnit.setIsDeleted(1);
                measurementUnit.setDeletedBy(userInfo.getId());
                measurementUnit.setDeletedTime(new Date());
                measurementUnitResponse.setSuccess(true);
                measurementUnitResponse.setError("");
                measurementUnitResponse.setMeasurementUnit(measurementUnitService.save(measurementUnit));
            } else {
                measurementUnitResponse.setSuccess(false);
                measurementUnitResponse.setError("Error occurred while moving measurement unit to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            measurementUnitResponse.setSuccess(false);
            measurementUnitResponse.setError(ex.getMessage());
        }
        return measurementUnitResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public MeasurementUnitResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        MeasurementUnitResponse measurementUnitResponse = new MeasurementUnitResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<MeasurementUnit> optionalMeasurementUnit = measurementUnitService.findById(formData.get("id"));
            if (optionalMeasurementUnit.isPresent()) {
                MeasurementUnit measurementUnit = optionalMeasurementUnit.get();
                measurementUnitResponse.setSuccess(true);
                measurementUnitResponse.setError("");
                measurementUnitResponse.setMeasurementUnit(measurementUnit);
            } else {
                measurementUnitResponse.setSuccess(false);
                measurementUnitResponse.setError("Error occurred while Fetching measurement unit!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            measurementUnitResponse.setSuccess(false);
            measurementUnitResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return measurementUnitResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public MeasurementUnitResponse getDeleted() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        MeasurementUnitResponse measurementUnitResponse = new MeasurementUnitResponse();
        try {
            measurementUnitResponse.setMeasurementUnitList(measurementUnitService.findAllByIsDeletedAndClientId(1, userInfo.getClient().getClientId()));
            measurementUnitResponse.setSuccess(true);
            measurementUnitResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            measurementUnitResponse.setSuccess(true);
            measurementUnitResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return measurementUnitResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public MeasurementUnitResponse getAll() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        MeasurementUnitResponse measurementUnitResponse = new MeasurementUnitResponse();
        try {
            measurementUnitResponse.setMeasurementUnitList(measurementUnitService.findAllByIsDeletedAndClientId(0, userInfo.getClient().getClientId()));
            measurementUnitResponse.setSuccess(true);
            measurementUnitResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            measurementUnitResponse.setSuccess(true);
            measurementUnitResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return measurementUnitResponse;
    }

    @RequestMapping(value = "/get-measurement-units", method = RequestMethod.POST)
    public MeasurementUnitResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        MeasurementUnitResponse measurementUnitResponse = new MeasurementUnitResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<MeasurementUnit> page = measurementUnitService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            measurementUnitResponse.setTotalRecords(page.getTotalElements());
            measurementUnitResponse.setTotalPages(page.getTotalPages());
            measurementUnitResponse.setMeasurementUnitList(page.getContent());
            measurementUnitResponse.setCurrentRecords(measurementUnitResponse.getMeasurementUnitList().size());
            measurementUnitResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            measurementUnitResponse.setSuccess(false);
            measurementUnitResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return measurementUnitResponse;
    }

    @RequestMapping(value = "/search-measurement-units", method = RequestMethod.POST)
    public MeasurementUnitResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        MeasurementUnitResponse measurementUnitResponse = new MeasurementUnitResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<MeasurementUnit> page;
            if (searchText == null) {
                page = measurementUnitService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            } else {
                page = measurementUnitService.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
            }
            measurementUnitResponse.setTotalRecords(page.getTotalElements());
            measurementUnitResponse.setTotalPages(page.getTotalPages());
            measurementUnitResponse.setMeasurementUnitList(page.getContent());
            measurementUnitResponse.setCurrentRecords(measurementUnitResponse.getMeasurementUnitList().size());
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            measurementUnitResponse.setSuccess(false);
            measurementUnitResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return measurementUnitResponse;
    }

    private boolean checkDuplicate(MeasurementUnit measurementUnit) {
        List<MeasurementUnit> measurementUnitList;
        if (measurementUnit.getId().equals("")) {
            measurementUnitList = measurementUnitService.findAllByClientIdAndTitle(measurementUnit.getClientId(), measurementUnit.getTitle());
        } else {
            measurementUnitList = measurementUnitService.findAllByClientIdAndTitleAndIdIsNot(
                    measurementUnit.getClientId(), measurementUnit.getTitle(), measurementUnit.getId());
        }
        if (measurementUnitList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public MeasurementUnitResponse search(@RequestBody SearchRequest searchRequest) {
        MeasurementUnitResponse measurementUnitResponse = new MeasurementUnitResponse();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<MeasurementUnit> criteriaQuery = criteriaBuilder.createQuery(MeasurementUnit.class);
            Root<MeasurementUnit> root = criteriaQuery.from(MeasurementUnit.class);
            criteriaQuery = SearchBuilder.buildSearch(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root,
                    MeasurementUnit.class,
                    searchRequest
            );
            long totalRecords = SearchBuilder.getTotalRecordCount(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root
            );
            TypedQuery<MeasurementUnit> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setFirstResult((searchRequest.getCurrentPage() - 1) * searchRequest.getPageSize());
            typedQuery.setMaxResults(searchRequest.getPageSize());
            List<MeasurementUnit> measurementUnitList = typedQuery.getResultList();
            measurementUnitResponse.setCurrentRecords(measurementUnitList.size());
            measurementUnitResponse.setTotalRecords(totalRecords);
            measurementUnitResponse.setSuccess(true);
            measurementUnitResponse.setError("");
            measurementUnitResponse.setMeasurementUnitList(measurementUnitList);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.toString(), ex);
            measurementUnitResponse.setSuccess(false);
            measurementUnitResponse.setError(ex.getMessage());
        }
        return measurementUnitResponse;
    }

}
