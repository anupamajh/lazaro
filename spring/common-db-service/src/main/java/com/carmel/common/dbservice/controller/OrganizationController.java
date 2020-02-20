package com.carmel.common.dbservice.controller;

import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.Organization;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.response.OrganizationResponse;
import com.carmel.common.dbservice.response.OrganizationsResponse;
import com.carmel.common.dbservice.services.OrganizationService;
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

import static com.carmel.common.dbservice.specifications.OrganizationSpecification.textInAllColumns;

@RestController
@RequestMapping(value = "/organization")
public class OrganizationController {

    Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    OrganizationService organizationService;

    @Autowired
    UserInformation userInformation;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public OrganizationResponse save(@Valid @RequestBody Organization organization) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        OrganizationResponse organizationResponse = new OrganizationResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(organization));
            if(organization.getParent() != null){
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
            organizationResponse.setSuccess(false);
            organizationResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return organizationResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public OrganizationResponse moveToTrash(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        OrganizationResponse organizationResponse = new OrganizationResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Organization> organization = organizationService.findById(formData.get("id"));
            if (organization != null) {
                Organization org = organization.get();
                org.setIsDeleted(1);
                org.setDeletedBy(userInfo.getId());
                org.setDeletedTime(new Date());
                organizationResponse.setSuccess(true);
                organizationResponse.setError("");
                organizationResponse.setOrganization(organizationService.save(org));
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

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public OrganizationResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
         logger.trace("Entering");
        OrganizationResponse organizationResponse = new OrganizationResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Organization> organization = organizationService.findById(formData.get("id"));
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
            organizationResponse.setSuccess(false);
            organizationResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return organizationResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public OrganizationsResponse getAll() {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        OrganizationsResponse organizationsResponse = new OrganizationsResponse();
        try {
            organizationsResponse.setOrganizationList(organizationService.findAllByDeletionStatus(0,userInfo.getClient()));
            organizationsResponse.setSuccess(true);
            organizationsResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            organizationsResponse.setSuccess(false);
            organizationsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return organizationsResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.GET)
    public OrganizationsResponse getDeleted() {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        OrganizationsResponse organizationsResponse = new OrganizationsResponse();
        try {
            organizationsResponse.setOrganizationList(organizationService.findAllByDeletionStatus(1,userInfo.getClient()));
            organizationsResponse.setSuccess(true);
            organizationsResponse.setError("");
        } catch (Exception ex) {
            organizationsResponse.setSuccess(false);
            organizationsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return organizationsResponse;
    }

    @RequestMapping(value = "/get-organizations", method = RequestMethod.POST)
    public OrganizationsResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        OrganizationsResponse orgResponse = new OrganizationsResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("orgName"));
            Page<Organization> page = organizationService.findAllByClient(userInfo.getClient(), pageable);
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

    @RequestMapping(value = "/search-organizations", method = RequestMethod.POST)
    public OrganizationsResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        OrganizationsResponse orgResponse = new OrganizationsResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("orgName"));
            Page<Organization> page;
            if (searchText == null ){
                page = organizationService.findAllByClient(userInfo.getClient(),pageable);
            } else  {
                page = organizationService.findAll(textInAllColumns(searchText, userInfo.getClient()), pageable);
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

    private boolean checkDuplicate(Organization organization) {
        List<Organization> orgList;
        if (organization.getId() == null) {
            organization.setId("");
        }
        if (organization.getId().isEmpty()) {
            orgList = organizationService.findAllByOrgName(organization.getOrgName());
        } else {
            orgList = organizationService.findAllByOrgNameAndIdIsNot(organization.getOrgName(), organization.getId());
        }
        if (orgList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
