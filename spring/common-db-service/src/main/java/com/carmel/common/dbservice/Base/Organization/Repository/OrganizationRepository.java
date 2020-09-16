package com.carmel.common.dbservice.Base.Organization.Repository;

import com.carmel.common.dbservice.Base.Client.Model.Client;
import com.carmel.common.dbservice.Base.Organization.Model.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, String> {
    List<Organization> findAllByOrgNameAndIsDeletedIs(String orgName, int isDeleted);

    List<Organization> findAllByOrgNameAndIdIsNotAndIsDeletedIs(String orgName, String id, int isDeleted);

    Page<Organization> findAllByIsDeletedIs(int isDeleted, Pageable pageable);

    List<Organization> findAllByIsDeletedAndClient(int isDeleted, Client client);

    List<Organization> findAllByOrgName(String orgName);

    List<Organization> findAllByOrgNameAndIdIsNot(String roleName, String id);

    Page<Organization> findAllByClientAndIsDeleted(Client client, int isDeleted, Pageable pageable);

    Page<Organization> findAll(Specification<Organization> textInAllColumns, Pageable pageable);

    Page<Organization> findAllByIsDeleted(Client client, Pageable pageable);
}
