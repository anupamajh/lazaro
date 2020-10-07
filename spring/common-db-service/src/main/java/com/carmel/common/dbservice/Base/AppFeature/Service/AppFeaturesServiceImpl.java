package com.carmel.common.dbservice.Base.AppFeature.Service;


import com.carmel.common.dbservice.Base.AppFeature.Model.AppFeatures;
import com.carmel.common.dbservice.Base.AppFeature.DTO.AppFeatureTreeDTO;
import com.carmel.common.dbservice.Base.AppFeature.Repository.AppFeaturesRepository;
import com.carmel.common.dbservice.Base.AppFeature.Response.AppFeaturesResponse;
import com.carmel.common.dbservice.common.Search.SearchBuilder;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.Base.Role.Model.Role;
import com.carmel.common.dbservice.Base.User.Model.User;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.Base.User.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.stream.Collectors;

import static com.carmel.common.dbservice.Base.AppFeature.Specification.AppFeatureSpecification.textInAllColumns;

@Service
public class AppFeaturesServiceImpl implements AppFeaturesService {

    private Logger logger = LoggerFactory.getLogger(AppFeaturesServiceImpl.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    AppFeaturesRepository appFeaturesRepository;

    @Autowired
    UserInformation userInformation;

    @Autowired
    UserService userService;

    @Autowired
    EntityManager entityManager;

    @Override
    public AppFeatures save(AppFeatures appFeatures) throws Exception {
        return appFeaturesRepository.save(appFeatures);
    }

    @Override
    public Optional<AppFeatures> findById(String id) throws Exception {
        return appFeaturesRepository.findById(id);
    }

    @Override
    public AppFeaturesResponse saveAppFeature(AppFeatures appFeatures) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        AppFeaturesResponse appFeatureResponse = new AppFeaturesResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(appFeatures));
            if (appFeatures.getParent() != null) {
                Optional<AppFeatures> optionalAppFeature = appFeaturesRepository.findById(appFeatures.getParent().getId());
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
                appFeatureResponse.setAppFeatures(this.save(appFeatures));
                appFeatureResponse.setSuccess(true);
                appFeatureResponse.setError("");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return appFeatureResponse;
    }

