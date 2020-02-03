package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    Optional<Group> findByInterestId(String id);

    Group save(Group group);

    Optional<Group> findById(String id);

    List<Group> findAllByClientIdAndIsDeleted(String clientId, int i);

    Page<Group> findAllByClientIdAndIsDeleted(String clientId, int i, Pageable pageable);

    List<Group> findAllByClientIdAndIsDeletedAndName(String clientId, int i, String name);

    List<Group> findAllByClientIdAndIsDeletedAndNameAndIdIsNot(String clientId, int i, String name, String id);

    Page<Group> findAll(Specification<Group> textInAllColumns, Pageable pageable);

    List<Group> findAllByClientIdAndIsDeletedAndGroupType(String clientId, int isDeleted, int groupType);

    List<Group> findAllByClientIdAndIsDeletedAndGroupTypeAndGroupOwnerId(String clientId, int isDeleted, int groupType, String userId);

    List<Group> findAllByClientIdAndIsDeletedAndGroupTypeAndGroupOwnerIdIsNot(String clientId, int isDeleted, int groupType, String userId);
}
