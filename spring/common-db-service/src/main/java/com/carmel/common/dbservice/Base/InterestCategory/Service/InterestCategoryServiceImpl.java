package com.carmel.common.dbservice.Base.InterestCategory.Service;

import com.carmel.common.dbservice.Base.AddressBook.Specification.specifications.InterestCategorySpecification;
import com.carmel.common.dbservice.Base.InterestCategory.Model.InterestCategory;
import com.carmel.common.dbservice.Base.InterestCategory.Repository.InterestCategoryRepository;
import com.carmel.common.dbservice.Base.InterestCategory.Responce.InterestCategoryResponse;
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


@Service
public class InterestCategoryServiceImpl implements InterestCategoryService {
    private  Logger logger = LoggerFactory.getLogger(InterestCategoryServiceImpl.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    InterestCategoryRepository interestCategoryRepository;

    @Autowired
    UserInformation userInformation;

    @Autowired
    InterestCategoryService interestCategoryService;

    @Autowired
    EntityManager entityManager;

    @Override
    public InterestCategory save(InterestCategory interestCategory) {
        return interestCategoryRepository.save(interestCategory);
    }

    @Override
    public InterestCategoryResponse saveInterestcategory(InterestCategory interestCategory) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
        try {
            if (interestCategory.getId() == null) {
                interestCategory.setId("");
            }
            if (interestCategory.getOrgId() == null || interestCategory.getOrgId().isEmpty()) {
                if (userInfo.getDefaultOrganization() != null) {
                    interestCategory.setOrgId(userInfo.getDefaultOrganization().getId());
                }
            }
            if (interestCategory.getId().equals("")) {
                interestCategory.setCreatedBy(userInfo.getId());
                interestCategory.setCreationTime(new Date());
            } else {
                interestCategory.setLastModifiedBy(userInfo.getId());
                interestCategory.setLastModifiedTime(new Date());
            }
            interestCategory.setClientId(userInfo.getClient().getClientId());
            if (checkDuplicate(interestCategory)) {
                interestCategoryResponse.setInterestCategory(interestCategory);
                interestCategoryResponse.setSuccess(false);
                interestCategoryResponse.setError("Duplicate Interest category Name name!");
            } else {
                interestCategoryResponse.setInterestCategory(interestCategoryService.save(interestCategory));
                interestCategoryResponse.setSuccess(true);
                interestCategoryResponse.setError("");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
           throw ex;
        }
        return interestCategoryResponse;
    }

    @Override
    public InterestCategoryResponse moveToTrash(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<InterestCategory> optionalInterestCategory = interestCategoryService.findById(formData.get("id"));
            if (optionalInterestCategory.isPresent()) {
                InterestCategory interestCategory = optionalInterestCategory.get();
                interestCategory.setIsDeleted(1);
                interestCategory.setDeletedBy(userInfo.getId());
                interestCategory.setDeletedTime(new Date());
                interestCategoryResponse.setSuccess(true);
                interestCategoryResponse.setError("");
                interestCategoryResponse.setInterestCategory(interestCategoryService.save(interestCategory));
            } else {
                interestCategoryResponse.setSuccess(false);
                interestCategoryResponse.setError("Error occurred while moving interest category to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        return interestCategoryResponse;
    }

    @Override
    public InterestCategoryResponse get(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<InterestCategory> optionalInterestCategory = interestCategoryService.findById(formData.get("id"));
            if (optionalInterestCategory.isPresent()) {
                InterestCategory interestCategory = optionalInterestCategory.get();
                interestCategoryResponse.setSuccess(true);
                interestCategoryResponse.setError("");
                interestCategoryResponse.setInterestCategory(interestCategory);
            } else {
                interestCategoryResponse.setSuccess(false);
                interestCategoryResponse.setError("Error occurred while Fetching interest category!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
           throw ex;
        }
        logger.trace("Exiting");
        return interestCategoryResponse;
    }

    @Override
    public InterestCategoryResponse getDeleted() throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
        try {
            interestCategoryResponse.setInterestCategoryList(interestCategoryRepository.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 1));
            interestCategoryResponse.setSuccess(true);
            interestCategoryResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
           throw ex;
        }
        logger.trace("Exiting");
        return interestCategoryResponse;
    }

    @Override
    public InterestCategoryResponse getAll() throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
        try {
            interestCategoryResponse.setInterestCategoryList(interestCategoryRepository.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0));
            interestCategoryResponse.setSuccess(true);
            interestCategoryResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return interestCategoryResponse;
    }

    @Override
    public InterestCategoryResponse getPaginated(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
            Page<InterestCategory> page = interestCategoryRepository.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            interestCategoryResponse.setTotalRecords(page.getTotalElements());
            interestCategoryResponse.setTotalPages(page.getTotalPages());
            interestCategoryResponse.setInterestCategoryList(page.getContent());
            interestCategoryResponse.setCurrentRecords(interestCategoryResponse.getInterestCategoryList().size());
            interestCategoryResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return interestCategoryResponse;
    }

    @Override
    public InterestCategoryResponse searchPaginated(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
            Page<InterestCategory> page;
            if (searchText == null) {
                page = interestCategoryRepository.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            } else {
                page = interestCategoryRepository.findAll(InterestCategorySpecification.textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
            }
            interestCategoryResponse.setTotalRecords(page.getTotalElements());
            interestCategoryResponse.setTotalPages(page.getTotalPages());
            interestCategoryResponse.setInterestCategoryList(page.getContent());
            interestCategoryResponse.setCurrentRecords(interestCategoryResponse.getInterestCategoryList().size());
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return interestCategoryResponse;
    }

    @Override
    public InterestCategoryResponse search(SearchRequest searchRequest) throws Exception {
        InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<InterestCategory> criteriaQuery = criteriaBuilder.createQuery(InterestCategory.class);
            Root<InterestCategory> root = criteriaQuery.from(InterestCategory.class);
            criteriaQuery = SearchBuilder.buildSearch(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root,
                    InterestCategory.class,
                    searchRequest
            );
            long totalRecords = SearchBuilder.getTotalRecordCount(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root
            );
            TypedQuery<InterestCategory> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setFirstResult((searchRequest.getCurrentPage() - 1) * searchRequest.getPageSize());
            typedQuery.setMaxResults(searchRequest.getPageSize());
            List<InterestCategory> interestCategoryList = typedQuery.getResultList();
            interestCategoryResponse.setCurrentRecords(interestCategoryList.size());
            interestCategoryResponse.setTotalRecords(totalRecords);
            interestCategoryResponse.setSuccess(true);
            interestCategoryResponse.setError("");
            interestCategoryResponse.setInterestCategoryList(interestCategoryList);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.toString(), ex);
            interestCategoryResponse.setSuccess(false);
            interestCategoryResponse.setError(ex.getMessage());
        }
        return interestCategoryResponse;
    }
    private boolean checkDuplicate(InterestCategory interestCategory) {
        List<InterestCategory> accountHeadList;
        if (interestCategory.getId().equals("")) {
            accountHeadList = interestCategoryRepository.findAllByClientIdAndIsDeletedAndName(interestCategory.getClientId(), 0, interestCategory.getName());
        } else {
            accountHeadList = interestCategoryRepository.findAllByClientIdAndIsDeletedAndNameAndIdIsNot(
                    interestCategory.getClientId(), 0, interestCategory.getName(), interestCategory.getId());
        }
        if (accountHeadList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public Optional<InterestCategory> findById(String id) {
        return interestCategoryRepository.findById(id);
    }

    @Override
    public InterestCategoryResponse findAllByClientIdAndIsDeleted(String clientId, int isDeleted) {
        return (InterestCategoryResponse) interestCategoryRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted);
    }

    @Override
    public InterestCategoryResponse findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return (InterestCategoryResponse) interestCategoryRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted, pageable);
    }

    @Override
    public InterestCategoryResponse findAll(Specification<InterestCategory> textInAllColumns, Pageable pageable) {
        return (InterestCategoryResponse) interestCategoryRepository.findAll(textInAllColumns, pageable);
    }

    @Override
    public InterestCategoryResponse findAllByClientIdAndIsDeletedAndName(String clientId, int isDeleted, String name) {
        return (InterestCategoryResponse) interestCategoryRepository.findAllByClientIdAndIsDeletedAndName(clientId, isDeleted, name);
    }

    @Override
    public InterestCategoryResponse findAllByClientIdAndIsDeletedAndNameAndIdIsNot(String clientId, int isDeleted, String name, String id) {
        return (InterestCategoryResponse) interestCategoryRepository.findAllByClientIdAndIsDeletedAndNameAndIdIsNot(clientId, isDeleted, name, id);
    }
}
