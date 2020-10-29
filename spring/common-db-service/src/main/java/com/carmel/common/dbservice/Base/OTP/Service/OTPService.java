package com.carmel.common.dbservice.Base.OTP.Service;

import com.carmel.common.dbservice.Base.OTP.Response.OTPResponse;

import java.util.Map;

public interface OTPService {
    OTPResponse requestOTP(Map<String, String> formData) throws Exception;

    OTPResponse verifyOTP(Map<String, String> formData) throws Exception;
}
