package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.Client;
import com.carmel.common.dbservice.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role save(Role role);

    List<Role> findAllByRoleNameAndClient(String roleName, Client client);

    List<Role> findAllByRoleNameAndIdIsNotAndClient(String roleName, String id, Client client);

    List<Role> findAllByIsDeletedAndClient(int isDeleted, Client client);

    Optional<Role> findById(String id);

    Page<Role> findAllByClient(Client client, Pageable pageable);

    Page<Role> findAllByClientAndIsDeleted(Client client, int isDeleted, Pageable pageable);

    Page<Role> findAll(Specification<Role> textInAllColumns, Pageable pageable);
}
