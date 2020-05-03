package com.carmel.guestjini.service.components;

import com.carmel.guestjini.service.config.CarmelConfig;
import com.carmel.guestjini.service.model.DTO.Common.ApplicationConstantDTO;
import com.carmel.guestjini.service.response.Common.ApplicationConstantResponse;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApplicationConstantsService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarmelConfig carmelConfig;

    public ApplicationConstantDTO getApplicationConstant(String key){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) auth.getDetails();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ oAuth2AuthenticationDetails.getTokenValue());
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject postData = new JSONObject();
        postData.put("key", key);
        HttpEntity<String> entity = new HttpEntity<>(postData.toJSONString(), headers);
        ResponseEntity<ApplicationConstantResponse> result =restTemplate.exchange(
                carmelConfig.getDbServiceURL() + "/application-constant/get-by-key",
                HttpMethod.POST, entity,
                ApplicationConstantResponse.class
        );
        return result.getBody().getApplicationConstant();
    }
}
