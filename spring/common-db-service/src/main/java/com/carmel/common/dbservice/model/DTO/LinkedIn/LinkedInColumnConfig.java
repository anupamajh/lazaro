package com.carmel.common.dbservice.model.DTO.LinkedIn;

import java.util.ArrayList;
import java.util.List;

public class LinkedInColumnConfig {
    private int linkedInId;
    private int fullName;
    private int email;
    private int profileURL;
    private int firstName;
    private int lastName;
    private int title;
    private int avatar;
    private int location;
    private int address;
    private int birthday;
    private int summary;
    private int followers;
    List<LinkedInOrganizationColumnConfig> linkedInOrganizationColumnConfigs;

    public LinkedInColumnConfig() {
        this.linkedInOrganizationColumnConfigs = new ArrayList<>();
    }

    public int getLinkedInId() {
        return linkedInId;
    }

    public void setLinkedInId(int linkedInId) {
        this.linkedInId = linkedInId;
    }

    public int getFullName() {
        return fullName;
    }

    public void setFullName(int fullName) {
        this.fullName = fullName;
    }

    public int getEmail() {
        return email;
    }

    public void setEmail(int email) {
        this.email = email;
    }

    public int getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(int profileURL) {
        this.profileURL = profileURL;
    }

    public int getFirstName() {
        return firstName;
    }

    public void setFirstName(int firstName) {
        this.firstName = firstName;
    }

    public int getLastName() {
        return lastName;
    }

    public void setLastName(int lastName) {
        this.lastName = lastName;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public int getSummary() {
        return summary;
    }

    public void setSummary(int summary) {
        this.summary = summary;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public List<LinkedInOrganizationColumnConfig> getLinkedInOrganizationColumnConfigs() {
        return linkedInOrganizationColumnConfigs;
    }

    public void setLinkedInOrganizationColumnConfigs(List<LinkedInOrganizationColumnConfig> linkedInOrganizationColumnConfigs) {
        this.linkedInOrganizationColumnConfigs = linkedInOrganizationColumnConfigs;
    }
}
