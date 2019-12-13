package com.carmel.guestjini.booking.components;

import com.carmel.guestjini.booking.config.YAMLConfig;
import com.carmel.guestjini.booking.model.Principal.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserInformation {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    YAMLConfig yamlConfig;

    public UserInfo getUserInfo(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) auth.getDetails();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ oAuth2AuthenticationDetails.getTokenValue());
        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        ResponseEntity<UserInfo> result =restTemplate.exchange(yamlConfig.getUserInfoURL(), HttpMethod.GET, entity, UserInfo.class);
        return result.getBody();
    }
}
