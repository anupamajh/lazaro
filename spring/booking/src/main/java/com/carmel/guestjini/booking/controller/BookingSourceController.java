package com.carmel.guestjini.booking.controller;

import com.carmel.guestjini.booking.components.UserInformation;
import com.carmel.guestjini.booking.model.BookingSource;
import com.carmel.guestjini.booking.model.Principal.UserInfo;
import com.carmel.guestjini.booking.response.BookingSourceResponse;
import com.carmel.guestjini.booking.service.BookingSourceService;
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

import static com.carmel.guestjini.booking.specifications.BookingSourceSpecification.textInAllColumns;

@RestController
@RequestMapping(value = "/booking-source")
public class BookingSourceController {
    Logger logger = LoggerFactory.getLogger(BookingSourceController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    BookingSourceService bookingSourceService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BookingSourceResponse save(@Valid @RequestBody BookingSource bookingSource) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingSourceResponse bookingSourceResponse = new BookingSourceResponse();
        try {
            if (bookingSource.getId() == null) {
                bookingSource.setId("");
            }
            if(bookingSource.getOrgId() == null || bookingSource.getOrgId().isEmpty()){
                bookingSource.setOrgId(userInfo.getDefaultOrganization().getId());
            }
            if (bookingSource.getId().equals("")) {
                bookingSource.setCreatedBy(userInfo.getId());
                bookingSource.setCreationTime(new Date());
            } else {
                bookingSource.setLastModifiedBy(userInfo.getId());
                bookingSource.setLastModifiedTime(new Date());
            }
            bookingSource.setClientId(userInfo.getClient().getClientId());
            if (checkDuplicate(bookingSource)) {
                bookingSourceResponse.setBookingSource(bookingSource);
                bookingSourceResponse.setSuccess(false);
                bookingSourceResponse.setError("Duplicate BookingSource name!");
            } else {
                bookingSourceResponse.setBookingSource(bookingSourceService.save(bookingSource));
                bookingSourceResponse.setSuccess(true);
                bookingSourceResponse.setError("");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            bookingSourceResponse.setSuccess(false);
            bookingSourceResponse.setError(ex.getMessage());
        }
        return bookingSourceResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public BookingSourceResponse moveToTrash(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingSourceResponse bookingSourceResponse = new BookingSourceResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<BookingSource> optionalBookingSource = bookingSourceService.findById(formData.get("id"));
            if (optionalBookingSource.isPresent()) {
                BookingSource bookingSource = optionalBookingSource.get();
                bookingSource.setIsDeleted(1);
                bookingSource.setDeletedBy(userInfo.getId());
                bookingSource.setDeletedTime(new Date());
                bookingSourceResponse.setSuccess(true);
                bookingSourceResponse.setError("");
                bookingSourceResponse.setBookingSource(bookingSourceService.save(bookingSource));
            } else {
                bookingSourceResponse.setSuccess(false);
                bookingSourceResponse.setError("Error occurred while moving bookingSource to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            bookingSourceResponse.setSuccess(false);
            bookingSourceResponse.setError(ex.getMessage());
        }
        return bookingSourceResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public BookingSourceResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingSourceResponse bookingSourceResponse = new BookingSourceResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<BookingSource> optionalBookingSource = bookingSourceService.findById(formData.get("id"));
            if (optionalBookingSource.isPresent()) {
                BookingSource bookingSource = optionalBookingSource.get();
                bookingSourceResponse.setSuccess(true);
                bookingSourceResponse.setError("");
                bookingSourceResponse.setBookingSource(bookingSource);
            } else {
                bookingSourceResponse.setSuccess(false);
                bookingSourceResponse.setError("Error occurred while Fetching bookingSource!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            bookingSourceResponse.setSuccess(false);
            bookingSourceResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return bookingSourceResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public BookingSourceResponse getDeleted() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingSourceResponse bookingSourceResponse = new BookingSourceResponse();
        try {
            bookingSourceResponse.setBookingSourceList(bookingSourceService.findAllByIsDeletedAndClientId(1, userInfo.getClient().getClientId()));
            bookingSourceResponse.setSuccess(true);
            bookingSourceResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            bookingSourceResponse.setSuccess(true);
            bookingSourceResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return bookingSourceResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public BookingSourceResponse getAll() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingSourceResponse bookingSourceResponse = new BookingSourceResponse();
        try {
            bookingSourceResponse.setBookingSourceList(bookingSourceService.findAllByIsDeletedAndClientId(0, userInfo.getClient().getClientId()));
            bookingSourceResponse.setSuccess(true);
            bookingSourceResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            bookingSourceResponse.setSuccess(true);
            bookingSourceResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return bookingSourceResponse;
    }

    @RequestMapping(value = "/get-booking-source", method = RequestMethod.POST)
    public BookingSourceResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingSourceResponse bookingSourceResponse = new BookingSourceResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<BookingSource> page = bookingSourceService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            bookingSourceResponse.setTotalRecords(page.getTotalElements());
            bookingSourceResponse.setTotalPages(page.getTotalPages());
            bookingSourceResponse.setBookingSourceList(page.getContent());
            bookingSourceResponse.setCurrentRecords(bookingSourceResponse.getBookingSourceList().size());
            bookingSourceResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            bookingSourceResponse.setSuccess(false);
            bookingSourceResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return bookingSourceResponse;
    }

    @RequestMapping(value = "/search-booking-source", method = RequestMethod.POST)
    public BookingSourceResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        BookingSourceResponse bookingSourceResponse = new BookingSourceResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<BookingSource> page;
            if (searchText == null) {
                page = bookingSourceService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(),0,pageable);
            } else {
                page = bookingSourceService.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
            }
            bookingSourceResponse.setTotalRecords(page.getTotalElements());
            bookingSourceResponse.setTotalPages(page.getTotalPages());
            bookingSourceResponse.setBookingSourceList(page.getContent());
            bookingSourceResponse.setCurrentRecords(bookingSourceResponse.getBookingSourceList().size());
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            bookingSourceResponse.setSuccess(false);
            bookingSourceResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return bookingSourceResponse;
    }

    private boolean checkDuplicate(BookingSource bookingSource) {
        List<BookingSource> amenities;
        if (bookingSource.getId().equals("")) {
            amenities = bookingSourceService.findAllByClientIdAndTitle(bookingSource.getClientId(), bookingSource.getTitle());
        } else {
            amenities = bookingSourceService.findAllByClientIdAndTitleAndIdIsNot(
                    bookingSource.getClientId(), bookingSource.getTitle(), bookingSource.getId());
        }
        if (amenities.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
