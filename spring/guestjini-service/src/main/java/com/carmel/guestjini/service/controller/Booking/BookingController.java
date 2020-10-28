package com.carmel.guestjini.service.controller.Booking;

import com.carmel.guestjini.service.common.Booking.BookingStatus;
import com.carmel.guestjini.service.common.DateUtil;
import com.carmel.guestjini.service.components.*;
import com.carmel.guestjini.service.config.CarmelConfig;
import com.carmel.guestjini.service.model.Accounts.AccountReceipts;
import com.carmel.guestjini.service.model.Booking.Booking;
import com.carmel.guestjini.service.model.Booking.BookingAdditionalCharge;
import com.carmel.guestjini.service.model.Booking.Guest;
import com.carmel.guestjini.service.model.DTO.Common.ApplicationConstantDTO;
import com.carmel.guestjini.service.model.Inventory.Package;
import com.carmel.guestjini.service.model.Inventory.PackageCharge;
import com.carmel.guestjini.service.model.Principal.UserInfo;
import com.carmel.guestjini.service.request.Booking.BookingRequest;
import com.carmel.guestjini.service.response.Accounts.AccountReceiptsResponse;
import com.carmel.guestjini.service.response.Booking.BookingResponse;
import com.carmel.guestjini.service.response.Common.UserResponse;
import com.carmel.guestjini.service.service.Accounts.AccountReceiptService;
import com.carmel.guestjini.service.service.Booking.BookingAdditionalChargeService;
import com.carmel.guestjini.service.service.Booking.BookingService;
import com.carmel.guestjini.service.service.Inventory.InventoryService;
import com.carmel.guestjini.service.service.Inventory.PackageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui.ava.html.image.generator.HtmlImageGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

