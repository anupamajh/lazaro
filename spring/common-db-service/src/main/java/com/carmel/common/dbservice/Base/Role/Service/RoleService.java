package com.carmel.common.dbservice.Base.Role.Service;

import com.carmel.common.dbservice.Base.AppFeature.Model.AppFeatures;
import com.carmel.common.dbservice.Base.AppFeature.Response.AppFeaturesResponse;
import com.carmel.common.dbservice.Base.Client.Model.Client;
import com.carmel.common.dbservice.Base.Role.Response.RolesResponse;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.Base.Role.Model.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;
import java.util.Optional;

public interface RoleService {
    Role save(Role role) throws Exception;

    Optional<Role> findById(String id) throws Exception;

    RolesResponse saveRole(Role role) throws Exception;

    RolesResponse moveToTrash(Map<String, String> formData) throws Exception;

    RolesResponse get(Map<String, String> formData) throws Exception;

    RolesResponse getDeleted() throws Exception;

    RolesResponse getAll() throws Exception;

    RolesResponse getPaginated(Map<String, String> formData) throws Exception;

    RolesResponse searchPaginated( Map<String, String> formData) throws Exception;

    RolesResponse search(SearchRequest searchRequest) throws Exception;

    RolesResponse findAllByRoleNameAndClient(String roleName, Client client) throws Exception;

    RolesResponse findAllByRoleNameAndIdIsNotAndClient(String roleName, String id, Client client) throws Exception;

    RolesResponse findAllByIsDeletedAndClient(int isDeleted, Client client) throws Exception;

    RolesResponse findAllByClient(Client client, Pageable pageable) throws Exception;

    RolesResponse findAllByClientAndIsDeleted(Client client, int isDeleted, Pageable pageable) throws Exception;

    RolesResponse findAll(Specification<Role> textInAllColumns, Pageable pageable) throws Exception;
}
