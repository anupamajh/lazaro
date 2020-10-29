package com.carmel.common.dbservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "carmel")
public class YAMLConfig {
    private String userInfoURL;
    private String dbServiceURL;
    private String inventoryServiceURL;
    private String accountsServiceURL;
    private String imageSavePath;
    private String fileUploadPath;
    private String linkedInConfigPath;
    private String facebookConfigPath;
    private String instagramConfigPath;
    private String twitterConfigPath;
    private String googleKey;

    private String OTPGatewayURL;

    private String OTPGateWayKey;

    public YAMLConfig() {
    }

    public String getUserInfoURL() {
        return userInfoURL;
    }

    public void setUserInfoURL(String userInfoURL) {
        this.userInfoURL = userInfoURL;
    }

    public String getDbServiceURL() {
        return dbServiceURL;
    }

    public void setDbServiceURL(String dbServiceURL) {
        this.dbServiceURL = dbServiceURL;
    }

    public String getInventoryServiceURL() {
        return inventoryServiceURL;
    }

    public void setInventoryServiceURL(String inventoryServiceURL) {
        this.inventoryServiceURL = inventoryServiceURL;
    }

    public String getAccountsServiceURL() {
        return accountsServiceURL;
    }

    public void setAccountsServiceURL(String accountsServiceURL) {
        this.accountsServiceURL = accountsServiceURL;
    }

    public String getImageSavePath() {
        return imageSavePath;
    }

    public void setImageSavePath(String imageSavePath) {
        this.imageSavePath = imageSavePath;
    }

    public String getFileUploadPath() {
        return fileUploadPath;
    }

    public void setFileUploadPath(String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }

    public String getLinkedInConfigPath() {
        return linkedInConfigPath;
    }

    public void setLinkedInConfigPath(String linkedInConfigPath) {
        this.linkedInConfigPath = linkedInConfigPath;
    }

    public String getFacebookConfigPath() {
        return facebookConfigPath;
    }

    public void setFacebookConfigPath(String facebookConfigPath) {
        this.facebookConfigPath = facebookConfigPath;
    }

    public String getInstagramConfigPath() {
        return instagramConfigPath;
    }

    public void setInstagramConfigPath(String instagramConfigPath) {
        this.instagramConfigPath = instagramConfigPath;
    }

    public String getGoogleKey() {
        return googleKey;
    }

    public void setGoogleKey(String googleKey) {
        this.googleKey = googleKey;
    }

    public String getTwitterConfigPath() {
        return twitterConfigPath;
    }

    public void setTwitterConfigPath(String twitterConfigPath) {
        this.twitterConfigPath = twitterConfigPath;
    }

    public String getOTPGatewayURL() {
        return OTPGatewayURL;
    }

    public void setOTPGatewayURL(String OTPGatewayURL) {
        this.OTPGatewayURL = OTPGatewayURL;
    }

    public String getOTPGateWayKey() {
        return OTPGateWayKey;
    }

    public void setOTPGateWayKey(String OTPGateWayKey) {
        this.OTPGateWayKey = OTPGateWayKey;
    }
}
