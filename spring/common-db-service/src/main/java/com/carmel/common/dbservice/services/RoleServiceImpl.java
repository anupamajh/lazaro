package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.Client;
import com.carmel.common.dbservice.model.Role;
import com.carmel.common.dbservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public List<Role> findAllByRoleNameAndClient(String roleName, Client client) {
        return roleRepository.findAllByRoleNameAndClient(roleName, client);
    }

    @Override
    public List<Role> findAllByRoleNameAndIdIsNotAndClient(String roleName, String id, Client client) {
        return roleRepository.findAllByRoleNameAndIdIsNotAndClient(roleName, id, client);
    }

    @Override
    public List<Role> findAllByIsDeletedAndClient(int isDeleted, Client client) {
        return roleRepository.findAllByIsDeletedAndClient(isDeleted, client);
    }

    @Override
    public Optional<Role> findById(String id) {
        return roleRepository.findById(id);
    }

    @Override
    public Page<Role> findAllByClient(Client client, Pageable pageable) {
        return roleRepository.findAllByClient(client, pageable);
    }

    @Override
    public Page<Role> findAllByClientAndIsDeleted(Client client, int isDeleted, Pageable pageable) {
        return roleRepository.findAllByClientAndIsDeleted(client, isDeleted, pageable);
    }

    @Override
    public Page<Role> findAll(Specification<Role> textInAllColumns, Pageable pageable) {
        return roleRepository.findAll(textInAllColumns, pageable);
    }
}
