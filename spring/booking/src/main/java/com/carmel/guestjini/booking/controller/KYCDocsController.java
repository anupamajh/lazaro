package com.carmel.guestjini.booking.controller;

import com.carmel.guestjini.booking.components.UserInformation;
import com.carmel.guestjini.booking.model.Booking;
import com.carmel.guestjini.booking.model.KYCDocs;
import com.carmel.guestjini.booking.model.Principal.UserInfo;
import com.carmel.guestjini.booking.response.KYCDocsResponse;
import com.carmel.guestjini.booking.service.BookingService;
import com.carmel.guestjini.booking.service.KYCDocsService;
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
import java.util.Map;
import java.util.Optional;

import static com.carmel.guestjini.booking.specifications.KYCDocsSpecification.textInAllColumns;

@RequestMapping(value = "/kyc-docs")
@RestController
public class KYCDocsController {
    Logger logger = LoggerFactory.getLogger(KYCDocsController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    KYCDocsService kycDocsService;

    @Autowired
    BookingService bookingService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public KYCDocsResponse save(@Valid @RequestBody KYCDocs kycDocs) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        KYCDocsResponse kycDocsResponse = new KYCDocsResponse();
        try {
            if (kycDocs.getId() == null) {
                kycDocs.setId("");
            }
            if (kycDocs.getOrgId() == null || kycDocs.getOrgId().isEmpty()) {
                if(userInfo.getDefaultOrganization()!=null) {
                    kycDocs.setOrgId(userInfo.getDefaultOrganization().getId());
                }
            }
            if (kycDocs.getId().equals("")) {
                kycDocs.setCreatedBy(userInfo.getId());
                kycDocs.setCreationTime(new Date());
            } else {
                kycDocs.setLastModifiedBy(userInfo.getId());
                kycDocs.setLastModifiedTime(new Date());
            }
            kycDocs.setClientId(userInfo.getClient().getClientId());
            kycDocsResponse.setKycDocs(kycDocsService.save(kycDocs));
            kycDocsResponse.setSuccess(true);
            kycDocsResponse.setError("");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kycDocsResponse.setSuccess(false);
            kycDocsResponse.setError(ex.getMessage());
        }
        return kycDocsResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public KYCDocsResponse moveToTrash(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        KYCDocsResponse kycDocsResponse = new KYCDocsResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<KYCDocs> optionalKYCDocs = kycDocsService.findById(formData.get("id"));
            if (optionalKYCDocs.isPresent()) {
                KYCDocs kycDocs = optionalKYCDocs.get();
                kycDocs.setIsDeleted(1);
                kycDocs.setDeletedBy(userInfo.getId());
                kycDocs.setDeletedTime(new Date());
                kycDocsResponse.setSuccess(true);
                kycDocsResponse.setError("");
                kycDocsResponse.setKycDocs(kycDocsService.save(kycDocs));
            } else {
                kycDocsResponse.setSuccess(false);
                kycDocsResponse.setError("Error occurred while moving KYC Docs to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kycDocsResponse.setSuccess(false);
            kycDocsResponse.setError(ex.getMessage());
        }
        return kycDocsResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public KYCDocsResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        KYCDocsResponse kycDocsResponse = new KYCDocsResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<KYCDocs> optionalKYCDocs = kycDocsService.findById(formData.get("id"));
            if (optionalKYCDocs.isPresent()) {
                KYCDocs kycDocs = optionalKYCDocs.get();
                kycDocsResponse.setSuccess(true);
                kycDocsResponse.setError("");
                kycDocsResponse.setKycDocs(kycDocs);
            } else {
                kycDocsResponse.setSuccess(false);
                kycDocsResponse.setError("Error occurred while Fetching KYC Docs!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kycDocsResponse.setSuccess(false);
            kycDocsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kycDocsResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public KYCDocsResponse getDeleted(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        KYCDocsResponse kycDocsResponse = new KYCDocsResponse();
        try {
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            Optional<Booking> optionalBooking = bookingService.findById(bookingId);
            optionalBooking.orElseThrow(() -> new Exception("Booking not found!!"));
            Booking booking = optionalBooking.get();
            kycDocsResponse.setKycDocsList(kycDocsService.findAllByIsDeletedAndClientIdAndBooking(1, userInfo.getClient().getClientId(), booking));
            kycDocsResponse.setSuccess(true);
            kycDocsResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kycDocsResponse.setSuccess(true);
            kycDocsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kycDocsResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public KYCDocsResponse getAll(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        KYCDocsResponse kycDocsResponse = new KYCDocsResponse();
        try {
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            Optional<Booking> optionalBooking = bookingService.findById(bookingId);
            optionalBooking.orElseThrow(() -> new Exception("Booking not found!!"));
            Booking booking = optionalBooking.get();
            kycDocsResponse.setKycDocsList(kycDocsService.findAllByIsDeletedAndClientIdAndBooking(0, userInfo.getClient().getClientId(), booking));
            kycDocsResponse.setSuccess(true);
            kycDocsResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kycDocsResponse.setSuccess(true);
            kycDocsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kycDocsResponse;
    }

    @RequestMapping(value = "/get-booking-source", method = RequestMethod.POST)
    public KYCDocsResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        KYCDocsResponse kycDocsResponse = new KYCDocsResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            Optional<Booking> optionalBooking = bookingService.findById(bookingId);
            optionalBooking.orElseThrow(() -> new Exception("Booking not found!!"));
            Booking booking = optionalBooking.get();
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("doc_title"));
            Page<KYCDocs> page = kycDocsService.findAllByClientIdAndIsDeletedAndBooking(userInfo.getClient().getClientId(), 0, booking, pageable);
            kycDocsResponse.setTotalRecords(page.getTotalElements());
            kycDocsResponse.setTotalPages(page.getTotalPages());
            kycDocsResponse.setKycDocsList(page.getContent());
            kycDocsResponse.setCurrentRecords(kycDocsResponse.getKycDocsList().size());
            kycDocsResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            kycDocsResponse.setSuccess(false);
            kycDocsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kycDocsResponse;
    }

    @RequestMapping(value = "/search-booking-source", method = RequestMethod.POST)
    public KYCDocsResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        KYCDocsResponse kycDocsResponse = new KYCDocsResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            Optional<Booking> optionalBooking = bookingService.findById(bookingId);
            optionalBooking.orElseThrow(() -> new Exception("Booking not found!!"));
            Booking booking = optionalBooking.get();
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("doc_title"));
            Page<KYCDocs> page;
            if (searchText == null) {
                page = kycDocsService.findAllByClientIdAndIsDeletedAndBooking(userInfo.getClient().getClientId(), 0, booking, pageable);
            } else {
                page = kycDocsService.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId(), booking), pageable);
            }
            kycDocsResponse.setTotalRecords(page.getTotalElements());
            kycDocsResponse.setTotalPages(page.getTotalPages());
            kycDocsResponse.setKycDocsList(page.getContent());
            kycDocsResponse.setCurrentRecords(kycDocsResponse.getKycDocsList().size());
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            kycDocsResponse.setSuccess(false);
            kycDocsResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kycDocsResponse;
    }
}
