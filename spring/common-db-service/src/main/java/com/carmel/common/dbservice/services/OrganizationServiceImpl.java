package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.Organization;
import com.carmel.common.dbservice.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public List<Organization> findAllByOrgName(String orgName) {
        return organizationRepository.findAllByOrgNameAndIsDeletedIs(orgName,0);
    }

    @Override
    public List<Organization> findAllByOrgNameAndIdIsNot(String orgName, String id) {
        return organizationRepository.findAllByOrgNameAndIdIsNotAndIsDeletedIs(orgName, id,0);
    }

    @Override
    public Optional<Organization> findById(String id) {
        return organizationRepository.findById(id);
    }

    @Override
    public List<Organization> findAllByDeletionStatus(int isDeleted) {
        return organizationRepository.findAllByIsDeleted(isDeleted);
    }

    @Override
    public Page<Organization> findAll(Pageable pageable) {
        return organizationRepository.findAllByIsDeletedIs(0,pageable);
    }

    @Override
    public Page<Organization> findAllByOrgNameContaining(String orgName, Pageable pageable) {
        return organizationRepository.findAllByOrgNameContainingAndIsDeleted(orgName, 0,pageable);
    }

    @Override
    public Page<Organization> findAllByDescriptionContaining(String description, Pageable pageable) {
        return organizationRepository.findAllByDescriptionContainingAndIsDeleted(description, 0,pageable);
    }

    @Override
    public Page<Organization> findAllByOrgNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String orgName, String description, Pageable pageable) {
        return organizationRepository.findAllByOrgNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndIsDeleted(orgName, description, 0,pageable);
    }
}
