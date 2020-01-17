package com.carmel.guestjini.helpdesk.service;

import com.carmel.guestjini.helpdesk.model.TaskRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface TaskRunnerService {
    TaskRunner save(TaskRunner taskRunner);

    Optional<TaskRunner> findById(String id);

    List<TaskRunner> findAllByClientIdAndIsDeleted(String clientId, int isDeleted);

    Page<TaskRunner> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<TaskRunner> findAll(Specification<TaskRunner> textInAllColumns, Pageable pageable);
}
