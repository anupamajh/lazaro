package com.carmel.guestjini.service.controller.Accounts;

import com.carmel.guestjini.service.components.UserInformation;
import com.carmel.guestjini.service.model.Accounts.AccountReceipts;
import com.carmel.guestjini.service.model.Principal.UserInfo;
import com.carmel.guestjini.service.response.Accounts.AccountReceiptsResponse;
import com.carmel.guestjini.service.service.Accounts.AccountReceiptService;
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

import static com.carmel.guestjini.service.specification.Accounts.AccountReceiptSpecification.textInAllColumns;


@RestController
@RequestMapping(value = "/account-receipts")
public class AccountReceiptController {
    Logger logger = LoggerFactory.getLogger(AccountReceiptController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    AccountReceiptService accountReceiptService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AccountReceiptsResponse save(@Valid @RequestBody AccountReceipts accountReceipts) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountReceiptsResponse accountReceiptsResponse = new AccountReceiptsResponse();
        try {
            if (accountReceipts.getId() == null) {
                accountReceipts.setId("");
            }
            if (accountReceipts.getOrgId() == null || accountReceipts.getOrgId().isEmpty()) {
                if(userInfo.getDefaultOrganization()!=null) {
                    accountReceipts.setOrgId(userInfo.getDefaultOrganization().getId());
                }
            }
            if (accountReceipts.getId().equals("")) {
                accountReceipts.setCreatedBy(userInfo.getId());
                accountReceipts.setCreationTime(new Date());
            } else {
                accountReceipts.setLastModifiedBy(userInfo.getId());
                accountReceipts.setLastModifiedTime(new Date());
            }
            accountReceipts.setClientId(userInfo.getClient().getClientId());
            if (checkDuplicate(accountReceipts)) {
                accountReceiptsResponse.setAccountReceipts(accountReceipts);
                accountReceiptsResponse.setSuccess(false);
                accountReceiptsResponse.setError("Duplicate AccountReceipts name!");
            } else {
                accountReceiptsResponse.setAccountReceipts(accountReceiptService.save(accountReceipts));
                accountReceiptsResponse.setSuccess(true);
                accountReceiptsResponse.setError("");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            accountReceiptsResponse.setSuccess(false);
            accountReceiptsResponse.setError(ex.getMessage());
        }
        return accountReceiptsResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public AccountReceiptsResponse moveToTrash(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountReceiptsResponse accountReceiptsResponse = new AccountReceiptsResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<AccountReceipts> optionalAccountReceipts = accountReceiptService.findById(formData.get("id"));
            if (optionalAccountReceipts.isPresent()) {
                AccountReceipts accountReceipts = optionalAccountReceipts.get();
                accountReceipts.setIsDeleted(1);
                accountReceipts.setDeletedBy(userInfo.getId());
                accountReceipts.setDeletedTime(new Date());
                accountReceiptsResponse.setSuccess(true);
                accountReceiptsResponse.setError("");
                accountReceiptsResponse.setAccountReceipts(accountReceiptService.save(accountReceipts));
            } else {
                accountReceiptsResponse.setSuccess(false);
                accountReceiptsResponse.setError("Error occurred while moving accountReceipts to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            accountReceiptsResponse.setSuccess(false);
            accountReceiptsResponse.setError(ex.getMessage());
        }
        return accountReceiptsResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public AccountReceiptsResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountReceiptsResponse accountReceiptsResponse = new AccountReceiptsResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<AccountReceipts> optionalAccountReceipts = accountReceiptService.findById(formData.get("id"));
            if (optionalAccountReceipts.isPresent()) {
                AccountReceipts accountReceipts = optionalAccountReceipts.get();
                accountReceiptsResponse.setSuccess(true);
                accountReceiptsResponse.setError("");
                accountReceiptsResponse.setAccountReceipts(accountReceipts);
            } else {
                accountReceiptsResponse.setSuccess(false);
                accountReceiptsResponse.setError("Error occurred while Fetching accountReceipts!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            accountReceiptsResponse.setSuccess(false);
            accountReceiptsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return accountReceiptsResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public AccountReceiptsResponse getDeleted(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountReceiptsResponse accountReceiptsResponse = new AccountReceiptsResponse();
        try {
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            if (bookingId == null) {
                throw new Exception("Booking ID not received");
            }
            accountReceiptsResponse.setAccountReceiptsList(accountReceiptService.findAllByIsDeletedAndBookingId(1, bookingId));
            accountReceiptsResponse.setSuccess(true);
            accountReceiptsResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            accountReceiptsResponse.setSuccess(true);
            accountReceiptsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return accountReceiptsResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public AccountReceiptsResponse getAll(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountReceiptsResponse accountReceiptsResponse = new AccountReceiptsResponse();
        try {
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            if (bookingId == null) {
                throw new Exception("Booking ID not received");
            }
            accountReceiptsResponse.setAccountReceiptsList(accountReceiptService.findAllByIsDeletedAndBookingId(0, bookingId));
            accountReceiptsResponse.setSuccess(true);
            accountReceiptsResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            accountReceiptsResponse.setSuccess(true);
            accountReceiptsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return accountReceiptsResponse;
    }

    @RequestMapping(value = "/get-account-receipts", method = RequestMethod.POST)
    public AccountReceiptsResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountReceiptsResponse accountReceiptsResponse = new AccountReceiptsResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            if (bookingId == null) {
                throw new Exception("Booking ID not received");
            }
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("receiptDate"));
            Page<AccountReceipts> page = accountReceiptService.findAllByIsDeletedAndBookingId(0, bookingId, pageable);
            accountReceiptsResponse.setTotalRecords(page.getTotalElements());
            accountReceiptsResponse.setTotalPages(page.getTotalPages());
            accountReceiptsResponse.setAccountReceiptsList(page.getContent());
            accountReceiptsResponse.setCurrentRecords(accountReceiptsResponse.getAccountReceiptsList().size());
            accountReceiptsResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            accountReceiptsResponse.setSuccess(false);
            accountReceiptsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return accountReceiptsResponse;
    }

    @RequestMapping(value = "/search-account-receipts", method = RequestMethod.POST)
    public AccountReceiptsResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountReceiptsResponse accountReceiptsResponse = new AccountReceiptsResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            if (bookingId == null) {
                throw new Exception("Booking ID not received");
            }
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("receiptDate"));
            Page<AccountReceipts> page;
            if (searchText == null) {
                page = accountReceiptService.findAllByIsDeletedAndBookingId(0, bookingId, pageable);
            } else {
                page = accountReceiptService.findAll(textInAllColumns(searchText, bookingId), pageable);
            }
            accountReceiptsResponse.setTotalRecords(page.getTotalElements());
            accountReceiptsResponse.setTotalPages(page.getTotalPages());
            accountReceiptsResponse.setAccountReceiptsList(page.getContent());
            accountReceiptsResponse.setCurrentRecords(accountReceiptsResponse.getAccountReceiptsList().size());
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            accountReceiptsResponse.setSuccess(false);
            accountReceiptsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return accountReceiptsResponse;
    }

    @RequestMapping(value = "/update-guest-receipts", method = RequestMethod.POST)
    public AccountReceiptsResponse updateGuestReceipts(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountReceiptsResponse accountReceiptsResponse = new AccountReceiptsResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            String guestId = formData.get("guestId") == null ? null : String.valueOf(formData.get("guestId"));
            if (bookingId == null) {
                throw new Exception("Booking ID not received");
            }
            List<AccountReceipts> accountReceiptsList = accountReceiptService.findAllByIsDeletedAndBookingId(0, bookingId);
            for (AccountReceipts accountReceipts : accountReceiptsList) {
                accountReceipts.setGuestId(guestId);
                accountReceiptService.save(accountReceipts);
            }
            accountReceiptsResponse.setAccountReceiptsList(accountReceiptService.findAllByIsDeletedAndBookingId(0, bookingId));
            accountReceiptsResponse.setSuccess(true);
            accountReceiptsResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            accountReceiptsResponse.setSuccess(false);
            accountReceiptsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return accountReceiptsResponse;

    }


    private boolean checkDuplicate(AccountReceipts accountReceipts) {
        return false;
    }


}
