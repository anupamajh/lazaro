package com.carmel.common.dbservice.repository;

import com.carmel.common.dbservice.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, String> {

    Optional<Group> findByInterestId(String id);

    List<Group> findAllByClientIdAndIsDeleted(String clientId, int isDeleted);

    List<Group> findAllByClientIdAndIsDeletedAndName(String clientId, int isDeleted, String name);

    List<Group> findAllByClientIdAndIsDeletedAndNameAndIdIsNot(String clientId, int isDeleted, String name, String id);

    Page<Group> findAll(Specification<Group> textInAllColumns, Pageable pageable);

    Page<Group> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);
}
