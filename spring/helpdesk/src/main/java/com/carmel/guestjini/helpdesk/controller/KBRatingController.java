package com.carmel.guestjini.helpdesk.controller;

import com.carmel.guestjini.helpdesk.components.UserInformation;
import com.carmel.guestjini.helpdesk.model.KBRating;
import com.carmel.guestjini.helpdesk.model.Principal.UserInfo;
import com.carmel.guestjini.helpdesk.response.KBRatingPercentResponse;
import com.carmel.guestjini.helpdesk.response.KBRatingResponse;
import com.carmel.guestjini.helpdesk.service.KBRatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/kb-rating")
public class KBRatingController {
    Logger logger = LoggerFactory.getLogger(KBController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    KBRatingService kbRatingService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public KBRatingResponse save(@RequestBody KBRating kbRating) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        KBRatingResponse kbRatingResponse = new KBRatingResponse();
        try {
            if (kbRating.getId() == null) {
                kbRating.setId("");
            }
            if (kbRating.getOrgId() == null || kbRating.getOrgId() == "") {
                kbRating.setOrgId(userInfo.getDefaultOrganization().getId());
            }
            kbRating.setClientId(userInfo.getClient().getClientId());
            if (kbRating.getId().equals("")) {
                kbRating.setRatingBy(userInfo.getId());
                kbRating.setCreatedBy(userInfo.getId());
                kbRating.setCreationTime(new Date());
            } else {
                kbRating.setLastModifiedBy(userInfo.getId());
                kbRating.setLastModifiedTime(new Date());
            }

            Optional<KBRating> optionalKBRating = kbRatingService.findByKbIdAndRatingBy(kbRating.getKbId(), kbRating.getRatingBy());
            optionalKBRating.ifPresent(rating -> kbRating.setId(rating.getId()));
            kbRatingResponse.setKbRating(kbRatingService.save(kbRating));
            kbRatingResponse.setSuccess(true);
            kbRatingResponse.setError("");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kbRatingResponse.setSuccess(false);
            kbRatingResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kbRatingResponse;
    }

    @RequestMapping(value = "/get-my-rating", method = RequestMethod.POST)
    public KBRatingResponse getMyRating(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        KBRatingResponse kbRatingResponse = new KBRatingResponse();
        try {
            Optional<KBRating> optionalKBRating = kbRatingService.findByKbIdAndRatingBy(formData.get("kbId"), userInfo.getId());
            optionalKBRating.ifPresent(kbRatingResponse::setKbRating);
            kbRatingResponse.setSuccess(true);
            kbRatingResponse.setError("");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kbRatingResponse.setSuccess(false);
            kbRatingResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kbRatingResponse;
    }
        @RequestMapping(value = "/get-rating-percent", method = RequestMethod.POST)
    public KBRatingPercentResponse getRatingPercent(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        KBRatingPercentResponse kbRatingPercentResponse = new KBRatingPercentResponse();
        try {

            kbRatingPercentResponse = kbRatingService.getPercentageData(formData.get("kbId"));
            kbRatingPercentResponse.setSuccess(true);
            kbRatingPercentResponse.setError("");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kbRatingPercentResponse.setSuccess(false);
            kbRatingPercentResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kbRatingPercentResponse;
    }


}
