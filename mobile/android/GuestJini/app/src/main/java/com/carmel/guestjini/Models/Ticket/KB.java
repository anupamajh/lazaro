package com.carmel.guestjini.Models.Ticket;

public class KB {
    private String id;
    private String clientId;
    private String orgId;
    private String topicTitle;
    private String topicNarration;
    private String authorName;
    private String authorLogoPath;
    private String creationTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicNarration() {
        return topicNarration;
    }

    public void setTopicNarration(String topicNarration) {
        this.topicNarration = topicNarration;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorLogoPath() {
        return authorLogoPath;
    }

    public void setAuthorLogoPath(String authorLogoPath) {
        this.authorLogoPath = authorLogoPath;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}
