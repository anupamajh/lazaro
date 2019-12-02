package com.carmel.guesture.lazaroservice.components;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "olive-crm")
public class YAMLConfig {

    private String crmURL;
    private String crmClientId;
    private String crmClientSecrete;
    private String crmAuthURL;
    private int leadSyncInterval;
    private int activitySyncInterval;

    public String getCrmURL() {
        return crmURL;
    }

    public void setCrmURL(String crmURL) {
        this.crmURL = crmURL;
    }

    public String getCrmClientId() {
        return crmClientId;
    }

    public void setCrmClientId(String crmClientId) {
        this.crmClientId = crmClientId;
    }

    public String getCrmClientSecrete() {
        return crmClientSecrete;
    }

    public void setCrmClientSecrete(String crmClientSecrete) {
        this.crmClientSecrete = crmClientSecrete;
    }

    public String getCrmAuthURL() {
        return crmAuthURL;
    }

    public void setCrmAuthURL(String crmAuthURL) {
        this.crmAuthURL = crmAuthURL;
    }

    public int getLeadSyncInterval() {
        return leadSyncInterval;
    }

    public void setLeadSyncInterval(int leadSyncInterval) {
        this.leadSyncInterval = leadSyncInterval;
    }

    public int getActivitySyncInterval() {
        return activitySyncInterval;
    }

    public void setActivitySyncInterval(int activitySyncInterval) {
        this.activitySyncInterval = activitySyncInterval;
    }
}
