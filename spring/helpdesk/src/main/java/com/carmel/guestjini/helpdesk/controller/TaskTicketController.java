package com.carmel.guestjini.helpdesk.controller;

import com.carmel.guestjini.helpdesk.common.SearchBuilder;
import com.carmel.guestjini.helpdesk.common.TicketStatus;
import com.carmel.guestjini.helpdesk.components.UserInformation;
import com.carmel.guestjini.helpdesk.config.YAMLConfig;
import com.carmel.guestjini.helpdesk.model.Principal.UserInfo;
import com.carmel.guestjini.helpdesk.model.TaskAttachment;
import com.carmel.guestjini.helpdesk.model.TaskTicket;
import com.carmel.guestjini.helpdesk.request.SearchRequest;
import com.carmel.guestjini.helpdesk.request.TicketRequest;
import com.carmel.guestjini.helpdesk.response.TaskAttachmentResponse;
import com.carmel.guestjini.helpdesk.response.TaskTicketResponse;
import com.carmel.guestjini.helpdesk.service.TaskAttachmentService;
import com.carmel.guestjini.helpdesk.service.TaskTicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import static com.carmel.guestjini.helpdesk.specification.TaskTicketSpecification.textInAllColumns;

@RestController
@RequestMapping(value = "/task-ticket")
public class TaskTicketController {
    Logger logger = LoggerFactory.getLogger(TaskTicketController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    TaskTicketService taskTicketService;

    @Autowired
    YAMLConfig yamlConfig;

    @Autowired
    TaskAttachmentService taskAttachmentService;

    @Autowired
    EntityManager entityManager;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public TaskTicketResponse save(@Valid @RequestBody TicketRequest ticketRequest) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");

        TaskTicketResponse taskTicketResponse = new TaskTicketResponse();
        try {
            TaskTicket taskTicket = ticketRequest.getTaskTicket();

            if (taskTicket.getId() == null) {
                taskTicket.setId("");
            }
            if (taskTicket.getOrgId() == null || taskTicket.getOrgId().isEmpty()) {
                if (userInfo.getDefaultOrganization() != null)
                    taskTicket.setOrgId(userInfo.getDefaultOrganization().getId());
            }
            taskTicket.setRequesterId(userInfo.getId());
            if (taskTicket.getId().equals("")) {
                taskTicket.setCreatedBy(userInfo.getId());
                taskTicket.setCreationTime(new Date());
                taskTicket.setTicketNo(String.valueOf(System.nanoTime()));
                taskTicket.setTicketStatus(TicketStatus.NOT_STARTED);
            } else {
                taskTicket.setLastModifiedBy(userInfo.getId());
                taskTicket.setLastModifiedTime(new Date());
            }
            taskTicket.setClientId(userInfo.getClient().getClientId());
            if (checkDuplicate(taskTicket)) {
                taskTicketResponse.setTaskTicket(taskTicket);
                taskTicketResponse.setSuccess(false);
                taskTicketResponse.setError("Duplicate TaskTicket name!");
            } else {
                TaskTicket savedTicket = taskTicketService.save(taskTicket);
                List<TaskAttachment> taskAttachmentList = new ArrayList<>();
                for (TaskAttachment taskAttachment : ticketRequest.getTaskAttachmentList()) {
                    taskAttachment.setTicketId(savedTicket.getId());
                    taskAttachmentList.add(taskAttachmentService.save(taskAttachment));
                }
                taskTicketResponse.setTaskTicket(savedTicket);
                taskTicketResponse.setTaskAttachments(taskAttachmentList);
                taskTicketResponse.setSuccess(true);
                taskTicketResponse.setError("");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskTicketResponse.setSuccess(false);
            taskTicketResponse.setError(ex.getMessage());
        }
        return taskTicketResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public TaskTicketResponse moveToTrash(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        TaskTicketResponse taskTicketResponse = new TaskTicketResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<TaskTicket> optionalTaskTicket = taskTicketService.findById(formData.get("id"));
            if (optionalTaskTicket.isPresent()) {
                TaskTicket taskTicket = optionalTaskTicket.get();
                taskTicket.setIsDeleted(1);
                taskTicket.setDeletedBy(userInfo.getId());
                taskTicket.setDeletedTime(new Date());
                taskTicketResponse.setSuccess(true);
                taskTicketResponse.setError("");
                taskTicketResponse.setTaskTicket(taskTicketService.save(taskTicket));
            } else {
                taskTicketResponse.setSuccess(false);
                taskTicketResponse.setError("Error occurred while moving taskTicket to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskTicketResponse.setSuccess(false);
            taskTicketResponse.setError(ex.getMessage());
        }
        return taskTicketResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public TaskTicketResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        TaskTicketResponse taskTicketResponse = new TaskTicketResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<TaskTicket> optionalTaskTicket = taskTicketService.findById(formData.get("id"));
            if (optionalTaskTicket.isPresent()) {
                TaskTicket taskTicket = optionalTaskTicket.get();
                taskTicketResponse.setSuccess(true);
                taskTicketResponse.setError("");
                taskTicketResponse.setTaskTicket(taskTicket);
                List<TaskAttachment> taskAttachmentList = taskAttachmentService.findByTicketId(taskTicket.getId());
                taskTicketResponse.setTaskAttachments(taskAttachmentList);
            } else {
                taskTicketResponse.setSuccess(false);
                taskTicketResponse.setError("Error occurred while Fetching taskTicket!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskTicketResponse.setSuccess(false);
            taskTicketResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskTicketResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public TaskTicketResponse getDeleted() {
        logger.trace("Entering");
        UserInfo userInfo = userInformation.getUserInfo();
        TaskTicketResponse taskTicketResponse = new TaskTicketResponse();
        try {
            taskTicketResponse.setTaskTicketList(taskTicketService.findAllByIsDeletedAndRequesterId(1, userInfo.getId()));
            taskTicketResponse.setSuccess(true);
            taskTicketResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskTicketResponse.setSuccess(true);
            taskTicketResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskTicketResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public TaskTicketResponse getAll() {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        TaskTicketResponse taskTicketResponse = new TaskTicketResponse();
        try {
            taskTicketResponse.setTaskTicketList(taskTicketService.findAllByIsDeletedAndRequesterId(0, userInfo.getId()));
            taskTicketResponse.setSuccess(true);
            taskTicketResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskTicketResponse.setSuccess(true);
            taskTicketResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskTicketResponse;
    }

    @RequestMapping(value = "/get-account-tickets", method = RequestMethod.POST)
    public TaskTicketResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        TaskTicketResponse taskTicketResponse = new TaskTicketResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("createdBy"));
            Page<TaskTicket> page = taskTicketService.findAllByIsDeletedAndRequesterId(0, userInfo.getId(), pageable);
            taskTicketResponse.setTotalRecords(page.getTotalElements());
            taskTicketResponse.setTotalPages(page.getTotalPages());
            taskTicketResponse.setTaskTicketList(page.getContent());
            taskTicketResponse.setCurrentRecords(taskTicketResponse.getTaskTicketList().size());
            taskTicketResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            taskTicketResponse.setSuccess(false);
            taskTicketResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskTicketResponse;
    }

    @RequestMapping(value = "/search-account-tickets", method = RequestMethod.POST)
    public TaskTicketResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        TaskTicketResponse taskTicketResponse = new TaskTicketResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("createdBy"));
            Page<TaskTicket> page;
            if (searchText == null) {
                page = taskTicketService.findAllByIsDeletedAndRequesterId(0, userInfo.getId(), pageable);
            } else {
                page = taskTicketService.findAll(textInAllColumns(searchText, userInfo.getId()), pageable);
            }
            taskTicketResponse.setTotalRecords(page.getTotalElements());
            taskTicketResponse.setTotalPages(page.getTotalPages());
            taskTicketResponse.setTaskTicketList(page.getContent());
            taskTicketResponse.setCurrentRecords(taskTicketResponse.getTaskTicketList().size());
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            taskTicketResponse.setSuccess(false);
            taskTicketResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskTicketResponse;
    }

    private boolean checkDuplicate(TaskTicket taskTicket) {
        return false;
    }

    @RequestMapping(value = "/change-status", method = RequestMethod.POST)
    public TaskTicketResponse deleteTicketsByGuestId(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        UserInfo userInfo = userInformation.getUserInfo();
        TaskTicketResponse taskTicketResponse = new TaskTicketResponse();
        try {
            String ticketId = formData.get("id") == null ? null : String.valueOf(formData.get("id"));
            int status = formData.get("status") == null ? Integer.parseInt(null) : Integer.parseInt(String.valueOf(formData.get("status")));
            Optional<TaskTicket> optionalTaskTicket = taskTicketService.findById(ticketId);
            if (optionalTaskTicket.isPresent()) {
                TaskTicket taskTicket = optionalTaskTicket.get();
                taskTicket.setTicketStatus(status);
                taskTicket.setLastModifiedBy(userInfo.getId());
                taskTicket.setLastModifiedTime(new Date());
                taskTicketResponse.setSuccess(true);
                taskTicketResponse.setError("");
                taskTicketResponse.setTaskTicket(taskTicketService.save(taskTicket));
            } else {
                taskTicketResponse.setSuccess(false);
                taskTicketResponse.setError("Error occurred while changing status!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskTicketResponse.setSuccess(false);
            taskTicketResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskTicketResponse;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public TaskTicketResponse search(@RequestBody SearchRequest searchRequest) {
        TaskTicketResponse taskTicketResponse = new TaskTicketResponse();
        UserInfo userInfo = userInformation.getUserInfo();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<TaskTicket> criteriaQuery = criteriaBuilder.createQuery(TaskTicket.class);
            Root<TaskTicket> root = criteriaQuery.from(TaskTicket.class);
            criteriaQuery = SearchBuilder.buildSearch(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root,
                    TaskTicket.class,
                    searchRequest
            );

            long totalRecords = SearchBuilder.getTotalRecordCount(
                   entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root
            );
             TypedQuery<TaskTicket> typedQuery = entityManager.createQuery(criteriaQuery);

            typedQuery.setFirstResult((searchRequest.getCurrentPage() - 1) * searchRequest.getPageSize());
            typedQuery.setMaxResults(searchRequest.getPageSize());
            List<TaskTicket> taskTicketList = typedQuery.getResultList();
            taskTicketResponse.setCurrentRecords(taskTicketList.size());
            taskTicketResponse.setTotalRecords(totalRecords);
            taskTicketResponse.setSuccess(true);
            taskTicketResponse.setError("");
            taskTicketResponse.setTaskTicketList(taskTicketList);
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            taskTicketResponse.setSuccess(false);
            taskTicketResponse.setError(ex.getMessage());
        }
        return taskTicketResponse;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public TaskAttachmentResponse attachFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("fileName") String fileName

    ) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        TaskAttachmentResponse taskAttachmentResponse = new TaskAttachmentResponse();
        try {
            if (file.isEmpty()) {
                throw new Exception("File not found");
            }
            String basePath = yamlConfig.getImageSavePath();
            File fileSaveDir = new File(basePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
            String filePath = fileSaveDir.getAbsolutePath() + File.separator + System.nanoTime() + "_" + file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            File scanFile = new File(filePath);
            BufferedOutputStream scanStream = new BufferedOutputStream(new FileOutputStream(scanFile));
            scanStream.write(bytes);
            scanStream.close();
            TaskAttachment taskAttachment = new TaskAttachment();
            taskAttachment.setClientId(userInfo.getClient().getClientId());
            if (userInfo.getDefaultOrganization() != null) {
                taskAttachment.setOrgId(userInfo.getDefaultOrganization().getId());
            }
            taskAttachment.setType(file.getContentType());
            taskAttachment.setDocTitle(file.getOriginalFilename());
            taskAttachment.setPath(filePath);
            taskAttachment.setName(file.getOriginalFilename());
            taskAttachment.setError(0);
            taskAttachment.setSize(file.getSize());
            taskAttachment.setCreatedBy(userInfo.getId());
            taskAttachment.setCreationTime(new Date());
            taskAttachmentResponse.setSuccess(true);
            taskAttachmentResponse.setError("");
            taskAttachmentResponse.setTaskAttachment(taskAttachmentService.save(taskAttachment));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskAttachmentResponse.setSuccess(false);
            taskAttachmentResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskAttachmentResponse;
    }

}
