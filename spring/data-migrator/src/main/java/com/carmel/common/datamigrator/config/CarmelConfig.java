package com.carmel.common.datamigrator.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties("carmel")
public class CarmelConfig {
    public String baseUrl;
    public String userInfoURL;
    public String dbServiceURL;
    public String accountsServiceURL;
    public String bookingServiceURL;
    public String helpdeskServiceURL;
    public String paymentServiceURL;
    public String inventoryServiceURL;
    public String fileUploadPath;
    public String guestJiniServiceURL;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
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

    public String getAccountsServiceURL() {
        return accountsServiceURL;
    }

    public void setAccountsServiceURL(String accountsServiceURL) {
        this.accountsServiceURL = accountsServiceURL;
    }

    public String getBookingServiceURL() {
        return bookingServiceURL;
    }

    public void setBookingServiceURL(String bookingServiceURL) {
        this.bookingServiceURL = bookingServiceURL;
    }

    public String getHelpdeskServiceURL() {
        return helpdeskServiceURL;
    }

    public void setHelpdeskServiceURL(String helpdeskServiceURL) {
        this.helpdeskServiceURL = helpdeskServiceURL;
    }

    public String getPaymentServiceURL() {
        return paymentServiceURL;
    }

    public void setPaymentServiceURL(String paymentServiceURL) {
        this.paymentServiceURL = paymentServiceURL;
    }

    public String getInventoryServiceURL() {
        return inventoryServiceURL;
    }

    public void setInventoryServiceURL(String inventoryServiceURL) {
        this.inventoryServiceURL = inventoryServiceURL;
    }

    public String getFileUploadPath() {
        return fileUploadPath;
    }

    public void setFileUploadPath(String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }

    public String getGuestJiniServiceURL() {
        return guestJiniServiceURL;
    }

    public void setGuestJiniServiceURL(String guestJiniServiceURL) {
        this.guestJiniServiceURL = guestJiniServiceURL;
    }
}
