package com.carmel.common.dbservice.repository;

import com.carmel.common.dbservice.model.GroupConversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupConversationRepository extends JpaRepository<GroupConversation, String> {
    List<GroupConversation> findAllByGroupId(String groupId);
}
