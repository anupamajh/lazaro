package com.carmel.guestjini.booking.components;

import com.carmel.guestjini.booking.config.YAMLConfig;
import com.carmel.guestjini.booking.model.DTO.AccountReceipts;
import com.carmel.guestjini.booking.response.AccountReceiptsResponse;
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
public class AccountReceiptsService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    YAMLConfig yamlConfig;

    public AccountReceiptsResponse saveAccountReceiept(AccountReceipts accountReceipts) throws Exception{
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) auth.getDetails();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + oAuth2AuthenticationDetails.getTokenValue());
            headers.setContentType(MediaType.APPLICATION_JSON);
            ObjectMapper objectMapper = new ObjectMapper();
            String postJsonString = objectMapper.writeValueAsString(accountReceipts);
            JSONParser parser = new JSONParser(JSONParser.MODE_PERMISSIVE);
            JSONObject postData = (JSONObject) parser.parse(postJsonString);
            HttpEntity<String> entity = new HttpEntity<>(postData.toJSONString(), headers);
            ResponseEntity<AccountReceiptsResponse> result = restTemplate.exchange(yamlConfig.getAccountsServiceURL() + "/account-receipts/save", HttpMethod.POST, entity, AccountReceiptsResponse.class);
            return result.getBody();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public AccountReceiptsResponse updateGuestReceipts(String bookingId, String guestId) throws Exception{
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) auth.getDetails();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + oAuth2AuthenticationDetails.getTokenValue());
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject postData = new JSONObject();
            postData.put("bookingId", bookingId);
            postData.put("guestId", guestId);
            HttpEntity<String> entity = new HttpEntity<>(postData.toJSONString(), headers);
            ResponseEntity<AccountReceiptsResponse> result = restTemplate.exchange(yamlConfig.getAccountsServiceURL() + "/account-receipts/update-guest-receipts", HttpMethod.POST, entity, AccountReceiptsResponse.class);
            return result.getBody();
        } catch (Exception ex) {
            throw ex;
        }

    }

}
