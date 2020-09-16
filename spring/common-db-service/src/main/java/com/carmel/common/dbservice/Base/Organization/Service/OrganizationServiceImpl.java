package com.carmel.common.dbservice.Base.Organization.Service;

import com.carmel.common.dbservice.Base.Client.Model.Client;
import com.carmel.common.dbservice.Base.Organization.Model.Organization;
import com.carmel.common.dbservice.Base.Organization.Responce.OrganizationResponse;
import com.carmel.common.dbservice.Base.Organization.Repository.OrganizationRepository;
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

import static com.carmel.common.dbservice.Base.Organization.Specification.OrganizationSpecification.textInAllColumns;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private  Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    private  ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    UserInformation userInformation;

    @Autowired
    EntityManager entityManager;

    @Override
    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public OrganizationResponse findAllByOrgName(String orgName) {
        return (OrganizationResponse) organizationRepository.findAllByOrgNameAndIsDeletedIs(orgName, 0);
    }

    @Override
    public OrganizationResponse findAllByOrgNameAndIdIsNot(String orgName, String id) {
        return (OrganizationResponse) organizationRepository.findAllByOrgNameAndIdIsNotAndIsDeletedIs(orgName, id, 0);
    }

    @Override
    public Optional<Organization> findById(String id) {
        return organizationRepository.findById(id);
    }

    @Override
    public OrganizationResponse findAllByDeletionStatus(int isDeleted, Client client) {
        return (OrganizationResponse) organizationRepository.findAllByIsDeletedAndClient(isDeleted, client);
    }

    @Override
    public OrganizationResponse findAll(Pageable pageable) {
        return (OrganizationResponse) organizationRepository.findAllByIsDeletedIs(0, pageable);
    }

    @Override
    public OrganizationResponse findAllByClient(Client client, Pageable pageable) {
        return (OrganizationResponse) organizationRepository.findAllByClientAndIsDeleted(client, 0, pageable);
    }

    @Override
    public OrganizationResponse findAll(Specification<Organization> textInAllColumns, Pageable pageable) {
        return (OrganizationResponse) organizationRepository.findAll(textInAllColumns, pageable);
    }

    @Override
    public OrganizationResponse saveOrgnization(Organization organization) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        OrganizationResponse organizationResponse = new OrganizationResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(organization));
            if (organization.getParent() != null) {
                Optional<Organization> optionalOrganization =
                        organizationService.findById(organization.getParent().getId());
                organization.setParent(optionalOrganization.get());
            }
            if (checkDuplicate(organization)) {
                organizationResponse.setOrganization(organization);
                organizationResponse.setSuccess(false);
                organizationResponse.setError("Duplicate organization name!");
            } else {
                if (organization.getId() != null) {
                    if (!organization.getId().trim().equals("")) {
                        organization.setLastModifiedBy(userInfo.getId());
                        organization.setLastModifiedTime(new Date());
                    } else {
                        organization.setCreatedBy(userInfo.getId());
                        organization.setCreationTime(new Date());
                    }
                } else {
                    organization.setCreatedBy(userInfo.getId());
                    organization.setCreationTime(new Date());
                }
                organization.setClient(userInfo.getClient());
                organizationResponse.setOrganization(organizationService.save(organization));
                organizationResponse.setSuccess(true);
                organizationResponse.setError("");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return organizationResponse;
    }

    @Override
    public OrganizationResponse moveToTrash(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        OrganizationResponse organizationResponse = new OrganizationResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Organization> organization = organizationRepository.findById(formData.get("id"));
            if (organization != null) {
                Organization org = organization.get();
                org.setIsDeleted(1);
                org.setDeletedBy(userInfo.getId());
                org.setDeletedTime(new Date());
                organizationResponse.setSuccess(true);
                organizationResponse.setError("");
                organizationResponse.setOrganization(organizationRepository.save(org));
            } else {
                organizationResponse.setSuccess(false);
                organizationResponse.setError("Error occurred while moving organization to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            organizationResponse.setSuccess(false);
            organizationResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return organizationResponse;
    }

    @Override
    public OrganizationResponse get(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        OrganizationResponse organizationResponse = new OrganizationResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Organization> organization = organizationRepository.findById(formData.get("id"));
            if (organization != null) {
                Organization org = organization.get();
                organizationResponse.setSuccess(true);
                organizationResponse.setError("");
                organizationResponse.setOrganization(org);
            } else {
                organizationResponse.setSuccess(false);
                organizationResponse.setError("Error occurred while fetching organization!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return organizationResponse;
    }

    @Override
    public OrganizationResponse getAll() throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        OrganizationResponse organizationResponse = new OrganizationResponse();
        try {
            organizationResponse.setOrganizationList(organizationRepository.findAllByIsDeletedAndClient(0, userInfo.getClient()));
            organizationResponse.setSuccess(true);
            organizationResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
    throw ex;
        }
        logger.trace("Exiting");
        return organizationResponse;
    }

    @Override
    public OrganizationResponse getDeleted() throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        OrganizationResponse organizationsResponse = new OrganizationResponse();
        try {
            organizationsResponse.setOrganizationList(organizationRepository.findAllByIsDeletedAndClient(1, userInfo.getClient()));
            organizationsResponse.setSuccess(true);
            organizationsResponse.setError("");
        } catch (Exception ex) {
            organizationsResponse.setSuccess(false);
            organizationsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return organizationsResponse;
    }

    @Override
    public OrganizationResponse getPaginated(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();

        logger.trace("Entering");
        OrganizationResponse orgResponse = new OrganizationResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("orgName"));
            Page<Organization> page = organizationRepository.findAllByClientAndIsDeleted(userInfo.getClient(),0, pageable);
            orgResponse.setTotalRecords(page.getTotalElements());
            orgResponse.setTotalPages(page.getTotalPages());
            orgResponse.setOrganizationList(page.getContent());
            orgResponse.setCurrentRecords(orgResponse.getOrganizationList().size());
            orgResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return orgResponse;
    }

    @Override
    public OrganizationResponse searchPaginated(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        OrganizationResponse orgResponse = new OrganizationResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("orgName"));
            Page<Organization> page;
            if (searchText == null) {
                page = organizationRepository.findAllByIsDeleted(userInfo.getClient(), pageable);
            } else {
                page = organizationRepository.findAll(textInAllColumns(searchText, userInfo.getClient()), pageable);
            }

            orgResponse.setTotalRecords(page.getTotalElements());
            orgResponse.setTotalPages(page.getTotalPages());
            orgResponse.setOrganizationList(page.getContent());
            orgResponse.setCurrentRecords(orgResponse.getOrganizationList().size());
            orgResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            orgResponse.setSuccess(false);
            orgResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return orgResponse;
    }

    @Override
    public OrganizationResponse search(SearchRequest searchRequest) throws Exception {
        OrganizationResponse organizationResponse = new OrganizationResponse();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
            Root<Organization> root = criteriaQuery.from(Organization.class);
            criteriaQuery = SearchBuilder.buildSearch(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root,
                    Organization.class,
                    searchRequest
            );
            long totalRecords = SearchBuilder.getTotalRecordCount(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root
            );
            TypedQuery<Organization> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setFirstResult((searchRequest.getCurrentPage() - 1) * searchRequest.getPageSize());
            typedQuery.setMaxResults(searchRequest.getPageSize());
            List<Organization> organizationList = typedQuery.getResultList();
            organizationResponse.setCurrentRecords(organizationList.size());
            organizationResponse.setTotalRecords(totalRecords);
            organizationResponse.setSuccess(true);
            organizationResponse.setError("");
            organizationResponse.setOrganizationList(organizationList);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.toString(), ex);
            throw ex;
        }
        return organizationResponse;
    }

    private boolean checkDuplicate(Organization organization) throws Exception {
        List<Organization> orgList;
        if (organization.getId() == null) {
            organization.setId("");
        }
        if (organization.getId().isEmpty()) {
            orgList = organizationRepository.findAllByOrgName(organization.getOrgName());
        } else {
            orgList = organizationRepository.findAllByOrgNameAndIdIsNot(organization.getOrgName(), organization.getId());
        }
        if (orgList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }


}
