package com.carmel.common.dbservice.Base.MeasurementUnit.Service;

import com.carmel.common.dbservice.Base.MeasurementUnit.Model.MeasurementUnit;
import com.carmel.common.dbservice.Base.MeasurementUnit.Repository.MeasurementUnitRepository;
import com.carmel.common.dbservice.Base.MeasurementUnit.Response.MeasurementUnitResponse;
import com.carmel.common.dbservice.common.Search.SearchBuilder;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.carmel.common.dbservice.Base.MeasurementUnit.Specification.MeasurementUnitSpecification.textInAllColumns;

@Service
public class MeasurementUnitServiceImpl implements MeasurementUnitService {

   private Logger logger = LoggerFactory.getLogger(MeasurementUnitServiceImpl.class);

   private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MeasurementUnitRepository measurementUnitRepository;

    @Autowired
    EntityManager entityManager;


    @Autowired
    MeasurementUnitService measurementUnitService;

    @Autowired
    UserInformation userInformation;



    @Override
    public MeasurementUnitResponse saveMeasurementUnit(MeasurementUnit measurementUnit) {
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
           throw ex;
        }
        return measurementUnitResponse;
    }

    @Override
    public MeasurementUnitResponse moveToTrash(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
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
            throw ex;
        }
        return measurementUnitResponse;
    }

    @Override
    public MeasurementUnitResponse get(Map<String, String> formData) throws Exception {
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
           throw ex;
        }
        logger.trace("Exiting");
        return measurementUnitResponse;
    }

    @Override
    public MeasurementUnitResponse getDeleted() throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        MeasurementUnitResponse measurementUnitResponse = new MeasurementUnitResponse();
        try {
            measurementUnitResponse.setMeasurementUnitList(measurementUnitRepository.findAllByIsDeletedAndClientId(1, userInfo.getClient().getClientId()));
            measurementUnitResponse.setSuccess(true);
            measurementUnitResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return measurementUnitResponse;
    }

    @Override
    public MeasurementUnitResponse getAll() throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        MeasurementUnitResponse measurementUnitResponse = new MeasurementUnitResponse();
        try {
            measurementUnitResponse.setMeasurementUnitList(measurementUnitRepository.findAllByIsDeletedAndClientId(0, userInfo.getClient().getClientId()));
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

    @Override
    public MeasurementUnitResponse getPaginated(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        MeasurementUnitResponse measurementUnitResponse = new MeasurementUnitResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<MeasurementUnit> page = measurementUnitRepository.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            measurementUnitResponse.setTotalRecords(page.getTotalElements());
            measurementUnitResponse.setTotalPages(page.getTotalPages());
            measurementUnitResponse.setMeasurementUnitList(page.getContent());
            measurementUnitResponse.setCurrentRecords(measurementUnitResponse.getMeasurementUnitList().size());
            measurementUnitResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
           throw ex;
        }
        logger.trace("Exiting");
        return measurementUnitResponse;
    }

    @Override
    public MeasurementUnitResponse searchPaginated(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
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
                page = measurementUnitRepository.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            } else {
                page = measurementUnitRepository.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
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

    @Override
    public MeasurementUnitResponse search(SearchRequest searchRequest) throws Exception {
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
    private boolean checkDuplicate(MeasurementUnit measurementUnit) {
        List<MeasurementUnit> measurementUnitList;
        if (measurementUnit.getId().equals("")) {
            measurementUnitList = measurementUnitRepository.findAllByClientIdAndTitle(measurementUnit.getClientId(), measurementUnit.getTitle());
        } else {
            measurementUnitList = measurementUnitRepository.findAllByClientIdAndTitleAndIdIsNot(
                    measurementUnit.getClientId(), measurementUnit.getTitle(), measurementUnit.getId());
        }
        if (measurementUnitList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public MeasurementUnit save(MeasurementUnit measurementUnit) {
        return measurementUnitRepository.save(measurementUnit);
    }

    @Override
    public Optional<MeasurementUnit> findById(String id) {
        return measurementUnitRepository.findById(id);
    }

    @Override
    public MeasurementUnitResponse findAllByIsDeletedAndClientId(int isDeleted, String clientId) {
        return (MeasurementUnitResponse) measurementUnitRepository.findAllByIsDeletedAndClientId(isDeleted, clientId);
    }

    @Override
    public MeasurementUnitResponse findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return (MeasurementUnitResponse) measurementUnitRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted, pageable);
    }

    @Override
    public MeasurementUnitResponse findAll(Specification<MeasurementUnit> textInAllColumns, Pageable pageable) {
        return (MeasurementUnitResponse) measurementUnitRepository.findAll(textInAllColumns, pageable);
    }

    @Override
    public MeasurementUnitResponse findAllByClientIdAndTitle(String clientId, String title) {
        return (MeasurementUnitResponse) measurementUnitRepository.findAllByClientIdAndTitleAndIsDeleted(clientId, title, 0);
    }

    @Override
    public MeasurementUnitResponse findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id) {
        return (MeasurementUnitResponse) measurementUnitRepository.findAllByClientIdAndTitleAndIdIsNotAndIsDeleted(clientId, title, id, 0);
    }

}
