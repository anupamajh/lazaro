package com.carmel.guestjini.inventory.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "carmel")
public class YAMLConfig {
    private String userInfoURL;
    private String dbServiceURL;

    public YAMLConfig(){}

    private static YAMLConfig _instance;

    public static YAMLConfig getConfig(){
        if(_instance == null){
            _instance = new YAMLConfig();
        }
        return _instance;
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
}
