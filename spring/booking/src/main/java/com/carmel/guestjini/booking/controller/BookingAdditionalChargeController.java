package com.carmel.guestjini.booking.controller;

import com.carmel.guestjini.booking.components.UserInformation;
import com.carmel.guestjini.booking.model.Booking;
import com.carmel.guestjini.booking.model.BookingAdditionalCharge;
import com.carmel.guestjini.booking.model.Principal.UserInfo;
import com.carmel.guestjini.booking.response.BookingAdditionalChargeResponse;
import com.carmel.guestjini.booking.service.BookingAdditionalChargeService;
import com.carmel.guestjini.booking.service.BookingService;
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

import static com.carmel.guestjini.booking.specifications.BookingAdditionalChargeSpecification.textInAllColumns;

@RequestMapping(value = "/booking-additional-charges")
@RestController
public class BookingAdditionalChargeController {

    Logger logger = LoggerFactory.getLogger(KYCController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    BookingService bookingService;

    @Autowired
    BookingAdditionalChargeService bookingAdditionalChargeService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BookingAdditionalChargeResponse save(@Valid @RequestBody BookingAdditionalCharge bookingAdditionalCharge) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingAdditionalChargeResponse bookingAdditionalChargeResponse = new BookingAdditionalChargeResponse();
        try {
            if (bookingAdditionalCharge.getId() == null) {
                bookingAdditionalCharge.setId("");
            }
            if (bookingAdditionalCharge.getOrgId() == null || bookingAdditionalCharge.getOrgId().isEmpty()) {
                bookingAdditionalCharge.setOrgId(userInfo.getDefaultOrganization().getId());
            }
            if (bookingAdditionalCharge.getId().equals("")) {
                bookingAdditionalCharge.setCreatedBy(userInfo.getId());
                bookingAdditionalCharge.setCreationTime(new Date());
            } else {
                bookingAdditionalCharge.setLastModifiedBy(userInfo.getId());
                bookingAdditionalCharge.setLastModifiedTime(new Date());
            }
            bookingAdditionalCharge.setClientId(userInfo.getClient().getClientId());
            if(bookingAdditionalCharge.getBooking() == null){
                throw new Exception("Booking data not received");
            }
            if(bookingAdditionalCharge.getBooking().getId() == ""){
                bookingAdditionalCharge.getBooking().setId(null);
            }
            if(bookingAdditionalCharge.getBooking().getId() == null){
                throw new Exception("Booking data not received");
            }
            Optional<Booking> optionalBooking = bookingService.findById(bookingAdditionalCharge.getBooking().getId());
            optionalBooking.orElseThrow(()-> new Exception("Booking Not found"));
            bookingAdditionalCharge.setBooking(optionalBooking.get());
            bookingAdditionalChargeResponse.setBookingAdditionalCharge(bookingAdditionalChargeService
                    .save(bookingAdditionalCharge));
            bookingAdditionalChargeResponse.setSuccess(true);
            bookingAdditionalChargeResponse.setError("");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            bookingAdditionalChargeResponse.setSuccess(false);
            bookingAdditionalChargeResponse.setError(ex.getMessage());
        }
        return bookingAdditionalChargeResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public BookingAdditionalChargeResponse moveToTrash(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingAdditionalChargeResponse bookingAdditionalChargeResponse = new BookingAdditionalChargeResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<BookingAdditionalCharge> optionalBookingAdditionalCharge
                    = bookingAdditionalChargeService.findById(formData.get("id"));
            if (optionalBookingAdditionalCharge.isPresent()) {
                BookingAdditionalCharge bookingAdditionalCharge = optionalBookingAdditionalCharge.get();
                bookingAdditionalCharge.setIsDeleted(1);
                bookingAdditionalCharge.setDeletedBy(userInfo.getId());
                bookingAdditionalCharge.setDeletedTime(new Date());
                bookingAdditionalChargeResponse.setSuccess(true);
                bookingAdditionalChargeResponse.setError("");
                bookingAdditionalChargeResponse.setBookingAdditionalCharge(
                        bookingAdditionalChargeService.save(bookingAdditionalCharge));
            } else {
                bookingAdditionalChargeResponse.setSuccess(false);
                bookingAdditionalChargeResponse.setError(
                        "Error occurred while moving bookingAdditionalCharge to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            bookingAdditionalChargeResponse.setSuccess(false);
            bookingAdditionalChargeResponse.setError(ex.getMessage());
        }
        return bookingAdditionalChargeResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public BookingAdditionalChargeResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingAdditionalChargeResponse bookingAdditionalChargeResponse = new BookingAdditionalChargeResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<BookingAdditionalCharge> optionalBookingAdditionalCharge
                    = bookingAdditionalChargeService.findById(formData.get("id"));
            if (optionalBookingAdditionalCharge.isPresent()) {
                BookingAdditionalCharge bookingAdditionalCharge = optionalBookingAdditionalCharge.get();
                bookingAdditionalChargeResponse.setSuccess(true);
                bookingAdditionalChargeResponse.setError("");
                bookingAdditionalChargeResponse.setBookingAdditionalCharge(bookingAdditionalCharge);
            } else {
                bookingAdditionalChargeResponse.setSuccess(false);
                bookingAdditionalChargeResponse.setError(
                        "Error occurred while Fetching bookingAdditionalCharge!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            bookingAdditionalChargeResponse.setSuccess(false);
            bookingAdditionalChargeResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return bookingAdditionalChargeResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public BookingAdditionalChargeResponse getDeleted(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        BookingAdditionalChargeResponse bookingAdditionalChargeResponse = new BookingAdditionalChargeResponse();
        try {
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            Optional<Booking> optionalBooking = bookingService.findById(bookingId);
            optionalBooking.orElseThrow(()-> new Exception("Booking not found!!"));
            Booking booking = optionalBooking.get();
            bookingAdditionalChargeResponse.setBookingAdditionalChargeList(
                    bookingAdditionalChargeService.findAllByIsDeletedAndClientIdAndBooking(1,
                            userInfo.getClient().getClientId(),booking));
            bookingAdditionalChargeResponse.setSuccess(true);
            bookingAdditionalChargeResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            bookingAdditionalChargeResponse.setSuccess(true);
            bookingAdditionalChargeResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return bookingAdditionalChargeResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public BookingAdditionalChargeResponse getAll(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        BookingAdditionalChargeResponse bookingAdditionalChargeResponse = new BookingAdditionalChargeResponse();
        try {
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            Optional<Booking> optionalBooking = bookingService.findById(bookingId);
            optionalBooking.orElseThrow(()-> new Exception("Booking not found!!"));
            Booking booking = optionalBooking.get();
            bookingAdditionalChargeResponse.setBookingAdditionalChargeList(
                    bookingAdditionalChargeService.findAllByIsDeletedAndClientIdAndBooking(0,
                            userInfo.getClient().getClientId(),booking));
            bookingAdditionalChargeResponse.setSuccess(true);
            bookingAdditionalChargeResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            bookingAdditionalChargeResponse.setSuccess(true);
            bookingAdditionalChargeResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return bookingAdditionalChargeResponse;
    }

    @RequestMapping(value = "/get-booking-additional-charges", method = RequestMethod.POST)
    public BookingAdditionalChargeResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingAdditionalChargeResponse bookingAdditionalChargeResponse = new BookingAdditionalChargeResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            Optional<Booking> optionalBooking = bookingService.findById(bookingId);
            optionalBooking.orElseThrow(()-> new Exception("Booking not found!!"));
            Booking booking = optionalBooking.get();
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("guestId"));
            Page<BookingAdditionalCharge> page =
                    bookingAdditionalChargeService.findAllByClientIdAndIsDeletedAndBooking(userInfo.getClient()
                            .getClientId(), 0,booking, pageable);
            bookingAdditionalChargeResponse.setTotalRecords(page.getTotalElements());
            bookingAdditionalChargeResponse.setTotalPages(page.getTotalPages());
            bookingAdditionalChargeResponse.setBookingAdditionalChargeList(page.getContent());
            bookingAdditionalChargeResponse.setCurrentRecords(bookingAdditionalChargeResponse
                    .getBookingAdditionalChargeList().size());
            bookingAdditionalChargeResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            bookingAdditionalChargeResponse.setSuccess(false);
            bookingAdditionalChargeResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return bookingAdditionalChargeResponse;
    }

    @RequestMapping(value = "/search-booking-additional-charges", method = RequestMethod.POST)
    public BookingAdditionalChargeResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingAdditionalChargeResponse bookingAdditionalChargeResponse = new BookingAdditionalChargeResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("guestId"));
            Page<BookingAdditionalCharge> page;
            Optional<Booking> optionalBooking = bookingService.findById(bookingId);
            optionalBooking.orElseThrow(()-> new Exception("Booking not found!!"));
            Booking booking = optionalBooking.get();
            if (searchText == null) {
                page = bookingAdditionalChargeService.findAllByClientIdAndIsDeletedAndBooking(
                        userInfo.getClient().getClientId(), 0,booking, pageable);
            } else {
                page = bookingAdditionalChargeService.findAll(
                        textInAllColumns(searchText, userInfo.getClient().getClientId(),booking), pageable);
            }
            if(page == null){
                bookingAdditionalChargeResponse.setTotalRecords(0);
                bookingAdditionalChargeResponse.setTotalPages(0);
                bookingAdditionalChargeResponse.setBookingAdditionalChargeList(new ArrayList<>());
                bookingAdditionalChargeResponse.setCurrentRecords(0);

            }else{
                bookingAdditionalChargeResponse.setTotalRecords(page.getTotalElements());
                bookingAdditionalChargeResponse.setTotalPages(page.getTotalPages());
                bookingAdditionalChargeResponse.setBookingAdditionalChargeList(page.getContent());
                bookingAdditionalChargeResponse.setCurrentRecords(
                        bookingAdditionalChargeResponse.getBookingAdditionalChargeList().size());

            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            bookingAdditionalChargeResponse.setSuccess(false);
            bookingAdditionalChargeResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return bookingAdditionalChargeResponse;
    }

}
