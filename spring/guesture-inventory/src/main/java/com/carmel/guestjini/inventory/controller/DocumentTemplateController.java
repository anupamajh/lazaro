package com.carmel.guestjini.inventory.controller;

import com.carmel.guestjini.inventory.components.UserInformation;
import com.carmel.guestjini.inventory.model.DocumentTemplate;
import com.carmel.guestjini.inventory.model.Principal.UserInfo;
import com.carmel.guestjini.inventory.response.DocumentTemplateResponse;
import com.carmel.guestjini.inventory.services.DocumentTemplateService;
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

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.carmel.guestjini.inventory.specifications.DocumentTemplateSpecification.textInAllColumns;

@RestController
@RequestMapping(value = "/document-template")
public class DocumentTemplateController {
    Logger logger = LoggerFactory.getLogger(AmenityController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    DocumentTemplateService documentTemplateService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public DocumentTemplateResponse save(@Valid @RequestBody DocumentTemplate documentTemplate) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        DocumentTemplateResponse documentTemplateResponse = new DocumentTemplateResponse();
        try {
            if (documentTemplate.getId() == null) {
                documentTemplate.setId("");
            }
            if(documentTemplate.getOrgId() == null || documentTemplate.getOrgId().isEmpty()){
                documentTemplate.setOrgId(userInfo.getDefaultOrganization().getId());
            }
            if (documentTemplate.getId().equals("")) {
                documentTemplate.setCreatedBy(userInfo.getId());
                documentTemplate.setCreationTime(new Date());
            } else {
                documentTemplate.setLastModifiedBy(userInfo.getId());
                documentTemplate.setLastModifiedTime(new Date());
            }
            documentTemplate.setClientId(userInfo.getClient().getClientId());
            if (checkDuplicate(documentTemplate)) {
                documentTemplateResponse.setDocumentTemplate(documentTemplate);
                documentTemplateResponse.setSuccess(false);
                documentTemplateResponse.setError("Duplicate DocumentTemplate Name name!");
            } else {
                documentTemplateResponse.setDocumentTemplate(documentTemplateService.save(documentTemplate));
                documentTemplateResponse.setSuccess(true);
                documentTemplateResponse.setError("");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            documentTemplateResponse.setSuccess(false);
            documentTemplateResponse.setError(ex.getMessage());
        }
        return documentTemplateResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public DocumentTemplateResponse moveToTrash(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        DocumentTemplateResponse documentTemplateResponse = new DocumentTemplateResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<DocumentTemplate> optionalDocumentTemplate = documentTemplateService.findById(formData.get("id"));
            if (optionalDocumentTemplate.isPresent()) {
                DocumentTemplate documentTemplate = optionalDocumentTemplate.get();
                documentTemplate.setIsDeleted(1);
                documentTemplate.setDeletedBy(userInfo.getId());
                documentTemplate.setDeletedTime(new Date());
                documentTemplateResponse.setSuccess(true);
                documentTemplateResponse.setError("");
                documentTemplateResponse.setDocumentTemplate(documentTemplateService.save(documentTemplate));
            } else {
                documentTemplateResponse.setSuccess(false);
                documentTemplateResponse.setError("Error occurred while moving documentTemplate to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            documentTemplateResponse.setSuccess(false);
            documentTemplateResponse.setError(ex.getMessage());
        }
        return documentTemplateResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public DocumentTemplateResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        DocumentTemplateResponse documentTemplateResponse = new DocumentTemplateResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<DocumentTemplate> optionalDocumentTemplate = documentTemplateService.findById(formData.get("id"));
            if (optionalDocumentTemplate.isPresent()) {
                DocumentTemplate documentTemplate = optionalDocumentTemplate.get();
                documentTemplateResponse.setSuccess(true);
                documentTemplateResponse.setError("");
                documentTemplateResponse.setDocumentTemplate(documentTemplate);
            } else {
                documentTemplateResponse.setSuccess(false);
                documentTemplateResponse.setError("Error occurred while Fetching documentTemplate!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            documentTemplateResponse.setSuccess(false);
            documentTemplateResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return documentTemplateResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public DocumentTemplateResponse getDeleted() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        DocumentTemplateResponse documentTemplateResponse = new DocumentTemplateResponse();
        try {
            documentTemplateResponse.setDocumentTemplateList(documentTemplateService.findAllByIsDeletedAndClientId(1, userInfo.getClient().getClientId()));
            documentTemplateResponse.setSuccess(true);
            documentTemplateResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            documentTemplateResponse.setSuccess(true);
            documentTemplateResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return documentTemplateResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public DocumentTemplateResponse getAll() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        DocumentTemplateResponse documentTemplateResponse = new DocumentTemplateResponse();
        try {
            documentTemplateResponse.setDocumentTemplateList(documentTemplateService.findAllByIsDeletedAndClientId(0, userInfo.getClient().getClientId()));
            documentTemplateResponse.setSuccess(true);
            documentTemplateResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            documentTemplateResponse.setSuccess(true);
            documentTemplateResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return documentTemplateResponse;
    }

    @RequestMapping(value = "/get-document-templates", method = RequestMethod.POST)
    public DocumentTemplateResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        DocumentTemplateResponse documentTemplateResponse = new DocumentTemplateResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<DocumentTemplate> page = documentTemplateService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            documentTemplateResponse.setTotalRecords(page.getTotalElements());
            documentTemplateResponse.setTotalPages(page.getTotalPages());
            documentTemplateResponse.setDocumentTemplateList(page.getContent());
            documentTemplateResponse.setCurrentRecords(documentTemplateResponse.getDocumentTemplateList().size());
            documentTemplateResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            documentTemplateResponse.setSuccess(false);
            documentTemplateResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return documentTemplateResponse;
    }

    @RequestMapping(value = "/search-document-templates", method = RequestMethod.POST)
    public DocumentTemplateResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        DocumentTemplateResponse documentTemplateResponse = new DocumentTemplateResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
            Page<DocumentTemplate> page;
            if (searchText == null) {
                page = documentTemplateService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(),0,pageable);
            } else {
                page = documentTemplateService.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
            }
            documentTemplateResponse.setTotalRecords(page.getTotalElements());
            documentTemplateResponse.setTotalPages(page.getTotalPages());
            documentTemplateResponse.setDocumentTemplateList(page.getContent());
            documentTemplateResponse.setCurrentRecords(documentTemplateResponse.getDocumentTemplateList().size());
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            documentTemplateResponse.setSuccess(false);
            documentTemplateResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return documentTemplateResponse;
    }

    private boolean checkDuplicate(DocumentTemplate documentTemplate) {
        List<DocumentTemplate> documentTemplates;
        if (documentTemplate.getId().equals("")) {
            documentTemplates = documentTemplateService.findAllByClientIdAndTitle(documentTemplate.getClientId(), documentTemplate.getTitle());
        } else {
            documentTemplates = documentTemplateService.findAllByClientIdAndTitleAndIdIsNot(
                    documentTemplate.getClientId(), documentTemplate.getTitle(), documentTemplate.getId());
        }
        if (documentTemplates.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
