package com.carmel.common.dbservice.controller;

import com.carmel.common.dbservice.model.Role;
import com.carmel.common.dbservice.response.RoleResponse;
import com.carmel.common.dbservice.response.RolesResponse;
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

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

    Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RoleResponse save(@RequestBody Role role) {
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
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        RoleResponse roleResponse = new RoleResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Role> optionalRole = roleService.findById(formData.get("id"));
            if (optionalRole != null) {
                Role role = optionalRole.get();
                role.setIsDeleted(1);
                roleResponse.setSuccess(true);
                roleResponse.setError("");
                roleResponse.setRole(roleService.save(role));
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


    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public RolesResponse getAll(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        String orgId = formData.get("orgId") == null ? "" : formData.get("orgId");
        RolesResponse rolesResponse = new RolesResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            rolesResponse.setRoleList(roleService.findAllByIsDeletedAndOrgId(0, orgId));
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

    @RequestMapping(value = "/get-deleted", method = RequestMethod.GET)
    public RolesResponse getDeleted(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        String orgId = formData.get("orgId") == null ? "" : formData.get("orgId");
        RolesResponse rolesResponse = new RolesResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            rolesResponse.setRoleList(roleService.findAllByIsDeletedAndOrgId(1, orgId));
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

    @RequestMapping(value = "/get-roles", method = RequestMethod.GET)
    public RolesResponse getPaginated(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        RolesResponse rolesResponse = new RolesResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String orgId = formData.get("org_id") == null ? "" : String.valueOf(formData.get("org_id"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("roleName"));
            Page<Role> page = roleService.findAllByOrOrgId(orgId, pageable);
            rolesResponse.setTotalRecords(page.getTotalElements());
            rolesResponse.setTotalPages(page.getTotalPages());
            rolesResponse.setRoleList(page.getContent());
            rolesResponse.setCurrentRecords(rolesResponse.getRoleList().size());
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

    @RequestMapping(value = "/search-roles", method = RequestMethod.GET)
    public RolesResponse searchPaginated(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        RolesResponse rolesResponse = new RolesResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String orgId = formData.get("org_id") == null ? "" : String.valueOf(formData.get("org_id"));
            String roleName = formData.get("role_name") == null ? null : String.valueOf(formData.get("role_name"));
            String description = formData.get("description") == null ? null : String.valueOf(formData.get("description"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("roleName"));
            Page<Role> page;
            if (roleName == null && description == null) {
                page = roleService.findAllByOrOrgId(orgId, pageable);
            } else if (roleName != null && description == null) {
                page = roleService.findAllByRoleNameContainingAndOrgId(roleName, orgId, pageable);
            } else if (description != null && roleName == null) {
                page = roleService.findAllByDescriptionContainingAndOrgId(description, orgId, pageable);
            } else {
                page = roleService
                        .findAllByOrgIdAndRoleNameContainingOrDescriptionContaining(orgId, roleName, description, pageable);
            }
            rolesResponse.setTotalRecords(page.getTotalElements());
            rolesResponse.setTotalPages(page.getTotalPages());
            rolesResponse.setRoleList(page.getContent());
            rolesResponse.setCurrentRecords(rolesResponse.getRoleList().size());
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

    private boolean checkDuplicate(Role role) {
        List<Role> roleList;
        if (role.getId() == null) {
            role.setId("");
        }
        if (role.getId() == "") {
            roleList = roleService.findAllByRoleNameAndOrgId(role.getRoleName(), role.getOrgId());
        } else {
            roleList = roleService.findAllByRoleNameAndIdIsNotAndOrgIdIs(role.getRoleName(), role.getId(), role.getOrgId());
        }
        if (roleList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
