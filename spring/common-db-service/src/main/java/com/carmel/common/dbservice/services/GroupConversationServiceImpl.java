package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.GroupConversation;
import com.carmel.common.dbservice.repository.GroupConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupConversationServiceImpl implements GroupConversationService {
    @Autowired
    GroupConversationRepository groupConversationRepository;

    @Override
    public GroupConversation save(GroupConversation groupConversation) {
        return groupConversationRepository.save(groupConversation);
    }

    @Override
    public List<GroupConversation> findAllByGroupId(String groupId) {
        return groupConversationRepository.findAllByGroupId(groupId);
    }
}
