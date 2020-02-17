package com.carmel.guestjini.accounts.components;

import com.carmel.guestjini.accounts.config.YAMLConfig;
import com.carmel.guestjini.accounts.response.GuestResponse;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GuestService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    YAMLConfig yamlConfig;

    public GuestResponse getGuestById(String guestId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) auth.getDetails();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ oAuth2AuthenticationDetails.getTokenValue());
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject postData = new JSONObject();
        postData.put("guestId",guestId);
        HttpEntity<String> entity = new HttpEntity<>(postData.toJSONString(), headers);
        ResponseEntity<GuestResponse> result =restTemplate.exchange(
                yamlConfig.getBookingServiceURL() + "/guest/gete",
                HttpMethod.POST,
                entity,
                GuestResponse.class
        );
        return result.getBody();
    }
}
