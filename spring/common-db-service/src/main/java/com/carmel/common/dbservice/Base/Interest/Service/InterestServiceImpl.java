package com.carmel.common.dbservice.Base.Interest.Service;

import com.carmel.common.dbservice.Base.Interest.Model.Interest;
import com.carmel.common.dbservice.Base.Interest.Repository.InterestRepository;
import com.carmel.common.dbservice.Base.Interest.Response.InterestResponse;
import com.carmel.common.dbservice.common.GroupType;
import com.carmel.common.dbservice.common.Search.SearchBuilder;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.Base.Group.Model.Group;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.Base.Group.Service.GroupService;
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

import static com.carmel.common.dbservice.Base.Interest.Specification.InterestSpecification.textInAllColumns;

@Service
public class InterestServiceImpl implements InterestService {

    private Logger logger = LoggerFactory.getLogger(InterestServiceImpl.class);

    @Autowired
    InterestRepository interestRepository;

    @Autowired
    GroupService groupService;

    @Autowired
    UserInformation userInformation;

    @Autowired
    InterestService interestService;

    @Autowired
    EntityManager entityManager;

    @Override
    public Interest save(Interest interest) {
        return interestRepository.save(interest);
    }

    @Override
    public Optional<Interest> findById(String id) {
        return interestRepository.findById(id);
    }

