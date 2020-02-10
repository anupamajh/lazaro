package com.carmel.common.dbservice.controller;

import com.carmel.common.dbservice.common.GroupType;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.Group;
import com.carmel.common.dbservice.model.Interest;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.response.InterestResponse;
import com.carmel.common.dbservice.services.GroupService;
import com.carmel.common.dbservice.services.InterestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.carmel.common.dbservice.specifications.InterestSpecification.textInAllColumns;

@RestController
@RequestMapping(value = "/interest")
public class InterestController {
    Logger logger = LoggerFactory.getLogger(InterestController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    InterestService interestService;

    @Autowired
    GroupService groupService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public InterestResponse save(@Valid @RequestBody Interest interest) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        InterestResponse interestResponse = new InterestResponse();
        try {
            if (interest.getId() == null) {
                interest.setId("");
            }
            if(interest.getOrgId() == null || interest.getOrgId().isEmpty()){
                if(userInfo.getDefaultOrganization() != null) {
                    interest.setOrgId(userInfo.getDefaultOrganization().getId());
                }
            }
            if (interest.getId().equals("")) {
                interest.setCreatedBy(userInfo.getId());
                interest.setCreationTime(new Date());
            } else {
                interest.setLastModifiedBy(userInfo.getId());
                interest.setLastModifiedTime(new Date());
            }
            interest.setClientId(userInfo.getClient().getClientId());
            if (checkDuplicate(interest)) {
                interestResponse.setInterest(interest);
                interestResponse.setSuccess(false);
                interestResponse.setError("Duplicate Interest name!");
            } else {
                Interest savedInterest = interestService.save(interest);
                Group group = new Group();
                Optional<Group> optionalGroup = groupService.findByInterestId(savedInterest.getId());
                if(optionalGroup.isPresent()) {
                    group = optionalGroup.get();
                }
                group.setClientId(savedInterest.getClientId());
                group.setOrgId(savedInterest.getOrgId());
                group.setInterestId(savedInterest.getId());
                group.setInterestCategoryId(savedInterest.getInterestCategoryId());
                group.setName(savedInterest.getName());
                group.setDescription(savedInterest.getDescription());
                group.setGroupType(GroupType.GROUP_TYPE_SYSTEM_GENERATED);
                group.setCreatedBy(userInfo.getId());
                group.setCreationTime(new Date());
                Group savedGroup = groupService.save(group);
                interestResponse.setInterest(savedInterest);
                interestResponse.setSuccess(true);
                interestResponse.setError("");


            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            interestResponse.setSuccess(false);
            interestResponse.setError(ex.getMessage());
        }
        return interestResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public InterestResponse moveToTrash(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InterestResponse interestResponse = new InterestResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Interest> optionalInterest = interestService.findById(formData.get("id"));
            if (optionalInterest.isPresent()) {
                Interest interest = optionalInterest.get();
                interest.setIsDeleted(1);
                interest.setDeletedBy(userInfo.getId());
                interest.setDeletedTime(new Date());
                interestResponse.setSuccess(true);
                interestResponse.setError("");
                interestResponse.setInterest(interestService.save(interest));
            } else {
                interestResponse.setSuccess(false);
                interestResponse.setError("Error occurred while moving interest to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            interestResponse.setSuccess(false);
            interestResponse.setError(ex.getMessage());
        }
        return interestResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public InterestResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InterestResponse interestResponse = new InterestResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Interest> optionalInterest = interestService.findById(formData.get("id"));
            if (optionalInterest.isPresent()) {
                Interest interest = optionalInterest.get();
                interestResponse.setSuccess(true);
                interestResponse.setError("");
                interestResponse.setInterest(interest);
            } else {
                interestResponse.setSuccess(false);
                interestResponse.setError("Error occurred while Fetching interest!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            interestResponse.setSuccess(false);
            interestResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return interestResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public InterestResponse getDeleted() {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        InterestResponse interestResponse = new InterestResponse();
        try {
            interestResponse.setInterestList(interestService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 1));
            interestResponse.setSuccess(true);
            interestResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            interestResponse.setSuccess(true);
            interestResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return interestResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public InterestResponse getAll() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InterestResponse interestResponse = new InterestResponse();
        try {
            interestResponse.setInterestList(interestService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0));
            interestResponse.setSuccess(true);
            interestResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            interestResponse.setSuccess(true);
            interestResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return interestResponse;
    }

    @RequestMapping(value = "/get-interest-categories", method = RequestMethod.POST)
    public InterestResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InterestResponse interestResponse = new InterestResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
            Page<Interest> page = interestService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            interestResponse.setTotalRecords(page.getTotalElements());
            interestResponse.setTotalPages(page.getTotalPages());
            interestResponse.setInterestList(page.getContent());
            interestResponse.setCurrentRecords(interestResponse.getInterestList().size());
            interestResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            interestResponse.setSuccess(false);
            interestResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return interestResponse;
    }

    @RequestMapping(value = "/search-account-heads", method = RequestMethod.POST)
    public InterestResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        InterestResponse interestResponse = new InterestResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
            Page<Interest> page;
            if (searchText == null) {
                page = interestService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(),0,pageable);
            } else {
                page = interestService.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
            }
            interestResponse.setTotalRecords(page.getTotalElements());
            interestResponse.setTotalPages(page.getTotalPages());
            interestResponse.setInterestList(page.getContent());
            interestResponse.setCurrentRecords(interestResponse.getInterestList().size());
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            interestResponse.setSuccess(false);
            interestResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return interestResponse;
    }

    private boolean checkDuplicate(Interest interest) {
        List<Interest> accountHeadList;
        if (interest.getId().equals("")) {
            accountHeadList = interestService.findAllByClientIdAndIsDeletedAndName(interest.getClientId(), 0, interest.getName());
        } else {
            accountHeadList = interestService.findAllByClientIdAndIsDeletedAndNameAndIdIsNot(
                    interest.getClientId(),0, interest.getName(), interest.getId());
        }
        if (accountHeadList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
