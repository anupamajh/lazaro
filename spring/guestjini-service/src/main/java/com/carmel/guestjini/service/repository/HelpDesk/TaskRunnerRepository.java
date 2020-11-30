package com.carmel.guestjini.service.repository.HelpDesk;

import com.carmel.guestjini.service.model.HelpDesk.TaskRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRunnerRepository extends JpaRepository<TaskRunner, String> {
    List<TaskRunner> findAllByClientIdAndIsDeleted(String clientId, int isDeleted);

    Page<TaskRunner> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<TaskRunner> findAll(Specification<TaskRunner> textInAllColumns, Pageable pageable);

    Optional<TaskRunner> findByTicketId(String ticketId);
}
