package com.carmel.common.dbservice.Base.Group.Service;

import com.carmel.common.dbservice.Base.Group.Model.Group;
import com.carmel.common.dbservice.Base.Group.Response.GroupResponse;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GroupService {

    Group save(Group group) throws Exception;

    Optional<Group> findById(String id) throws Exception;

    Optional<Group> findByInterestId(String id) throws Exception;

    GroupResponse saveGroup(Group group) throws Exception;

    GroupResponse moveToTrash(Map<String, String> formData) throws Exception;

    GroupResponse subscribe(Map<String, String> formData) throws Exception;

    GroupResponse get(Map<String, String> formData) throws Exception;

    GroupResponse inviteToGroup(Map<String, String> formData) throws Exception;

    GroupResponse getDeleted() throws Exception;

    GroupResponse getAll() throws Exception;

    GroupResponse getAllByType(Map<String, String> formData) throws Exception;

    GroupResponse getPaginated(Map<String, String> formData) throws Exception;

    GroupResponse searchPaginated(Map<String, String> formData) throws Exception;

    GroupResponse search(SearchRequest searchRequest) throws Exception;

    GroupResponse findAllByClientIdAndIsDeleted(String clientId, int i) throws Exception;

    GroupResponse findAllByClientIdAndIsDeleted(String clientId, int i, Pageable pageable) throws Exception;

    GroupResponse findAllByClientIdAndIsDeletedAndName(String clientId, int i, String name) throws Exception;

    GroupResponse findAllByClientIdAndIsDeletedAndNameAndIdIsNot(String clientId, int i, String name, String id) throws Exception;

    GroupResponse findAll(Specification<Group> textInAllColumns, Pageable pageable) throws Exception;

    GroupResponse findAllByClientIdAndIsDeletedAndGroupType(String clientId, int isDeleted, int groupType);

    GroupResponse findAllByClientIdAndIsDeletedAndGroupTypeAndGroupOwnerId(String clientId, int isDeleted, int groupType, String userId);

    GroupResponse findAllByClientIdAndIsDeletedAndGroupTypeAndGroupOwnerIdIsNot(String clientId, int isDeleted, int groupType, String userId);
}
