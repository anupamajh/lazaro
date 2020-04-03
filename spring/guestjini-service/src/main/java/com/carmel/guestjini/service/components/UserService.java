package com.carmel.guestjini.service.components;

import com.carmel.guestjini.service.config.CarmelConfig;
import com.carmel.guestjini.service.model.DTO.Common.UserDTO;
import com.carmel.guestjini.service.response.Common.UserResponse;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarmelConfig carmelConfig;

    public UserResponse saveUser(UserDTO user) throws Exception{
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) auth.getDetails();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + oAuth2AuthenticationDetails.getTokenValue());
            headers.setContentType(MediaType.APPLICATION_JSON);
            ObjectMapper objectMapper = new ObjectMapper();
            String postJsonString = objectMapper.writeValueAsString(user);
            JSONParser parser = new JSONParser(JSONParser.MODE_PERMISSIVE);
            JSONObject postData = (JSONObject) parser.parse(postJsonString);
            HttpEntity<String> entity = new HttpEntity<>(postData.toJSONString(), headers);
            ResponseEntity<UserResponse> result =
                    restTemplate.exchange(carmelConfig.getDbServiceURL() + "/user/save",
                            HttpMethod.POST, entity, UserResponse.class);
            return result.getBody();
        } catch (Exception ex) {
            throw ex;
        }
    }
}
