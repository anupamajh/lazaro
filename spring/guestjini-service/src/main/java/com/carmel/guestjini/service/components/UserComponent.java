package com.carmel.guestjini.service.components;


import com.carmel.guestjini.service.config.CarmelConfig;
import com.carmel.guestjini.service.response.Common.UserResponse;
import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class UserComponent {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarmelConfig carmelConfig;

    public UserResponse findUserByIds(List<String> userIds) throws Exception{
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) auth.getDetails();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + oAuth2AuthenticationDetails.getTokenValue());
            ObjectMapper objectMapper = new ObjectMapper();
            String postJsonString = objectMapper.writeValueAsString(userIds);
            JSONParser parser = new JSONParser(JSONParser.MODE_PERMISSIVE);
            JSONArray postData = (JSONArray) parser.parse(postJsonString);
            HttpEntity<String> entity = new HttpEntity<>(postData.toJSONString(), headers);
            ResponseEntity<UserResponse> result = restTemplate.exchange(
                    carmelConfig.getDbServiceURL() + "/user/find-users-in",
                    HttpMethod.POST,
                    entity,
                    UserResponse.class
            );
            return result.getBody();
        }catch(Exception ex){
            throw ex;
        }
    }


}