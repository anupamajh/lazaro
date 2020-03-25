package com.carmel.guestjini.inventory.components;

import com.carmel.guestjini.inventory.common.YAMLConfig;
import com.carmel.guestjini.inventory.model.Photo;
import com.carmel.guestjini.inventory.response.BookingResponse;
import com.carmel.guestjini.inventory.response.PhotoResponse;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BookingService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    YAMLConfig yamlConfig;

    public BookingResponse getInventoryAvailability(String inventoryId, String checkInDate, String checkOutDate){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) auth.getDetails();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ oAuth2AuthenticationDetails.getTokenValue());
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject postData = new JSONObject();
        postData.put("inventoryId", inventoryId);
        postData.put("checkInDate", checkInDate);
        postData.put("checkOutDate", checkOutDate);
        HttpEntity<String> entity = new HttpEntity<>(postData.toJSONString(), headers);
        ResponseEntity<BookingResponse> result =restTemplate.exchange(
                yamlConfig.getBookingServiceURL() + "/booking/get-available-inventory",
                HttpMethod.POST, entity,
                BookingResponse.class);
        return result.getBody();
    }

}
