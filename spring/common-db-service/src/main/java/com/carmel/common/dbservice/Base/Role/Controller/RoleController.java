package com.carmel.common.dbservice.Base.Role.Controller;

import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.Base.Role.Model.Role;
import com.carmel.common.dbservice.Base.Role.Response.RolesResponse;
import com.carmel.common.dbservice.Base.Role.Service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

    Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RolesResponse save(@Valid @RequestBody Role role) {
        logger.trace("Entering");
        RolesResponse rolesResponse = new RolesResponse();
        try {
            rolesResponse = roleService
                    .saveRole(role);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            rolesResponse.setSuccess(true);
            rolesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return rolesResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public RolesResponse moveToTrash(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        RolesResponse rolesResponse = new RolesResponse();
        try {
            rolesResponse = roleService
                    .moveToTrash(formData);
        } catch (Exception ex) {
            rolesResponse.setSuccess(true);
            rolesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return rolesResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public RolesResponse get(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        RolesResponse rolesResponse = new RolesResponse();
        try {
            rolesResponse = roleService
                    .get(formData);
        } catch (Exception ex) {
            rolesResponse.setSuccess(true);
            rolesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return rolesResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public RolesResponse getDeleted() {
        logger.trace("Entering");
        RolesResponse rolesResponse = new RolesResponse();
        try {
            rolesResponse = roleService
                    .getDeleted();
        } catch (Exception ex) {
            rolesResponse.setSuccess(true);
            rolesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return rolesResponse;
    }


    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public RolesResponse getAll() {
        logger.trace("Entering");
        RolesResponse rolesResponse = new RolesResponse();
        try {
            rolesResponse = roleService
                    .getAll();
        } catch (Exception ex) {
            rolesResponse.setSuccess(true);
            rolesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return rolesResponse;
    }

    @RequestMapping(value = "/get-roles", method = RequestMethod.POST)
    public RolesResponse getPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        RolesResponse rolesResponse = new RolesResponse();
        try {
            rolesResponse = roleService
                    .getPaginated(formData);
        } catch (Exception ex) {
            rolesResponse.setSuccess(true);
            rolesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return rolesResponse;
    }

    @RequestMapping(value = "/search-roles", method = RequestMethod.POST)
    public RolesResponse searchPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        RolesResponse rolesResponse = new RolesResponse();
        try {
            rolesResponse = roleService
                    .searchPaginated(formData);
        } catch (Exception ex) {
            rolesResponse.setSuccess(true);
            rolesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return rolesResponse;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public RolesResponse search(@RequestBody SearchRequest searchRequest) {
        logger.trace("Entering");
        RolesResponse rolesResponse = new RolesResponse();
        try {
            rolesResponse = roleService
                    .search(searchRequest);
        } catch (Exception ex) {
            rolesResponse.setSuccess(true);
            rolesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return rolesResponse;
    }



}
