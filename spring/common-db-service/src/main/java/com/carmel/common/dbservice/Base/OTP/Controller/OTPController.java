package com.carmel.common.dbservice.Base.OTP.Controller;

import com.carmel.common.dbservice.Base.OTP.Response.OTPResponse;
import com.carmel.common.dbservice.Base.OTP.Service.OTPService;
import com.carmel.common.dbservice.Base.Organization.Controller.OrganizationController;
import com.carmel.common.dbservice.Base.Organization.Responce.OrganizationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/otp")
public class OTPController {
    Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    OTPService otpService;

    @RequestMapping(value = "/request-otp", method = RequestMethod.POST)
    public OTPResponse requestOTP(@RequestBody Map<String, String> formData) {
        OTPResponse otpResponse = new OTPResponse();
        try {
            otpResponse = otpService.requestOTP(formData);
        } catch (Exception ex) {
            otpResponse.setStatus("failed");
        }

        return otpResponse;
    }

    @RequestMapping(value = "/verify-otp", method = RequestMethod.POST)
    public OTPResponse verifyOTP(@RequestBody Map<String, String> formData) {
        OTPResponse otpResponse = new OTPResponse();
        try {
            otpResponse = otpService.verifyOTP(formData);
        } catch (Exception ex) {
            otpResponse.setStatus("failed");
        }

        return otpResponse;
    }

}
