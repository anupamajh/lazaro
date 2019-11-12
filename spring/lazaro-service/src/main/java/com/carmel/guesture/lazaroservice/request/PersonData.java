package com.carmel.guesture.lazaroservice.request;

import com.carmel.guesture.lazaroservice.model.Source;

public class PersonData {

    private String when;
    private String client;
    private String phoneNumber;
    private String id;
    private boolean verified;
    private SourceData source;
    private String name;
    private String[] interests;
    private String[] emailAddresses;
    private boolean missed;
    private AgentData agent;
    private String startTime;
    private String endTime;
    private int duration;
    private String recordingURL;

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public SourceData getSource() {
        return source;
    }

    public void setSource(SourceData source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }

    public String[] getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(String[] emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public boolean isMissed() {
        return missed;
    }

    public void setMissed(boolean missed) {
        this.missed = missed;
    }

    public AgentData getAgent() {
        return agent;
    }

    public void setAgent(AgentData agent) {
        this.agent = agent;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getRecordingURL() {
        return recordingURL;
    }

    public void setRecordingURL(String recordingURL) {
        this.recordingURL = recordingURL;
    }
}
