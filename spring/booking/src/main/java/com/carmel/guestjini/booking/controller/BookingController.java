package com.carmel.guestjini.booking.controller;

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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.awt.print.Book;
import java.util.*;

import static com.carmel.guestjini.booking.specifications.BookingSpecification.checkInventoryAvailability;

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



}
