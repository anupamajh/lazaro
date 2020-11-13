package com.carmel.guestjini.service.controller.HelpDesk;

import com.carmel.guestjini.service.model.HelpDesk.TaskForceGroup;
import com.carmel.guestjini.service.response.HelpDesk.TaskForceGroupResponse;
import com.carmel.guestjini.service.service.HelpDesk.TaskForceGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/task-force-group")
public class TaskForceGroupController {
    Logger logger = LoggerFactory.getLogger(TaskForceGroupController.class);

    @Autowired
    TaskForceGroupService taskForceGroupService;

    @PostConstruct
    public void setUpServices() {
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public TaskForceGroupResponse save(@Valid @RequestBody TaskForceGroup taskForceGroup) {
        logger.trace("Entering");
        TaskForceGroupResponse taskForceGroupResponse = new TaskForceGroupResponse();
        try {
            taskForceGroupResponse = taskForceGroupService
                    .saveTaskForceGroup(taskForceGroup);
        } catch (Exception ex) {
            taskForceGroupResponse.setSuccess(true);
            taskForceGroupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceGroupResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public TaskForceGroupResponse moveToTrash(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        TaskForceGroupResponse taskForceGroupResponse = new TaskForceGroupResponse();
        try {
            taskForceGroupResponse = taskForceGroupService.moveToTrash(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceGroupResponse.setSuccess(false);
            taskForceGroupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceGroupResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public TaskForceGroupResponse get(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        TaskForceGroupResponse taskForceGroupResponse = new TaskForceGroupResponse();
        try {
            taskForceGroupResponse = taskForceGroupService.get(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceGroupResponse.setSuccess(false);
            taskForceGroupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceGroupResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public TaskForceGroupResponse getDeleted() {
        logger.trace("Entering");
        TaskForceGroupResponse taskForceGroupResponse = new TaskForceGroupResponse();
        try {
            taskForceGroupResponse = taskForceGroupService.getDeleted();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceGroupResponse.setSuccess(false);
            taskForceGroupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceGroupResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public TaskForceGroupResponse getAll() {
        logger.trace("Entering");
        TaskForceGroupResponse taskForceGroupResponse = new TaskForceGroupResponse();
        try {
            taskForceGroupResponse = taskForceGroupService.getAll();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceGroupResponse.setSuccess(false);
            taskForceGroupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceGroupResponse;
    }

    @RequestMapping(value = "/get-item-categories", method = RequestMethod.POST)
    public TaskForceGroupResponse getPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        TaskForceGroupResponse taskForceGroupResponse = new TaskForceGroupResponse();
        try {
            taskForceGroupResponse = taskForceGroupService.getPaginated(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceGroupResponse.setSuccess(false);
            taskForceGroupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceGroupResponse;
    }

    @RequestMapping(value = "/search-item-categories", method = RequestMethod.POST)
    public TaskForceGroupResponse searchPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        TaskForceGroupResponse taskForceGroupResponse = new TaskForceGroupResponse();
        try {
            taskForceGroupResponse = taskForceGroupService.searchPaginated(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceGroupResponse.setSuccess(false);
            taskForceGroupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceGroupResponse;
    }
}
