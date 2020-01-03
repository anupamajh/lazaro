package com.carmel.guestjini.booking.components;

import com.carmel.guestjini.booking.config.YAMLConfig;
import com.carmel.guestjini.booking.model.DTO.AccountTicket;
import com.carmel.guestjini.booking.model.Guest;
import com.carmel.guestjini.booking.response.AccountTicketResponse;
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
public class AccountTicketService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    YAMLConfig yamlConfig;

    public AccountTicketResponse saveAccountTicket(AccountTicket accountTicket) throws Exception {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) auth.getDetails();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + oAuth2AuthenticationDetails.getTokenValue());
            headers.setContentType(MediaType.APPLICATION_JSON);
            ObjectMapper objectMapper = new ObjectMapper();
            String postJsonString = objectMapper.writeValueAsString(accountTicket);
            JSONParser parser = new JSONParser(JSONParser.MODE_PERMISSIVE);
            JSONObject postData = (JSONObject) parser.parse(postJsonString);
            HttpEntity<String> entity = new HttpEntity<>(postData.toJSONString(), headers);
            ResponseEntity<AccountTicketResponse> result =
                    restTemplate.exchange(yamlConfig.getAccountsServiceURL() + "/account-tickets/save",
                            HttpMethod.POST, entity, AccountTicketResponse.class);
            return result.getBody();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public AccountTicketResponse deleteAccountTicketsByGuest(String guestId) throws Exception {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) auth.getDetails();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + oAuth2AuthenticationDetails.getTokenValue());
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject postData = new JSONObject();
            postData.put("guestId", guestId);
            HttpEntity<String> entity = new HttpEntity<>(postData.toJSONString(), headers);
            ResponseEntity<AccountTicketResponse> result =
                    restTemplate.exchange(yamlConfig.getAccountsServiceURL() + "/account-tickets//delete-account-tickets-by-guest",
                            HttpMethod.POST, entity, AccountTicketResponse.class);
            return result.getBody();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public AccountTicketResponse generateMonthRentInvoice(Guest guest) throws Exception {
        try {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) auth.getDetails();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + oAuth2AuthenticationDetails.getTokenValue());
            headers.setContentType(MediaType.APPLICATION_JSON);
            ObjectMapper objectMapper = new ObjectMapper();
            String postJsonString = objectMapper.writeValueAsString(guest);
            JSONParser parser = new JSONParser(JSONParser.MODE_PERMISSIVE);
            JSONObject postData = (JSONObject) parser.parse(postJsonString);
            HttpEntity<String> entity = new HttpEntity<>(postData.toJSONString(), headers);
            ResponseEntity<AccountTicketResponse> result =
                    restTemplate.exchange(yamlConfig.getAccountsServiceURL() + "/account-tickets//generate-month-invoices",
                            HttpMethod.POST, entity, AccountTicketResponse.class);
            return result.getBody();

        } catch (Exception ex) {
            throw ex;
        }
    }

    public AccountTicketResponse generateDayRentInvoice(Guest guest) throws Exception {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) auth.getDetails();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + oAuth2AuthenticationDetails.getTokenValue());
            headers.setContentType(MediaType.APPLICATION_JSON);
            ObjectMapper objectMapper = new ObjectMapper();
            String postJsonString = objectMapper.writeValueAsString(guest);
            JSONParser parser = new JSONParser(JSONParser.MODE_PERMISSIVE);
            JSONObject postData = (JSONObject) parser.parse(postJsonString);
            HttpEntity<String> entity = new HttpEntity<>(postData.toJSONString(), headers);
            ResponseEntity<AccountTicketResponse> result =
                    restTemplate.exchange(yamlConfig.getAccountsServiceURL() + "/account-tickets/generate-day-invoices",
                            HttpMethod.POST, entity, AccountTicketResponse.class);
            return result.getBody();

        } catch (Exception ex) {
            throw ex;
        }
    }

}
