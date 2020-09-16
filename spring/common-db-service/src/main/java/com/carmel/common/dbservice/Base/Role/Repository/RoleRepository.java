package com.carmel.common.dbservice.Base.Role.Repository;

import com.carmel.common.dbservice.Base.Client.Model.Client;
import com.carmel.common.dbservice.Base.Role.Model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {

    List<Role> findAllByRoleNameAndClient(String roleName, Client client);

    List<Role> findAllByRoleNameAndIdIsNotAndClient(String roleName, String id, Client client);

    List<Role> findAllByIsDeletedAndClient(int isDeleted, Client client);

    Page<Role> findAllByClient(Client client, Pageable pageable);

    Page<Role> findAllByClientAndIsDeleted(Client client, int isDeleted, Pageable pageable);

    Page<Role> findAll(Specification<Role> textInAllColumns, Pageable pageable);
}
