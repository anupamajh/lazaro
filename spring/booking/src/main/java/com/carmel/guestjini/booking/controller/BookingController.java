package com.carmel.guestjini.booking.controller;

import com.carmel.guestjini.booking.common.BookingStatus;
import com.carmel.guestjini.booking.components.InventoryService;
import com.carmel.guestjini.booking.components.UserInformation;
import com.carmel.guestjini.booking.model.Booking;
import com.carmel.guestjini.booking.model.Principal.UserInfo;
import com.carmel.guestjini.booking.response.BookingResponse;
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
import java.util.*;

import static com.carmel.guestjini.booking.specifications.BookingSpecification.checkInventoryAvailability;
import static com.carmel.guestjini.booking.specifications.BookingSpecification.textInAllColumns;

@RestController
@RequestMapping(value = "/booking")
public class BookingController {

    Logger logger = LoggerFactory.getLogger(BookingSourceController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    BookingService bookingService;

    @Autowired
    InventoryService inventoryService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BookingResponse save(@Valid @RequestBody Booking booking) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingResponse bookingResponse = new BookingResponse();
        try {
            if (booking.getId() == null) {
                booking.setId("");
            }
            if (booking.getInventoryId() == null) {
                booking.setInventoryId("");
            }
            if (booking.getOrgId() == null || booking.getOrgId().isEmpty()) {
                booking.setOrgId(userInfo.getDefaultOrganization().getId());
            }
            if (booking.getId().equals("")) {
                booking.setCreatedBy(userInfo.getId());
                booking.setCreationTime(new Date());

            } else {
                booking.setLastModifiedBy(userInfo.getId());
                booking.setLastModifiedTime(new Date());
            }
            booking.setClientId(userInfo.getClient().getClientId());
            Booking existingBooking;
            if (booking.getId() != "") {
                Optional<Booking> optionalBooking = bookingService.findById(booking.getId());
                if (optionalBooking.isPresent()) {
                    existingBooking = optionalBooking.get();
                    booking.setReferenceNo(existingBooking.getReferenceNo());
                    booking.setBookingStatus(existingBooking.getBookingStatus());
                    booking.setPackageId(existingBooking.getPackageId());
                    booking.setRent(existingBooking.getRent());
                    booking.setRentUnit(existingBooking.getRentUnit());
                    booking.setPromoCode(existingBooking.getPromoCode());
                    booking.setDiscountValue(existingBooking.getDiscountValue());
                    booking.setDiscountIdentifier(existingBooking.getDiscountIdentifier());
                    booking.setDiscountValueIdentifier(existingBooking.getDiscountValueIdentifier());
                    booking.setInventoryId(existingBooking.getInventoryId());
                    booking.setProcessId(existingBooking.getProcessId());
                    booking.setApprovalStatus(existingBooking.getApprovalStatus());
                }
            } else {
                booking.setReferenceNo(String.valueOf(System.nanoTime()));
                booking.setBookingStatus(1);
            }
            bookingResponse.setBooking(bookingService.save(booking));
            bookingResponse.setSuccess(true);
            bookingResponse.setError("");


        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            bookingResponse.setSuccess(false);
            bookingResponse.setError(ex.getMessage());
        }
        return bookingResponse;
    }

