package com.carmel.guesture.crmdashboard.models;

public class AgentLeadData {
    public String agentName;
    public String agentId;
    public long leadCount;

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public long getLeadCount() {
        return leadCount;
    }

    public void setLeadCount(long leadCount) {
        this.leadCount = leadCount;
    }
}
