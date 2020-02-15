package com.carmel.guestjini.accounts.components;

import com.carmel.guestjini.accounts.config.YAMLConfig;
import com.carmel.guestjini.accounts.model.DTO.Guest;
import com.carmel.guestjini.accounts.response.BookingAdditionalChargeResponse;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BookingAdditionalChargeService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    YAMLConfig yamlConfig;

    public BookingAdditionalChargeResponse getRecurringPackageCharges(Guest guest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) auth.getDetails();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ oAuth2AuthenticationDetails.getTokenValue());
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject postData = new JSONObject();
        postData.put("bookingId", guest.getBooking());
        postData.put("billingCycle", "2");
        HttpEntity<String> entity = new HttpEntity<>(postData.toJSONString(), headers);
        ResponseEntity<BookingAdditionalChargeResponse> result =restTemplate.exchange(yamlConfig.getInventoryServiceURL() + "/booking-additional-charges/get-additional-charges-by-type", HttpMethod.POST, entity, BookingAdditionalChargeResponse.class);
        return result.getBody();
    }

    public BookingAdditionalChargeResponse getOneTimePackageCharges(Guest guest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) auth.getDetails();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ oAuth2AuthenticationDetails.getTokenValue());
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject postData = new JSONObject();
        postData.put("bookingId", guest.getBooking());
        postData.put("billingCycle", "1");
        HttpEntity<String> entity = new HttpEntity<>(postData.toJSONString(), headers);
        ResponseEntity<BookingAdditionalChargeResponse> result =restTemplate.exchange(yamlConfig.getInventoryServiceURL() + "/booking-additional-charges/get-additional-charges-by-type", HttpMethod.POST, entity, BookingAdditionalChargeResponse.class);
        return result.getBody();
    }

}
