package com.carmel.guestjini.helpdesk.repository;

import com.carmel.guestjini.helpdesk.model.TaskRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRunnerRepository extends JpaRepository<TaskRunner, String> {
    List<TaskRunner> findAllByClientIdAndIsDeleted(String clientId, int isDeleted);

    Page<TaskRunner> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<TaskRunner> findAll(Specification<TaskRunner> textInAllColumns, Pageable pageable);
}
