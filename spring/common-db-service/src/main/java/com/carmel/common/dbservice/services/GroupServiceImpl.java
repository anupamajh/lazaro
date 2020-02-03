package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.Group;
import com.carmel.common.dbservice.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Override
    public Optional<Group> findByInterestId(String id) {
        return groupRepository.findByInterestId(id);
    }

    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Optional<Group> findById(String id) {
        return groupRepository.findById(id);
    }

    @Override
    public List<Group> findAllByClientIdAndIsDeleted(String clientId, int isDeleted) {
        return groupRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted);
    }

    @Override
    public Page<Group> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return groupRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted, pageable);
    }

    @Override
    public List<Group> findAllByClientIdAndIsDeletedAndName(String clientId, int isDeleted, String name) {
        return groupRepository.findAllByClientIdAndIsDeletedAndName(clientId, isDeleted, name);
    }

    @Override
    public List<Group> findAllByClientIdAndIsDeletedAndNameAndIdIsNot(String clientId, int isDeleted, String name, String id) {
        return groupRepository.findAllByClientIdAndIsDeletedAndNameAndIdIsNot(clientId, isDeleted, name, id);
    }

    @Override
    public Page<Group> findAll(Specification<Group> textInAllColumns, Pageable pageable) {
        return groupRepository.findAll(textInAllColumns, pageable);
    }

    @Override
    public List<Group> findAllByClientIdAndIsDeletedAndGroupType(String clientId, int isDeleted, int groupType) {
        return groupRepository.findAllByClientIdAndIsDeletedAndGroupType(clientId, isDeleted, groupType);
    }

    @Override
    public List<Group> findAllByClientIdAndIsDeletedAndGroupTypeAndGroupOwnerId(String clientId, int isDeleted, int groupType, String userId) {
        return groupRepository.findAllByClientIdAndIsDeletedAndGroupTypeAndGroupOwnerId(clientId, isDeleted, groupType, userId);
    }

    @Override
    public List<Group> findAllByClientIdAndIsDeletedAndGroupTypeAndGroupOwnerIdIsNot(String clientId, int isDeleted, int groupType, String userId) {
        return groupRepository.findAllByClientIdAndIsDeletedAndGroupTypeAndGroupOwnerIdIsNot(clientId, isDeleted, groupType, userId);
    }

}
