package com.carmel.guestjini.service.controller.HelpDesk;


import com.carmel.guestjini.service.HelpDesk.TaskTicket.DTO.TaskAssigneeDTO;
import com.carmel.guestjini.service.HelpDesk.TaskTicket.Response.TaskAssigneeResponse;
import com.carmel.guestjini.service.common.Search.SearchBuilder;
import com.carmel.guestjini.service.common.Search.SearchRequest;
import com.carmel.guestjini.service.components.MailClient;
import com.carmel.guestjini.service.components.UserInformation;
import com.carmel.guestjini.service.components.UserService;
import com.carmel.guestjini.service.config.CarmelConfig;
import com.carmel.guestjini.service.model.Booking.Guest;
import com.carmel.guestjini.service.model.DTO.HelpDesk.TaskForceDTO;
import com.carmel.guestjini.service.model.DTO.HelpDesk.TaskForceGroupDTO;
import com.carmel.guestjini.service.model.DTO.HelpDesk.TicketCountDTO;
import com.carmel.guestjini.service.model.HelpDesk.TaskAttachment;
import com.carmel.guestjini.service.model.HelpDesk.TaskRunner;
import com.carmel.guestjini.service.model.HelpDesk.TaskTicket;
import com.carmel.guestjini.service.model.Inventory.InventoryDetail;
import com.carmel.guestjini.service.model.Principal.UserInfo;
import com.carmel.guestjini.service.request.HelpDesk.TicketRequest;
import com.carmel.guestjini.service.response.HelpDesk.TaskAttachmentResponse;
import com.carmel.guestjini.service.response.HelpDesk.TaskForceGroupResponse;
import com.carmel.guestjini.service.response.HelpDesk.TaskForceResponse;
import com.carmel.guestjini.service.response.HelpDesk.TaskTicketResponse;
import com.carmel.guestjini.service.service.Booking.GuestService;
import com.carmel.guestjini.service.service.HelpDesk.*;
import com.carmel.guestjini.service.service.Inventory.InventoryDetailService;
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

import static com.carmel.guestjini.service.specification.HelpDesk.TaskTicketSpecification.textInAllColumns;


