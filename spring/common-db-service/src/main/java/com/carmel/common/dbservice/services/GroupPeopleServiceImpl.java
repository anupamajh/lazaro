package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.GroupPeople;
import com.carmel.common.dbservice.repository.GroupPeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupPeopleServiceImpl implements GroupPeopleService {

    @Autowired
    GroupPeopleRepository groupPeopleRepository;

    @Override
    public Optional<GroupPeople> findByUserIdAndGroupId(String userId, String groupId) {
        return groupPeopleRepository.findByUserIdAndGroupId(userId, groupId);
    }

    @Override
    public void delete(GroupPeople groupPeople) {
        groupPeopleRepository.delete(groupPeople);
    }

    @Override
    public GroupPeople save(GroupPeople groupPeople) {
        return groupPeopleRepository.save(groupPeople);
    }

    @Override
    public List<GroupPeople> findByUserId(String id) {
        return groupPeopleRepository.findByUserId(id);
    }

    @Override
    public List<GroupPeople> findByGroupIdAndHasAcceptedInvitation(String id, int isDeleted) {
        return groupPeopleRepository.findByGroupIdAndHasAcceptedInvitation(id, isDeleted);
    }

    @Override
    public List<GroupPeople> findAllByGroupIdAndHasAcceptedInvitationAndUserIdIsNot(String id, int isDeleted, String userId) {
        return groupPeopleRepository.findAllByGroupIdAndHasAcceptedInvitationAndUserIdIsNot(id, isDeleted, userId);
    }
}
