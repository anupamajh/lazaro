package com.carmel.guestjini.booking.controller;

import com.carmel.guestjini.booking.components.UserInformation;
import com.carmel.guestjini.booking.model.Guest;
import com.carmel.guestjini.booking.model.Principal.UserInfo;
import com.carmel.guestjini.booking.response.GuestResponse;
import com.carmel.guestjini.booking.service.GuestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/guest")
public class GuestController {

    Logger logger = LoggerFactory.getLogger(BookingSourceController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    GuestService guestService;


    @RequestMapping(value = "/checkout")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public GuestResponse checkout(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        GuestResponse guestResponse = new GuestResponse();
        try {
            String guestId = formData.get("guestId") == null ? null : String.valueOf(formData.get("guestId"));
            Date actualCheckout = formData.get("actual_checkout") != null ? new Date(formData.get("actual_checkout")) : null;
            if (guestId == null) {
                throw new Exception("Guest ID not received");
            }
            Optional<Guest> optionalGuest = guestService.findById(guestId);
            optionalGuest.orElseThrow(() -> new Exception("Guest not found"));
            Guest guest = optionalGuest.get();
            guest = guestService.doCheckout(guest, actualCheckout);
            guestResponse.setGuest(guest);
            guestResponse.setSuccess(false);
            guestResponse.setError("");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            guestResponse.setSuccess(false);
            guestResponse.setError(ex.getMessage());
        }
        return guestResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public GuestResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        GuestResponse guestResponse = new GuestResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Guest> optionalGuest = guestService.findById(formData.get("id"));
            if (optionalGuest.isPresent()) {
                Guest guest = optionalGuest.get();
                guestResponse.setSuccess(true);
                guestResponse.setError("");
                guestResponse.setGuest(guest);
            } else {
                guestResponse.setSuccess(false);
                guestResponse.setError("Error occurred while Fetching Guest!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            guestResponse.setSuccess(false);
            guestResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return guestResponse;
    }


    @RequestMapping(value = "/get-guest-in-period", method = RequestMethod.POST)
    public GuestResponse getGuestsInPeriod(Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        GuestResponse guestResponse = new GuestResponse();
        try {

            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            guestResponse.setSuccess(false);
            guestResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return guestResponse;
    }


}
