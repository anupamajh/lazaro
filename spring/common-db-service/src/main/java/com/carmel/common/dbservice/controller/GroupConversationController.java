package com.carmel.common.dbservice.controller;

import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.DTO.GroupConversationDTO;
import com.carmel.common.dbservice.model.GroupConversation;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.response.GroupConversationResponse;
import com.carmel.common.dbservice.services.GroupConversationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/group-conversation")
public class GroupConversationController {
    Logger logger = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    GroupConversationService groupConversationService;

    @Autowired
    EntityManager entityManager;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public GroupConversationResponse save(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        GroupConversationResponse groupConversationResponse = new GroupConversationResponse();
        try {
            String groupId = formData.get("groupId");
            String message = formData.get("message");
            GroupConversation groupConversation = new GroupConversation();
            groupConversation.setClientId(userInfo.getClient().getClientId());
            groupConversation.setGroupId(groupId);
            groupConversation.setUserId(userInfo.getId());
            groupConversation.setDisplayName(userInfo.getFullName());
            groupConversation.setMessage(message);
            groupConversation.setCreationTime(new Date());
            groupConversation.setCreatedBy(userInfo.getId());
            groupConversationResponse.setGroupConversation(groupConversationService.save(groupConversation));
            groupConversationResponse.setSuccess(true);
            groupConversationResponse.setError("");
        } catch (Exception ex) {

            logger.error(ex.getMessage(), ex);
            groupConversationResponse.setSuccess(false);
            groupConversationResponse.setError(ex.getMessage());
        }
        return groupConversationResponse;
    }

    @RequestMapping(value = "/get-by-group", method = RequestMethod.POST)
    public GroupConversationResponse getByGroup(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        GroupConversationResponse groupConversationResponse = new GroupConversationResponse();
        try {
            String groupId = formData.get("groupId");
            List<GroupConversation> groupConversationList = groupConversationService.findAllByGroupId(groupId);
            List<GroupConversationDTO> conversationDTOS = new ArrayList<>();
            groupConversationList.forEach(groupConversation -> {
                GroupConversationDTO groupConversationDTO = new GroupConversationDTO(groupConversation);
                if (groupConversation.getUserId().equals(userInfo.getId())) {
                    groupConversationDTO.setIsItMe(1);
                }
                conversationDTOS.add(groupConversationDTO);
            });

            groupConversationResponse.setGroupConversationListDTO(conversationDTOS);
            groupConversationResponse.setSuccess(true);
            groupConversationResponse.setError("");
        } catch (Exception ex) {

            logger.error(ex.getMessage(), ex);
            groupConversationResponse.setSuccess(false);
            groupConversationResponse.setError(ex.getMessage());
        }
        return groupConversationResponse;
    }
}
