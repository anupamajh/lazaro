package com.carmel.common.dbservice.Base.GroupConversation.Repository;

import com.carmel.common.dbservice.Base.GroupConversation.Model.GroupConversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupConversationRepository extends JpaRepository<GroupConversation, String> {
    List<GroupConversation> findAllByGroupId(String groupId);
}
