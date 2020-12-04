package com.carmel.guestjini.service.service.HelpDesk;


import com.carmel.guestjini.service.HelpDesk.TaskTicket.DTO.TaskAssigneeDTO;
import com.carmel.guestjini.service.HelpDesk.TaskTicket.Response.TaskAssigneeResponse;
import com.carmel.guestjini.service.common.HelpDesk.TicketStatus;
import com.carmel.guestjini.service.components.UserInformation;
import com.carmel.guestjini.service.components.UserService;
import com.carmel.guestjini.service.model.HelpDesk.TaskForceGroup;
import com.carmel.guestjini.service.model.HelpDesk.TaskRunner;
import com.carmel.guestjini.service.model.HelpDesk.TaskTicket;
import com.carmel.guestjini.service.model.Principal.UserInfo;
import com.carmel.guestjini.service.repository.HelpDesk.TaskRunnerRepository;
import com.carmel.guestjini.service.response.Common.UserResponse;
import com.carmel.guestjini.service.response.HelpDesk.TaskRunnerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskRunnerServiceImpl implements TaskRunnerService {

    @Autowired
    TaskRunnerRepository taskRunnerRepository;

    @Autowired
    TaskTicketService taskTicketService;

    @Autowired
    UserService userService;

    @Autowired
    TaskForceGroupService taskForceGroupService;

    @Autowired
    TaskRunnerService taskRunnerService;

    @Autowired
    UserInformation userInformation;


    @Override
    public TaskRunner save(TaskRunner taskRunner) {
        return taskRunnerRepository.save(taskRunner);
    }

    @Override
    public Optional<TaskRunner> findById(String id) {
        return taskRunnerRepository.findById(id);
    }

    @Override
    public List<TaskRunner> findAllByClientIdAndIsDeleted(String clientId, int isDeleted) {
        return taskRunnerRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted);
    }

    @Override
    public Page<TaskRunner> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return taskRunnerRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted, pageable);
    }

    @Override
    public Page<TaskRunner> findAll(Specification<TaskRunner> textInAllColumns, Pageable pageable) {
        return taskRunnerRepository.findAll(textInAllColumns, pageable);
    }

    @Override
    public TaskRunnerResponse assignTicket(TaskAssigneeDTO taskAssigneeDTO) throws Exception {

        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        TaskRunnerResponse taskRunnerResponse = new TaskRunnerResponse();
        try {
            TaskRunner taskRunner;
            Optional<TaskRunner> optionalTaskRunner = taskRunnerRepository.findByTicketId(taskAssigneeDTO.getTicketId());
            if (optionalTaskRunner.isPresent()) {
                taskRunner = optionalTaskRunner.get();
            } else {
                taskRunner = new TaskRunner();
            }
            if (taskAssigneeDTO.getIsGroup() == 1) {
                taskRunner.setTaskForceGroupId(taskAssigneeDTO.getId());
            } else {
                taskRunner.setUserId(taskAssigneeDTO.getId());
            }
            taskRunner.setTaskStatus(3);
            taskRunner.setTicketId(taskAssigneeDTO.getTicketId());
            taskRunner.setIsDeleted(0);
            taskRunner.setCreatedBy(userInfo.getId());
            taskRunner.setCreationTime(new Date());
            taskRunner.setLastModifiedBy(userInfo.getId());
            taskRunner.setLastModifiedTime(new Date());
            taskRunner = taskRunnerRepository.save(taskRunner);

            Optional<TaskTicket> optionalTaskTicket = taskTicketService.findById(taskRunner.getTicketId());
            if (optionalTaskTicket.isPresent()) {
                TaskTicket taskTicket = optionalTaskTicket.get();
                taskTicket.setTicketStatus(TicketStatus.WORK_IN_PROGRESS);
                taskTicketService.save(taskTicket);
            }
            taskRunnerResponse.setSuccess(true);
            taskRunnerResponse.setTaskRunner(taskRunner);
        } catch (Exception ex) {
            throw ex;
        }
        return taskRunnerResponse;
    }

    @Override
    public TaskAssigneeResponse getTaskAssignmentDetails(Map<String, String> formData) throws Exception {
        TaskAssigneeResponse taskAssigneeResponse = new TaskAssigneeResponse();
        try {
            String ticketId = formData.get("ticketId");
            Optional<TaskRunner> optionalTaskRunner =
                    taskRunnerRepository.findByTicketId(ticketId);
            if (optionalTaskRunner.isPresent()) {
                TaskRunner taskRunner = optionalTaskRunner.get();
                String taskForceGroupId = taskRunner.getTaskForceGroupId();
                if (taskForceGroupId == null) {
                    taskForceGroupId = "";
                }
                if (!taskForceGroupId.equals("")) {
                    Optional<TaskForceGroup> optionalTaskForceGroup =
                            taskForceGroupService.findById(taskForceGroupId);
                    if (optionalTaskForceGroup.isPresent()) {
                        taskAssigneeResponse.setTaskForceGroup(optionalTaskForceGroup.get());
                    }
                }

                String userId = taskRunner.getUserId();
                if (userId == null) {
                    userId = "";
                }
                if (!userId.equals("")) {
                    UserResponse userResponse = userService.findUserById(userId);
                    if (userResponse.isSuccess()) {
                        if (userResponse.getUser() != null) {
                            taskAssigneeResponse
                                    .setUserDTO(userResponse.getUser());
                        }
                    }
                }

                userId = taskRunner.getCreatedBy();
                if (userId == null) {
                    userId = "";
                }
                if (!userId.equals("")) {
                    UserResponse userResponse = userService.findUserById(userId);
                    if (userResponse.isSuccess()) {
                        if (userResponse.getUser() != null) {
                            taskAssigneeResponse
                                    .setCreatedBy(userResponse.getUser());
                        }
                    }
                }
                userId = taskRunner.getLastModifiedBy();
                if (userId == null) {
                    userId = "";
                }
                if (!userId.equals("")) {
                    UserResponse userResponse = userService.findUserById(userId);
                    if (userResponse.isSuccess()) {
                        if (userResponse.getUser() != null) {
                            taskAssigneeResponse
                                    .setModifiedBy(userResponse.getUser());
                        }
                    }
                }
                taskAssigneeResponse.setCreationTime(taskRunner.getCreationTime());
                taskAssigneeResponse.setLastModifiedTime(taskRunner.getLastModifiedTime());
                taskAssigneeResponse.setIsDeleted(taskRunner.getIsDeleted());
                taskAssigneeResponse.setSuccess(true);
            } else {
                taskAssigneeResponse.setSuccess(true);
            }


        } catch (Exception ex) {
            throw ex;
        }
        return taskAssigneeResponse;
    }

    @Override
    public TaskRunner getByTicketId(String ticketId) {
        Optional<TaskRunner> optionalTaskRunner =
                taskRunnerRepository.findByTicketId(ticketId);
        if (optionalTaskRunner.isPresent()) {
            return optionalTaskRunner.get();
        } else {
            return new TaskRunner();
        }
    }

    @Override
    public TaskRunnerResponse findAllByUserIdAndTaskStatus(String userId, int taskStatus) throws Exception {
        TaskRunnerResponse taskRunnerResponse = new TaskRunnerResponse();
        try {
            List<TaskRunner> taskRunners = taskRunnerRepository
                    .findAllByUserIdAndTaskStatus(userId, taskStatus);
            taskRunnerResponse.setSuccess(true);
            taskRunnerResponse.setTaskRunnerList(taskRunners);
        } catch (Exception ex) {
            throw ex;
        }
        return taskRunnerResponse;
    }

    @Override
    public TaskRunnerResponse findAllByUserIdAndTaskStatusIsNot(String userId, int taskStatus) throws Exception {
        TaskRunnerResponse taskRunnerResponse = new TaskRunnerResponse();
        try {
            List<TaskRunner> taskRunners = taskRunnerRepository
                    .findAllByUserIdAndTaskStatusIsNot(userId, taskStatus);
            taskRunnerResponse.setSuccess(true);
            taskRunnerResponse.setTaskRunnerList(taskRunners);
        } catch (Exception ex) {
            throw ex;
        }
        return taskRunnerResponse;
    }

    @Override
    public TaskRunnerResponse findAllByTaskForceGroupIdAndTaskStatus(String groupId, int taskStatus) throws Exception {
        TaskRunnerResponse taskRunnerResponse = new TaskRunnerResponse();
        try {
            List<TaskRunner> taskRunners = taskRunnerRepository
                    .findAllByTaskForceGroupIdAndTaskStatus(groupId, taskStatus);
            taskRunnerResponse.setSuccess(true);
            taskRunnerResponse.setTaskRunnerList(taskRunners);
        } catch (Exception ex) {
            throw ex;
        }
        return taskRunnerResponse;
    }

    @Override
    public TaskRunnerResponse findAllByTaskForceGroupIdAndTaskStatusIsNot(String groupId, int taskStatus) throws Exception {
        TaskRunnerResponse taskRunnerResponse = new TaskRunnerResponse();
        try {
            List<TaskRunner> taskRunners = taskRunnerRepository
                    .findAllByTaskForceGroupIdAndTaskStatusIsNot(groupId, taskStatus);
            taskRunnerResponse.setSuccess(true);
            taskRunnerResponse.setTaskRunnerList(taskRunners);
        } catch (Exception ex) {
            throw ex;
        }
        return taskRunnerResponse;
    }

    @Override
    public TaskRunnerResponse findAllByTaskForceGroupIdAndUserId(String groupId, String userId) throws Exception {
        TaskRunnerResponse taskRunnerResponse = new TaskRunnerResponse();
        try {
            List<TaskRunner> taskRunners = taskRunnerRepository
                    .findAllByTaskForceGroupIdAndUserId(groupId, userId);
            taskRunnerResponse.setSuccess(true);
            taskRunnerResponse.setTaskRunnerList(taskRunners);
        } catch (Exception ex) {
            throw ex;
        }
        return taskRunnerResponse;
    }
}
