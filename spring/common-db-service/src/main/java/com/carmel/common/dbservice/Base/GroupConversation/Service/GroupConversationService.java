package com.carmel.common.dbservice.Base.GroupConversation.Service;

import com.carmel.common.dbservice.Base.GroupConversation.Model.GroupConversation;
import com.carmel.common.dbservice.Base.GroupConversation.Responce.GroupConversationResponse;

import java.util.Map;

public interface GroupConversationService {
    GroupConversation save(GroupConversation groupConversation) throws Exception;

    GroupConversationResponse saveGroupConcersation(Map<String,String> formData) throws Exception;

    GroupConversationResponse getByGroup(Map<String,String> formData) throws Exception;

    GroupConversationResponse findAllByGroupId(String groupId);

}
