package com.carmel.guestjini.booking.controller;

import com.carmel.guestjini.booking.components.UserInformation;
import com.carmel.guestjini.booking.model.Booking;
import com.carmel.guestjini.booking.model.KYC;
import com.carmel.guestjini.booking.model.Principal.UserInfo;
import com.carmel.guestjini.booking.response.KYCResponse;
import com.carmel.guestjini.booking.service.BookingService;
import com.carmel.guestjini.booking.service.KYCService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import static com.carmel.guestjini.booking.specifications.KYCSpecification.textInAllColumns;

@RequestMapping(value = "/kyc")
@RestController
public class KYCController {
    Logger logger = LoggerFactory.getLogger(KYCController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    KYCService kycService;

    @Autowired
    BookingService bookingService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public KYCResponse save(@Valid @RequestBody KYC kyc) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        KYCResponse kycResponse = new KYCResponse();
        try {
            if (kyc.getId() == null) {
                kyc.setId("");
            }
            if (kyc.getOrgId() == null || kyc.getOrgId().isEmpty()) {
                if(userInfo.getDefaultOrganization()!=null) {
                    kyc.setOrgId(userInfo.getDefaultOrganization().getId());
                }
            }
            if (kyc.getId().equals("")) {
                kyc.setCreatedBy(userInfo.getId());
                kyc.setCreationTime(new Date());
            } else {
                kyc.setLastModifiedBy(userInfo.getId());
                kyc.setLastModifiedTime(new Date());
            }
            kyc.setClientId(userInfo.getClient().getClientId());
            if(kyc.getBooking() == null){
                throw new Exception("Booking data not received");
            }
            if(kyc.getBooking().getId() == ""){
                kyc.getBooking().setId(null);
            }
            if(kyc.getBooking().getId() == null){
                throw new Exception("Booking data not received");
            }
            Optional<Booking> optionalBooking = bookingService.findById(kyc.getBooking().getId());
            optionalBooking.orElseThrow(()-> new Exception("Booking Not found"));
            kyc.setBooking(optionalBooking.get());
            kycResponse.setKyc(kycService.save(kyc));
            kycResponse.setSuccess(true);
            kycResponse.setError("");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kycResponse.setSuccess(false);
            kycResponse.setError(ex.getMessage());
        }
        return kycResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public KYCResponse moveToTrash(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        KYCResponse kycResponse = new KYCResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<KYC> optionalKYC = kycService.findById(formData.get("id"));
            if (optionalKYC.isPresent()) {
                KYC kyc = optionalKYC.get();
                kyc.setIsDeleted(1);
                kyc.setDeletedBy(userInfo.getId());
                kyc.setDeletedTime(new Date());
                kycResponse.setSuccess(true);
                kycResponse.setError("");
                kycResponse.setKyc(kycService.save(kyc));
            } else {
                kycResponse.setSuccess(false);
                kycResponse.setError("Error occurred while moving kyc to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kycResponse.setSuccess(false);
            kycResponse.setError(ex.getMessage());
        }
        return kycResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public KYCResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        KYCResponse kycResponse = new KYCResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<KYC> optionalKYC = kycService.findById(formData.get("id"));
            if (optionalKYC.isPresent()) {
                KYC kyc = optionalKYC.get();
                kycResponse.setSuccess(true);
                kycResponse.setError("");
                kycResponse.setKyc(kyc);
            } else {
                kycResponse.setSuccess(false);
                kycResponse.setError("Error occurred while Fetching kyc!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kycResponse.setSuccess(false);
            kycResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kycResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public KYCResponse getDeleted(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        KYCResponse kycResponse = new KYCResponse();
        try {
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            Optional<Booking> optionalBooking = bookingService.findById(bookingId);
            optionalBooking.orElseThrow(()-> new Exception("Booking not found!!"));
            Booking booking = optionalBooking.get();
            kycResponse.setKycList(kycService.findAllByIsDeletedAndClientIdAndBooking(1, userInfo.getClient().getClientId(),booking));
            kycResponse.setSuccess(true);
            kycResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kycResponse.setSuccess(true);
            kycResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kycResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public KYCResponse getAll(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        KYCResponse kycResponse = new KYCResponse();
        try {
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            Optional<Booking> optionalBooking = bookingService.findById(bookingId);
            optionalBooking.orElseThrow(()-> new Exception("Booking not found!!"));
            Booking booking = optionalBooking.get();
            kycResponse.setKycList(kycService.findAllByIsDeletedAndClientIdAndBooking(0, userInfo.getClient().getClientId(),booking));
            kycResponse.setSuccess(true);
            kycResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kycResponse.setSuccess(true);
            kycResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kycResponse;
    }

    @RequestMapping(value = "/get-kyc", method = RequestMethod.POST)
    public KYCResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        KYCResponse kycResponse = new KYCResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            Optional<Booking> optionalBooking = bookingService.findById(bookingId);
            optionalBooking.orElseThrow(()-> new Exception("Booking not found!!"));
            Booking booking = optionalBooking.get();
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("guestId"));
            Page<KYC> page = kycService.findAllByClientIdAndIsDeletedAndBooking(userInfo.getClient().getClientId(), 0,booking, pageable);
            kycResponse.setTotalRecords(page.getTotalElements());
            kycResponse.setTotalPages(page.getTotalPages());
            kycResponse.setKycList(page.getContent());
            kycResponse.setCurrentRecords(kycResponse.getKycList().size());
            kycResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            kycResponse.setSuccess(false);
            kycResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kycResponse;
    }

    @RequestMapping(value = "/search-kyc", method = RequestMethod.POST)
    public KYCResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        KYCResponse kycResponse = new KYCResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("guestId"));
            Page<KYC> page;
            Optional<Booking> optionalBooking = bookingService.findById(bookingId);
            optionalBooking.orElseThrow(()-> new Exception("Booking not found!!"));
            Booking booking = optionalBooking.get();
            if (searchText == null) {
                page = kycService.findAllByClientIdAndIsDeletedAndBooking(userInfo.getClient().getClientId(), 0,booking, pageable);
            } else {
                page = kycService.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId(),booking), pageable);
            }
            if(page == null){
                kycResponse.setTotalRecords(0);
                kycResponse.setTotalPages(0);
                kycResponse.setKycList(new ArrayList<>());
                kycResponse.setCurrentRecords(0);

            }else{
                kycResponse.setTotalRecords(page.getTotalElements());
                kycResponse.setTotalPages(page.getTotalPages());
                kycResponse.setKycList(page.getContent());
                kycResponse.setCurrentRecords(kycResponse.getKycList().size());

            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            kycResponse.setSuccess(false);
            kycResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kycResponse;
    }

}
