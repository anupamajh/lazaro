package com.carmel.guestjini.service.service.HelpDesk;

import com.carmel.guestjini.service.components.UserInformation;
import com.carmel.guestjini.service.model.DTO.HelpDesk.TaskForceGroupDTO;
import com.carmel.guestjini.service.model.HelpDesk.TaskForceGroup;
import com.carmel.guestjini.service.model.Principal.UserInfo;
import com.carmel.guestjini.service.repository.HelpDesk.TaskForceGroupRepository;
import com.carmel.guestjini.service.response.HelpDesk.TaskForceGroupResponse;
import com.carmel.guestjini.service.specification.HelpDesk.TaskForceGroupSpecification;
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
public class TaskForceGroupServiceImpl implements TaskForceGroupService {


    @Autowired
    UserInformation userInformationService;

    @Autowired
    TaskForceGroupRepository taskForceGroupRepository;

    private ObjectMapper objectMapper = new ObjectMapper();
    private Logger logger = LoggerFactory.getLogger(TaskForceGroupServiceImpl.class);


    public TaskForceGroupServiceImpl() {
    }

    @Override
    public Optional<TaskForceGroup> findById(String id) throws Exception {
        return taskForceGroupRepository.findById(id);
    }

    @Override
    public TaskForceGroup save(TaskForceGroup taskForceGroup) throws Exception {
        return taskForceGroupRepository.save(taskForceGroup);
    }

