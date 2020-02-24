package com.carmel.guestjini.accounts.controller;

import com.carmel.guestjini.accounts.common.AccountTicketStatus;
import com.carmel.guestjini.accounts.components.UserInformation;
import com.carmel.guestjini.accounts.model.AccountTicket;
import com.carmel.guestjini.accounts.model.DTO.Guest;
import com.carmel.guestjini.accounts.model.Principal.UserInfo;
import com.carmel.guestjini.accounts.response.AccountTicketResponse;
import com.carmel.guestjini.accounts.service.AccountTicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

import static com.carmel.guestjini.accounts.specification.AccountTicketSpecification.textInAllColumns;

@RestController
@RequestMapping(value = "/account-tickets")
public class AccountTicketController {
    Logger logger = LoggerFactory.getLogger(AccountTicketController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    AccountTicketService accountTicketService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AccountTicketResponse save(@Valid @RequestBody AccountTicket accountTicket) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountTicketResponse accountTicketResponse = new AccountTicketResponse();
        try {
            if (accountTicket.getId() == null) {
                accountTicket.setId("");
            }
            if (accountTicket.getOrgId() == null || accountTicket.getOrgId().isEmpty()) {
                if(userInfo.getDefaultOrganization()!=null) {
                    accountTicket.setOrgId(userInfo.getDefaultOrganization().getId());
                }
            }
            if (accountTicket.getId().equals("")) {
                accountTicket.setCreatedBy(userInfo.getId());
                accountTicket.setCreationTime(new Date());
                accountTicket.setTicketNumber(String.valueOf(System.nanoTime()));
                accountTicket.setTicketStatus(AccountTicketStatus.ACTIVE.getValue());
            } else {
                accountTicket.setLastModifiedBy(userInfo.getId());
                accountTicket.setLastModifiedTime(new Date());
            }
            accountTicket.setClientId(userInfo.getClient().getClientId());
            if (checkDuplicate(accountTicket)) {
                accountTicketResponse.setAccountTicket(accountTicket);
                accountTicketResponse.setSuccess(false);
                accountTicketResponse.setError("Duplicate AccountTicket name!");
            } else {
                accountTicketResponse.setAccountTicket(accountTicketService.save(accountTicket));
                accountTicketResponse.setSuccess(true);
                accountTicketResponse.setError("");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            accountTicketResponse.setSuccess(false);
            accountTicketResponse.setError(ex.getMessage());
        }
        return accountTicketResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public AccountTicketResponse moveToTrash(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountTicketResponse accountTicketResponse = new AccountTicketResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<AccountTicket> optionalAccountTicket = accountTicketService.findById(formData.get("id"));
            if (optionalAccountTicket.isPresent()) {
                AccountTicket accountTicket = optionalAccountTicket.get();
                accountTicket.setIsDeleted(1);
                accountTicket.setDeletedBy(userInfo.getId());
                accountTicket.setDeletedTime(new Date());
                accountTicketResponse.setSuccess(true);
                accountTicketResponse.setError("");
                accountTicketResponse.setAccountTicket(accountTicketService.save(accountTicket));
            } else {
                accountTicketResponse.setSuccess(false);
                accountTicketResponse.setError("Error occurred while moving accountTicket to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            accountTicketResponse.setSuccess(false);
            accountTicketResponse.setError(ex.getMessage());
        }
        return accountTicketResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public AccountTicketResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountTicketResponse accountTicketResponse = new AccountTicketResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<AccountTicket> optionalAccountTicket = accountTicketService.findById(formData.get("id"));
            if (optionalAccountTicket.isPresent()) {
                AccountTicket accountTicket = optionalAccountTicket.get();
                accountTicketResponse.setSuccess(true);
                accountTicketResponse.setError("");
                accountTicketResponse.setAccountTicket(accountTicket);
            } else {
                accountTicketResponse.setSuccess(false);
                accountTicketResponse.setError("Error occurred while Fetching accountTicket!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            accountTicketResponse.setSuccess(false);
            accountTicketResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return accountTicketResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public AccountTicketResponse getDeleted(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountTicketResponse accountTicketResponse = new AccountTicketResponse();
        try {
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            if (bookingId == null) {
                throw new Exception("Booking ID not received");
            }
            accountTicketResponse.setAccountTicketList(accountTicketService.findAllByIsDeletedAndBookingId(1, bookingId));
            accountTicketResponse.setSuccess(true);
            accountTicketResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            accountTicketResponse.setSuccess(true);
            accountTicketResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return accountTicketResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public AccountTicketResponse getAll(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountTicketResponse accountTicketResponse = new AccountTicketResponse();
        try {
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            if (bookingId == null) {
                throw new Exception("Booking ID not received");
            }
            accountTicketResponse.setAccountTicketList(accountTicketService.findAllByIsDeletedAndBookingId(0, bookingId));
            accountTicketResponse.setSuccess(true);
            accountTicketResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            accountTicketResponse.setSuccess(true);
            accountTicketResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return accountTicketResponse;
    }

    @RequestMapping(value = "/get-account-tickets", method = RequestMethod.POST)
    public AccountTicketResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountTicketResponse accountTicketResponse = new AccountTicketResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            if (bookingId == null) {
                throw new Exception("Booking ID not received");
            }
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("ticketDate"));
            Page<AccountTicket> page = accountTicketService.findAllByIsDeletedAndBookingId(0, bookingId, pageable);
            accountTicketResponse.setTotalRecords(page.getTotalElements());
            accountTicketResponse.setTotalPages(page.getTotalPages());
            accountTicketResponse.setAccountTicketList(page.getContent());
            accountTicketResponse.setCurrentRecords(accountTicketResponse.getAccountTicketList().size());
            accountTicketResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            accountTicketResponse.setSuccess(false);
            accountTicketResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return accountTicketResponse;
    }

    @RequestMapping(value = "/search-account-tickets", method = RequestMethod.POST)
    public AccountTicketResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        AccountTicketResponse accountTicketResponse = new AccountTicketResponse();
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
            Page<AccountTicket> page;
            if (searchText == null) {
                page = accountTicketService.findAllByIsDeletedAndBookingId(0, bookingId, pageable);
            } else {
                page = accountTicketService.findAll(textInAllColumns(searchText, bookingId), pageable);
            }
            accountTicketResponse.setTotalRecords(page.getTotalElements());
            accountTicketResponse.setTotalPages(page.getTotalPages());
            accountTicketResponse.setAccountTicketList(page.getContent());
            accountTicketResponse.setCurrentRecords(accountTicketResponse.getAccountTicketList().size());
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            accountTicketResponse.setSuccess(false);
            accountTicketResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return accountTicketResponse;
    }

    private boolean checkDuplicate(AccountTicket accountTicket) {
        return false;
    }

    @RequestMapping(value = "/delete-account-tickets-by-guest", method = RequestMethod.POST)
    public AccountTicketResponse deleteTicketsByGuestId(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        AccountTicketResponse accountTicketResponse = new AccountTicketResponse();
        try {
            String guestId = formData.get("guestId") == null ? null : String.valueOf(formData.get("guestId"));
            List<AccountTicket> accountTickets = accountTicketService.findAllByGuestId(guestId);
            accountTicketService.deleteAll(accountTickets);
            accountTicketResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            accountTicketResponse.setSuccess(false);
            accountTicketResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return accountTicketResponse;
    }

    @RequestMapping(value = "/generate-day-invoices")
    @Transactional(rollbackFor = Exception.class)
    public AccountTicketResponse generateDayInvoices(@RequestBody Guest guest) {
        logger.trace("Entering");
        AccountTicketResponse accountTicketResponse = new AccountTicketResponse();
        try {
            if (guest == null) {
                throw new Exception("Guest not received");
            }
            accountTicketService.generateDayInvoices(guest);
            accountTicketResponse.setSuccess(true);
            accountTicketResponse.setError("");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            accountTicketResponse.setSuccess(false);
            accountTicketResponse.setError(ex.getMessage());
        }
        return accountTicketResponse;
    }

    @RequestMapping(value = "/generate-month-invoices")
    @Transactional(rollbackFor = Exception.class)
    public AccountTicketResponse generateMonthInvoices(@RequestBody HashMap<String, String> formData) {
        logger.trace("Entering");
        AccountTicketResponse accountTicketResponse = new AccountTicketResponse();
        try {
            String guestId = formData.get("guestId");
            int month = (formData.get("month") == null)? 0 : Integer.parseInt(formData.get("month"));
            int year = (formData.get("year") == null)? 0 : Integer.parseInt(formData.get("year"));
             accountTicketService.generateMonthInvoices(guestId, month, year);
           // accountTicketResponse.setAccountTicketList(accountTickets);
            accountTicketResponse.setSuccess(true);
            accountTicketResponse.setError("");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            accountTicketResponse.setSuccess(false);
            accountTicketResponse.setError(ex.getMessage());
        }
        return accountTicketResponse;
    }
}
