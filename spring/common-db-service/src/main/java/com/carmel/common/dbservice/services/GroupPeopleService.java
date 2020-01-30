package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.GroupPeople;

import java.util.List;
import java.util.Optional;

public interface GroupPeopleService {
    Optional<GroupPeople> findByUserIdAndGroupId(String userId, String groupId);

    void delete(GroupPeople groupPeople);

    GroupPeople save(GroupPeople groupPeople);

    List<GroupPeople> findByUserId(String id);

    List<GroupPeople> findByGroupIdAndHasAcceptedInvitation(String id, int isDeleted);

    List<GroupPeople> findAllByGroupIdAndHasAcceptedInvitationAndUserIdIsNot(String id, int isDeleted, String userId);
}
