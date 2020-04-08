package com.carmel.guestjini.service.components;


import com.carmel.guestjini.service.config.CarmelConfig;
import com.carmel.guestjini.service.model.DTO.Common.Photo;
import com.carmel.guestjini.service.response.Common.PhotoResponse;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PhotoInformation {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarmelConfig carmelConfig;

    public Photo getPhoto(String photoId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) auth.getDetails();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ oAuth2AuthenticationDetails.getTokenValue());
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject postData = new JSONObject();
        postData.put("id", photoId);
        HttpEntity<String> entity = new HttpEntity<>(postData.toJSONString(), headers);
        ResponseEntity<PhotoResponse> result =restTemplate.exchange(
                carmelConfig.getDbServiceURL() + "/photos/get",
                HttpMethod.POST, entity,
                PhotoResponse.class
        );
        return result.getBody().getPhoto();
    }

}