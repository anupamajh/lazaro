package com.carmel.common.dbservice.Base.AccountHead.Controller;

import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.Base.AccountHead.Model.AccountHead;
import com.carmel.common.dbservice.Base.AccountHead.Response.AccountHeadResponse;
import com.carmel.common.dbservice.Base.AccountHead.Service.AccountHeadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/account-head")
public class AccountHeadController {
    Logger logger = LoggerFactory.getLogger(AccountHeadController.class);

    @Autowired
    AccountHeadService accountHeadService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AccountHeadResponse save(@Valid @RequestBody AccountHead accountHead) {
        logger.trace("Entering");
        AccountHeadResponse accountHeadResponse = new AccountHeadResponse();
        try{
            accountHeadResponse = accountHeadService.saveAccountHead(accountHead);
        }catch (Exception ex){
            accountHeadResponse.setSuccess(true);
            accountHeadResponse.setError(ex.getMessage());
        }
        return accountHeadResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public AccountHeadResponse moveToTrash(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        AccountHeadResponse accountHeadResponse = new AccountHeadResponse();
        try{
          accountHeadResponse = accountHeadService.moveToTrash(formData);
        }catch (Exception ex){
            accountHeadResponse.setSuccess(true);
            accountHeadResponse.setError(ex.getMessage());
        }
        return accountHeadResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public AccountHeadResponse get(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        AccountHeadResponse accountHeadResponse = new AccountHeadResponse();
        try{
            accountHeadResponse = accountHeadService.get(formData);
        }catch (Exception ex){
            accountHeadResponse.setSuccess(true);
            accountHeadResponse.setError(ex.getMessage());
        }
        return accountHeadResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public AccountHeadResponse getDeleted() {
        logger.trace("Entering");
        AccountHeadResponse accountHeadResponse = new AccountHeadResponse();
        try{
            accountHeadResponse = accountHeadService.getDeleted();
        }catch (Exception ex){
            accountHeadResponse.setSuccess(true);
            accountHeadResponse.setError(ex.getMessage());
        }
        return accountHeadResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public AccountHeadResponse getAll() {
        logger.trace("Entering");
        AccountHeadResponse accountHeadResponse = new AccountHeadResponse();
        try{
            accountHeadResponse = accountHeadService.getAll();
        }catch (Exception ex){
            accountHeadResponse.setSuccess(true);
            accountHeadResponse.setError(ex.getMessage());
        }
        return accountHeadResponse;
    }

    @RequestMapping(value = "/get-account-heads", method = RequestMethod.POST)
    public AccountHeadResponse getPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        AccountHeadResponse accountHeadResponse = new AccountHeadResponse();
        try{
            accountHeadResponse = accountHeadService.getPaginated(formData);
        }catch (Exception ex){
            accountHeadResponse.setSuccess(true);
            accountHeadResponse.setError(ex.getMessage());
        }
        return accountHeadResponse;
    }

    @RequestMapping(value = "/search-account-heads", method = RequestMethod.POST)
    public AccountHeadResponse searchPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        AccountHeadResponse accountHeadResponse = new AccountHeadResponse();
        try{
            accountHeadResponse = accountHeadService.searchPaginated(formData);
        }catch (Exception ex){
            accountHeadResponse.setSuccess(true);
            accountHeadResponse.setError(ex.getMessage());
        }
        return accountHeadResponse;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public AccountHeadResponse search(@RequestBody SearchRequest searchRequest) {
        logger.trace("Entering");
        AccountHeadResponse accountHeadResponse = new AccountHeadResponse();
        try{
            accountHeadResponse = accountHeadService.search(searchRequest);
        }catch (Exception ex){
            accountHeadResponse.setSuccess(true);
            accountHeadResponse.setError(ex.getMessage());
        }
        return accountHeadResponse;
    }


}
