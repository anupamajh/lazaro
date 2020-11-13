package com.carmel.guestjini.service.service.HelpDesk;

import com.carmel.guestjini.service.model.HelpDesk.TaskForceGroup;
import com.carmel.guestjini.service.response.HelpDesk.TaskForceGroupResponse;

import java.util.Map;
import java.util.Optional;

public interface TaskForceGroupService {
    Optional<TaskForceGroup> findById(String id) throws Exception;

    TaskForceGroup save(TaskForceGroup taskForceGroup) throws Exception;

    TaskForceGroupResponse saveTaskForceGroup(TaskForceGroup taskForceGroup) throws Exception;

    TaskForceGroupResponse moveToTrash(Map<String, String> formData) throws Exception;

    TaskForceGroupResponse get(Map<String, String> formData) throws Exception;

    TaskForceGroupResponse getDeleted() throws Exception;

    TaskForceGroupResponse getAll() throws Exception;

    TaskForceGroupResponse getPaginated(Map<String, String> formData) throws Exception;

    TaskForceGroupResponse searchPaginated(Map<String, String> formData) throws Exception;
}
