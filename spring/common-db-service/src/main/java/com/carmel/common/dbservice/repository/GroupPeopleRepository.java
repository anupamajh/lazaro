package com.carmel.common.dbservice.repository;

import com.carmel.common.dbservice.model.GroupPeople;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupPeopleRepository extends JpaRepository<GroupPeople, String> {
    Optional<GroupPeople> findByUserIdAndGroupId(String userId, String groupId);

    List<GroupPeople> findByUserId(String id);

    List<GroupPeople> findByGroupIdAndHasAcceptedInvitation(String id, int isDeleted);

    List<GroupPeople> findAllByGroupIdAndHasAcceptedInvitationAndUserIdIsNot(String id, int isDeleted, String userId);

    List<GroupPeople> findAllByGroupId(String id);
}