    @Override
    public InterestResponse saveInterest(Interest interest) throws Exception {
        UserInfo userInfo = null;
        try {
            userInfo = userInformation.getUserInfo();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        logger.trace("Entering");
        InterestResponse interestResponse = new InterestResponse();
        try {
            if (interest.getId() == null) {
                interest.setId("");
            }
            if (interest.getOrgId() == null || interest.getOrgId().isEmpty()) {
                if (userInfo.getDefaultOrganization() != null) {
                    interest.setOrgId(userInfo.getDefaultOrganization().getId());
                }
            }
            if (interest.getId().equals("")) {
                interest.setCreatedBy(userInfo.getId());
                interest.setCreationTime(new Date());
            } else {
                interest.setLastModifiedBy(userInfo.getId());
                interest.setLastModifiedTime(new Date());
            }
            interest.setClientId(userInfo.getClient().getClientId());
            if (checkDuplicate(interest)) {
                interestResponse.setInterest(interest);
                interestResponse.setSuccess(false);
                interestResponse.setError("Duplicate Interest name!");
            } else {
                Interest savedInterest = interestService.save(interest);
                Group group = new Group();
                Optional<Group> optionalGroup = groupService.findByInterestId(savedInterest.getId());
                if (optionalGroup.isPresent()) {
                    group = optionalGroup.get();
                }
                group.setClientId(savedInterest.getClientId());
                group.setOrgId(savedInterest.getOrgId());
                group.setInterestId(savedInterest.getId());
                group.setInterestCategoryId(savedInterest.getInterestCategoryId());
                group.setName(savedInterest.getName());
                group.setDescription(savedInterest.getDescription());
                group.setGroupType(GroupType.GROUP_TYPE_SYSTEM_GENERATED);
                group.setCreatedBy(userInfo.getId());
                group.setCreationTime(new Date());
                Group savedGroup = groupService.save(group);
                interestResponse.setInterest(savedInterest);
                interestResponse.setSuccess(true);
                interestResponse.setError("");


            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            interestResponse.setSuccess(false);
            interestResponse.setError(ex.getMessage());
        }
        return interestResponse;
    }

    @Override
    public InterestResponse moveToTrash(Map<String, String> formData) throws Exception {
        UserInfo userInfo = null;
        try {
            userInfo = userInformation.getUserInfo();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InterestResponse interestResponse = new InterestResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Interest> optionalInterest = interestService.findById(formData.get("id"));
            if (optionalInterest.isPresent()) {
                Interest interest = optionalInterest.get();
                interest.setIsDeleted(1);
                interest.setDeletedBy(userInfo.getId());
                interest.setDeletedTime(new Date());
                interestResponse.setSuccess(true);
                interestResponse.setError("");
                interestResponse.setInterest(interestService.save(interest));
            } else {
                interestResponse.setSuccess(false);
                interestResponse.setError("Error occurred while moving interest to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        return interestResponse;
    }

    @Override
    public InterestResponse get(Map<String, String> formData) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InterestResponse interestResponse = new InterestResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Interest> optionalInterest = interestService.findById(formData.get("id"));
            if (optionalInterest.isPresent()) {
                Interest interest = optionalInterest.get();
                interestResponse.setSuccess(true);
                interestResponse.setError("");
                interestResponse.setInterest(interest);
            } else {
                interestResponse.setSuccess(false);
                interestResponse.setError("Error occurred while Fetching interest!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return interestResponse;
    }

    @Override
    public InterestResponse getDeleted() throws Exception {
        UserInfo userInfo = null;
        try {
            userInfo = userInformation.getUserInfo();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        logger.trace("Entering");
        InterestResponse interestResponse = new InterestResponse();
        try {
            interestResponse.setInterestList(interestRepository.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 1));
            interestResponse.setSuccess(true);
            interestResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return interestResponse;
    }

    @Override
    public InterestResponse getAll() throws Exception {
        UserInfo userInfo = null;
        try {
            userInfo = userInformation.getUserInfo();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InterestResponse interestResponse = new InterestResponse();
        try {
            interestResponse.setInterestList(interestRepository.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0));
            interestResponse.setSuccess(true);
            interestResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return interestResponse;
    }

    @Override
    public InterestResponse getPaginated(Map<String, String> formData) throws Exception {
        UserInfo userInfo = null;
        try {
            userInfo = userInformation.getUserInfo();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InterestResponse interestResponse = new InterestResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
            Page<Interest> page = interestRepository.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            interestResponse.setTotalRecords(page.getTotalElements());
            interestResponse.setTotalPages(page.getTotalPages());
            interestResponse.setInterestList(page.getContent());
            interestResponse.setCurrentRecords(interestResponse.getInterestList().size());
            interestResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return interestResponse;
    }

    @Override
    public InterestResponse searchPaginated(Map<String, String> formData) throws Exception {
        UserInfo userInfo = null;
        try {
            userInfo = userInformation.getUserInfo();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InterestResponse interestResponse = new InterestResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
            Page<Interest> page;
            if (searchText == null) {
                page = interestRepository.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            } else {
                page = interestRepository.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
            }
            interestResponse.setTotalRecords(page.getTotalElements());
            interestResponse.setTotalPages(page.getTotalPages());
            interestResponse.setInterestList(page.getContent());
            interestResponse.setCurrentRecords(interestResponse.getInterestList().size());
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return interestResponse;
    }

    @Override
    public InterestResponse search(SearchRequest searchRequest) throws Exception {
        InterestResponse interestResponse = new InterestResponse();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Interest> criteriaQuery = criteriaBuilder.createQuery(Interest.class);
            Root<Interest> root = criteriaQuery.from(Interest.class);
            criteriaQuery = SearchBuilder.buildSearch(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root,
                    Interest.class,
                    searchRequest
            );
            long totalRecords = SearchBuilder.getTotalRecordCount(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root
            );
            TypedQuery<Interest> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setFirstResult((searchRequest.getCurrentPage() - 1) * searchRequest.getPageSize());
            typedQuery.setMaxResults(searchRequest.getPageSize());
            List<Interest> interestList = typedQuery.getResultList();
            interestResponse.setCurrentRecords(interestList.size());
            interestResponse.setTotalRecords(totalRecords);
            interestResponse.setSuccess(true);
            interestResponse.setError("");
            interestResponse.setInterestList(interestList);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.toString(), ex);
            throw ex;
        }
        return interestResponse;
    }

    @Override
    public InterestResponse findAllByClientIdAndIsDeleted(String clientId, int isDeleted) {
        InterestResponse interestResponse = new InterestResponse();
        List<Interest> interests =  interestRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted);
        interestResponse.setSuccess(true);
        interestResponse.setInterestList(interests);
        return interestResponse;
    }

    @Override
    public InterestResponse findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return null;
    }

    @Override
    public InterestResponse findAll(Specification<Interest> textInAllColumns, Pageable pageable) {
        return null;
    }

    @Override
    public InterestResponse findAllByClientIdAndIsDeletedAndName(String clientId, int isDeleted, String name) {
        return null;
    }

    @Override
    public InterestResponse findAllByClientIdAndIsDeletedAndNameAndIdIsNot(String clientId, int isDeleted, String name, String id) {
        return null;
    }

    @Override
    public int countByIsDeleted(int isDeleted) {
        return interestRepository.countByIsDeleted(isDeleted);
    }

    private boolean checkDuplicate(Interest interest) {
        List<Interest> accountHeadList;
        if (interest.getId().equals("")) {
            accountHeadList = interestRepository.findAllByClientIdAndIsDeletedAndName(interest.getClientId(), 0, interest.getName());
        } else {
            accountHeadList = interestRepository.findAllByClientIdAndIsDeletedAndNameAndIdIsNot(
                    interest.getClientId(), 0, interest.getName(), interest.getId());
        }
        if (accountHeadList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Interest> findAllByInterestCategoryId(String categoryId) {
        return interestRepository.findAllByInterestCategoryId(categoryId);
    }
}
