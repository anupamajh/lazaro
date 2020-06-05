package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.Client;
import com.carmel.common.dbservice.model.Organization;
import com.carmel.common.dbservice.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
        return organizationRepository.findAllByOrgNameAndIsDeletedIs(orgName, 0);
    }

    @Override
    public List<Organization> findAllByOrgNameAndIdIsNot(String orgName, String id) {
        return organizationRepository.findAllByOrgNameAndIdIsNotAndIsDeletedIs(orgName, id, 0);
    }

    @Override
    public Optional<Organization> findById(String id) {
        return organizationRepository.findById(id);
    }

    @Override
    public List<Organization> findAllByDeletionStatus(int isDeleted, Client client) {
        return organizationRepository.findAllByIsDeletedAndClient(isDeleted, client);
    }

    @Override
    public Page<Organization> findAll(Pageable pageable) {
        return organizationRepository.findAllByIsDeletedIs(0, pageable);
    }

    @Override
    public Page<Organization> findAllByClient(Client client, Pageable pageable) {
        return organizationRepository.findAllByClientAndIsDeleted(client, 0, pageable);
    }

    @Override
    public Page<Organization> findAll(Specification<Organization> textInAllColumns, Pageable pageable) {
        return organizationRepository.findAll(textInAllColumns, pageable);
    }
}
