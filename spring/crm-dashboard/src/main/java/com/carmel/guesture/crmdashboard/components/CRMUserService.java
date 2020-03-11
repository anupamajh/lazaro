package com.carmel.guesture.crmdashboard.components;

import com.carmel.guesture.crmdashboard.response.CRMUserResponse;
import com.carmel.guesture.crmdashboard.response.CRMUsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CRMUserService {

    @Autowired
    CarmelConfig carmelConfig;

    @Autowired
    RestTemplate restTemplate;


    public CRMUsersResponse getCRMUsers() {
        String url = carmelConfig.getCrmURL();
        url += "/User";
        CRMUsersResponse crmUsersResponse = restTemplate.getForObject(url,
                CRMUsersResponse.class);
        return crmUsersResponse;
    }

    public CRMUserResponse getCRMUserById(String agentId) {
        String url = carmelConfig.getCrmURL();
        url += "/User/" + agentId;
        CRMUserResponse crmUserResponse = restTemplate.getForObject(url,
                CRMUserResponse.class);
        return crmUserResponse;
    }


}
