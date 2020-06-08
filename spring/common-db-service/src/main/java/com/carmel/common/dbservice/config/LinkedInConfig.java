package com.carmel.common.dbservice.config;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class LinkedInConfig {

    private Map<String, Object> linkedInConfig;

    private List<String> linkedInId;
    private List<String> fullName;
    private List<String> email;
    private List<String> profileURL;
    private List<String> firstName;
    private List<String> lastName;
    private List<String> title;
    private List<String> avatar;
    private List<String> location;
    private List<String> address;
    private List<String> birthday;
    private List<String> summary;
    private List<String> website;
    private List<String> industry;
    private List<String> followers;

    private List<String> oraganization;
    private List<String> organizationTitle;
    private List<String> organizationStart;
    private List<String> organizationEnd;
    private List<String> organizationDescription;
    private List<String> organizationLocation;
    private List<String> organizationLiURL;
    private List<String> organizationLiTitle;
    private List<String> organizationWWW;
    private List<String> organizationDomain;


    public LinkedInConfig(YAMLConfig yamlConfig) {
        try {
            Yaml yaml = new Yaml();
            FileInputStream inputStream =
                    new FileInputStream(new File(
                            yamlConfig.getLinkedInConfigPath()
                    ));
            this.linkedInConfig = yaml.load(inputStream);
            processConfig();
        } catch (Exception ex) {

        }
    }

    public Map<String, Object> getLinkedInConfig() {
        return linkedInConfig;
    }

    private void processConfig(){
        Map<String, String> linkedIn = (Map<String, String>) this.linkedInConfig.get("linkedIn");
        Map<String, String> organization = (Map<String, String>) this.linkedInConfig.get("organization");
        this.linkedInId = Arrays.asList(linkedIn.get("linkedInId").split(","));
        this.fullName = Arrays.asList(linkedIn.get("fullName").split(","));
        this.email = Arrays.asList(linkedIn.get("email").split(","));
        this.profileURL = Arrays.asList(linkedIn.get("profileURL").split(","));
        this.firstName = Arrays.asList(linkedIn.get("firstName").split(","));
        this.lastName = Arrays.asList(linkedIn.get("lastName").split(","));
        this.title = Arrays.asList(linkedIn.get("title").split(","));
        this.avatar = Arrays.asList(linkedIn.get("avatar").split(","));
        this.location = Arrays.asList(linkedIn.get("location").split(","));
        this.address = Arrays.asList(linkedIn.get("address").split(","));
        this.birthday = Arrays.asList(linkedIn.get("birthday").split(","));
        this.summary = Arrays.asList(linkedIn.get("summary").split(","));
        this.followers = Arrays.asList(linkedIn.get("followers").split(","));
        this.website = Arrays.asList(linkedIn.get("website").split(","));
        this.industry = Arrays.asList(linkedIn.get("industry").split(","));

        this.oraganization = Arrays.asList(organization.get("oraganization").split(","));
        this.organizationTitle = Arrays.asList(organization.get("organizationTitle").split(","));
        this.organizationStart = Arrays.asList(organization.get("organizationStart").split(","));
        this.organizationEnd = Arrays.asList(organization.get("organizationEnd").split(","));
        this.organizationDescription = Arrays.asList(organization.get("organizationDescription").split(","));
        this.organizationLocation = Arrays.asList(organization.get("organizationLocation").split(","));
        this.organizationLiURL = Arrays.asList(organization.get("organizationLiURL").split(","));
        this.organizationLiTitle = Arrays.asList(organization.get("organizationLiTitle").split(","));
        this.organizationWWW = Arrays.asList(organization.get("organizationWWW").split(","));
        this.organizationDomain = Arrays.asList(organization.get("organizationDomain").split(","));

    }

    public List<String> getLinkedInId() {
        return linkedInId;
    }

    public List<String> getFullName() {
        return fullName;
    }

    public List<String> getEmail() {
        return email;
    }

    public List<String> getProfileURL() {
        return profileURL;
    }

    public List<String> getFirstName() {
        return firstName;
    }

    public List<String> getLastName() {
        return lastName;
    }

    public List<String> getTitle() {
        return title;
    }

    public List<String> getAvatar() {
        return avatar;
    }

    public List<String> getLocation() {
        return location;
    }

    public List<String> getAddress() {
        return address;
    }

    public List<String> getBirthday() {
        return birthday;
    }

    public List<String> getSummary() {
        return summary;
    }

    public List<String> getWebsite() {
        return website;
    }

    public List<String> getIndustry() {
        return industry;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public List<String> getOraganization() {
        return oraganization;
    }

    public List<String> getOrganizationTitle() {
        return organizationTitle;
    }

    public List<String> getOrganizationStart() {
        return organizationStart;
    }

    public List<String> getOrganizationEnd() {
        return organizationEnd;
    }

    public List<String> getOrganizationDescription() {
        return organizationDescription;
    }

    public List<String> getOrganizationLocation() {
        return organizationLocation;
    }

    public List<String> getOrganizationLiURL() {
        return organizationLiURL;
    }

    public List<String> getOrganizationLiTitle() {
        return organizationLiTitle;
    }

    public List<String> getOrganizationWWW() {
        return organizationWWW;
    }

    public List<String> getOrganizationDomain() {
        return organizationDomain;
    }
}
