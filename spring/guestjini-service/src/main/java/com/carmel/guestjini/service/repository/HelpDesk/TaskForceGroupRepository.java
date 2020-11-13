package com.carmel.guestjini.service.repository.HelpDesk;

import com.carmel.guestjini.service.model.HelpDesk.TaskForceGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskForceGroupRepository extends JpaRepository<TaskForceGroup, String> {
    List<TaskForceGroup> findAllByClientIdAndIsDeleted(String clientId, int isDeleted);

    Page<TaskForceGroup> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<TaskForceGroup> findAll(Specification<TaskForceGroup> textInAllColumns, Pageable pageable);

    List<TaskForceGroup> findAllByClientIdAndIsDeletedAndName(String clientId, int isDeleted, String name);

    List<TaskForceGroup> findAllByClientIdAndIsDeletedAndNameAndIdIsNot(String clientId, int isDeleted, String name, String id);

}
