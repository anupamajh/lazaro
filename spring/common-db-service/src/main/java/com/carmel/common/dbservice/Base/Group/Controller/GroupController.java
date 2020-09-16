package com.carmel.common.dbservice.Base.Group.Controller;


import com.carmel.common.dbservice.Base.Group.Model.Group;
import com.carmel.common.dbservice.Base.Group.Response.GroupResponse;
import com.carmel.common.dbservice.Base.Group.Service.GroupService;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/groups")
public class GroupController {
    Logger logger = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    GroupService groupService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public GroupResponse save(@Valid @RequestBody Group group) {
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            groupResponse = groupService
                    .saveGroup(group);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            groupResponse.setSuccess(true);
            groupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return groupResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public GroupResponse moveToTrash(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            groupResponse = groupService
                    .moveToTrash(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            groupResponse.setSuccess(true);
            groupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return groupResponse;
    }

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public GroupResponse subscribe(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            groupResponse = groupService
                    .subscribe(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            groupResponse.setSuccess(true);
            groupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return groupResponse;
    }


    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public GroupResponse get(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            groupResponse = groupService
                    .get(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            groupResponse.setSuccess(true);
            groupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return groupResponse;
    }

    @RequestMapping(value = "/invite", method = RequestMethod.POST)
    public GroupResponse inviteToGroup(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            groupResponse = groupService
                    .inviteToGroup(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            groupResponse.setSuccess(true);
            groupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return groupResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public GroupResponse getDeleted() {
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            groupResponse = groupService
                    .getDeleted();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            groupResponse.setSuccess(true);
            groupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return groupResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public GroupResponse getAll() {
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            groupResponse = groupService
                    .getAll();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            groupResponse.setSuccess(true);
            groupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return groupResponse;
    }

    @RequestMapping(value = "/get-all-by-type", method = RequestMethod.POST)
    public GroupResponse getAllByType(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            groupResponse = groupService
                    .getAllByType(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            groupResponse.setSuccess(true);
            groupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return groupResponse;
    }

    @RequestMapping(value = "/get-groups", method = RequestMethod.POST)
    public GroupResponse getPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            groupResponse = groupService
                    .getPaginated(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            groupResponse.setSuccess(true);
            groupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return groupResponse;
    }

    @RequestMapping(value = "/search-groups", method = RequestMethod.POST)
    public GroupResponse searchPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            groupResponse = groupService
                    .searchPaginated(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            groupResponse.setSuccess(true);
            groupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return groupResponse;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public GroupResponse search(@RequestBody SearchRequest searchRequest) {
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            groupResponse = groupService
                    .search(searchRequest);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            groupResponse.setSuccess(true);
            groupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return groupResponse;
    }

}
