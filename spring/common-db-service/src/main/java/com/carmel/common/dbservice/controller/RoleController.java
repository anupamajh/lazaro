package com.carmel.common.dbservice.controller;

import com.carmel.common.dbservice.common.Search.SearchBuilder;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.Role;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.response.RoleResponse;
import com.carmel.common.dbservice.response.RolesResponse;
import com.carmel.common.dbservice.services.OrganizationService;
import com.carmel.common.dbservice.services.RoleService;
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

import static com.carmel.common.dbservice.specifications.RoleSpecification.textInAllColumns;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

    Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    RoleService roleService;

    @Autowired
    UserInformation userInformation;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    EntityManager entityManager;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RoleResponse save(@Valid @RequestBody Role role) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        RoleResponse roleResponse = new RoleResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(role));
            if (checkDuplicate(role)) {
                roleResponse.setRole(role);
                roleResponse.setSuccess(false);
                roleResponse.setError("Duplicate role name!");
            } else {
                if (role.getId() != null) {
                    if (!role.getId().trim().equals("")) {
                        role.setLastModifiedBy(userInfo.getId());
                        role.setLastModifiedTime(new Date());
                    } else {
                        role.setCreatedBy(userInfo.getId());
                        role.setCreationTime(new Date());
                    }
                } else {
                    role.setCreatedBy(userInfo.getId());
                    role.setCreationTime(new Date());
                }
                role.setClient(userInfo.getClient());
                roleResponse.setRole(roleService.save(role));
                roleResponse.setSuccess(true);
                roleResponse.setError("");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            roleResponse.setSuccess(false);
            roleResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return roleResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public RoleResponse moveToTrash(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        RoleResponse roleResponse = new RoleResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Role> optionalRole = roleService.findById(formData.get("id"));
            if (optionalRole != null) {
                Role role = optionalRole.get();
                role.setIsDeleted(1);
                role.setDeletedBy(userInfo.getId());
                role.setDeletedTime(new Date());
                roleResponse.setSuccess(true);
                roleResponse.setError("");
                roleResponse.setRole(roleService.save(role));
            } else {
                roleResponse.setSuccess(false);
                roleResponse.setError("Error occurred while moving role to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            roleResponse.setSuccess(false);
            roleResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return roleResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public RoleResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        RoleResponse roleResponse = new RoleResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Role> optionalRole = roleService.findById(formData.get("id"));
            if (optionalRole != null) {
                Role role = optionalRole.get();
                roleResponse.setSuccess(true);
                roleResponse.setError("");
                roleResponse.setRole(role);
            } else {
                roleResponse.setSuccess(false);
                roleResponse.setError("Error occurred while moving organization to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            roleResponse.setSuccess(false);
            roleResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return roleResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public RolesResponse getDeleted() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        RolesResponse rolesResponse = new RolesResponse();
        try {
            rolesResponse.setRoleList(roleService.findAllByIsDeletedAndClient(1, userInfo.getClient()));
            rolesResponse.setSuccess(true);
            rolesResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            rolesResponse.setSuccess(true);
            rolesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return rolesResponse;
    }


    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public RolesResponse getAll() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        RolesResponse rolesResponse = new RolesResponse();
        try {
            rolesResponse.setRoleList(roleService.findAllByIsDeletedAndClient(0, userInfo.getClient()));
            rolesResponse.setSuccess(true);
            rolesResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            rolesResponse.setSuccess(true);
            rolesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return rolesResponse;
    }

    @RequestMapping(value = "/get-roles", method = RequestMethod.POST)
    public RolesResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        RolesResponse rolesResponse = new RolesResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("roleName"));
            Page<Role> page = roleService.findAllByClientAndIsDeleted(userInfo.getClient(), 0, pageable);
            rolesResponse.setTotalRecords(page.getTotalElements());
            rolesResponse.setTotalPages(page.getTotalPages());
            rolesResponse.setRoleList(page.getContent());
            rolesResponse.setCurrentRecords(rolesResponse.getRoleList().size());
            rolesResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            rolesResponse.setSuccess(false);
            rolesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return rolesResponse;
    }

    @RequestMapping(value = "/search-roles", method = RequestMethod.POST)
    public RolesResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        RolesResponse rolesResponse = new RolesResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("roleName"));
            Page<Role> page;
            if (searchText == null) {
                page = roleService.findAllByClient(userInfo.getClient(), pageable);
            } else {
                page = roleService.findAll(textInAllColumns(searchText, userInfo.getClient()), pageable);
            }
            rolesResponse.setTotalRecords(page.getTotalElements());
            rolesResponse.setTotalPages(page.getTotalPages());
            rolesResponse.setRoleList(page.getContent());
            rolesResponse.setCurrentRecords(rolesResponse.getRoleList().size());
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            rolesResponse.setSuccess(false);
            rolesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return rolesResponse;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public RolesResponse search(@RequestBody SearchRequest searchRequest) {
        RolesResponse rolesResponse = new RolesResponse();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
            Root<Role> root = criteriaQuery.from(Role.class);
            criteriaQuery = SearchBuilder.buildSearch(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root,
                    Role.class,
                    searchRequest
            );
            long totalRecords = SearchBuilder.getTotalRecordCount(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root
            );
            TypedQuery<Role> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setFirstResult((searchRequest.getCurrentPage() - 1) * searchRequest.getPageSize());
            typedQuery.setMaxResults(searchRequest.getPageSize());
            List<Role> roleList = typedQuery.getResultList();
            rolesResponse.setCurrentRecords(roleList.size());
            rolesResponse.setTotalRecords(totalRecords);
            rolesResponse.setSuccess(true);
            rolesResponse.setError("");
            rolesResponse.setRoleList(roleList);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.toString(), ex);
            rolesResponse.setSuccess(false);
            rolesResponse.setError(ex.getMessage());
        }
        return rolesResponse;
    }


    private boolean checkDuplicate(Role role) {
        List<Role> roleList;
        if (role.getId() == null) {
            role.setId("");
        }
        if (role.getId() == "") {
            roleList = roleService.findAllByRoleNameAndClient(role.getRoleName(), role.getClient());
        } else {
            roleList = roleService.findAllByRoleNameAndIdIsNotAndClient(role.getRoleName(), role.getId(), role.getClient());
        }
        if (roleList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
