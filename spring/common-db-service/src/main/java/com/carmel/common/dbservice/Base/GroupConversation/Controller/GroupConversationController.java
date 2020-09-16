package com.carmel.common.dbservice.Base.GroupConversation.Controller;

import com.carmel.common.dbservice.Base.GroupConversation.Responce.GroupConversationResponse;
import com.carmel.common.dbservice.Base.GroupConversation.Service.GroupConversationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/group-conversation")
public class GroupConversationController {
    Logger logger = LoggerFactory.getLogger(GroupConversationController.class);

    @Autowired
    GroupConversationService groupConversationService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public GroupConversationResponse save(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        GroupConversationResponse groupConversationResponse = new GroupConversationResponse();
        try {
            groupConversationResponse = groupConversationService.saveGroupConcersation(formData);
        } catch (Exception ex) {
            groupConversationResponse.setSuccess(true);
            groupConversationResponse.setError(ex.getMessage());
        }
        return groupConversationResponse;
    }

    @RequestMapping(value = "/get-by-group", method = RequestMethod.POST)
    public GroupConversationResponse getByGroup(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        GroupConversationResponse groupConversationResponse = new GroupConversationResponse();
        try {
            groupConversationResponse = groupConversationService.getByGroup(formData);
        } catch (Exception ex) {
            groupConversationResponse.setSuccess(true);
            groupConversationResponse.setError(ex.getMessage());
        }
        return groupConversationResponse;
    }
}