@RestController
@RequestMapping(value = "/task-ticket")
public class TaskTicketController {
    Logger logger = LoggerFactory.getLogger(TaskTicketController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    TaskTicketService taskTicketService;

    @Autowired
    CarmelConfig carmelConfig;

    @Autowired
    TaskAttachmentService taskAttachmentService;

    @Autowired
    EntityManager entityManager;

    @Autowired
    MailClient mailClient;

    @Autowired
    GuestService guestService;

    @Autowired
    InventoryDetailService inventoryDetailService;

    @Autowired
    TaskTicketCategoriesService taskTicketCategoriesService;

    @Autowired
    TaskForceGroupService taskForceGroupService;

    @Autowired
    TaskForceService taskForceService;

    @Autowired
    TaskRunnerService taskRunnerService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public TaskTicketResponse save(@Valid @RequestBody TicketRequest ticketRequest) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        boolean isNewTicket = false;
        TaskTicketResponse taskTicketResponse = new TaskTicketResponse();
        try {
            TaskTicket taskTicket = ticketRequest.getTaskTicket();

            if (taskTicket.getId() == null) {
                taskTicket.setId("");
                isNewTicket = true;
            } else {
                Optional<TaskTicket> optionalTaskTicket = taskTicketService.findById(
                        taskTicket.getId()
                );
                optionalTaskTicket.ifPresent(ticket -> taskTicket.setTicketNo(ticket.getTicketNo()));
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

            if (isNewTicket) {
                this.setNewTicketEmail(ticketRequest, userInfo);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskTicketResponse.setSuccess(false);
            taskTicketResponse.setError(ex.getMessage());
        }
        return taskTicketResponse;
    }

    private boolean setNewTicketEmail(TicketRequest ticketRequest, UserInfo userInfo) {
        Optional<Guest> optionalGuest = guestService.findByEmail(userInfo.getUserName());
        String strInventory = "";
        String strDelim = "";
        if (optionalGuest.isPresent()) {
            if (optionalGuest.get().getInventoryId() != null) {
                Optional<InventoryDetail> optionalInventoryDetail = inventoryDetailService.findByInventoryId(optionalGuest.get().getInventoryId());
                Optional<InventoryDetail> temp;
                if (optionalInventoryDetail.isPresent()) {
                    if (optionalInventoryDetail.get().getPodId() != null) {
                        strInventory = optionalInventoryDetail.get().getTitle();
                        strDelim = "->";
                    }
                    temp = inventoryDetailService.findByInventoryId(optionalInventoryDetail.get().getRoomId());
                    if (temp.isPresent()) {
                        strInventory = temp.get().getTitle() + strDelim + strInventory;
                        strDelim = "->";
                    }
                    temp = inventoryDetailService.findByInventoryId(optionalInventoryDetail.get().getFlatId());
                    if (temp.isPresent()) {
                        strInventory = temp.get().getTitle() + strDelim + strInventory;
                        strDelim = "->";
                    }
                    temp = inventoryDetailService.findByInventoryId(optionalInventoryDetail.get().getFloorId());
                    if (temp.isPresent()) {
                        strInventory = temp.get().getTitle() + strDelim + strInventory;
                        strDelim = "->";
                    }
                    temp = inventoryDetailService.findByInventoryId(optionalInventoryDetail.get().getBlockId());
                    if (temp.isPresent()) {
                        strInventory = temp.get().getTitle() + strDelim + strInventory;
                        strDelim = "->";
                    }
                    temp = inventoryDetailService.findByInventoryId(optionalInventoryDetail.get().getPropertyId());
                    if (temp.isPresent()) {
                        strInventory = temp.get().getTitle() + strDelim + strInventory;
                        strDelim = "->";
                    }

                }
            }
        }
        mailClient.setTicketEmail(ticketRequest, userInfo, strInventory);
        return true;
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
                taskTicketResponse
                        .setTaskTicketCategories(
                                taskTicketCategoriesService
                                        .getAllParents(taskTicket.getTicketCategoryId()
                                        )
                        );
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

    @RequestMapping(value = "/get-account-tickets-by-status", method = RequestMethod.POST)
    public TaskTicketResponse getTicketByStatus(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        TaskTicketResponse taskTicketResponse = new TaskTicketResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int ticketStatus = formData.get("ticket_status") == null ? 3 : Integer.parseInt(formData.get("ticket_status"));
            List<TaskTicket> page = taskTicketService.findAllByIsDeletedAndRequesterIdAndTicketStatus(0, userInfo.getId(), ticketStatus);
            if (page == null) {
                page = new ArrayList<>();
            }
            taskTicketResponse.setTotalRecords(page.size());
            taskTicketResponse.setTaskTicketList(page);
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

    @RequestMapping(value = "/get-count-by-status", method = RequestMethod.POST)
    public TicketCountDTO getTicketCountByStatus() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        TicketCountDTO ticketCountDTO = new TicketCountDTO();
        try {
            ticketCountDTO = taskTicketService.getTicketCountByStatus(userInfo.getId());
            ticketCountDTO.setSuccess(true);

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ticketCountDTO.setSuccess(false);
            ticketCountDTO.setError(ex.getMessage());
        }

        return ticketCountDTO;
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
        } catch (Exception ex) {
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
            String basePath = carmelConfig.getImageSavePath();
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

    @RequestMapping(value = "/search-shared-inbox", method = RequestMethod.POST)
    public TaskTicketResponse searchSharedInbox(@RequestBody SearchRequest searchRequest) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        TaskTicketResponse taskTicketResponse = new TaskTicketResponse();
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
            List<TaskTicket> taskTickets = typedQuery.getResultList();
            taskTicketResponse.setCurrentRecords(taskTickets.size());
            taskTicketResponse.setTotalRecords(totalRecords);
            taskTicketResponse.setSuccess(true);
            taskTicketResponse.setError("");
            taskTicketResponse.setTaskTicketList(taskTickets);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            taskTicketResponse.setSuccess(false);
            taskTicketResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskTicketResponse;
    }

    @RequestMapping(value = "/get-assignee", method = RequestMethod.POST)
    public TaskAssigneeResponse getAssignee() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        TaskAssigneeResponse taskAssigneeResponse = new TaskAssigneeResponse();
        try {

            List<TaskAssigneeDTO> taskAssigneeDTOS = new ArrayList<>();
            TaskForceGroupResponse taskForceGroupResponse =
                    taskForceGroupService.getAll();
            if (taskForceGroupResponse.isSuccess()) {
                List<TaskForceGroupDTO> taskForceGroups =
                        taskForceGroupResponse.getTaskForceGroupList();
                for (TaskForceGroupDTO taskForceGroup : taskForceGroups) {
                    TaskAssigneeDTO taskAssigneeDTO = new TaskAssigneeDTO();
                    taskAssigneeDTO.setIsGroup(1);
                    taskAssigneeDTO.setName(taskForceGroup.getName());
                    taskAssigneeDTO.setId(taskForceGroup.getId());
                    taskAssigneeDTOS.add(taskAssigneeDTO);
                }
            }
            TaskForceResponse taskForceResponse = taskForceService.getAll();
            List<TaskForceDTO> taskForces = taskForceResponse.getTaskForceList();
            for (TaskForceDTO taskForce : taskForces) {
                if (taskForce.getUserId() != null) {
                    if (taskForce.getUserId() != "") {
                        TaskAssigneeDTO taskAssigneeDTO = new TaskAssigneeDTO();
                        taskAssigneeDTO.setIsGroup(0);
                        taskAssigneeDTO.setName(taskForce.getPhone());
                        taskAssigneeDTO.setId(taskForce.getUserId());
                        taskAssigneeDTOS.add(taskAssigneeDTO);
                    }
                }
            }
            taskAssigneeResponse.setSuccess(true);
            taskAssigneeResponse.setTaskAssigneeList(taskAssigneeDTOS);
        } catch (Exception ex) {
            logger.error(ex.toString());
            taskAssigneeResponse.setSuccess(false);
            taskAssigneeResponse.setError(ex.getMessage());
        }
        return taskAssigneeResponse;
    }

    @RequestMapping(value = "/get-assignee-by-group", method = RequestMethod.POST)
    public TaskAssigneeResponse getAssigneeByGroup(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        TaskAssigneeResponse taskAssigneeResponse = new TaskAssigneeResponse();
        try {
            String groupId = formData.get("groupId");
            String ticketId = formData.get("ticketId");
            if (groupId == null) {
                groupId = "";
            }

            if (groupId.equals("")) {
                TaskRunner taskRunner = taskRunnerService.getByTicketId(ticketId);
                if (taskRunner.getTaskForceGroupId() != null) {
                    groupId = taskRunner.getTaskForceGroupId();
                }
            }
            if (groupId == null) {
                groupId = "";
            }

            List<TaskAssigneeDTO> taskAssigneeDTOS = new ArrayList<>();
            TaskForceResponse taskForceResponse;
            if (groupId.equals("")) {
                taskForceResponse = taskForceService.getAll();
            } else {
                taskForceResponse = taskForceService.getByGroup(groupId);
            }
            List<TaskForceDTO> taskForces = taskForceResponse.getTaskForceList();
            for (TaskForceDTO taskForce : taskForces) {
                if (taskForce.getUserId() != null) {
                    if (taskForce.getUserId() != "") {
                        TaskAssigneeDTO taskAssigneeDTO = new TaskAssigneeDTO();
                        taskAssigneeDTO.setIsGroup(0);
                        taskAssigneeDTO.setName(taskForce.getPhone());
                        taskAssigneeDTO.setId(taskForce.getUserId());
                        taskAssigneeDTOS.add(taskAssigneeDTO);
                    }
                }
            }
            taskAssigneeResponse.setSuccess(true);
            taskAssigneeResponse.setTaskAssigneeList(taskAssigneeDTOS);
        } catch (Exception ex) {
            logger.error(ex.toString());
            taskAssigneeResponse.setSuccess(false);
            taskAssigneeResponse.setError(ex.getMessage());
        }
        return taskAssigneeResponse;
    }


}
