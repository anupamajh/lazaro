package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.Role;
import com.carmel.common.dbservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> findAllByRoleNameAndOrgId(String roleName, String orgId) {
        return roleRepository.findAllByRoleNameAndOrgIdAndIsDeletedIs(roleName, orgId,0);
    }

    @Override
    public List<Role> findAllByRoleNameAndIdIsNotAndOrgIdIs(String roleName, String id, String orgId) {
        return roleRepository.findAllByRoleNameAndIdIsNotAndOrgIdIsAndIsDeletedIs(roleName, id, orgId,0);
    }

    @Override
    public List<Role> findAllByIsDeletedAndOrgId(int isDeleted, String orgId) {
        return roleRepository.findAllByIsDeletedAndOrgId(isDeleted, orgId);
    }

    @Override
    public Page<Role> findAllByOrOrgId(String orgId, Pageable pageable) {
        return roleRepository.findAllByOrOrgIdAndIsDeleted(orgId, 0, pageable);
    }

    @Override
    public Page<Role> findAllByRoleNameContainingAndOrgId(String roleName, String orgId, Pageable pageable) {
        return roleRepository.findAllByRoleNameContainingAndOrgIdAndIsDeleted(roleName, orgId, 0, pageable);
    }

    @Override
    public Page<Role> findAllByDescriptionContainingAndOrgId(String description, String orgId, Pageable pageable) {
        return roleRepository.findAllByDescriptionContainingAndOrgIdAndIsDeleted(description, orgId, 0, pageable);
    }

    @Override
    public Page<Role> findAllByOrgIdAndRoleNameContainingOrDescriptionContaining(String orgId, String roleName, String description, Pageable pageable) {
        return roleRepository.findAllByOrgIdAndRoleNameContainingOrDescriptionContainingAndIsDeleted(orgId, roleName, description, 0, pageable);
    }

    @Override
    public Optional<Role> findById(String id) {
        return roleRepository.findById(id);
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }
}
