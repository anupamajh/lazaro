package com.carmel.guestjini.helpdesk.controller;


import com.carmel.guestjini.helpdesk.components.UserComponent;
import com.carmel.guestjini.helpdesk.components.UserInformation;
import com.carmel.guestjini.helpdesk.model.DTO.TaskNoteDTO;
import com.carmel.guestjini.helpdesk.model.DTO.UserDTO;
import com.carmel.guestjini.helpdesk.model.Principal.UserInfo;
import com.carmel.guestjini.helpdesk.model.TaskNote;
import com.carmel.guestjini.helpdesk.response.TaskNoteResponse;
import com.carmel.guestjini.helpdesk.response.UserResponse;
import com.carmel.guestjini.helpdesk.service.TaskNoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/task-ticket-notes")
public class TaskNoteController {
    Logger logger = LoggerFactory.getLogger(TaskTicketController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    TaskNoteService taskNoteService;

    @Autowired
    UserComponent userComponent;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public TaskNoteResponse save(@Valid @RequestBody TaskNote taskNote) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        TaskNoteResponse taskNoteResponse = new TaskNoteResponse();
        try {
            if (taskNote.getId() == null) {
                taskNote.setId("");
            }
            if (taskNote.getOrgId() == null || taskNote.getOrgId().isEmpty()) {
                if (userInfo.getDefaultOrganization() != null) {
                    taskNote.setOrgId(userInfo.getDefaultOrganization().getId());
                }
            }
            taskNote.setClientId(userInfo.getClient().getClientId());
            if (taskNote.getId().equals("")) {
                taskNote.setCreatedBy(userInfo.getId());
                taskNote.setUserId(userInfo.getId());
                taskNote.setCreationTime(new Date());
                taskNote.setIsRead(0);
            } else {
                taskNote.setLastModifiedBy(userInfo.getId());
                taskNote.setLastModifiedTime(new Date());
            }
            taskNoteResponse.setTaskNote(taskNoteService.save(taskNote));
            taskNoteResponse.setSuccess(true);
            taskNoteResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskNoteResponse.setSuccess(false);
            taskNoteResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskNoteResponse;
    }

    @RequestMapping(value = "/get-ticket-notes", method = RequestMethod.POST)
    public TaskNoteResponse getTicketNoes(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        TaskNoteResponse taskNoteResponse = new TaskNoteResponse();
        try {
            String ticketId = formData.get("ticketId");
            int isDeleted = 0;
            List<TaskNote> taskNoteList = taskNoteService.findByTicketIdAndIsDeleted(ticketId, isDeleted);
            List<String> userIds = new ArrayList<>();
            taskNoteList.forEach(taskNote -> {
                userIds.add(taskNote.getUserId());
            });
            UserResponse userResponse = new UserResponse();
            if (userIds.size() > 0) {
                userResponse = userComponent.findUserByIds(userIds);
            }
            List<TaskNoteDTO> taskNoteDTOS = new ArrayList<>();
            List<UserDTO> userDTOList = userResponse.getUserList();
            taskNoteList.forEach(taskNote -> {
                TaskNoteDTO taskNoteDTO = new TaskNoteDTO(taskNote);
                Optional<UserDTO> optionalUserDTO = userDTOList.stream()
                        .filter(
                                udto -> udto.getId()
                                        .equals(taskNote.getUserId())
                        ).findFirst();
                if(optionalUserDTO.isPresent()){
                    taskNoteDTO.setUserName(optionalUserDTO.get().getFullName());
                }
                taskNoteDTOS.add(taskNoteDTO);
            });
            taskNoteResponse.setTaskNoteListDTO(taskNoteDTOS);
            taskNoteResponse.setSuccess(true);
            taskNoteResponse.setError("");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            taskNoteResponse.setSuccess(false);
            taskNoteResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return taskNoteResponse;
    }

}
