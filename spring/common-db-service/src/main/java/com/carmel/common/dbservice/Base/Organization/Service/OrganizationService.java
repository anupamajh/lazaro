package com.carmel.common.dbservice.Base.Organization.Service;

import com.carmel.common.dbservice.Base.Client.Model.Client;
import com.carmel.common.dbservice.Base.Organization.Model.Organization;
import com.carmel.common.dbservice.Base.Organization.Responce.OrganizationResponse;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;
import java.util.Optional;

public interface OrganizationService {

    Organization save(Organization organization) throws Exception;

    Optional<Organization> findById(String id) throws Exception;

    OrganizationResponse findAllByOrgName(String orgName) throws Exception;

    OrganizationResponse  findAllByOrgNameAndIdIsNot(String orgName, String id) throws Exception;

    OrganizationResponse  findAllByDeletionStatus(int isDeleted, Client client) throws Exception;

    OrganizationResponse  findAll(Pageable pageable) throws Exception;

    OrganizationResponse  findAllByClient(Client client, Pageable pageable) throws Exception;

    OrganizationResponse  findAll(Specification<Organization> textInAllColumns, Pageable pageable) throws Exception;

    OrganizationResponse saveOrgnization(Organization organization) throws Exception;

    OrganizationResponse  moveToTrash(Map<String, String> formData) throws Exception;

    OrganizationResponse  get(Map<String, String> formData) throws Exception;

    OrganizationResponse getAll() throws Exception;

    OrganizationResponse getDeleted() throws Exception;

    OrganizationResponse getPaginated(Map<String, String> formData) throws Exception;

    OrganizationResponse searchPaginated(Map<String, String> formData) throws Exception;

    OrganizationResponse search(SearchRequest searchRequest) throws Exception;

}
