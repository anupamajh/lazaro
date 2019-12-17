package com.carmel.guestjini.booking.components;

import com.carmel.guestjini.booking.config.YAMLConfig;
import com.carmel.guestjini.booking.model.DTO.Package;
import com.carmel.guestjini.booking.response.PackageResponse;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PackageService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    YAMLConfig yamlConfig;

    public Package getPackage(String packageId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) auth.getDetails();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ oAuth2AuthenticationDetails.getTokenValue());
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject postData = new JSONObject();
        postData.put("id", packageId);
        HttpEntity<String> entity = new HttpEntity<>(postData.toJSONString(), headers);
        ResponseEntity<PackageResponse> result =restTemplate.exchange(yamlConfig.getInventoryServiceURL() + "/package/get", HttpMethod.POST, entity, PackageResponse.class);
        return result.getBody().getPackage();
    }

}
