package com.carmel.guestjini.service.components;

import com.carmel.guestjini.service.config.CarmelConfig;
import com.carmel.guestjini.service.model.DTO.Common.UserDTO;
import com.carmel.guestjini.service.request.Booking.BookingRequest;
import com.carmel.guestjini.service.response.Common.UserResponse;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class UserService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    @LoadBalanced
    RestTemplate clientAuthenticated;


    @Autowired
    CarmelConfig carmelConfig;

    public UserResponse saveUser(UserDTO user) throws Exception {
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
                    restTemplate.exchange(carmelConfig.getDbServiceURL() + "/user/guest-sign-up",
                            HttpMethod.POST, entity, UserResponse.class);
            return result.getBody();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public UserResponse signUpGuest(BookingRequest bookingRequest) throws Exception {
        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setFullName(bookingRequest.getFullName());
            userDTO.setAccountStatus(2);
            userDTO.setUserName(bookingRequest.getEmailAddress());
            userDTO.setPhone(bookingRequest.getMobileNumber());
            userDTO.setGender(bookingRequest.getGender());
            userDTO.setIsOperator(0);
            userDTO.setPassword("");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            ObjectMapper objectMapper = new ObjectMapper();
            String postJsonString = objectMapper.writeValueAsString(userDTO);
            JSONParser parser = new JSONParser(JSONParser.MODE_PERMISSIVE);
            JSONObject postData = (JSONObject) parser.parse(postJsonString);
            HttpEntity<String> entity = new HttpEntity<String>(postData.toJSONString(), headers);
            ResponseEntity<UserResponse> result =
                    clientAuthenticated.postForEntity(
                            carmelConfig.getDbServiceURL() + "/user/guest-sign-up",
                            entity,
                            UserResponse.class
                    );
            return result.getBody();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public UserResponse checkPhoneNumber(Map<String, String> formData) throws Exception {
        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setPhone(formData.get("phone"));
            userDTO.setFullName(formData.get("fullName"));
            userDTO.setPassword("");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            ObjectMapper objectMapper = new ObjectMapper();
            String postJsonString = objectMapper.writeValueAsString(userDTO);
            JSONParser parser = new JSONParser(JSONParser.MODE_PERMISSIVE);
            JSONObject postData = (JSONObject) parser.parse(postJsonString);
            HttpEntity<String> entity = new HttpEntity<String>(postData.toJSONString(), headers);
            ResponseEntity<UserResponse> result =
                    clientAuthenticated.postForEntity(
                            carmelConfig.getDbServiceURL() + "/user/check-phone-number",
                            entity,
                            UserResponse.class
                    );
            return result.getBody();
        } catch (Exception ex) {
            throw ex;
        }
    }
}
