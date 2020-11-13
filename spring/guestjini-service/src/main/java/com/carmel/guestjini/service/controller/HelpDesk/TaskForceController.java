package com.carmel.guestjini.service.controller.HelpDesk;

import com.carmel.guestjini.service.model.HelpDesk.TaskForce;
import com.carmel.guestjini.service.response.HelpDesk.TaskForceResponse;
import com.carmel.guestjini.service.service.HelpDesk.TaskForceService;
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
@RequestMapping(value = "/task-force")
public class TaskForceController {
    Logger logger = LoggerFactory.getLogger(TaskForceController.class);

    @Autowired
    TaskForceService taskForceService;

    @PostConstruct
    public void setUpServices() {
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public TaskForceResponse save(@Valid @RequestBody TaskForce taskForce) {
        logger.trace("Entering");
        TaskForceResponse taskForceResponse = new TaskForceResponse();
        try {
            taskForceResponse = taskForceService
                    .saveTaskForce(taskForce);
        } catch (Exception ex) {
            taskForceResponse.setSuccess(true);
            taskForceResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public TaskForceResponse moveToTrash(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        TaskForceResponse taskForceResponse = new TaskForceResponse();
        try {
            taskForceResponse = taskForceService.moveToTrash(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceResponse.setSuccess(false);
            taskForceResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public TaskForceResponse get(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        TaskForceResponse taskForceResponse = new TaskForceResponse();
        try {
            taskForceResponse = taskForceService.get(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceResponse.setSuccess(false);
            taskForceResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public TaskForceResponse getDeleted() {
        logger.trace("Entering");
        TaskForceResponse taskForceResponse = new TaskForceResponse();
        try {
            taskForceResponse = taskForceService.getDeleted();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceResponse.setSuccess(false);
            taskForceResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public TaskForceResponse getAll() {
        logger.trace("Entering");
        TaskForceResponse taskForceResponse = new TaskForceResponse();
        try {
            taskForceResponse = taskForceService.getAll();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceResponse.setSuccess(false);
            taskForceResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceResponse;
    }

    @RequestMapping(value = "/get-item-categories", method = RequestMethod.POST)
    public TaskForceResponse getPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        TaskForceResponse taskForceResponse = new TaskForceResponse();
        try {
            taskForceResponse = taskForceService.getPaginated(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceResponse.setSuccess(false);
            taskForceResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceResponse;
    }

    @RequestMapping(value = "/search-item-categories", method = RequestMethod.POST)
    public TaskForceResponse searchPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        TaskForceResponse taskForceResponse = new TaskForceResponse();
        try {
            taskForceResponse = taskForceService.searchPaginated(formData);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceResponse.setSuccess(false);
            taskForceResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceResponse;
    }
}
