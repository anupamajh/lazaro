package com.carmel.guestjini.service.controller.HelpDesk;


import com.carmel.guestjini.service.components.UserInformation;
import com.carmel.guestjini.service.model.HelpDesk.TaskTicketCategories;
import com.carmel.guestjini.service.model.Principal.UserInfo;
import com.carmel.guestjini.service.response.HelpDesk.TaskTicketCategoriesResponse;
import com.carmel.guestjini.service.service.HelpDesk.TaskTicketCategoriesService;
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

import static com.carmel.guestjini.service.specification.HelpDesk.TaskTicketCategoriesSpecification.textInAllColumns;


@RestController
@RequestMapping(value = "/task-ticket-categories")
public class TaskTicketCategoriesController {
    Logger logger = LoggerFactory.getLogger(TaskTicketController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    TaskTicketCategoriesService taskTicketCategoriesService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public TaskTicketCategoriesResponse save(@Valid @RequestBody TaskTicketCategories taskTicketCategories) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        TaskTicketCategoriesResponse taskTicketCategoriesResponse = new TaskTicketCategoriesResponse();
        try {
            if (taskTicketCategories.getId() == null) {
                taskTicketCategories.setId("");
            }
            if (taskTicketCategories.getOrgId() == null || taskTicketCategories.getOrgId().isEmpty()) {
                if(userInfo.getDefaultOrganization() != null) {
                    taskTicketCategories.setOrgId(userInfo.getDefaultOrganization().getId());
                }
            }
            if (taskTicketCategories.getId().equals("")) {
                taskTicketCategories.setCreatedBy(userInfo.getId());
                taskTicketCategories.setCreationTime(new Date());
            } else {
                taskTicketCategories.setLastModifiedBy(userInfo.getId());
                taskTicketCategories.setLastModifiedTime(new Date());
            }
            taskTicketCategories.setClientId(userInfo.getClient().getClientId());
            if (checkDuplicate(taskTicketCategories)) {
                taskTicketCategoriesResponse.setTaskTicketCategories(taskTicketCategories);
                taskTicketCategoriesResponse.setSuccess(false);
                taskTicketCategoriesResponse.setError("Duplicate TaskTicket name!");
            } else {
                taskTicketCategoriesResponse.setTaskTicketCategories(taskTicketCategoriesService.save(taskTicketCategories));
                taskTicketCategoriesResponse.setSuccess(true);
                taskTicketCategoriesResponse.setError("");
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskTicketCategoriesResponse.setSuccess(false);
            taskTicketCategoriesResponse.setError(ex.getMessage());
        }
        return taskTicketCategoriesResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public TaskTicketCategoriesResponse moveToTrash(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        TaskTicketCategoriesResponse taskTicketCategoriesResponse = new TaskTicketCategoriesResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<TaskTicketCategories> optionalTaskTicketCategories =
                    taskTicketCategoriesService.findById(formData.get("id"));
            if(optionalTaskTicketCategories.isPresent()){
                TaskTicketCategories taskTicketCategories = optionalTaskTicketCategories.get();
                taskTicketCategories.setIsDeleted(1);
                taskTicketCategories.setDeletedBy(userInfo.getId());
                taskTicketCategories.setDeletedTime(new Date());
                taskTicketCategoriesResponse.setSuccess(true);
                taskTicketCategoriesResponse.setError("");
                taskTicketCategoriesResponse.setTaskTicketCategories(taskTicketCategoriesService.save(taskTicketCategories));
            }else{
                taskTicketCategoriesResponse.setSuccess(false);
                taskTicketCategoriesResponse.setError("Error occurred while moving Task Ticket Category to Trash!! Please try after sometime");
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskTicketCategoriesResponse.setSuccess(false);
            taskTicketCategoriesResponse.setError(ex.getMessage());
        }
        return taskTicketCategoriesResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public TaskTicketCategoriesResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        TaskTicketCategoriesResponse taskTicketCategoriesResponse = new TaskTicketCategoriesResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<TaskTicketCategories> optionalTaskTicketCategories = taskTicketCategoriesService.findById(formData.get("id"));
            if(optionalTaskTicketCategories.isPresent()){
                TaskTicketCategories ticketCategories = optionalTaskTicketCategories.get();
                taskTicketCategoriesResponse.setSuccess(true);
                taskTicketCategoriesResponse.setError("");
                taskTicketCategoriesResponse.setTaskTicketCategories(ticketCategories);
            } else {
                taskTicketCategoriesResponse.setSuccess(false);
                taskTicketCategoriesResponse.setError("Error occurred while fetching Task Ticket Category!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskTicketCategoriesResponse.setSuccess(false);
            taskTicketCategoriesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskTicketCategoriesResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public TaskTicketCategoriesResponse getAll() {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        TaskTicketCategoriesResponse taskTicketCategoriesResponse = new TaskTicketCategoriesResponse();
        try {
            taskTicketCategoriesResponse.setTaskTicketCategoriesList(
                    taskTicketCategoriesService
                            .findAllByDeletionStatus(
                                    0,
                                    userInfo.getClient().getClientId()
                            )
            );
            taskTicketCategoriesResponse.setSuccess(true);
            taskTicketCategoriesResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskTicketCategoriesResponse.setSuccess(false);
            taskTicketCategoriesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskTicketCategoriesResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public TaskTicketCategoriesResponse getDeleted() {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        TaskTicketCategoriesResponse taskTicketCategoriesResponse = new TaskTicketCategoriesResponse();
        try {
            taskTicketCategoriesResponse.setTaskTicketCategoriesList(
                    taskTicketCategoriesService
                            .findAllByDeletionStatus(
                                    1,
                                    userInfo.getClient().getClientId()
                            )
            );
            taskTicketCategoriesResponse.setSuccess(true);
            taskTicketCategoriesResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskTicketCategoriesResponse.setSuccess(false);
            taskTicketCategoriesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskTicketCategoriesResponse;
    }

    @RequestMapping(value = "/get-task-categories", method = RequestMethod.POST)
    public TaskTicketCategoriesResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        TaskTicketCategoriesResponse taskTicketCategoriesResponse = new TaskTicketCategoriesResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<TaskTicketCategories> page = taskTicketCategoriesService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(),0, pageable);
            taskTicketCategoriesResponse.setTotalRecords(page.getTotalElements());
            taskTicketCategoriesResponse.setTotalPages(page.getTotalPages());
            taskTicketCategoriesResponse.setTaskTicketCategoriesList(page.getContent());
            taskTicketCategoriesResponse.setCurrentRecords(taskTicketCategoriesResponse.getTaskTicketCategoriesList().size());
            taskTicketCategoriesResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskTicketCategoriesResponse.setSuccess(false);
            taskTicketCategoriesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskTicketCategoriesResponse;
    }

    @RequestMapping(value = "/search-task-categories", method = RequestMethod.POST)
    public TaskTicketCategoriesResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        TaskTicketCategoriesResponse taskTicketCategoriesResponse = new TaskTicketCategoriesResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<TaskTicketCategories> page;
            if (searchText == null ){
                page = taskTicketCategoriesService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(),0,pageable);
            } else  {
                page = taskTicketCategoriesService.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
            }

            taskTicketCategoriesResponse.setTotalRecords(page.getTotalElements());
            taskTicketCategoriesResponse.setTotalPages(page.getTotalPages());
            taskTicketCategoriesResponse.setTaskTicketCategoriesList(page.getContent());
            taskTicketCategoriesResponse.setCurrentRecords(taskTicketCategoriesResponse.getTaskTicketCategoriesList().size());
            taskTicketCategoriesResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskTicketCategoriesResponse.setSuccess(false);
            taskTicketCategoriesResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskTicketCategoriesResponse;
    }


    private boolean checkDuplicate(TaskTicketCategories taskTicketCategories) {
        List<TaskTicketCategories> ticketCategoriesList;
        if (taskTicketCategories.getId() == "") {
            ticketCategoriesList = taskTicketCategoriesService
                    .findAllByTitleAndClientId(
                            taskTicketCategories.getTitle(),
                            taskTicketCategories.getClientId()
                    );
        } else {
            ticketCategoriesList = taskTicketCategoriesService
                    .findAllByTitleAndClientIdAndId(
                            taskTicketCategories.getTitle(),
                            taskTicketCategories.getClientId(),
                            taskTicketCategories.getId()
                    );
        }
        if (ticketCategoriesList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
