package com.carmel.guesture.crmdashboard.components;

import com.carmel.guesture.crmdashboard.response.CRMTaskResponse;
import com.carmel.guesture.crmdashboard.response.CRMTasksResponse;
import com.carmel.guesture.crmdashboard.response.CRMUserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CRMTasksService {
    Logger logger = LoggerFactory.getLogger(CRMLeadsService.class);

    @Autowired
    CarmelConfig carmelConfig;

    @Autowired
    RestTemplate restTemplate;

    public CRMTasksResponse getLeadsTasks(String leadId) {
        String url = carmelConfig.getCrmURL();
        url += "/Tasks?filter[operator]=and&filter[parent_id][eq]=" + leadId;
        CRMTasksResponse crmTasksResponse = restTemplate.getForObject(url,
                CRMTasksResponse.class);
        return crmTasksResponse;
    }

    public CRMTasksResponse getAgentsTasks(String agentId, Integer year, Integer month) {
        String url = carmelConfig.getCrmURL();
        url += "/Tasks?filter[operator]=and&filter[assigned_user_id][eq]=" + agentId;
        if(year != null && month != null){
            LocalDate localDate = LocalDate.of(year, month, 1);
            String startDate = localDate.format(DateTimeFormatter.ofPattern("yyyy-M-dd"));
            LocalDate monthend = localDate.plusDays(localDate.lengthOfMonth()-1);
            String endDate = monthend.format(DateTimeFormatter.ofPattern("yyyy-M-dd"));
            url += "&filter[operator]=and" +
                    "&filter[date_entered][gte]=" +
                    startDate +
                    "&filter[operator]=and" +
                    "&filter[date_entered][lte]=" +
                    endDate;
        }
        CRMTasksResponse crmTasksResponse = restTemplate.getForObject(url,
                CRMTasksResponse.class);
        return crmTasksResponse;
    }
}