    @RequestMapping(value = "/assign-inventory", method = RequestMethod.POST)
    public BookingResponse assignInventory(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingResponse bookingResponse = new BookingResponse();
        try {
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            String inventoryId = formData.get("inventoryId") == null ? null : String.valueOf(formData.get("inventoryId"));
            if (bookingId == null) {
                throw new Exception("Booking ID not received");
            }
            if (inventoryId == null) {
                throw new Exception("Inventory Id not received");
            }

            Optional<Booking> optionalBooking = bookingService.findById(bookingId);
            if (optionalBooking.isPresent()) {
                Booking booking = optionalBooking.get();
                String parentIds = inventoryService.getParentIds(inventoryId);
                List<String> inventoryIds = Arrays.asList(parentIds.split("\\s*,\\s*"));
                List<Booking> bookings = bookingService.findAll(
                        checkInventoryAvailability(inventoryIds, booking.getCheckInTime(),
                                booking.getCheckOutTime(), booking.getReferenceNo()));
                if (bookings.size() > 0) {
                    throw new Exception("Selected inventory not available for booking, Please select some other inventory");
                }
                booking.setInventoryId(inventoryId);
                bookingResponse.setBooking(bookingService.save(booking));
                bookingResponse.setSuccess(true);
                bookingResponse.setError("");
            } else {
                throw new Exception("Booking not found!!!");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            bookingResponse.setSuccess(false);
            bookingResponse.setError(ex.getMessage());
        }
        return bookingResponse;
    }

    @RequestMapping(value = "/apply-discount", method = RequestMethod.POST)
    public BookingResponse applyDiscount(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingResponse bookingResponse = new BookingResponse();
        try {
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            String strDiscountValue = formData.get("discountValue") == null ? null : String.valueOf(formData.get("discountValue"));
            String strDiscountValueIdentifier = formData.get("discountValueIdentifier") == null ? null : String.valueOf(formData.get("discountValueIdentifier"));
            String strDiscountIdentifier = formData.get("discountIdentifier") == null ? null : String.valueOf(formData.get("discountIdentifier"));

            if (bookingId == null) {
                throw new Exception("Booking ID not received");
            }
            if (strDiscountValue == null) {
                throw new Exception("Discount Value not received");
            }

            if (strDiscountValueIdentifier == null) {
                throw new Exception("Discount value type not received");
            }

            if (strDiscountIdentifier == null) {
                throw new Exception("Discount type not received");
            }

            Optional<Booking> optionalBooking = bookingService.findById(bookingId);
            if (optionalBooking.isPresent()) {
                Booking booking = optionalBooking.get();
                booking.setDiscountValue(Double.parseDouble(strDiscountValue));
                booking.setDiscountValueIdentifier(Integer.parseInt(strDiscountValueIdentifier));
                booking.setDiscountValue(Integer.parseInt(strDiscountValue));
                bookingResponse.setBooking(bookingService.save(booking));
                bookingResponse.setSuccess(true);
                bookingResponse.setError("");
            } else {
                throw new Exception("Booking not found!!!");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            bookingResponse.setSuccess(false);
            bookingResponse.setError(ex.getMessage());
        }
        return bookingResponse;
    }

    @RequestMapping(value = "/release-inventory", method = RequestMethod.POST)
    public BookingResponse releaseInventory(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingResponse bookingResponse = new BookingResponse();
        try {
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            if (bookingId == null) {
                throw new Exception("Booking ID not received");
            }
            Optional<Booking> optionalBooking = bookingService.findById(bookingId);
            if (optionalBooking.isPresent()) {
                Booking booking = optionalBooking.get();
                booking.setInventoryId(null);
                bookingResponse.setBooking(bookingService.save(booking));
                bookingResponse.setSuccess(true);
                bookingResponse.setError("");
            } else {
                throw new Exception("Booking not found!!!");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            bookingResponse.setSuccess(false);
            bookingResponse.setError(ex.getMessage());
        }
        return bookingResponse;
    }


    @RequestMapping(value = "/cancel-booking")
    public BookingResponse cancelBooking(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingResponse bookingResponse = new BookingResponse();
        try {
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            if (bookingId == null) {
                throw new Exception("Booking ID not received");
            }
            Optional<Booking> optionalBooking = bookingService.findById(bookingId);
            if (optionalBooking.isPresent()) {
                Booking booking = optionalBooking.get();
                booking.setBookingStatus(BookingStatus.CANCELLED.getValue());
                bookingResponse.setBooking(bookingService.save(booking));
                bookingResponse.setSuccess(true);
                bookingResponse.setError("");
            } else {
                throw new Exception("Booking not found!!!");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            bookingResponse.setSuccess(false);
            bookingResponse.setError(ex.getMessage());
        }
        return bookingResponse;
    }


    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public BookingResponse moveToTrash(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingResponse bookingResponse = new BookingResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Booking> optionalBooking = bookingService.findById(formData.get("id"));
            if (optionalBooking != null) {
                Booking booking = optionalBooking.get();
                booking.setIsDeleted(1);
                booking.setBookingStatus(BookingStatus.CANCELLED.getValue());
                booking.setDeletedBy(userInfo.getId());
                booking.setDeletedTime(new Date());
                bookingResponse.setSuccess(true);
                bookingResponse.setError("");
                bookingResponse.setBooking(bookingService.save(booking));
            } else {
                bookingResponse.setSuccess(false);
                bookingResponse.setError("Error occurred while moving inventory to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            bookingResponse.setSuccess(false);
            bookingResponse.setError(ex.getMessage());
        }
        return bookingResponse;
    }


    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public BookingResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingResponse bookingResponse = new BookingResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Booking> optionalBooking = bookingService.findById(formData.get("id"));
            if (optionalBooking.isPresent()) {
                Booking booking = optionalBooking.get();
                bookingResponse.setSuccess(true);
                bookingResponse.setError("");
                bookingResponse.setBooking(booking);
            } else {
                bookingResponse.setSuccess(false);
                bookingResponse.setError("Error occurred while fetching booking!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            bookingResponse.setSuccess(false);
            bookingResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return bookingResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public BookingResponse getDeleted() {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        BookingResponse bookingResponse = new BookingResponse();
        try {
            bookingResponse.setBookingList(bookingService.findAllByIsDeletedAndClientId(1, userInfo.getClient().getClientId()));
            bookingResponse.setSuccess(true);
            bookingResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            bookingResponse.setSuccess(true);
            bookingResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return bookingResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public BookingResponse getAll() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingResponse bookingResponse = new BookingResponse();
        try {
            bookingResponse.setBookingList(bookingService.findAllByIsDeletedAndClientId(0, userInfo.getClient().getClientId()));
            bookingResponse.setSuccess(true);
            bookingResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            bookingResponse.setSuccess(true);
            bookingResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return bookingResponse;
    }

    @RequestMapping(value = "/get-bookings", method = RequestMethod.POST)
    public BookingResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingResponse bookingResponse = new BookingResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("checkIn"));
            Page<Booking> page = bookingService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            bookingResponse.setTotalRecords(page.getTotalElements());
            bookingResponse.setTotalPages(page.getTotalPages());
            bookingResponse.setBookingList(page.getContent());
            bookingResponse.setCurrentRecords(bookingResponse.getBookingList().size());
            bookingResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            bookingResponse.setSuccess(false);
            bookingResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return bookingResponse;
    }

    @RequestMapping(value = "/search-bookings", method = RequestMethod.POST)
    public BookingResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingResponse bookingResponse = new BookingResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("checkIn"));
            Page<Booking> page;
            if (searchText == null) {
                page = bookingService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            } else {
                page = bookingService.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
            }
            bookingResponse.setTotalRecords(page.getTotalElements());
            bookingResponse.setTotalPages(page.getTotalPages());
            bookingResponse.setBookingList(page.getContent());
            bookingResponse.setCurrentRecords(bookingResponse.getBookingList().size());
            bookingResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            bookingResponse.setSuccess(false);
            bookingResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return bookingResponse;
    }

}
