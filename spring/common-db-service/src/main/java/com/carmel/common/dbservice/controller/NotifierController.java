package com.carmel.common.dbservice.controller;

import com.carmel.common.dbservice.Base.UserDeviceData.Service.UserDeviceDataService;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.response.GenericResponse;
import com.turo.pushy.apns.ApnsClient;
import com.turo.pushy.apns.ApnsClientBuilder;
import com.turo.pushy.apns.PushNotificationResponse;
import com.turo.pushy.apns.auth.ApnsSigningKey;
import com.turo.pushy.apns.util.ApnsPayloadBuilder;
import com.turo.pushy.apns.util.SimpleApnsPushNotification;
import com.turo.pushy.apns.util.concurrent.PushNotificationFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Map;

@RestController
@RequestMapping(value = "/notifier")
public class NotifierController {

    Logger logger = LoggerFactory.getLogger(NotifierController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    UserDeviceDataService userDeviceDataService;

    @RequestMapping(value = "/send-by-user-name")
    public GenericResponse sendNotificationByUserName(Map<String, String> formData) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            Resource resource = resourceLoader.getResource("classpath:certificates/AuthKey_AKFM2GC67F.p8");
            File certificateFIle = resource.getFile();
            final ApnsClient apnsClient = new ApnsClientBuilder()
                    .setApnsServer(ApnsClientBuilder.DEVELOPMENT_APNS_HOST)
                    .setSigningKey(ApnsSigningKey.loadFromPkcs8File(certificateFIle,
                            "9UEK3YG88G", "AKFM2GC67F"))
                    .build();

            ApnsPayloadBuilder payloadBuilder = new ApnsPayloadBuilder();
            payloadBuilder.setAlertBody("Hello test notification");
            payloadBuilder.setAlertTitle("❌️ Exception thrown");
            payloadBuilder.setSound("default");
            String payload = payloadBuilder.buildWithDefaultMaximumLength();

            SimpleApnsPushNotification pushNotification = new SimpleApnsPushNotification("ad7257ea014e384f3b552123ceb48f8ba30fc74ac1db9b008f9632fc8994df2a", "com.carmel.iOS.GuestJini", payload);
            PushNotificationFuture<SimpleApnsPushNotification, PushNotificationResponse<SimpleApnsPushNotification>> sendNotificationFuture = apnsClient.sendNotification(pushNotification);
            PushNotificationResponse<SimpleApnsPushNotification> pushNotificationResponse = sendNotificationFuture.get();
            if (!pushNotificationResponse.isAccepted()) {
                System.err.println("Notification rejected by the APNs gateway: " + pushNotificationResponse.getRejectionReason());
            }

        } catch (Exception ex) {
            logger.error(ex.toString());
            genericResponse.setError(ex.getMessage());
            genericResponse.setSuccess(false);
        }
        return genericResponse;
    }


}
