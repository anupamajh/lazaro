package com.carmel.guestjini.service.repository.HelpDesk;


import com.carmel.guestjini.service.model.HelpDesk.TaskForce;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskForceRepository extends JpaRepository<TaskForce, String> {
    List<TaskForce> findAllByClientIdAndIsDeleted(String clientId, int isDeleted);

    Page<TaskForce> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<TaskForce> findAll(Specification<TaskForce> textInAllColumns, Pageable pageable);

    List<TaskForce> findAllByClientIdAndIsDeletedAndPhone(String clientId, int isDeleted, String phone);

    List<TaskForce> findAllByClientIdAndIsDeletedAndPhoneAndIdIsNot(String clientId, int isDeleted, String phone, String id);

    Optional<TaskForce> findByPhone(String phone);

    List<TaskForce> findAllByClientIdAndIsDeletedAndGroupId(String clientId, int isDeleted, String groupId);
}
