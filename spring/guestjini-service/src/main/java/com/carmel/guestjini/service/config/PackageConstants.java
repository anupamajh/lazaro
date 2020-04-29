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
    private Map<String, String> propertyConstants;
    private Map<String, String> allowedPropertyTypes;

    public Map<String, String> getPackageConstants() {
        return packageConstants;
    }

    public void setPackageConstants(Map<String, String> packageConstants) {
        this.packageConstants = packageConstants;
    }

    public Map<String, String> getPropertyConstants() {
        return propertyConstants;
    }

    public void setPropertyConstants(Map<String, String> propertyConstants) {
        this.propertyConstants = propertyConstants;
    }

    public Map<String, String> getAllowedPropertyTypes() {
        return allowedPropertyTypes;
    }

    public void setAllowedPropertyTypes(Map<String, String> allowedPropertyTypes) {
        this.allowedPropertyTypes = allowedPropertyTypes;
    }
}
