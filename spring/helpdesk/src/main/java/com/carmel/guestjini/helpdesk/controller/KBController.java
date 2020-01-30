package com.carmel.guestjini.helpdesk.controller;

import com.carmel.guestjini.helpdesk.components.UserInformation;
import com.carmel.guestjini.helpdesk.model.KB;
import com.carmel.guestjini.helpdesk.model.Principal.UserInfo;
import com.carmel.guestjini.helpdesk.response.KBResponse;
import com.carmel.guestjini.helpdesk.service.KBService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.carmel.guestjini.helpdesk.specification.KBSpecification.textInAllColumns;

@RestController
@RequestMapping(value = "/kb")
public class KBController {
    Logger logger = LoggerFactory.getLogger(KBController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    KBService kbService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public KBResponse save(@Valid @RequestBody KB kb) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        KBResponse kbResponse = new KBResponse();
        try {
            if (kb.getId() == null) {
                kb.setId("");
            }
            if (kb.getOrgId() == null || kb.getOrgId() == "") {
                kb.setOrgId(userInfo.getDefaultOrganization().getId());
            }
            kb.setClientId(userInfo.getClient().getClientId());
            if (kb.getId().equals("")) {
                kb.setCreatedBy(userInfo.getId());
                kb.setCreationTime(new Date());
            } else {
                kb.setLastModifiedBy(userInfo.getId());
                kb.setLastModifiedTime(new Date());
            }
            if (checkDuplicate(kb)) {
                kbResponse.setKb(kb);
                kbResponse.setSuccess(false);
                kbResponse.setError("Duplicate KB name!");
            } else {
                kbResponse.setKb(kbService.save(kb));
                kbResponse.setSuccess(true);
                kbResponse.setError("");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kbResponse.setSuccess(false);
            kbResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kbResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public KBResponse moveToTrash(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        KBResponse kbResponse = new KBResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<KB> optionalKB = kbService.findById(formData.get("id"));
            if (optionalKB.isPresent()) {
                KB kb = optionalKB.get();
                kb.setIsDeleted(1);
                kb.setDeletedBy(userInfo.getId());
                kb.setDeletedTime(new Date());
                kbResponse.setSuccess(true);
                kbResponse.setError("");
                kbResponse.setKb(kbService.save(kb));
            } else {
                kbResponse.setSuccess(false);
                kbResponse.setError("Error occurred while moving KB to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kbResponse.setSuccess(false);
            kbResponse.setError(ex.getMessage());
        }
        return kbResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public KBResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        KBResponse kbResponse = new KBResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<KB> optionalKB = kbService.findById(formData.get("id"));
            if (optionalKB.isPresent()) {
                KB kb = optionalKB.get();
                kbResponse.setSuccess(true);
                kbResponse.setError("");
                kbResponse.setKb(kb);
            } else {
                kbResponse.setSuccess(false);
                kbResponse.setError("Error occurred while Fetching KB!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kbResponse.setSuccess(false);
            kbResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kbResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public KBResponse getDeleted() {
        logger.trace("Entering");
        UserInfo userInfo = userInformation.getUserInfo();
        KBResponse kbResponse = new KBResponse();
        try {
            kbResponse.setKbList(kbService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(),1 ));
            kbResponse.setSuccess(true);
            kbResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kbResponse.setSuccess(true);
            kbResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kbResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public KBResponse getAll() {
        logger.trace("Entering");
        UserInfo userInfo = userInformation.getUserInfo();
        KBResponse kbResponse = new KBResponse();
        try {
            kbResponse.setKbList(kbService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(),0 ));
            kbResponse.setSuccess(true);
            kbResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            kbResponse.setSuccess(true);
            kbResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kbResponse;
    }

    @RequestMapping(value = "/get-kb-list", method = RequestMethod.POST)
    public KBResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        KBResponse kbResponse = new KBResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("topicTitle"));
            Page<KB> page = kbService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(),0, pageable);
            kbResponse.setTotalRecords(page.getTotalElements());
            kbResponse.setTotalPages(page.getTotalPages());
            kbResponse.setKbList(page.getContent());
            kbResponse.setCurrentRecords(kbResponse.getKbList().size());
            kbResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            kbResponse.setSuccess(false);
            kbResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kbResponse;
    }

    @RequestMapping(value = "/search-kbs", method = RequestMethod.POST)
    public KBResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        KBResponse kbResponse = new KBResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("topicTitle"));
            Page<KB> page;
            if (searchText == null) {
                 page = kbService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(),0, pageable);
            } else {
                page = kbService.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
            }
            kbResponse.setTotalRecords(page.getTotalElements());
            kbResponse.setTotalPages(page.getTotalPages());
            kbResponse.setKbList(page.getContent());
            kbResponse.setCurrentRecords(kbResponse.getKbList().size());
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            kbResponse.setSuccess(false);
            kbResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return kbResponse;
    }

    private boolean checkDuplicate(KB kb) {
        List<KB> kbList;
        if (kb.getId().equals("")) {
            kbList = kbService.findAllByClientIdAndIsDeletedAndTopicTitle(
                    kb.getClientId(),
                    0,
                    kb.getTopicTitle()
            );
        } else {
            kbList = kbService.findAllByClientIdAndIsDeletedAndTopicTitleAndIdIsNot(
                    kb.getClientId(),
                    0,
                    kb.getTopicTitle(),
                    kb.getId()
            );
        }
        if (kbList.size() > 0) {
            return true;
        }
        return false;
    }

    @PostMapping("/kb-author/pic")
    public String kbAuthorPic(@RequestBody Map<String, String> formData) {
        try {
            Optional<KB> optionalKB = kbService.findById(formData.get("id"));
            if (optionalKB.isPresent()) {
                KB kb = optionalKB.get();
                if(kb.getAuthorLogoPath().trim() != "") {
                    String logoPath = kb.getAuthorLogoPath();
                    File myPic = new File(logoPath);
                    FileInputStream fileInputStreamReader = new FileInputStream(myPic);
                    byte[] bytes = new byte[(int) myPic.length()];
                    fileInputStreamReader.read(bytes);
                    return new String(Base64.encodeBase64(bytes), "UTF-8");
                }
            }
        }catch (Exception ex){
            logger.trace(ex.getMessage());
        }
        return "";
    }

}
