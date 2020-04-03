package com.carmel.guestjini.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "carmel")
public class PackageConstants {
    private Map<String, String> packageConstants;

    public Map<String, String> getPackageConstants() {
        return packageConstants;
    }

    public void setPackageConstants(Map<String, String> packageConstants) {
        this.packageConstants = packageConstants;
    }
}
