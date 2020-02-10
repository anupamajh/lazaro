package com.carmel.common.dbservice.controller;

import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.AccountHead;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.response.AccountHeadResponse;
import com.carmel.common.dbservice.services.AccountHeadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.carmel.common.dbservice.specifications.AccountHeadSpecification.textInAllColumns;

@RestController
@RequestMapping(value = "/account-head")
public class AccountHeadController {
    Logger logger = LoggerFactory.getLogger(AccountHeadController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    AccountHeadService accountHeadService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AccountHeadResponse save(@Valid @RequestBody AccountHead accountHead) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountHeadResponse accountHeadResponse = new AccountHeadResponse();
        try {
            if (accountHead.getId() == null) {
                accountHead.setId("");
            }
            if(accountHead.getOrgId() == null || accountHead.getOrgId().isEmpty()){
                if(userInfo.getDefaultOrganization() != null) {
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
            accountHeadResponse.setSuccess(false);
            accountHeadResponse.setError(ex.getMessage());
        }
        return accountHeadResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public AccountHeadResponse moveToTrash(@RequestBody Map<String, String> formData) {
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
            accountHeadResponse.setSuccess(false);
            accountHeadResponse.setError(ex.getMessage());
        }
        return accountHeadResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public AccountHeadResponse get(@RequestBody Map<String, String> formData) {
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
            accountHeadResponse.setSuccess(false);
            accountHeadResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return accountHeadResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public AccountHeadResponse getDeleted() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountHeadResponse accountHeadResponse = new AccountHeadResponse();
        try {
            accountHeadResponse.setAccountHeadList(accountHeadService.findAllByIsDeletedAndClientId(1, userInfo.getClient().getClientId()));
            accountHeadResponse.setSuccess(true);
            accountHeadResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            accountHeadResponse.setSuccess(true);
            accountHeadResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return accountHeadResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public AccountHeadResponse getAll() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountHeadResponse accountHeadResponse = new AccountHeadResponse();
        try {
            accountHeadResponse.setAccountHeadList(accountHeadService.findAllByIsDeletedAndClientId(0, userInfo.getClient().getClientId()));
            accountHeadResponse.setSuccess(true);
            accountHeadResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            accountHeadResponse.setSuccess(true);
            accountHeadResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return accountHeadResponse;
    }

    @RequestMapping(value = "/get-account-heads", method = RequestMethod.POST)
    public AccountHeadResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountHeadResponse accountHeadResponse = new AccountHeadResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<AccountHead> page = accountHeadService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            accountHeadResponse.setTotalRecords(page.getTotalElements());
            accountHeadResponse.setTotalPages(page.getTotalPages());
            accountHeadResponse.setAccountHeadList(page.getContent());
            accountHeadResponse.setCurrentRecords(accountHeadResponse.getAccountHeadList().size());
            accountHeadResponse.setSuccess(true);
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

    @RequestMapping(value = "/search-account-heads", method = RequestMethod.POST)
    public AccountHeadResponse searchPaginated(@RequestBody Map<String, String> formData) {
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
                page = accountHeadService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(),0,pageable);
            } else {
                page = accountHeadService.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
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

    private boolean checkDuplicate(AccountHead accountHead) {
        List<AccountHead> accountHeadList;
        if (accountHead.getId().equals("")) {
            accountHeadList = accountHeadService.findAllByClientIdAndTitle(accountHead.getClientId(), accountHead.getTitle());
        } else {
            accountHeadList = accountHeadService.findAllByClientIdAndTitleAndIdIsNot(
                    accountHead.getClientId(), accountHead.getTitle(), accountHead.getId());
        }
        if (accountHeadList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }



}