import static com.carmel.guestjini.service.specification.Booking.BookingSpecification.checkInventoryAvailability;
import static com.carmel.guestjini.service.specification.Booking.BookingSpecification.textInAllColumns;


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

    @Autowired
    PackageService packageService;

    @Autowired
    BookingAdditionalChargeService bookingAdditionalChargeService;

    @Autowired
    AccountReceiptService accountReceiptsService;

    @Autowired
    UserService userService;

    @Autowired
    ApplicationConstantsService applicationConstantsService;

    @Autowired
    CarmelConfig carmelConfig;

    @Autowired
    MailClient mailClient;

    @Autowired
    AccessCardImageBuilder accessCardImageBuilder;

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
//            if (booking.getOrgId() == null || booking.getOrgId().isEmpty()) {
//                if(userInfo.getDefaultOrganization()!=null) {
//                    booking.setOrgId(userInfo.getDefaultOrganization().getId());
//                }
//            }
//            if (booking.getId().equals("")) {
//                booking.setCreatedBy(userInfo.getId());
//                booking.setCreationTime(new Date());
//
//            } else {
//                booking.setLastModifiedBy(userInfo.getId());
//                booking.setLastModifiedTime(new Date());
//            }
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
                booking.setLastModifiedBy(userInfo.getId());
                booking.setLastModifiedTime(new Date());
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

    @RequestMapping(value = "/get-available-inventory", method = RequestMethod.POST)
    public BookingResponse getAvailableInventory(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingResponse bookingResponse = new BookingResponse();
        try {
            String inventoryId = formData.get("inventoryId") == null ? null : String.valueOf(formData.get("inventoryId"));
            String strCheckInDate = formData.get("checkInDate") == null ? null : String.valueOf(formData.get("checkInDate"));
            String strCheckoutDate = formData.get("checkOutDate") == null ? null : String.valueOf(formData.get("checkOutDate"));

            Date checkInDate = DateUtil.convertToDate(strCheckInDate);
            Date checkOutDate = DateUtil.convertToDate(strCheckoutDate);

            if (inventoryId == null) {
                throw new Exception("Inventory Id not received");
            }

            String parentIds = inventoryService.getParentIds(inventoryId);
            List<String> inventoryIds = Arrays.asList(parentIds.split("\\s*,\\s*"));
            List<Booking> bookings = bookingService.findAll(
                    checkInventoryAvailability(inventoryIds, checkInDate,
                            checkOutDate));
            if (bookings.size() > 0) {
                throw new Exception("Selected inventory not available for booking, Please select some other inventory");
            } else {
                bookingResponse.setInventoryId(inventoryId);
                bookingResponse.setSuccess(true);
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
                booking.setLastModifiedBy(userInfo.getId());
                booking.setLastModifiedTime(new Date());
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
                booking.setLastModifiedBy(userInfo.getId());
                booking.setLastModifiedTime(new Date());
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
    @Transactional(rollbackFor = Exception.class)
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
                booking.setBookingStatus(BookingStatus.CANCELLED);
                booking.setLastModifiedBy(userInfo.getId());
                booking.setLastModifiedTime(new Date());
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
                booking.setBookingStatus(BookingStatus.CANCELLED);
                booking.setDeletedBy(userInfo.getId());
                booking.setDeletedTime(new Date());
                bookingResponse.setSuccess(true);
                bookingResponse.setError("");
                booking.setLastModifiedBy(userInfo.getId());
                booking.setLastModifiedTime(new Date());
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

    @RequestMapping(value = "/get-booking-by-mobile-number", method = RequestMethod.POST)
    public BookingResponse getBookingByMobileNumber(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingResponse bookingResponse = new BookingResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Booking> optionalBooking = bookingService.findByPhoneAndBookingStatus(formData.get("mobileNumber"), 1);
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

    @RequestMapping(value = "/remove-package")
    @Transactional(rollbackFor = Exception.class)
    public BookingResponse removePackage(@RequestBody Map<String, String> formData) {
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
                if (booking.getPackageId() == null) {
                    booking.setPackageId("");
                }
                if (booking.getPackageId().equals("")) {
                    throw new Exception("Package is not assigned to this booking");
                }
                String packageId = booking.getPackageId();
                booking.setPackageId(null);
                booking.setRent(0.0);
                booking.setRentUnit(0);
                bookingResponse.setBooking(bookingService.save(booking));
                List<BookingAdditionalCharge> bookingAdditionalCharges = bookingAdditionalChargeService.findAllByPackageId(packageId);
                bookingAdditionalChargeService.deleteAll(bookingAdditionalCharges);
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

    @RequestMapping(value = "/add-package")
    @Transactional(rollbackFor = Exception.class)
    public BookingResponse addPackage(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingResponse bookingResponse = new BookingResponse();
        try {
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            String packageId = formData.get("packageId") == null ? null : String.valueOf(formData.get("packageId"));
            if (bookingId == null) {
                throw new Exception("Booking ID not received");
            }
            if (packageId == null) {
                throw new Exception("Package Id not received");
            }
            Optional<Booking> optionalBooking = bookingService.findById(bookingId);
            if (optionalBooking.isPresent()) {
                Booking booking = optionalBooking.get();
                if (booking.getPackageId() == null) {
                    booking.setPackageId("");
                }
                if (!booking.getPackageId().equals("")) {
                    throw new Exception("Package is already assigned to booking, Please remove package and the assign new package");
                }
                Package aPackage = packageService.getPackage(packageId);
                booking.setPackageId(aPackage.getId());
                booking.setRent(aPackage.getRent());
                booking.setRentUnit(aPackage.getRentCycle());
                booking.setLastModifiedBy(userInfo.getId());
                booking.setLastModifiedTime(new Date());
                bookingResponse.setBooking(bookingService.save(booking));
                BookingAdditionalCharge bookingAdditionalCharge;
                for (PackageCharge packageCharge : aPackage.getPackageCharges()) {
                    bookingAdditionalCharge = new BookingAdditionalCharge(booking, packageCharge);
                    if (userInfo.getDefaultOrganization() != null) {
                        bookingAdditionalCharge.setOrgId(userInfo.getDefaultOrganization().getId());
                    }
                    bookingAdditionalCharge.setCreatedBy(userInfo.getId());
                    bookingAdditionalCharge.setCreationTime(new Date());
                    bookingAdditionalCharge.setClientId(userInfo.getClient().getClientId());
                    bookingAdditionalCharge.setPackageId(packageId);
                    bookingAdditionalChargeService.save(bookingAdditionalCharge);
                }
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

    @RequestMapping(value = "/checkin")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public BookingResponse checkIn(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingResponse bookingResponse = new BookingResponse();
        try {
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            Date actualCheckInDate = formData.get("actual_checkin") != null ? DateUtil.convertToDate(formData.get("actual_checkin")) : null;
            if (bookingId == null) {
                throw new Exception("Booking ID not received");
            }
            Optional<Booking> optionalBooking = bookingService.findById(bookingId);
            if (optionalBooking.isPresent()) {
                Booking booking = optionalBooking.get();
                if (actualCheckInDate == null) {
                    actualCheckInDate = booking.getCheckInTime();
                }

                booking.setLastModifiedBy(userInfo.getId());
                booking.setLastModifiedTime(new Date());
                Guest guest = bookingService.doCheckIn(booking, booking.getCheckInTime());
                bookingResponse.setBooking(booking);
                bookingResponse.setGuest(guest);
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

    @RequestMapping(value = "/undo-checkin")
    @Transactional(rollbackFor = Exception.class)
    public BookingResponse undoCheckIn(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingResponse bookingResponse = new BookingResponse();
        try {
            String bookingId = formData.get("bookingId") == null ? null : String.valueOf(formData.get("bookingId"));
            Date actualCheckInDate = formData.get("actual_checkin") != null ? DateUtil.convertToDate(formData.get("actual_checkin")) : null;
            if (bookingId == null) {
                throw new Exception("Booking ID not received");
            }
            Optional<Booking> optionalBooking = bookingService.findById(bookingId);
            if (optionalBooking.isPresent()) {
                Booking booking = optionalBooking.get();
                booking.setLastModifiedBy(userInfo.getId());
                booking.setLastModifiedTime(new Date());
                booking = bookingService.doCancelCheckIn(booking);
                bookingResponse.setBooking(booking);
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

    @RequestMapping(value = "/save-payment-receipts")
    @Transactional(rollbackFor = Exception.class)
    public AccountReceiptsResponse savePaymentReceipts(@RequestBody AccountReceipts accountReceipts) {
        AccountReceiptsResponse accountReceiptsResponse;
        try {
            accountReceiptsResponse = accountReceiptsService.saveAccountReceiept(accountReceipts);
        } catch (Exception ex) {
            accountReceiptsResponse = new AccountReceiptsResponse();
            logger.error(ex.getMessage(), ex);
            accountReceiptsResponse.setSuccess(false);
            accountReceiptsResponse.setError(ex.getMessage());
        }
        return accountReceiptsResponse;
    }


    @RequestMapping(value = "/create-booking")
    @Transactional(rollbackFor = Exception.class)
    public BookingResponse createBooking(
            @RequestBody BookingRequest bookingRequest
    ) {
        UserInfo userInfo = userInformation.getUserInfo();
        BookingResponse bookingResponse = new BookingResponse();
        try {
            Optional<Booking> optionalBooking = bookingService.findByPhoneAndBookingStatus(bookingRequest.getMobileNumber(), BookingStatus.ACTIVE);
            if (optionalBooking.isPresent()) {
                bookingResponse.setSuccess(false);
                bookingResponse.setError("We already have an active booking with the mobile number! Kindly contact administrator");
            } else {
                Booking booking = new Booking();
                booking.setReferenceNo(String.valueOf(System.nanoTime()));
                booking.setBookingStatus(1);
                booking.setProcessId(bookingRequest.getOrderId());
                booking.setPhone(bookingRequest.getMobileNumber());
                booking.setFullName(bookingRequest.getFullName());
                booking.setEmail(bookingRequest.getEmailAddress());
                booking.setGender(bookingRequest.getGender());
                booking.setCheckIn(DateUtil.convertToDate(bookingRequest.getCheckinDate()));
                booking.setCheckOut(DateUtil.convertToDate(bookingRequest.getCheckoutDate()));
                booking.setCheckInTime(DateUtil.convertToDate(bookingRequest.getCheckinDate()));
                booking.setCheckOutTime(DateUtil.convertToDate(bookingRequest.getCheckoutDate()));
                booking = bookingService.save(booking);
                ApplicationConstantDTO applicationConstantDTO =
                        applicationConstantsService.getApplicationConstant(bookingRequest.getStayPackage());
                String packageId = applicationConstantDTO.getValue();
                Optional<Package> optionalPackage = packageService
                        .findById(packageId);
                if (optionalPackage.isPresent()) {
                    Package aPackage = optionalPackage.get();
                    booking.setPackageId(aPackage.getId());
                    booking.setRent(aPackage.getRent());
                    booking.setRentUnit(aPackage.getRentCycle());
                    booking = bookingService.save(booking);
                } else {
                    throw new Exception("Selected package is not available!");
                }
                String parentIds = inventoryService.getParentIds(bookingRequest.getSelectedInventory());
                List<String> inventoryIds = Arrays.asList(parentIds.split("\\s*,\\s*"));
                List<Booking> bookings = bookingService.findAll(
                        checkInventoryAvailability(inventoryIds, booking.getCheckInTime(),
                                booking.getCheckOutTime(), booking.getReferenceNo()));
                if (bookings.size() > 0) {
                    throw new Exception("Selected inventory not available for booking, Please select some other inventory");
                }
                booking.setInventoryId(bookingRequest.getSelectedInventory());
                booking = bookingService.save(booking);
                UserResponse userResponse = userService.signUpGuest(bookingRequest);
                if (!userResponse.isSuccess()) {
                    throw new Exception(userResponse.getError());
                }
                bookingRequest.setActivationLink(
                        carmelConfig.getBaseUrl() + "/user/activate-account/" + userResponse.getUser().getId()
                );
                this.sendActivationEmail(bookingRequest);
                bookingResponse.setSuccess(true);
                bookingResponse.setBooking(booking);
            }
        } catch (Exception ex) {
            bookingResponse.setSuccess(false);
            bookingResponse.setError(ex.getMessage());
        }
        return bookingResponse;
    }
    private boolean sendActivationEmail(BookingRequest bookingRequest) {
        mailClient.setGuestSignUpEmail(bookingRequest);
        return true;
    }

    @RequestMapping(value = "/generate-id-card")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String generateIDCard(){
        String content = accessCardImageBuilder.buildAccessCardImage();
        HtmlImageGenerator htmlImageGenerator = new HtmlImageGenerator();
        htmlImageGenerator.loadHtml(content);
        htmlImageGenerator.saveAsImage(carmelConfig.getImageSavePath() + "test.png");
        return content;
    }


}
