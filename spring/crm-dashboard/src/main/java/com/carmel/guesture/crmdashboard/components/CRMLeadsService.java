package com.carmel.guesture.crmdashboard.components;

import com.carmel.guesture.crmdashboard.models.CRMLead;
import com.carmel.guesture.crmdashboard.response.CRMLeadResponse;
import com.carmel.guesture.crmdashboard.response.CRMLeadsResponse;
import com.carmel.guesture.crmdashboard.response.CRMUsersResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class CRMLeadsService {
    Logger logger = LoggerFactory.getLogger(CRMLeadsService.class);

    @Autowired
    CarmelConfig carmelConfig;

    @Autowired
    RestTemplate restTemplate;


    public long getTotalLeadCount() {
        long totalLeads = 0;
        try {
            String url = carmelConfig.getCrmURL();
            url += "/Leads?page[number]=1&page[size]=1";
            String responseData = restTemplate.getForObject(url,
                    String.class);
            JsonObject jsonObject = new JsonParser().parse(responseData).getAsJsonObject();
            if (jsonObject.has("meta")) {
                JsonObject metaObject = jsonObject.getAsJsonObject("meta");
                if (metaObject.has("total-pages")) {
                    totalLeads = metaObject.get("total-pages").getAsLong();
                }
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }

        return totalLeads;
    }

    public long getUnAssignedLeadCount() {
        long totalLeads = 0;
        try {
            String url = carmelConfig.getCrmURL();
            url += "/Leads?page[number]=1&page[size]=1&filter[assigned_user_id][eq]=";
            String responseData = restTemplate.getForObject(url,
                    String.class);
            JsonObject jsonObject = new JsonParser().parse(responseData).getAsJsonObject();
            if (jsonObject.has("meta")) {
                JsonObject metaObject = jsonObject.getAsJsonObject("meta");
                if (metaObject.has("total-pages")) {
                    totalLeads = metaObject.get("total-pages").getAsLong();
                }
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }

        return totalLeads;
    }

    public long getActiveLeadCountByAgent(String agentId) {
        long totalLeads = 0;
        try {
            long tempLeadCount = this.getAgentLeadCountByStatus(agentId, "");
            totalLeads += tempLeadCount;
            tempLeadCount = this.getAgentLeadCountByStatus(agentId, "New");
            totalLeads += tempLeadCount;
            tempLeadCount = this.getAgentLeadCountByStatus(agentId, "Assigned");
            totalLeads += tempLeadCount;
            tempLeadCount = this.getAgentLeadCountByStatus(agentId, "Following Up");
            totalLeads += tempLeadCount;
            tempLeadCount = this.getAgentLeadCountByStatus(agentId, "Recycled");
            totalLeads += tempLeadCount;
            tempLeadCount = this.getAgentLeadCountByStatus(agentId, "Requested Call Back");
            totalLeads += tempLeadCount;
            tempLeadCount = this.getAgentLeadCountByStatus(agentId, "Site Visit Scheduled");
            totalLeads += tempLeadCount;
            tempLeadCount = this.getAgentLeadCountByStatus(agentId, "Coordinate Booking");
            totalLeads += tempLeadCount;
            tempLeadCount = this.getAgentLeadCountByStatus(agentId, "Missed Call");
            totalLeads += tempLeadCount;
        } catch (Exception ex) {
            logger.error(ex.toString());
        }

        return totalLeads;
    }

    private long getAgentLeadCountByStatus(String agentId, String status) {
        long totalLeads = 0;
        try {
            String url = carmelConfig.getCrmURL();
            url += "/Leads?page[number]=1&page[size]=1&" +
                    "filter[assigned_user_id][eq]=" +
                    agentId +
                    "&filter[operator]=and&filter[status][eq]=";

            String responseData = restTemplate.getForObject(url + status,
                    String.class);
            JsonObject jsonObject = new JsonParser().parse(responseData).getAsJsonObject();
            if (jsonObject.has("meta")) {
                JsonObject metaObject = jsonObject.getAsJsonObject("meta");
                if (metaObject.has("total-pages")) {
                    totalLeads = metaObject.get("total-pages").getAsLong();
                }
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return totalLeads;
    }

    public long getLeadCountByStatus(String status) {
        long totalLeads = 0;
        try {
            String url = carmelConfig.getCrmURL();
            url += "/Leads?page[number]=1&page[size]=1" +
                    "&filter[operator]=and&filter[status][eq]=";

            String responseData = restTemplate.getForObject(url + status,
                    String.class);
            JsonObject jsonObject = new JsonParser().parse(responseData).getAsJsonObject();
            if (jsonObject.has("meta")) {
                JsonObject metaObject = jsonObject.getAsJsonObject("meta");
                if (metaObject.has("total-pages")) {
                    totalLeads = metaObject.get("total-pages").getAsLong();
                }
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return totalLeads;
    }

    public List<CRMLead> getAgentLeads(String agentId) {
        List<CRMLead> crmLeadList = new ArrayList<>();
        CRMLeadsResponse leadsResponse = this.getAgentLeadsByStatus(agentId, "");
        crmLeadList.addAll(leadsResponse.getData());
        leadsResponse = this.getAgentLeadsByStatus(agentId,  "New");
        crmLeadList.addAll(leadsResponse.getData());
        leadsResponse = this.getAgentLeadsByStatus(agentId,  "Assigned");
        crmLeadList.addAll(leadsResponse.getData());
        leadsResponse = this.getAgentLeadsByStatus(agentId,  "Following Up");
        crmLeadList.addAll(leadsResponse.getData());
        leadsResponse = this.getAgentLeadsByStatus(agentId, "Recycled");
        crmLeadList.addAll(leadsResponse.getData());
        leadsResponse = this.getAgentLeadsByStatus(agentId,  "Requested Call Back");
        crmLeadList.addAll(leadsResponse.getData());
        leadsResponse = this.getAgentLeadsByStatus(agentId, "Site Visit Scheduled");
        crmLeadList.addAll(leadsResponse.getData());
        leadsResponse = this.getAgentLeadsByStatus(agentId,  "Coordinate Booking");
        crmLeadList.addAll(leadsResponse.getData());
        leadsResponse = this.getAgentLeadsByStatus(agentId,  "Missed Call");
        crmLeadList.addAll(leadsResponse.getData());
        return crmLeadList;

    }

    public CRMLeadsResponse getAgentLeadsByStatus(String agentId, String status) {
        String url = carmelConfig.getCrmURL();
        url += "/Leads?filter[operator]=and" +
                "&filter[assigned_user_id][eq]=" +
                agentId +
                "&filter[operator]=and" +
                "&filter[status][eq]=" +
                status +
                "&page[number]=1&page[size]=800";
        CRMLeadsResponse crmLeadsResponse = restTemplate.getForObject(url,
                CRMLeadsResponse.class);
        return crmLeadsResponse;
    }

    public CRMLeadResponse getLeadById(String leadId){
        String url = carmelConfig.getCrmURL();
        url += "/Leads/" + leadId;
        CRMLeadResponse crmLeadResponse = restTemplate.getForObject(url,
                CRMLeadResponse.class);
        return crmLeadResponse;
    }

    public List<CRMLead>  getLeadsByStatus(String status) {
        String url = carmelConfig.getCrmURL();
        url += "/Leads?filter[operator]=and" +
                "&filter[operator]=and" +
                "&filter[status][eq]=" +
                status +
                "&page[number]=1&page[size]=800";
        CRMLeadsResponse crmLeadsResponse = restTemplate.getForObject(url,
                CRMLeadsResponse.class);
        return crmLeadsResponse.getData();
    }
}
