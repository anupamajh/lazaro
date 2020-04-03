package com.carmel.guestjini.service.controller.HelpDesk;


import com.carmel.guestjini.service.components.UserInformation;
import com.carmel.guestjini.service.model.HelpDesk.KBReview;
import com.carmel.guestjini.service.model.Principal.UserInfo;
import com.carmel.guestjini.service.response.HelpDesk.KBReviewResponse;
import com.carmel.guestjini.service.service.HelpDesk.KBReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/kb-review")
public class KBReviewController {

    Logger logger = LoggerFactory.getLogger(KBController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    KBReviewService kbReviewService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public KBReviewResponse save(@RequestBody KBReview kbReview) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        KBReviewResponse kbReviewResponse = new KBReviewResponse();
        try {
            if (kbReview.getId() == null) {
                kbReview.setId("");
            }
            if (kbReview.getOrgId() == null || kbReview.getOrgId() == "") {
                if(userInfo.getDefaultOrganization() != null) {
                    kbReview.setOrgId(userInfo.getDefaultOrganization().getId());
                }
            }
            kbReview.setClientId(userInfo.getClient().getClientId());
            if (kbReview.getId().equals("")) {
                kbReview.setReviewBy(userInfo.getId());
                kbReview.setReviewByName(userInfo.getFullName());
                kbReview.setCreatedBy(userInfo.getId());
                kbReview.setCreationTime(new Date());
            }
            kbReviewResponse.setKbReview(kbReviewService.save(kbReview));
            kbReviewResponse.setSuccess(true);
            kbReviewResponse.setError("");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kbReviewResponse.setSuccess(false);
            kbReviewResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kbReviewResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public KBReviewResponse moveToTrash(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        KBReviewResponse kbReviewResponse = new KBReviewResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<KBReview> optionalKBReview = kbReviewService.findById(formData.get("id"));
            if (optionalKBReview.isPresent()) {
                KBReview kbReview = optionalKBReview.get();
                kbReview.setIsDeleted(1);
                kbReview.setDeletedBy(userInfo.getId());
                kbReview.setDeletedTime(new Date());
                kbReviewResponse.setSuccess(true);
                kbReviewResponse.setError("");
                kbReviewResponse.setKbReview(kbReviewService.save(kbReview));
            } else {
                kbReviewResponse.setSuccess(false);
                kbReviewResponse.setError("Error occurred while moving KB to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kbReviewResponse.setSuccess(false);
            kbReviewResponse.setError(ex.getMessage());
        }
        return kbReviewResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public KBReviewResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        KBReviewResponse kbReviewResponse = new KBReviewResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<KBReview> optionalKBReview = kbReviewService.findById(formData.get("id"));
            if (optionalKBReview.isPresent()) {
                KBReview kbReview = optionalKBReview.get();
                kbReviewResponse.setSuccess(true);
                kbReviewResponse.setError("");
                kbReviewResponse.setKbReview(kbReview);
            } else {
                kbReviewResponse.setSuccess(false);
                kbReviewResponse.setError("Error occurred while Fetching KB Review!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kbReviewResponse.setSuccess(false);
            kbReviewResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kbReviewResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public KBReviewResponse getDeleted(@RequestBody Map<String , String> formData) {
        logger.trace("Entering");
        KBReviewResponse kbReviewResponse = new KBReviewResponse();
        try {
            kbReviewResponse.setKbReviewList(kbReviewService.findAllByKbIdAndIsDeleted(formData.get("kbId"),1 ));
            kbReviewResponse.setSuccess(true);
            kbReviewResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kbReviewResponse.setSuccess(true);
            kbReviewResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kbReviewResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public KBReviewResponse getAll(@RequestBody Map<String , String> formData) {
        logger.trace("Entering");
        KBReviewResponse kbReviewResponse = new KBReviewResponse();
        try {
            kbReviewResponse.setKbReviewList(kbReviewService.findAllByKbIdAndIsDeleted(formData.get("kbId"),0 ));
            kbReviewResponse.setSuccess(true);
            kbReviewResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kbReviewResponse.setSuccess(true);
            kbReviewResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kbReviewResponse;
    }

    @RequestMapping(value = "/get-kb-list", method = RequestMethod.POST)
    public KBReviewResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        KBReviewResponse kbReviewResponse = new KBReviewResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("creationTime"));
            Page<KBReview> page = kbReviewService.findAllByKbIdAndIsDeleted(formData.get("kbId"),0, pageable);
            kbReviewResponse.setTotalRecords(page.getTotalElements());
            kbReviewResponse.setTotalPages(page.getTotalPages());
            kbReviewResponse.setKbReviewList(page.getContent());
            kbReviewResponse.setCurrentRecords(kbReviewResponse.getKbReviewList().size());
            kbReviewResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            kbReviewResponse.setSuccess(false);
            kbReviewResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kbReviewResponse;
    }


}