    @Override
    public AppFeaturesResponse moveToTrash(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        AppFeaturesResponse appFeatureResponse = new AppFeaturesResponse();
        try {
            Optional<AppFeatures> optionalAppFeature = appFeaturesRepository.findById(formData.get("id"));
            if (optionalAppFeature != null) {
                AppFeatures appFeatures = optionalAppFeature.get();
                appFeatures.setIsDeleted(1);
                appFeatures.setDeletedBy(userInfo.getId());
                appFeatures.setDeletedTime(new Date());
                appFeatureResponse.setSuccess(true);
                appFeatureResponse.setError("");
                appFeatureResponse.setAppFeatures(this.save(appFeatures));
            } else {
                appFeatureResponse.setSuccess(false);
                appFeatureResponse.setError("Error occurred while moving application feature to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return appFeatureResponse;
    }

    @Override
    public AppFeaturesResponse get(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        AppFeaturesResponse appFeatureResponse = new AppFeaturesResponse();
        try {
            Optional<AppFeatures> optionalAppFeature = appFeaturesRepository.findById(formData.get("id"));
            if (optionalAppFeature != null) {
                AppFeatures appFeatures = optionalAppFeature.get();
                appFeatureResponse.setSuccess(true);
                appFeatureResponse.setError("");
                appFeatureResponse.setAppFeatures(this.save(appFeatures));
            } else {
                appFeatureResponse.setSuccess(false);
                appFeatureResponse.setError("Error occurred while fetching application feature!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return appFeatureResponse;
    }

    @Override
    public AppFeaturesResponse getAll() throws Exception {
        logger.trace("Entering");
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            appFeaturesResponse.setAppFeaturesList(appFeaturesRepository.findAllByIsDeleted(0));
            appFeaturesResponse.setSuccess(true);
            appFeaturesResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw  ex;
        }
        logger.trace("Exiting");
        return appFeaturesResponse;
    }

    @Override
    public AppFeaturesResponse getDeleted() throws Exception {
        logger.trace("Entering");
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            appFeaturesResponse.setAppFeaturesList(appFeaturesRepository.findAllByIsDeleted(1));
            appFeaturesResponse.setSuccess(true);
            appFeaturesResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return appFeaturesResponse;
    }

    @Override
    public AppFeaturesResponse getPaginated(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("featureName"));
            Page<AppFeatures> page = appFeaturesRepository.findAllByIsDeleted(0, pageable);
            appFeaturesResponse.setTotalRecords(page.getTotalElements());
            appFeaturesResponse.setTotalPages(page.getTotalPages());
            appFeaturesResponse.setAppFeaturesList(page.getContent());
            appFeaturesResponse.setCurrentRecords(appFeaturesResponse.getAppFeaturesList().size());
            appFeaturesResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return appFeaturesResponse;
    }

    @Override
    public AppFeaturesResponse searchPaginated(Map<String, String> formData) throws Exception {

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
                page = appFeaturesRepository.findAll(pageable);
            } else {
                page = appFeaturesRepository.findAll(textInAllColumns(searchText), pageable);
            }
            appFeaturesResponse.setTotalRecords(page.getTotalElements());
            appFeaturesResponse.setTotalPages(page.getTotalPages());
            appFeaturesResponse.setAppFeaturesList(page.getContent());
            appFeaturesResponse.setCurrentRecords(appFeaturesResponse.getAppFeaturesList().size());
            appFeaturesResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return appFeaturesResponse;
    }


    @Override
    public AppFeaturesResponse getDeepAppFeatures(Map<String, String> formData) throws Exception {
        List<AppFeatureTreeDTO> treeData = new ArrayList<>();
        AppFeaturesResponse appFeaturesResponse = new AppFeaturesResponse();
        try {
            String parentId = formData.get("parentId");
            treeData = this.generateTree(parentId,treeData);
            appFeaturesResponse.setTreeData(treeData);
            appFeaturesResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
           throw ex;
        }
        return appFeaturesResponse;
    }

    @Override
    public AppFeaturesResponse getUserAppFeatures() throws Exception {
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
            List<AppFeatures> appFeaturesList = appFeaturesRepository.findAllByIdInAndIsDeleted(ids, 0);
            appFeaturesResponse.setSimpleAppFeaturesList(appFeaturesList);
            appFeaturesResponse.setSuccess(true);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        return appFeaturesResponse;
    }

    @Override
    public AppFeaturesResponse search(SearchRequest searchRequest) throws Exception {
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
            throw ex;
        }
        return appFeaturesResponse;
    }

    @Override
    public AppFeaturesResponse findAllByFeatureNameAndParentIdIs(String featureName, String parentId) throws Exception {
        return null;
    }

    @Override
    public AppFeaturesResponse findAllByFeatureNameAndParentIdIsAndIdIsNot(String featureName, String parentId, String id) throws Exception {
        return null;
    }

    @Override
    public AppFeaturesResponse findAllByIsDeleted(int isDeleted) throws Exception {
        return null;
    }

    @Override
    public AppFeaturesResponse findAll(Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public AppFeaturesResponse findAllByFeatureNameContaining(String featureName, Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public AppFeaturesResponse findAllByIsDeleted(int isDeleted, Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public AppFeaturesResponse findAll(Specification<AppFeatures> textInAllColumns, Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public AppFeaturesResponse getTreeData(String parentId) throws Exception {
        return null;
    }

    @Override
    public AppFeaturesResponse findAllByIdInAndIsDeleted(List<String> ids, int isDeleted) throws Exception {
        return null;
    }

    private List<AppFeatureTreeDTO> generateTree(String parentId, List<AppFeatureTreeDTO> treeData) {
        List<AppFeatures> appFeaturesList = appFeaturesRepository.findAllByParentIdIsAndIsDeleted(parentId,0);
        appFeaturesList.forEach(appFeatures -> {
            AppFeatureTreeDTO appFeatureTreeDTO = new AppFeatureTreeDTO(appFeatures);
            List<AppFeatureTreeDTO> treeDTOS = generateTree(appFeatureTreeDTO.getId(), new ArrayList<>());
            appFeatureTreeDTO.setChildren(treeDTOS);
            treeData.add(appFeatureTreeDTO);
        });
        return treeData;
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
            appFeaturesList = appFeaturesRepository.findAllByFeatureNameAndParentIdIs(
                    appFeatures.getFeatureName(), parentId
            );
        } else {
            appFeaturesList = appFeaturesRepository.findAllByFeatureNameAndParentIdIsAndIdIsNot(
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
