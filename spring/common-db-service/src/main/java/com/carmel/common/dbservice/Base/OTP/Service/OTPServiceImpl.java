package com.carmel.common.dbservice.Base.OTP.Service;

import com.carmel.common.dbservice.Base.OTP.Response.OTPResponse;
import com.carmel.common.dbservice.config.YAMLConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.SecureRandom;
import java.util.Map;

@Service
public class OTPServiceImpl implements OTPService {

    @Autowired
    RestTemplate externalRestTemplate;

    @Autowired
    YAMLConfig carmelConfig;
    static final String AB = "0123456789";
    static SecureRandom rnd = new SecureRandom();

    @Override
    public OTPResponse requestOTP(Map<String, String> formData) throws Exception {
        try {
            String phone = formData.get("phone");
            ResponseEntity<OTPResponse> otpResponse =
                    externalRestTemplate.getForEntity(
                            carmelConfig.getOTPGatewayURL() +
                                    carmelConfig.getOTPGateWayKey() +
                                    "/SMS/+91" + phone + "/" + generateOTP(),
                            OTPResponse.class
                    );
            return otpResponse.getBody();
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public OTPResponse verifyOTP(Map<String, String> formData) throws Exception {
        try {
            String sessionId = formData.get("sessionId");
            String otp = formData.get("otp");
            ResponseEntity<OTPResponse> otpResponse =
                    externalRestTemplate.getForEntity(
                            carmelConfig.getOTPGatewayURL() +
                                    carmelConfig.getOTPGateWayKey() +
                                    "/SMS/VERIFY/" + sessionId + "/" + otp,
                            OTPResponse.class
                    );
            return otpResponse.getBody();
        } catch (Exception ex) {
            throw ex;
        }
    }

    private String generateOTP() {
        int len = 4;
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }
}
