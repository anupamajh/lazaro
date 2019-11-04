package com.carmel.common.dbservice.repository;

import com.carmel.common.dbservice.model.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, String> {
    List<Organization> findAllByOrgNameAndIsDeletedIs(String orgName, int isDeleted);

    List<Organization> findAllByOrgNameAndIdIsNotAndIsDeletedIs(String orgName, String id, int isDeleted);

    List<Organization> findAllByIsDeleted(int isDeleted);

    Page<Organization> findAllByIsDeletedIs(int isDeleted, Pageable pageable);

    Page<Organization> findAllByOrgNameContainingAndIsDeleted(String orgName, int isDeleted, Pageable pageable);

    Page<Organization> findAllByDescriptionContainingAndIsDeleted(String description, int isDeleted, Pageable pageable);

    Page<Organization> findAllByOrgNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndIsDeleted(String orgName, String description, int isDeleted, Pageable pageable);
}
