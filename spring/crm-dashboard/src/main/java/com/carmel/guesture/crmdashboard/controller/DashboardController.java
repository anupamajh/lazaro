package com.carmel.guesture.crmdashboard.controller;

import com.carmel.guesture.crmdashboard.components.CRMLeadsService;
import com.carmel.guesture.crmdashboard.components.CRMUserService;
import com.carmel.guesture.crmdashboard.models.AgentLeadData;
import com.carmel.guesture.crmdashboard.models.CRMLead;
import com.carmel.guesture.crmdashboard.response.CRMLeadsResponse;
import com.carmel.guesture.crmdashboard.response.CRMUserResponse;
import com.carmel.guesture.crmdashboard.response.CRMUsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping(value = "/")
public class DashboardController {

    @Autowired
    CRMLeadsService crmLeadsService;

    @Autowired
    CRMUserService crmUserService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        List<AgentLeadData> agentLeadData = new ArrayList<>();
        CRMUsersResponse crmUsersResponse = crmUserService.getCRMUsers();
        crmUsersResponse.getData().forEach(crmUser -> {
            if (!agentLeadData.stream().anyMatch(ald -> ald.getAgentId().equals(crmUser.getId()))) {
                long activeLeadCount = crmLeadsService.getActiveLeadCountByAgent(crmUser.getId());
                AgentLeadData agentData = new AgentLeadData();
                agentData.setAgentId(crmUser.getId());
                agentData.setAgentName(crmUser.getAttributes().getFull_name());
                agentData.setLeadCount(activeLeadCount);
                agentLeadData.add(agentData);
            }
        });

        long totalLeadCount = crmLeadsService.getTotalLeadCount();
        long totalUnAssignedLeads = crmLeadsService.getUnAssignedLeadCount();
        long totalConvertedLeadCount = crmLeadsService.getLeadCountByStatus("Converted");
        model.addAttribute("agentData", agentLeadData);
        model.addAttribute("totalLeads", totalLeadCount);
        model.addAttribute("totalUnAssignedLeads", totalUnAssignedLeads);
        model.addAttribute("totalConvertedLeads", totalConvertedLeadCount);
        return "dashboard/home";
    }

    @RequestMapping(value = "/lead-data/{agentId}", method = RequestMethod.GET)
    public String form(Model model, @PathVariable(name = "agentId", required = false) String agentId) {
        CRMUserResponse crmUserResponse = crmUserService.getCRMUserById(agentId);
        model.addAttribute("agent", crmUserResponse.getData());
        return "dashboard/lead-data";
    }

    @RequestMapping(value = "/get-leads", method = RequestMethod.GET)
    public @ResponseBody
    CRMLeadsResponse getLeadsByAgent(
            @RequestParam(name = "agentId", required = false) String agentId
    ){
        CRMLeadsResponse crmLeadsResponse = new CRMLeadsResponse();
        crmLeadsResponse.setSuccess(false);
        List<CRMLead> crmLeadList = new ArrayList<>();
        try{
            crmLeadList = crmLeadsService.getAgentLeads(agentId);
            Comparator<CRMLead> compareByCreatedDate = new Comparator<CRMLead>() {
                @Override
                public int compare(CRMLead o1, CRMLead o2) {
                    return o1.getAttributes().getDate_entered().compareTo(o2.getAttributes().getDate_entered());
                }
            };
            crmLeadList.sort(compareByCreatedDate.reversed());
            crmLeadsResponse.setData(crmLeadList);
            crmLeadsResponse.setSuccess(true);

        }catch (Exception ex){
            crmLeadsResponse.setSuccess(false);
        }
        return crmLeadsResponse;
    }
}
