package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.GroupConversation;

import java.util.List;

public interface GroupConversationService {
    GroupConversation save(GroupConversation groupConversation);

    List<GroupConversation> findAllByGroupId(String groupId);
}
