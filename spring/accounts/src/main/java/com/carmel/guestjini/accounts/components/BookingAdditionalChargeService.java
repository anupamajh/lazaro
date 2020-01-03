package com.carmel.guestjini.accounts.components;

import com.carmel.guestjini.accounts.config.YAMLConfig;
import com.carmel.guestjini.accounts.model.DTO.Guest;
import com.carmel.guestjini.accounts.response.BookingAdditionalChargeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BookingAdditionalChargeService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    YAMLConfig yamlConfig;

    public BookingAdditionalChargeResponse getRecurringPackageCharges(Guest guest) {
        return null;
    }

    public BookingAdditionalChargeResponse getOneTimePackageCharges(Guest guest) {
        return null;
    }

}
