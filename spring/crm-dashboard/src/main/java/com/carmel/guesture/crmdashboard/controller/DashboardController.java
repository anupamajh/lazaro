package com.carmel.guesture.crmdashboard.controller;

import com.carmel.guesture.crmdashboard.components.CRMLeadsService;
import com.carmel.guesture.crmdashboard.components.CRMTasksService;
import com.carmel.guesture.crmdashboard.components.CRMUserService;
import com.carmel.guesture.crmdashboard.models.AgentLeadData;
import com.carmel.guesture.crmdashboard.models.CRMLead;
import com.carmel.guesture.crmdashboard.models.CRMTasks;
import com.carmel.guesture.crmdashboard.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping(value = "/")
public class DashboardController {

    @Autowired
    CRMLeadsService crmLeadsService;

    @Autowired
    CRMUserService crmUserService;

    @Autowired
    CRMTasksService crmTasksService;

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
        long totalActiveLeads = agentLeadData.stream().mapToLong(leadDatas -> leadDatas.getLeadCount()).sum();
        long totalLeadCount = crmLeadsService.getTotalLeadCount();
        long totalUnAssignedLeads = crmLeadsService.getUnAssignedLeadCount();
        long totalConvertedLeadCount = crmLeadsService.getLeadCountByStatus("Converted");
        long totalNoProductFit = crmLeadsService.getLeadCountByStatus("LOST - No Product Fit");
        long totalLostLocation = crmLeadsService.getLeadCountByStatus("Lost Location");
        long totalLostBudget = crmLeadsService.getLeadCountByStatus("Lost Budget");
        long totalLostOther = crmLeadsService.getLeadCountByStatus("Lost Other");
        long totalLostDead = crmLeadsService.getLeadCountByStatus("Dead");
        model.addAttribute("agentData", agentLeadData);
        model.addAttribute("totalLeads", totalLeadCount);
        model.addAttribute("totalUnAssignedLeads", totalUnAssignedLeads);
        model.addAttribute("totalConvertedLeads", totalConvertedLeadCount);
        model.addAttribute("totalInactiveLeads", totalLeadCount - (totalActiveLeads + totalConvertedLeadCount));
        model.addAttribute("totalNoProductFit", totalNoProductFit);
        model.addAttribute("totalLostLocation", totalLostLocation);
        model.addAttribute("totalLostBudget", totalLostBudget);
        model.addAttribute("totalLostOther", totalLostOther);
        model.addAttribute("totalLostDead", totalLostDead);
        model.addAttribute("totalActiveLeads", totalActiveLeads);
        return "dashboard/home";
    }

    @RequestMapping(value = "/lead-data/{agentId}", method = RequestMethod.GET)
    public String leadList(Model model, @PathVariable(name = "agentId", required = false) String agentId) {
        CRMUserResponse crmUserResponse = crmUserService.getCRMUserById(agentId);
        model.addAttribute("agent", crmUserResponse.getData());
        return "dashboard/lead-data";
    }

    @RequestMapping(value = "/all-leads", method = RequestMethod.GET)
    public String allLeads(){
        return "dashboard/all-leads";
    }
    @RequestMapping(value = "/get-leads", method = RequestMethod.GET)
    public @ResponseBody
    CRMLeadsResponse getLeadsByAgent(
            @RequestParam(name = "agentId", required = false) String agentId
    ) {
        CRMLeadsResponse crmLeadsResponse = new CRMLeadsResponse();
        crmLeadsResponse.setSuccess(false);
        List<CRMLead> crmLeadList = new ArrayList<>();
        try {
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

        } catch (Exception ex) {
            crmLeadsResponse.setSuccess(false);
        }
        return crmLeadsResponse;
    }

    @RequestMapping(value = "/lead-details/{agentId}/{leadId}", method = RequestMethod.GET)
    public String leadDetails(Model model,
                              @PathVariable(name = "agentId", required = false) String agentId,
                              @PathVariable(name = "leadId", required = false) String leadId
    ) {
        CRMLeadResponse crmLeadResponse = crmLeadsService.getLeadById(leadId);
        CRMTasksResponse crmTasksResponse = crmTasksService.getLeadsTasks(leadId);
        model.addAttribute("lead", crmLeadResponse.getData());
        model.addAttribute("tasks", crmTasksResponse.getData());
        model.addAttribute("agentId", agentId);
        model.addAttribute("leadId", leadId);
        return "dashboard/lead-details";
    }

    @RequestMapping(value = {"/agent-activities/{agentId}/{year}/{month}", "/agent-activities/{agentId}"}, method = RequestMethod.GET)
    public String agentActivities(Model model,
                                  @PathVariable(name = "agentId", required = false) String agentId,
                                  @PathVariable(name = "year", required = false) Integer year,
                                  @PathVariable(name = "month", required = false) Integer month
    ) {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonth().getValue();
        List<String> yearList = new ArrayList<>();
        for (int i = currentYear - 5; i <= currentYear; i++) {
            yearList.add(String.valueOf(i));
        }
        if(year != null){
            currentYear = year;
        }
        if(month != null){
            currentMonth = month;
        }
        CRMTasksResponse crmTasksResponse = crmTasksService.getAgentsTasks(agentId, currentYear, currentMonth);
        CRMUserResponse crmUserResponse = crmUserService.getCRMUserById(agentId);
        List<CRMTasks> crmTasks = crmTasksResponse.getData();
        Comparator<CRMTasks> comparatorByCreatedDate = new Comparator<CRMTasks>() {
            @Override
            public int compare(CRMTasks o1, CRMTasks o2) {
                return o1.getAttributes().getDate_entered().compareTo(o2.getAttributes().getDate_entered());
            }
        };
        HashMap<String, List<CRMTasks>> sortedLeadData = new HashMap<>();
        crmTasks.sort(comparatorByCreatedDate.reversed());
        model.addAttribute("agent", crmUserResponse.getData());
        model.addAttribute("agentId", agentId);
        model.addAttribute("tasks", crmTasks);
        model.addAttribute("year", currentYear);
        model.addAttribute("yearList", yearList);
        model.addAttribute("month", currentMonth);
        return "dashboard/agent-activities";
    }

    @RequestMapping(value = "/lead-data-by-status/{status}", method = RequestMethod.GET)
    public String leadList(Model model, @PathVariable(name = "status", required = false) int status) {
        model.addAttribute("status", status);
        return "dashboard/lead-data-by-status";
    }

    @RequestMapping(value = "/get-paged-leads", method = RequestMethod.GET)
    public @ResponseBody
    CRMLeadsResponse getPagedLeads(
            @RequestParam(name = "page", required = false) int page
    ) {
        CRMLeadsResponse crmLeadsResponse = new CRMLeadsResponse();
        crmLeadsResponse.setSuccess(false);
        try {
            crmLeadsResponse = crmLeadsService.getAllLeads(page);
            crmLeadsResponse.setSuccess(true);
        } catch (Exception ex) {
            crmLeadsResponse.setSuccess(false);
        }
        return crmLeadsResponse;
    }

    @RequestMapping(value = "/get-leads-by-status", method = RequestMethod.GET)
    public @ResponseBody
    CRMLeadsResponse getLeadsByStatus(
            @RequestParam(name = "status", required = false) int status
    ) {
        String statusText = "";
        switch (status) {
            case 1:
                statusText = "Converted";
                break;
            case 2:
                statusText = "LOST - No Product Fit";
                break;
            case 3:
                statusText = "Lost Location";
                break;
            case 4:
                statusText = "Lost Budget";
                break;
            case 5:
                statusText = "Lost Other";
                break;
            case 6:
                statusText = "Dead";
                break;
        }
        CRMLeadsResponse crmLeadsResponse = new CRMLeadsResponse();
        crmLeadsResponse.setSuccess(false);
        List<CRMLead> crmLeadList = new ArrayList<>();
        try {
            crmLeadList = crmLeadsService.getLeadsByStatus(statusText);
            Comparator<CRMLead> compareByCreatedDate = new Comparator<CRMLead>() {
                @Override
                public int compare(CRMLead o1, CRMLead o2) {
                    return o1.getAttributes().getDate_entered().compareTo(o2.getAttributes().getDate_entered());
                }
            };
            crmLeadList.sort(compareByCreatedDate.reversed());
            crmLeadsResponse.setData(crmLeadList);
            crmLeadsResponse.setSuccess(true);

        } catch (Exception ex) {
            crmLeadsResponse.setSuccess(false);
        }
        return crmLeadsResponse;
    }
}
