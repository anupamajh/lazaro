package com.carmel.guestjini.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "carmel")
public class CarmelConfig {
    private String userInfoURL;
    private String dbServiceURL;
    private String inventoryServiceURL;
    private String accountsServiceURL;
    private String imageSavePath;

    public CarmelConfig(){}

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
}
