package com.carmel.common.dbservice.Base.Role.Service;

import com.carmel.common.dbservice.Base.Client.Model.Client;
import com.carmel.common.dbservice.Base.Organization.Service.OrganizationService;
import com.carmel.common.dbservice.Base.Role.Model.Role;
import com.carmel.common.dbservice.Base.Role.Repository.RoleRepository;
import com.carmel.common.dbservice.Base.Role.Response.RolesResponse;
import com.carmel.common.dbservice.common.Search.SearchBuilder;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.carmel.common.dbservice.Base.Role.Specification.RoleSpecification.textInAllColumns;

@Service
public class RoleServiceImpl implements RoleService {

    private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserInformation userInformation;

    @Autowired
    EntityManager entityManager;

    @Override
    public Role save(Role role) throws Exception {
        return roleRepository.save(role);
    }

    @Override
    public RolesResponse saveRole(Role role) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        RolesResponse rolesResponse = new RolesResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(role));
            if (checkDuplicate(role)) {
                rolesResponse.setRole(role);
                rolesResponse.setSuccess(false);
                rolesResponse.setError("Duplicate role name!");
            } else {
                if (role.getId() != null) {
                    if (!role.getId().trim().equals("")) {
                        role.setLastModifiedBy(userInfo.getId());
                        role.setLastModifiedTime(new Date());
                    } else {
                        role.setCreatedBy(userInfo.getId());
                        role.setCreationTime(new Date());
                    }
                } else {
                    role.setCreatedBy(userInfo.getId());
                    role.setCreationTime(new Date());
                }
                role.setClient(userInfo.getClient());
                rolesResponse.setRole(this.save(role));
                rolesResponse.setSuccess(true);
                rolesResponse.setError("");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return rolesResponse;
    }

    @Override
    public RolesResponse findAllByRoleNameAndClient(String roleName, Client client) throws Exception {
        return null;
    }

    @Override
    public RolesResponse findAllByRoleNameAndIdIsNotAndClient(String roleName, String id, Client client) throws Exception {
        return null;
    }

    @Override
    public RolesResponse findAllByIsDeletedAndClient(int isDeleted, Client client) throws Exception {
        return null;
    }

    @Override
    public Optional<Role> findById(String id) throws Exception {
        return roleRepository.findById(id);
    }

    @Override
    public RolesResponse moveToTrash(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        RolesResponse roleResponse = new RolesResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Role> optionalRole = roleRepository.findById(formData.get("id"));
            if (optionalRole != null) {
                Role role = optionalRole.get();
                role.setIsDeleted(1);
                role.setDeletedBy(userInfo.getId());
                role.setDeletedTime(new Date());
                roleResponse.setSuccess(true);
                roleResponse.setError("");
                roleResponse.setRole(this.save(role));
            } else {
                roleResponse.setSuccess(false);
                roleResponse.setError("Error occurred while moving role to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return roleResponse;
    }

    @Override
    public RolesResponse get(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        RolesResponse rolesResponse = new RolesResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Role> optionalRole = roleRepository.findById(formData.get("id"));
            if (optionalRole != null) {
                Role role = optionalRole.get();
                rolesResponse.setSuccess(true);
                rolesResponse.setError("");
                rolesResponse.setRole(role);
            } else {
                rolesResponse.setSuccess(false);
                rolesResponse.setError("Error occurred while moving organization to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return rolesResponse;
    }

    @Override
    public RolesResponse getDeleted() throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        RolesResponse rolesResponse = new RolesResponse();
        try {
            rolesResponse.setRoleList(roleRepository.findAllByIsDeletedAndClient(1, userInfo.getClient()));
            rolesResponse.setSuccess(true);
            rolesResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return rolesResponse;
    }

    @Override
    public RolesResponse getAll() throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        RolesResponse rolesResponse = new RolesResponse();
        try {
            rolesResponse.setRoleList(roleRepository.findAllByIsDeletedAndClient(0, userInfo.getClient()));
            rolesResponse.setSuccess(true);
            rolesResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return rolesResponse;
    }

    @Override
    public RolesResponse getPaginated(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        RolesResponse rolesResponse = new RolesResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("roleName"));
            Page<Role> page = roleRepository.findAllByClientAndIsDeleted(userInfo.getClient(), 0, pageable);
            rolesResponse.setTotalRecords(page.getTotalElements());
            rolesResponse.setTotalPages(page.getTotalPages());
            rolesResponse.setRoleList(page.getContent());
            rolesResponse.setCurrentRecords(rolesResponse.getRoleList().size());
            rolesResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return rolesResponse;
    }

    @Override
    public RolesResponse searchPaginated(Map<String, String> formData) throws Exception {
        logger.trace("Entering");
        UserInfo userInfo = userInformation.getUserInfo();
        RolesResponse rolesResponse = new RolesResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("roleName"));
            Page<Role> page;
            if (searchText == null) {
                page = roleRepository.findAllByClient(userInfo.getClient(), pageable);
            } else {
                page = roleRepository.findAll(textInAllColumns(searchText, userInfo.getClient()), pageable);
            }
            rolesResponse.setTotalRecords(page.getTotalElements());
            rolesResponse.setTotalPages(page.getTotalPages());
            rolesResponse.setRoleList(page.getContent());
            rolesResponse.setCurrentRecords(rolesResponse.getRoleList().size());
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return rolesResponse;
    }

    @Override
    public RolesResponse search(SearchRequest searchRequest) throws Exception {
        RolesResponse rolesResponse = new RolesResponse();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
            Root<Role> root = criteriaQuery.from(Role.class);
            criteriaQuery = SearchBuilder.buildSearch(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root,
                    Role.class,
                    searchRequest
            );
            long totalRecords = SearchBuilder.getTotalRecordCount(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root
            );
            TypedQuery<Role> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setFirstResult((searchRequest.getCurrentPage() - 1) * searchRequest.getPageSize());
            typedQuery.setMaxResults(searchRequest.getPageSize());
            List<Role> roleList = typedQuery.getResultList();
            rolesResponse.setCurrentRecords(roleList.size());
            rolesResponse.setTotalRecords(totalRecords);
            rolesResponse.setSuccess(true);
            rolesResponse.setError("");
            rolesResponse.setRoleList(roleList);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.toString(), ex);
            throw ex;
        }
        return rolesResponse;
    }

    @Override
    public RolesResponse findAllByClient(Client client, Pageable pageable) {
        return null;
    }

    @Override
    public RolesResponse findAllByClientAndIsDeleted(Client client, int isDeleted, Pageable pageable) {
        return null;
    }

    @Override
    public RolesResponse findAll(Specification<Role> textInAllColumns, Pageable pageable) {
        return null;
    }

    private boolean checkDuplicate(Role role) {
        List<Role> roleList;
        if (role.getId() == null) {
            role.setId("");
        }
        if (role.getId() == "") {
            roleList = roleRepository.findAllByRoleNameAndClient(role.getRoleName(), role.getClient());
        } else {
            roleList = roleRepository.findAllByRoleNameAndIdIsNotAndClient(role.getRoleName(), role.getId(), role.getClient());
        }
        if (roleList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
