package com.carmel.common.dbservice.controller;

import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.InterestCategory;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.response.InterestCategoryResponse;
import com.carmel.common.dbservice.services.InterestCategoryService;
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

import static com.carmel.common.dbservice.specifications.InterestCategorySpecification.textInAllColumns;

@RestController
@RequestMapping(value = "/interest-category")
public class InterestCategoryController {
    Logger logger = LoggerFactory.getLogger(InterestCategoryController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    InterestCategoryService interestCategoryService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public InterestCategoryResponse save(@Valid @RequestBody InterestCategory interestCategory) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
        try {
            if (interestCategory.getId() == null) {
                interestCategory.setId("");
            }
            if(interestCategory.getOrgId() == null || interestCategory.getOrgId().isEmpty()){
                interestCategory.setOrgId(userInfo.getDefaultOrganization().getId());
            }
            if (interestCategory.getId().equals("")) {
                interestCategory.setCreatedBy(userInfo.getId());
                interestCategory.setCreationTime(new Date());
            } else {
                interestCategory.setLastModifiedBy(userInfo.getId());
                interestCategory.setLastModifiedTime(new Date());
            }
            interestCategory.setClientId(userInfo.getClient().getClientId());
            if (checkDuplicate(interestCategory)) {
                interestCategoryResponse.setInterestCategory(interestCategory);
                interestCategoryResponse.setSuccess(false);
                interestCategoryResponse.setError("Duplicate Interest category Name name!");
            } else {
                interestCategoryResponse.setInterestCategory(interestCategoryService.save(interestCategory));
                interestCategoryResponse.setSuccess(true);
                interestCategoryResponse.setError("");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            interestCategoryResponse.setSuccess(false);
            interestCategoryResponse.setError(ex.getMessage());
        }
        return interestCategoryResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public InterestCategoryResponse moveToTrash(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<InterestCategory> optionalInterestCategory = interestCategoryService.findById(formData.get("id"));
            if (optionalInterestCategory.isPresent()) {
                InterestCategory interestCategory = optionalInterestCategory.get();
                interestCategory.setIsDeleted(1);
                interestCategory.setDeletedBy(userInfo.getId());
                interestCategory.setDeletedTime(new Date());
                interestCategoryResponse.setSuccess(true);
                interestCategoryResponse.setError("");
                interestCategoryResponse.setInterestCategory(interestCategoryService.save(interestCategory));
            } else {
                interestCategoryResponse.setSuccess(false);
                interestCategoryResponse.setError("Error occurred while moving interest category to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            interestCategoryResponse.setSuccess(false);
            interestCategoryResponse.setError(ex.getMessage());
        }
        return interestCategoryResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public InterestCategoryResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<InterestCategory> optionalInterestCategory = interestCategoryService.findById(formData.get("id"));
            if (optionalInterestCategory.isPresent()) {
                InterestCategory interestCategory = optionalInterestCategory.get();
                interestCategoryResponse.setSuccess(true);
                interestCategoryResponse.setError("");
                interestCategoryResponse.setInterestCategory(interestCategory);
            } else {
                interestCategoryResponse.setSuccess(false);
                interestCategoryResponse.setError("Error occurred while Fetching interest category!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            interestCategoryResponse.setSuccess(false);
            interestCategoryResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return interestCategoryResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public InterestCategoryResponse getDeleted() {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
        try {
            interestCategoryResponse.setInterestCategoryList(interestCategoryService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 1));
            interestCategoryResponse.setSuccess(true);
            interestCategoryResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            interestCategoryResponse.setSuccess(true);
            interestCategoryResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return interestCategoryResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public InterestCategoryResponse getAll() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
        try {
            interestCategoryResponse.setInterestCategoryList(interestCategoryService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0));
            interestCategoryResponse.setSuccess(true);
            interestCategoryResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            interestCategoryResponse.setSuccess(true);
            interestCategoryResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return interestCategoryResponse;
    }

    @RequestMapping(value = "/get-interest-categories", method = RequestMethod.POST)
    public InterestCategoryResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
            Page<InterestCategory> page = interestCategoryService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            interestCategoryResponse.setTotalRecords(page.getTotalElements());
            interestCategoryResponse.setTotalPages(page.getTotalPages());
            interestCategoryResponse.setInterestCategoryList(page.getContent());
            interestCategoryResponse.setCurrentRecords(interestCategoryResponse.getInterestCategoryList().size());
            interestCategoryResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            interestCategoryResponse.setSuccess(false);
            interestCategoryResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return interestCategoryResponse;
    }

    @RequestMapping(value = "/search-account-heads", method = RequestMethod.POST)
    public InterestCategoryResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InterestCategoryResponse interestCategoryResponse = new InterestCategoryResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
            Page<InterestCategory> page;
            if (searchText == null) {
                page = interestCategoryService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(),0,pageable);
            } else {
                page = interestCategoryService.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
            }
            interestCategoryResponse.setTotalRecords(page.getTotalElements());
            interestCategoryResponse.setTotalPages(page.getTotalPages());
            interestCategoryResponse.setInterestCategoryList(page.getContent());
            interestCategoryResponse.setCurrentRecords(interestCategoryResponse.getInterestCategoryList().size());
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            interestCategoryResponse.setSuccess(false);
            interestCategoryResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return interestCategoryResponse;
    }

    private boolean checkDuplicate(InterestCategory interestCategory) {
        List<InterestCategory> accountHeadList;
        if (interestCategory.getId().equals("")) {
            accountHeadList = interestCategoryService.findAllByClientIdAndIsDeletedAndName(interestCategory.getClientId(), 0, interestCategory.getName());
        } else {
            accountHeadList = interestCategoryService.findAllByClientIdAndIsDeletedAndNameAndIdIsNot(
                    interestCategory.getClientId(),0, interestCategory.getName(), interestCategory.getId());
        }
        if (accountHeadList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
