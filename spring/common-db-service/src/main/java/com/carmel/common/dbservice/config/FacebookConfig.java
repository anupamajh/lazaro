package com.carmel.common.dbservice.config;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FacebookConfig {

    private Map<String, Object> facebookConfig;

    private List<String> fbName;
    private List<String> titleURL;
    private List<String> image;
    private List<String> companyName;
    private List<String> pacURL;
    private List<String> designation;
    private List<String> pacURL2;
    private List<String> currentWorking;
    private List<String> liveAt;
    private List<String> eh52URL;
    private List<String> eh524;
    private List<String> followers;

    public FacebookConfig(YAMLConfig yamlConfig) {
        try {
            Yaml yaml = new Yaml();
            FileInputStream inputStream =
                    new FileInputStream(new File(
                            yamlConfig.getFacebookConfigPath()
                    ));
            this.facebookConfig = yaml.load(inputStream);
            this.processConfig();
        } catch (Exception ex) {
            String message = ex.getMessage();

        }
    }

    private void processConfig() {
        Map<String, String> facebook = (Map<String, String>) this.facebookConfig.get("facebook");
        this.fbName = Arrays.asList(facebook.get("fbName").split(","));
        this.titleURL = Arrays.asList(facebook.get("titleURL").split(","));
        this.image = Arrays.asList(facebook.get("image").split(","));
        this.companyName = Arrays.asList(facebook.get("companyName").split(","));
        this.pacURL = Arrays.asList(facebook.get("pacURL").split(","));
        this.designation = Arrays.asList(facebook.get("designation").split(","));
        this.pacURL2 = Arrays.asList(facebook.get("pacURL2").split(","));
        this.currentWorking = Arrays.asList(facebook.get("currentWorking").split(","));
        this.liveAt = Arrays.asList(facebook.get("liveAt").split(","));
        this.eh52URL = Arrays.asList(facebook.get("eh52URL").split(","));
        this.eh524 = Arrays.asList(facebook.get("eh524").split(","));
        this.followers = Arrays.asList(facebook.get("followers").split(","));
    }

    public List<String> getFbName() {
        return fbName;
    }

    public List<String> getTitleURL() {
        return titleURL;
    }

    public List<String> getImage() {
        return image;
    }

    public List<String> getCompanyName() {
        return companyName;
    }

    public List<String> getPacURL() {
        return pacURL;
    }

    public List<String> getDesignation() {
        return designation;
    }

    public List<String> getPacURL2() {
        return pacURL2;
    }

    public List<String> getCurrentWorking() {
        return currentWorking;
    }

    public List<String> getLiveAt() {
        return liveAt;
    }

    public List<String> getEh52URL() {
        return eh52URL;
    }

    public List<String> getEh524() {
        return eh524;
    }

    public List<String> getFollowers() {
        return followers;
    }
}
