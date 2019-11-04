package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role save(Role role);
    List<Role> findAllByRoleNameAndOrgId(String roleName, String orgId);
    List<Role> findAllByRoleNameAndIdIsNotAndOrgIdIs(String roleName, String id, String orgId);
    Optional<Role> findById(String id);
    Page<Role> findAll(Pageable pageable);
    List<Role> findAllByIsDeletedAndOrgId(int isDeleted, String orgId);
    Page<Role> findAllByOrOrgId(String orgId, Pageable pageable);
    Page<Role> findAllByRoleNameContainingAndOrgId(String roleName, String orgId, Pageable pageable);
    Page<Role> findAllByDescriptionContainingAndOrgId(String description, String orgId, Pageable pageable);
    Page<Role> findAllByOrgIdAndRoleNameContainingOrDescriptionContaining(String orgId, String roleName, String description, Pageable pageable);

}