    @Override
    public TaskForceGroupResponse saveTaskForceGroup(TaskForceGroup taskForceGroup) throws Exception {
        logger.trace("Entering");
        TaskForceGroupResponse taskForceGroupResponse = new TaskForceGroupResponse();
        try {
            UserInfo userInfo = userInformationService.getUserInfo();
            if (taskForceGroup.getId() == null) {
                taskForceGroup.setId("");
            }
            taskForceGroup.setClientId(userInfo.getClient().getClientId());
            if (checkDuplicate(taskForceGroup)) {
                taskForceGroupResponse.setTaskForceGroup(new TaskForceGroupDTO(taskForceGroup));
                taskForceGroupResponse.setSuccess(false);
                taskForceGroupResponse.setError("Duplicate Item Category name!");
            } else {
                if (taskForceGroup.getId().equals("")) {
                    taskForceGroup.setCreatedBy(userInfo.getId());
                    taskForceGroup.setCreationTime(new Date());
                } else {
                    taskForceGroup.setLastModifiedBy(userInfo.getId());
                    taskForceGroup.setLastModifiedTime(new Date());
                }
                taskForceGroupResponse.setTaskForceGroup(
                        new TaskForceGroupDTO(
                                this.save(taskForceGroup)
                        ));
                taskForceGroupResponse.setSuccess(true);
                taskForceGroupResponse.setError("");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            throw ex;
        }
        return taskForceGroupResponse;
    }

    @Override
    public TaskForceGroupResponse moveToTrash(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformationService.getUserInfo();
        logger.trace("Entering");
        TaskForceGroupResponse taskForceGroupResponse = new TaskForceGroupResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<TaskForceGroup> optionalItemCategory = this.findById(formData.get("id"));
            if (optionalItemCategory.isPresent()) {
                TaskForceGroup taskForceGroup = optionalItemCategory.get();
                taskForceGroup.setIsDeleted(1);
                taskForceGroup.setDeletedBy(userInfo.getId());
                taskForceGroup.setDeletedTime(new Date());
                taskForceGroupResponse.setSuccess(true);
                taskForceGroupResponse.setError("");
                taskForceGroupResponse.setTaskForceGroup(
                        new TaskForceGroupDTO(
                                this.save(taskForceGroup)
                        ));
            } else {
                taskForceGroupResponse.setSuccess(false);
                taskForceGroupResponse.setError("Error occurred while moving Item Category to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceGroupResponse.setSuccess(false);
            taskForceGroupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceGroupResponse;
    }

    @Override
    public TaskForceGroupResponse get(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        TaskForceGroupResponse taskForceGroupResponse = new TaskForceGroupResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<TaskForceGroup> optionalItemCategory = this.findById(formData.get("id"));
            if (optionalItemCategory.isPresent()) {
                TaskForceGroup taskForceGroup = optionalItemCategory.get();
                taskForceGroupResponse.setSuccess(true);
                taskForceGroupResponse.setError("");
                taskForceGroupResponse.setTaskForceGroup(
                        new TaskForceGroupDTO(taskForceGroup)
                );
            } else {
                taskForceGroupResponse.setSuccess(false);
                taskForceGroupResponse.setError("Error occurred while fetching Item Category!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceGroupResponse.setSuccess(false);
            taskForceGroupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceGroupResponse;
    }

    @Override
    public TaskForceGroupResponse getDeleted() throws Exception {
        logger.trace("Entering");
        UserInfo userInfo = userInformationService.getUserInfo();
        TaskForceGroupResponse taskForceGroupResponse = new TaskForceGroupResponse();
        try {
            taskForceGroupResponse.setTaskForceGroupList(
                    getItemCategoryDTOS(taskForceGroupRepository
                            .findAllByClientIdAndIsDeleted(
                                    userInfo.getClient().getClientId(),
                                    1
                            )
                    ));
            taskForceGroupResponse.setSuccess(true);
            taskForceGroupResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceGroupResponse.setSuccess(true);
            taskForceGroupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceGroupResponse;
    }

    @Override
    public TaskForceGroupResponse getAll() throws Exception {
        logger.trace("Entering");
        UserInfo userInfo = userInformationService.getUserInfo();
        TaskForceGroupResponse taskForceGroupResponse = new TaskForceGroupResponse();
        try {
            taskForceGroupResponse.setTaskForceGroupList(
                    getItemCategoryDTOS(taskForceGroupRepository
                            .findAllByClientIdAndIsDeleted(
                                    userInfo.getClient().getClientId(),
                                    0
                            )
                    ));
            taskForceGroupResponse.setSuccess(true);
            taskForceGroupResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceGroupResponse.setSuccess(true);
            taskForceGroupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceGroupResponse;
    }

    @Override
    public TaskForceGroupResponse getPaginated(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        UserInfo userInfo = userInformationService.getUserInfo();
        TaskForceGroupResponse taskForceGroupResponse = new TaskForceGroupResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
            Page<TaskForceGroup> page = taskForceGroupRepository
                    .findAllByClientIdAndIsDeleted(
                            userInfo.getClient().getClientId(),
                            0,
                            pageable
                    );
            taskForceGroupResponse.setTotalRecords(page.getTotalElements());
            taskForceGroupResponse.setTotalPages(page.getTotalPages());
            taskForceGroupResponse.setTaskForceGroupList(
                    getItemCategoryDTOS(page.getContent())
            );
            taskForceGroupResponse.setCurrentRecords(taskForceGroupResponse.getTaskForceGroupList().size());
            taskForceGroupResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceGroupResponse.setSuccess(false);
            taskForceGroupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceGroupResponse;
    }

    @Override
    public TaskForceGroupResponse searchPaginated(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        UserInfo userInfo = userInformationService.getUserInfo();
        TaskForceGroupResponse taskForceGroupResponse = new TaskForceGroupResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
            Page<TaskForceGroup> page;
            if (searchText == null) {
                page = taskForceGroupRepository
                        .findAllByClientIdAndIsDeleted(
                                userInfo.getClient().getClientId(),
                                0,
                                pageable
                        );
            } else {
                page = taskForceGroupRepository.findAll(
                        TaskForceGroupSpecification.textInAllColumns(
                                userInfo.getClient().getClientId(),
                                searchText
                        ), pageable
                );
            }
            taskForceGroupResponse.setTotalRecords(page.getTotalElements());
            taskForceGroupResponse.setTotalPages(page.getTotalPages());
            taskForceGroupResponse.setTaskForceGroupList(
                    getItemCategoryDTOS(page.getContent())
            );
            taskForceGroupResponse.setCurrentRecords(taskForceGroupResponse.getTaskForceGroupList().size());
            taskForceGroupResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskForceGroupResponse.setSuccess(false);
            taskForceGroupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskForceGroupResponse;
    }

    private List<TaskForceGroupDTO> getItemCategoryDTOS(List<TaskForceGroup> itemCategories) {
        List<TaskForceGroupDTO> itemCategoryDTOS = new ArrayList<>();
        for (TaskForceGroup taskForceGroup : itemCategories) {
            itemCategoryDTOS.add(new TaskForceGroupDTO(taskForceGroup));
        }
        return itemCategoryDTOS;
    }

    private boolean checkDuplicate(TaskForceGroup taskForceGroup) {
        List<TaskForceGroup> taskForceGroups;
        if (taskForceGroup.getId() == null) {
            taskForceGroup.setId("");
        }
        if (taskForceGroup.getId().equals("")) {
            taskForceGroups = taskForceGroupRepository
                    .findAllByClientIdAndIsDeletedAndName(
                            taskForceGroup.getClientId(),
                            0,
                            taskForceGroup.getName()
                    );
        } else {
            taskForceGroups = taskForceGroupRepository
                    .findAllByClientIdAndIsDeletedAndNameAndIdIsNot(
                            taskForceGroup.getClientId(),
                            0,
                            taskForceGroup.getName(),
                            taskForceGroup.getId()
                    );
        }
        return !taskForceGroups.isEmpty();
    }


}
