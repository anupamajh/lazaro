package com.carmel.guestjini.service.controller.HelpDesk;


import com.carmel.guestjini.service.HelpDesk.TaskTicket.DTO.TaskAssigneeDTO;
import com.carmel.guestjini.service.HelpDesk.TaskTicket.Response.TaskAssigneeResponse;
import com.carmel.guestjini.service.components.UserInformation;
import com.carmel.guestjini.service.model.HelpDesk.TaskRunner;
import com.carmel.guestjini.service.model.Principal.UserInfo;
import com.carmel.guestjini.service.response.HelpDesk.TaskRunnerResponse;
import com.carmel.guestjini.service.service.HelpDesk.TaskRunnerService;
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
import java.util.Map;
import java.util.Optional;

import static com.carmel.guestjini.service.specification.HelpDesk.TaskRunnerSpecification.textInAllColumns;


@RestController
@RequestMapping(value = "/task-runner")
public class TaskRunnerController {

    Logger logger = LoggerFactory.getLogger(TaskTicketController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    TaskRunnerService taskRunnerService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public TaskRunnerResponse save(@Valid @RequestBody TaskRunner taskRunner) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        TaskRunnerResponse taskRunnerResponse = new TaskRunnerResponse();
        try {
            if (taskRunner.getId() == null) {
                taskRunner.setId("");
            }
            if (taskRunner.getOrgId() == null || taskRunner.getOrgId().isEmpty()) {
                if (userInfo.getDefaultOrganization() != null) {
                    taskRunner.setOrgId(userInfo.getDefaultOrganization().getId());
                }
            }
            if (taskRunner.getId().equals("")) {
                taskRunner.setCreatedBy(userInfo.getId());
                taskRunner.setCreationTime(new Date());
            } else {
                taskRunner.setLastModifiedBy(userInfo.getId());
                taskRunner.setLastModifiedTime(new Date());
            }
            taskRunner.setClientId(userInfo.getClient().getClientId());
            if (checkDuplicate(taskRunner)) {
                taskRunnerResponse.setTaskRunner(taskRunner);
                taskRunnerResponse.setSuccess(false);
                taskRunnerResponse.setError("Duplicate TaskTicket name!");
            } else {
                taskRunnerResponse.setTaskRunner(taskRunnerService.save(taskRunner));
                taskRunnerResponse.setSuccess(true);
                taskRunnerResponse.setError("");
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskRunnerResponse.setSuccess(false);
            taskRunnerResponse.setError(ex.getMessage());
        }
        return taskRunnerResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public TaskRunnerResponse moveToTrash(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        TaskRunnerResponse taskRunnerResponse = new TaskRunnerResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<TaskRunner> optionalTaskRunner =
                    taskRunnerService.findById(formData.get("id"));
            if (optionalTaskRunner.isPresent()) {
                TaskRunner taskRunner = optionalTaskRunner.get();
                taskRunner.setIsDeleted(1);
                taskRunner.setDeletedBy(userInfo.getId());
                taskRunner.setDeletedTime(new Date());
                taskRunnerResponse.setSuccess(true);
                taskRunnerResponse.setError("");
                taskRunnerResponse.setTaskRunner(taskRunnerService.save(taskRunner));
            } else {
                taskRunnerResponse.setSuccess(false);
                taskRunnerResponse.setError("Error occurred while moving Task Runner to Trash!! Please try after sometime");
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskRunnerResponse.setSuccess(false);
            taskRunnerResponse.setError(ex.getMessage());
        }
        return taskRunnerResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public TaskRunnerResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        TaskRunnerResponse taskRunnerResponse = new TaskRunnerResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<TaskRunner> optionalTaskRunner = taskRunnerService.findById(formData.get("id"));
            if (optionalTaskRunner.isPresent()) {
                TaskRunner ticketCategories = optionalTaskRunner.get();
                taskRunnerResponse.setSuccess(true);
                taskRunnerResponse.setError("");
                taskRunnerResponse.setTaskRunner(ticketCategories);
            } else {
                taskRunnerResponse.setSuccess(false);
                taskRunnerResponse.setError("Error occurred while fetching Task Runner!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskRunnerResponse.setSuccess(false);
            taskRunnerResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskRunnerResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public TaskRunnerResponse getAll() {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        TaskRunnerResponse taskRunnerResponse = new TaskRunnerResponse();
        try {
            taskRunnerResponse.setTaskRunnerList(
                    taskRunnerService
                            .findAllByClientIdAndIsDeleted(
                                    userInfo.getClient().getClientId(),
                                    0
                            )
            );
            taskRunnerResponse.setSuccess(true);
            taskRunnerResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskRunnerResponse.setSuccess(false);
            taskRunnerResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskRunnerResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public TaskRunnerResponse getDeleted() {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        TaskRunnerResponse taskRunnerResponse = new TaskRunnerResponse();
        try {
            taskRunnerResponse.setTaskRunnerList(
                    taskRunnerService
                            .findAllByClientIdAndIsDeleted(
                                    userInfo.getClient().getClientId(),
                                    1
                            )
            );
            taskRunnerResponse.setSuccess(true);
            taskRunnerResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskRunnerResponse.setSuccess(false);
            taskRunnerResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskRunnerResponse;
    }

    @RequestMapping(value = "/get-task-runners", method = RequestMethod.POST)
    public TaskRunnerResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        TaskRunnerResponse taskRunnerResponse = new TaskRunnerResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<TaskRunner> page = taskRunnerService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            taskRunnerResponse.setTotalRecords(page.getTotalElements());
            taskRunnerResponse.setTotalPages(page.getTotalPages());
            taskRunnerResponse.setTaskRunnerList(page.getContent());
            taskRunnerResponse.setCurrentRecords(taskRunnerResponse.getTaskRunnerList().size());
            taskRunnerResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskRunnerResponse.setSuccess(false);
            taskRunnerResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskRunnerResponse;
    }

    @RequestMapping(value = "/search-task-runners", method = RequestMethod.POST)
    public TaskRunnerResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        TaskRunnerResponse taskRunnerResponse = new TaskRunnerResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<TaskRunner> page;
            if (searchText == null) {
                page = taskRunnerService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            } else {
                page = taskRunnerService.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
            }

            taskRunnerResponse.setTotalRecords(page.getTotalElements());
            taskRunnerResponse.setTotalPages(page.getTotalPages());
            taskRunnerResponse.setTaskRunnerList(page.getContent());
            taskRunnerResponse.setCurrentRecords(taskRunnerResponse.getTaskRunnerList().size());
            taskRunnerResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskRunnerResponse.setSuccess(false);
            taskRunnerResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskRunnerResponse;
    }


    private boolean checkDuplicate(TaskRunner taskRunner) {
        return false;
    }


    @RequestMapping(value = "/assign-ticket", method = RequestMethod.POST)
    public TaskRunnerResponse assignTicket(@RequestBody TaskAssigneeDTO taskAssigneeDTO) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        TaskRunnerResponse taskRunnerResponse = new TaskRunnerResponse();
        try {
            taskRunnerResponse = taskRunnerService.assignTicket(taskAssigneeDTO);

        } catch (Exception ex) {
            taskRunnerResponse.setSuccess(false);
            taskRunnerResponse.setError(ex.getMessage());
            logger.error(ex.toString());
        }
        return taskRunnerResponse;
    }

    @RequestMapping(value = "/get-assignment-details", method = RequestMethod.POST)
    public TaskAssigneeResponse getAssignmentDetails(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        TaskAssigneeResponse taskAssigneeResponse = new TaskAssigneeResponse();
        try {
            taskAssigneeResponse = taskRunnerService.getTaskAssignmentDetails(formData);
        }catch (Exception ex) {
            logger.error(ex.toString());
            taskAssigneeResponse.setSuccess(false);
            taskAssigneeResponse.setError(ex.getMessage());
        }
        return taskAssigneeResponse;
    }


}
