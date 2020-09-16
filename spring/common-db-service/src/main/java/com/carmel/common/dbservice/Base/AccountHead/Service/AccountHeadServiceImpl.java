package com.carmel.common.dbservice.Base.AccountHead.Service;

import com.carmel.common.dbservice.Base.AccountHead.Controller.AccountHeadController;
import com.carmel.common.dbservice.Base.AccountHead.Model.AccountHead;
import com.carmel.common.dbservice.Base.AccountHead.Responce.AccountHeadResponse;
import com.carmel.common.dbservice.Base.AccountHead.Respository.AccountHeadRepository;
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
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.carmel.common.dbservice.Base.AccountHead.Specification.AccountHeadSpecification.textInAllColumns;

@Component
public class AccountHeadServiceImpl implements AccountHeadService {

   private Logger logger = LoggerFactory.getLogger(AccountHeadServiceImpl.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    AccountHeadService accountHeadService;

    @Autowired
    EntityManager entityManager;

    @Autowired
    AccountHeadRepository accountHeadRepository;

    @Override
    public AccountHeadResponse saveAccountHead(AccountHead accountHead) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountHeadResponse accountHeadResponse = new AccountHeadResponse();
        try {
            if (accountHead.getId() == null) {
                accountHead.setId("");
            }
            if (accountHead.getOrgId() == null || accountHead.getOrgId().isEmpty()) {
                if (userInfo.getDefaultOrganization() != null) {
                    accountHead.setOrgId(userInfo.getDefaultOrganization().getId());
                }
            }
            if (accountHead.getId().equals("")) {
                accountHead.setCreatedBy(userInfo.getId());
                accountHead.setCreationTime(new Date());
            } else {
                accountHead.setLastModifiedBy(userInfo.getId());
                accountHead.setLastModifiedTime(new Date());
            }
            accountHead.setClientId(userInfo.getClient().getClientId());
            if (checkDuplicate(accountHead)) {
                accountHeadResponse.setAccountHead(accountHead);
                accountHeadResponse.setSuccess(false);
                accountHeadResponse.setError("Duplicate account head Name name!");
            } else {
                accountHeadResponse.setAccountHead(accountHeadService.save(accountHead));
                accountHeadResponse.setSuccess(true);
                accountHeadResponse.setError("");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        return accountHeadResponse;
    }

    @Override
    public AccountHeadResponse moveToTrash(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountHeadResponse accountHeadResponse = new AccountHeadResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<AccountHead> optionalAccountHead = accountHeadService.findById(formData.get("id"));
            if (optionalAccountHead.isPresent()) {
                AccountHead accountHead = optionalAccountHead.get();
                accountHead.setIsDeleted(1);
                accountHead.setDeletedBy(userInfo.getId());
                accountHead.setDeletedTime(new Date());
                accountHeadResponse.setSuccess(true);
                accountHeadResponse.setError("");
                accountHeadResponse.setAccountHead(accountHeadService.save(accountHead));
            } else {
                accountHeadResponse.setSuccess(false);
                accountHeadResponse.setError("Error occurred while moving account head to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        return accountHeadResponse;
    }

    @Override
    public AccountHeadResponse get(Map<String, String> formData) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountHeadResponse accountHeadResponse = new AccountHeadResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<AccountHead> optionalAccountHead = accountHeadService.findById(formData.get("id"));
            if (optionalAccountHead.isPresent()) {
                AccountHead accountHead = optionalAccountHead.get();
                accountHeadResponse.setSuccess(true);
                accountHeadResponse.setError("");
                accountHeadResponse.setAccountHead(accountHead);
            } else {
                accountHeadResponse.setSuccess(false);
                accountHeadResponse.setError("Error occurred while Fetching account head!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
           throw ex;
        }
        logger.trace("Exiting");
        return accountHeadResponse;
    }

    @Override
    public AccountHeadResponse getDeleted() throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountHeadResponse accountHeadResponse = new AccountHeadResponse();
        try {
            accountHeadResponse.setAccountHeadList(accountHeadRepository.findAllByIsDeletedAndClientId(1, userInfo.getClient().getClientId()));
            accountHeadResponse.setSuccess(true);
            accountHeadResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
           throw ex;
        }
        logger.trace("Exiting");
        return accountHeadResponse;
    }

    @Override
    public AccountHeadResponse getAll() throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountHeadResponse accountHeadResponse = new AccountHeadResponse();
        try {
            accountHeadResponse.setAccountHeadList(accountHeadRepository.findAllByIsDeletedAndClientId(0, userInfo.getClient().getClientId()));
            accountHeadResponse.setSuccess(true);
            accountHeadResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
         throw ex;
        }
        logger.trace("Exiting");
        return accountHeadResponse;
    }

    @Override
    public AccountHeadResponse getPaginated(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountHeadResponse accountHeadResponse = new AccountHeadResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<AccountHead> page = accountHeadRepository.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            accountHeadResponse.setTotalRecords(page.getTotalElements());
            accountHeadResponse.setTotalPages(page.getTotalPages());
            accountHeadResponse.setAccountHeadList(page.getContent());
            accountHeadResponse.setCurrentRecords(accountHeadResponse.getAccountHeadList().size());
            accountHeadResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
           throw ex;
        }
        logger.trace("Exiting");
        return accountHeadResponse;
    }

    @Override
    public AccountHeadResponse searchPaginated(Map<String, String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountHeadResponse accountHeadResponse = new AccountHeadResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<AccountHead> page;
            if (searchText == null) {
                page = accountHeadRepository.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            } else {
                page = accountHeadRepository.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
            }
            accountHeadResponse.setTotalRecords(page.getTotalElements());
            accountHeadResponse.setTotalPages(page.getTotalPages());
            accountHeadResponse.setAccountHeadList(page.getContent());
            accountHeadResponse.setCurrentRecords(accountHeadResponse.getAccountHeadList().size());
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            accountHeadResponse.setSuccess(false);
            accountHeadResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return accountHeadResponse;
    }

    @Override
    public AccountHeadResponse search(SearchRequest searchRequest) throws Exception {
        AccountHeadResponse accountHeadResponse = new AccountHeadResponse();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<AccountHead> criteriaQuery = criteriaBuilder.createQuery(AccountHead.class);
            Root<AccountHead> root = criteriaQuery.from(AccountHead.class);
            criteriaQuery = SearchBuilder.buildSearch(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root,
                    AccountHead.class,
                    searchRequest
            );
            long totalRecords = SearchBuilder.getTotalRecordCount(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root
            );
            TypedQuery<AccountHead> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setFirstResult((searchRequest.getCurrentPage() - 1) * searchRequest.getPageSize());
            typedQuery.setMaxResults(searchRequest.getPageSize());
            List<AccountHead> accountHeadList = typedQuery.getResultList();
            accountHeadResponse.setCurrentRecords(accountHeadList.size());
            accountHeadResponse.setTotalRecords(totalRecords);
            accountHeadResponse.setSuccess(true);
            accountHeadResponse.setError("");
            accountHeadResponse.setAccountHeadList(accountHeadList);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.toString(), ex);
            accountHeadResponse.setSuccess(false);
            accountHeadResponse.setError(ex.getMessage());
        }
        return accountHeadResponse;
    }
    private boolean checkDuplicate(AccountHead accountHead) {
        List<AccountHead> accountHeadList;
        if (accountHead.getId().equals("")) {
            accountHeadList = accountHeadRepository.findAllByClientIdAndTitle(accountHead.getClientId(), accountHead.getTitle());
        } else {
            accountHeadList = accountHeadRepository.findAllByClientIdAndTitleAndIdIsNot(
                    accountHead.getClientId(), accountHead.getTitle(), accountHead.getId());
        }
        if (accountHeadList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public AccountHead save(AccountHead accountHead) {
        return accountHeadRepository.save(accountHead);
    }

    @Override
    public AccountHeadResponse findAllByClientIdAndTitle(String clientId, String title) {
        return (AccountHeadResponse) accountHeadRepository.findAllByClientIdAndTitle(clientId, title);
    }

    @Override
    public AccountHeadResponse findAllByClientIdAndTitleAndIdIsNot(String clientId, String title, String id) {
        return (AccountHeadResponse) accountHeadRepository.findAllByClientIdAndTitleAndIdIsNot(clientId, title, id);
    }

    @Override
    public Optional<AccountHead> findById(String id) {
        return accountHeadRepository.findById(id);
    }

    @Override
    public AccountHeadResponse findAllByIsDeletedAndClientId(int isDeleted, String clientId) {
        return (AccountHeadResponse) accountHeadRepository.findAllByIsDeletedAndClientId(isDeleted, clientId);
    }

    @Override
    public AccountHeadResponse findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return (AccountHeadResponse) accountHeadRepository.findAllByClientIdAndIsDeleted(clientId, isDeleted, pageable);
    }

    @Override
    public AccountHeadResponse findAll(Specification<AccountHead> textInAllColumns, Pageable pageable) {
        return (AccountHeadResponse) accountHeadRepository.findAll(textInAllColumns, pageable);
    }
}
