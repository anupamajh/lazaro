package com.carmel.guestjini.service.service.HelpDesk;

import com.carmel.guestjini.service.model.HelpDesk.TaskForce;
import com.carmel.guestjini.service.response.HelpDesk.TaskForceResponse;

import java.util.Map;
import java.util.Optional;

public interface TaskForceService {
    Optional<TaskForce> findById(String id) throws Exception;

    TaskForce save(TaskForce taskForce) throws Exception;

    TaskForceResponse saveTaskForce(TaskForce taskForce) throws Exception;

    TaskForceResponse moveToTrash(Map<String, String> formData) throws Exception;

    TaskForceResponse get(Map<String, String> formData) throws Exception;

    TaskForceResponse getDeleted() throws Exception;

    TaskForceResponse getAll() throws Exception;

    TaskForceResponse getPaginated(Map<String, String> formData) throws Exception;

    TaskForceResponse searchPaginated(Map<String, String> formData) throws Exception;

    Optional<TaskForce> findByPhone(String phone);

    TaskForceResponse getByGroup(String groupId);

    TaskForceResponse findByUserId(String userId) throws Exception;
}
