package com.carmel.common.dbservice.repository;

import com.carmel.common.dbservice.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {
    List<Role> findAllByRoleNameAndOrgIdAndIsDeletedIs(String roleName, String orgId, int isDeleted);

    List<Role> findAllByRoleNameAndIdIsNotAndOrgIdIsAndIsDeletedIs(String roleName, String id, String orgId, int isDeleted);

    List<Role> findAllByIsDeletedAndOrgId(int isDeleted, String orgId);

    Page<Role> findAllByOrOrgIdAndIsDeleted(String orgId, int isDeleted, Pageable pageable);

    Page<Role> findAllByRoleNameContainingAndOrgIdAndIsDeleted(String roleName, String orgId, int isDeleted, Pageable pageable);

    Page<Role> findAllByDescriptionContainingAndOrgIdAndIsDeleted(String description, String orgId, int isDeleted, Pageable pageable);

    Page<Role> findAllByOrgIdAndRoleNameContainingOrDescriptionContainingAndIsDeleted(String orgId, String roleName, String description, int isDeleted, Pageable pageable);
}
