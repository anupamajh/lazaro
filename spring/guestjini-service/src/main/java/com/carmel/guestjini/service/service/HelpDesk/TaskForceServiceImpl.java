package com.carmel.guestjini.service.service.HelpDesk;

import com.carmel.guestjini.service.components.UserInformation;
import com.carmel.guestjini.service.model.DTO.HelpDesk.TaskForceDTO;
import com.carmel.guestjini.service.model.HelpDesk.TaskForce;
import com.carmel.guestjini.service.model.Principal.UserInfo;
import com.carmel.guestjini.service.repository.HelpDesk.TaskForceRepository;
import com.carmel.guestjini.service.response.HelpDesk.TaskForceResponse;
import com.carmel.guestjini.service.specification.HelpDesk.TaskForceSpecification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskForceServiceImpl implements TaskForceService {


    @Autowired
    UserInformation userInformationService;

    @Autowired
    TaskForceRepository taskForceRepository;

    private ObjectMapper objectMapper = new ObjectMapper();
    private Logger logger = LoggerFactory.getLogger(TaskForceGroupServiceImpl.class);


    public TaskForceServiceImpl() {
    }

    @Override
    public Optional<TaskForce> findById(String id) throws Exception {
        return taskForceRepository.findById(id);
    }

    @Override
    public TaskForce save(TaskForce taskForce) throws Exception {
        return taskForceRepository.save(taskForce);
    }

    @Override
    public TaskForceResponse saveTaskForce(TaskForce taskForce) throws Exception {
        logger.trace("Entering");
        TaskForceResponse taskForceResponse = new TaskForceResponse();
        try {
            UserInfo userInfo = userInformationService.getUserInfo();
            if (taskForce.getId() == null) {
                taskForce.setId("");
            }
            taskForce.setClientId(userInfo.getClient().getClientId());
            if (checkDuplicate(taskForce)) {
                taskForceResponse.setTaskForce(new TaskForceDTO(taskForce));
                taskForceResponse.setSuccess(false);
                taskForceResponse.setError("Duplicate Item Category name!");
            } else {
                if (taskForce.getId().equals("")) {
                    taskForce.setCreatedBy(userInfo.getId());
                    taskForce.setCreationTime(new Date());
                } else {
                    taskForce.setLastModifiedBy(userInfo.getId());
                    taskForce.setLastModifiedTime(new Date());
                }
                taskForceResponse.setTaskForce(
                        new TaskForceDTO(
                                this.save(taskForce)
                        ));
                taskForceResponse.setSuccess(true);
                taskForceResponse.setError("");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            throw ex;
        }
        return taskForceResponse;
    }

    @Override
    public TaskForceResponse moveToTrash(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformationService.getUserInfo();
        logger.trace("Entering");
        TaskForceResponse taskForceResponse = new TaskForceResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<TaskForce> optionalItemCategory = this.findById(formData.get("id"));
            if (optionalItemCategory.isPresent()) {
                TaskForce taskForce = optionalItemCategory.get();
                taskForce.setIsDeleted(1);
                taskForce.setDeletedBy(userInfo.getId());
                taskForce.setDeletedTime(new Date());
                taskForceResponse.setSuccess(true);
                taskForceResponse.setError("");
                taskForceResponse.setTaskForce(
                        new TaskForceDTO(
                                this.save(taskForce)
                        ));
            } else {
                taskForceResponse.setSuccess(false);
                taskForceResponse.setError("Error occurred while moving Item Category to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceResponse.setSuccess(false);
            taskForceResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceResponse;
    }

    @Override
    public TaskForceResponse get(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        TaskForceResponse taskForceResponse = new TaskForceResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<TaskForce> optionalItemCategory = this.findById(formData.get("id"));
            if (optionalItemCategory.isPresent()) {
                TaskForce taskForce = optionalItemCategory.get();
                taskForceResponse.setSuccess(true);
                taskForceResponse.setError("");
                taskForceResponse.setTaskForce(
                        new TaskForceDTO(taskForce)
                );
            } else {
                taskForceResponse.setSuccess(false);
                taskForceResponse.setError("Error occurred while fetching Item Category!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceResponse.setSuccess(false);
            taskForceResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceResponse;
    }

    @Override
    public TaskForceResponse getDeleted() throws Exception {
        logger.trace("Entering");
        UserInfo userInfo = userInformationService.getUserInfo();
        TaskForceResponse taskForceResponse = new TaskForceResponse();
        try {
            taskForceResponse.setTaskForceList(
                    getTaskForceDTOS(taskForceRepository
                            .findAllByClientIdAndIsDeleted(
                                    userInfo.getClient().getClientId(),
                                    1
                            )
                    ));
            taskForceResponse.setSuccess(true);
            taskForceResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceResponse.setSuccess(true);
            taskForceResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceResponse;
    }

    @Override
    public TaskForceResponse getAll() throws Exception {
        logger.trace("Entering");
        UserInfo userInfo = userInformationService.getUserInfo();
        TaskForceResponse taskForceResponse = new TaskForceResponse();
        try {
            taskForceResponse.setTaskForceList(
                    getTaskForceDTOS(taskForceRepository
                            .findAllByClientIdAndIsDeleted(
                                    userInfo.getClient().getClientId(),
                                    0
                            )
                    ));
            taskForceResponse.setSuccess(true);
            taskForceResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceResponse.setSuccess(true);
            taskForceResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceResponse;
    }

    @Override
    public TaskForceResponse getPaginated(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        UserInfo userInfo = userInformationService.getUserInfo();
        TaskForceResponse taskForceResponse = new TaskForceResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
            Page<TaskForce> page = taskForceRepository
                    .findAllByClientIdAndIsDeleted(
                            userInfo.getClient().getClientId(),
                            0,
                            pageable
                    );
            taskForceResponse.setTotalRecords(page.getTotalElements());
            taskForceResponse.setTotalPages(page.getTotalPages());
            taskForceResponse.setTaskForceList(
                    getTaskForceDTOS(page.getContent())
            );
            taskForceResponse.setCurrentRecords(taskForceResponse.getTaskForceList().size());
            taskForceResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceResponse.setSuccess(false);
            taskForceResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceResponse;
    }

    @Override
    public TaskForceResponse searchPaginated(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        UserInfo userInfo = userInformationService.getUserInfo();
        TaskForceResponse taskForceResponse = new TaskForceResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
            Page<TaskForce> page;
            if (searchText == null) {
                page = taskForceRepository
                        .findAllByClientIdAndIsDeleted(
                                userInfo.getClient().getClientId(),
                                0,
                                pageable
                        );
            } else {
                page = taskForceRepository.findAll(
                        TaskForceSpecification.textInAllColumns(
                                userInfo.getClient().getClientId(),
                                searchText
                        ), pageable
                );
            }
            taskForceResponse.setTotalRecords(page.getTotalElements());
            taskForceResponse.setTotalPages(page.getTotalPages());
            taskForceResponse.setTaskForceList(
                    getTaskForceDTOS(page.getContent())
            );
            taskForceResponse.setCurrentRecords(taskForceResponse.getTaskForceList().size());
            taskForceResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceResponse.setSuccess(false);
            taskForceResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceResponse;
    }

    private List<TaskForceDTO> getTaskForceDTOS(List<TaskForce> itemCategories) {
        List<TaskForceDTO> itemCategoryDTOS = new ArrayList<>();
        for (TaskForce taskForce : itemCategories) {
            itemCategoryDTOS.add(new TaskForceDTO(taskForce));
        }
        return itemCategoryDTOS;
    }

    private boolean checkDuplicate(TaskForce taskForce) {
        List<TaskForce> taskForceGroups;
        if (taskForce.getId() == null) {
            taskForce.setId("");
        }
        if (taskForce.getId().equals("")) {
            taskForceGroups = taskForceRepository
                    .findAllByClientIdAndIsDeletedAndPhone(
                            taskForce.getClientId(),
                            0,
                            taskForce.getPhone()
                    );
        } else {
            taskForceGroups = taskForceRepository
                    .findAllByClientIdAndIsDeletedAndPhoneAndIdIsNot(
                            taskForce.getClientId(),
                            0,
                            taskForce.getPhone(),
                            taskForce.getId()
                    );
        }
        return !taskForceGroups.isEmpty();
    }

    @Override
    public Optional<TaskForce> findByPhone(String phone) {
        return  taskForceRepository.findByPhone(phone);
    }
}
