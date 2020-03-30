package com.carmel.common.authserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties("carmel")
public class CarmelConfig {
    public String guestureRedirectURL;

    public String getGuestureRedirectURL() {
        return guestureRedirectURL;
    }

    public void setGuestureRedirectURL(String guestureRedirectURL) {
        this.guestureRedirectURL = guestureRedirectURL;
    }
}
