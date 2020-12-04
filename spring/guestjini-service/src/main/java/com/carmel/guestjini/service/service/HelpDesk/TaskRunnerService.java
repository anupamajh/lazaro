package com.carmel.guestjini.service.service.HelpDesk;


import com.carmel.guestjini.service.HelpDesk.TaskTicket.DTO.TaskAssigneeDTO;
import com.carmel.guestjini.service.HelpDesk.TaskTicket.Response.TaskAssigneeResponse;
import com.carmel.guestjini.service.model.HelpDesk.TaskRunner;
import com.carmel.guestjini.service.response.HelpDesk.TaskRunnerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TaskRunnerService {
    TaskRunner save(TaskRunner taskRunner);

    Optional<TaskRunner> findById(String id);

    List<TaskRunner> findAllByClientIdAndIsDeleted(String clientId, int isDeleted);

    Page<TaskRunner> findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable);

    Page<TaskRunner> findAll(Specification<TaskRunner> textInAllColumns, Pageable pageable);

    TaskRunnerResponse assignTicket(TaskAssigneeDTO taskAssigneeDTO) throws Exception;

    TaskAssigneeResponse getTaskAssignmentDetails(Map<String, String> formData) throws Exception;

    TaskRunner getByTicketId(String ticketId);

    TaskRunnerResponse findAllByUserIdAndTaskStatus(String userId, int taskStatus) throws Exception;

    TaskRunnerResponse findAllByUserIdAndTaskStatusIsNot(String userId, int taskStatus) throws Exception;

    TaskRunnerResponse findAllByTaskForceGroupIdAndTaskStatus(String userId, int taskStatus) throws Exception;

    TaskRunnerResponse findAllByTaskForceGroupIdAndTaskStatusIsNot(String userId,  int taskStatus) throws Exception;

    TaskRunnerResponse findAllByTaskForceGroupIdAndUserId(String groupId, String userId) throws Exception;
}
