package com.carmel.common.dbservice.controller;


import com.carmel.common.dbservice.common.GroupType;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.*;
import com.carmel.common.dbservice.model.DTO.GroupDTO;
import com.carmel.common.dbservice.response.GroupResponse;
import com.carmel.common.dbservice.services.*;
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
import java.util.*;

import static com.carmel.common.dbservice.specifications.GroupSpecification.textInAllColumns;

@RestController
@RequestMapping(value = "/groups")
public class GroupController {
    Logger logger = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    GroupService groupService;

    @Autowired
    InterestCategoryService interestCategoryService;

    @Autowired
    GroupPeopleService groupPeopleService;

    @Autowired
    AddressBookService addressBookService;

    @Autowired
    UserInterestsService userInterestsService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public GroupResponse save(@Valid @RequestBody Group group) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            if (group.getId() == null) {
                group.setId("");
            }
            if (group.getOrgId() == null || group.getOrgId().isEmpty()) {
                group.setOrgId(userInfo.getDefaultOrganization().getId());
            }
            if (group.getId().equals("")) {
                group.setCreatedBy(userInfo.getId());
                group.setCreationTime(new Date());
                group.setGroupOwnerId(userInfo.getId());
                group.setGroupType(GroupType.GROUP_TYPE_USER_GENERATED);
            } else {
                group.setLastModifiedBy(userInfo.getId());
                group.setLastModifiedTime(new Date());
            }
            group.setClientId(userInfo.getClient().getClientId());
            if (checkDuplicate(group)) {
                groupResponse.setGroup(group);
                groupResponse.setSuccess(false);
                groupResponse.setError("Duplicate Group name!");
            } else {
                groupResponse.setGroup(groupService.save(group));
                groupResponse.setSuccess(true);
                groupResponse.setError("");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            groupResponse.setSuccess(false);
            groupResponse.setError(ex.getMessage());
        }
        return groupResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public GroupResponse moveToTrash(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Group> optionalInterest = groupService.findById(formData.get("id"));
            if (optionalInterest.isPresent()) {
                Group group = optionalInterest.get();
                group.setIsDeleted(1);
                group.setDeletedBy(userInfo.getId());
                group.setDeletedTime(new Date());
                groupResponse.setSuccess(true);
                groupResponse.setError("");
                groupResponse.setGroup(groupService.save(group));
            } else {
                groupResponse.setSuccess(false);
                groupResponse.setError("Error occurred while moving group to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            groupResponse.setSuccess(false);
            groupResponse.setError(ex.getMessage());
        }
        return groupResponse;
    }

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public GroupResponse subscribe(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            GroupPeople groupPeople = new GroupPeople();
            groupPeople.setCreatedBy(userInfo.getId());
            groupPeople.setCreationTime(new Date());
            groupPeople.setUserId(userInfo.getId());
            groupPeople.setGroupId(formData.get("groupId"));
            groupPeople.setHasAcceptedInvitation(1);
            groupPeople.setClientId(userInfo.getClient().getClientId());
            groupPeople = groupPeopleService.save(groupPeople);
            groupResponse.setSuccess(true);
            groupResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            groupResponse.setSuccess(false);
            groupResponse.setError(ex.getMessage());
        }
        return groupResponse;
    }


    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public GroupResponse get(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Group> optionalInterest = groupService.findById(formData.get("id"));
            if (optionalInterest.isPresent()) {
                Group group = optionalInterest.get();
                GroupDTO groupDTO = new GroupDTO(group);
                if (group.getInterestCategoryId() != null && !group.getInterestCategoryId().equals("")) {
                    Optional<InterestCategory> optionalInterestCategory = interestCategoryService.findById(group.getInterestCategoryId());
                    optionalInterestCategory.ifPresent(interestCategory -> groupDTO.setInterestCategoryName(interestCategory.getName()));
                }
                List<GroupPeople> groupPeopleList = groupPeopleService.findAllByGroupIdAndHasAcceptedInvitationAndUserIdIsNot(group.getId(), 1, userInfo.getId());
                Optional<GroupPeople> optionalGroupPeople = groupPeopleService.findByUserIdAndGroupId(userInfo.getId(), group.getId());
                if (optionalGroupPeople.isPresent()) {
                    groupDTO.setIsSubscribed(1);
                    groupDTO.setSubscribedDate(optionalGroupPeople.get().getCreationTime());
                }
                List<AddressBook> addressBookList = new ArrayList<>();
                groupPeopleList.forEach(groupPeople -> {
                    Optional<AddressBook> optionalAddressBook = addressBookService.findByUserId(groupPeople.getUserId());
                    if (optionalAddressBook.isPresent()) {
                        AddressBook addressBook = optionalAddressBook.get();
                        addressBook.setCreationTime(groupPeople.getCreationTime());
                        addressBookList.add(addressBook);
                    }
                });
                groupResponse.setGroupPeople(addressBookList);
                groupResponse.setGroupDTO(groupDTO);
                groupResponse.setSuccess(true);
                groupResponse.setError("");
            } else {
                groupResponse.setSuccess(false);
                groupResponse.setError("Error occurred while Fetching group!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            groupResponse.setSuccess(false);
            groupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return groupResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.POST)
    public GroupResponse getDeleted() {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            groupResponse.setGroupList(groupService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 1));
            groupResponse.setSuccess(true);
            groupResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            groupResponse.setSuccess(true);
            groupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return groupResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public GroupResponse getAll() {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            groupResponse.setGroupList(groupService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0));
            groupResponse.setSuccess(true);
            groupResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            groupResponse.setSuccess(true);
            groupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return groupResponse;
    }

    @RequestMapping(value = "/get-all-by-type", method = RequestMethod.POST)
    public GroupResponse getAllByType(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            List<InterestCategory> interestCategoryList =
                    interestCategoryService
                            .findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0);
            List<GroupPeople> groupPeopleList = groupPeopleService.findByUserId(userInfo.getId());
            List<UserInterests> userInterests = userInterestsService.findByUserId(userInfo.getId());

                    groupResponse.setGroupListWithInterestCategory(groupService
                            .findAllByClientIdAndIsDeletedAndGroupType(
                                    userInfo.getClient().getClientId(),
                                    0,
                                    Integer.parseInt(formData.get("groupType"))
                            ),
                    interestCategoryList,
                    groupPeopleList,
                            userInterests
            );
            groupResponse.setSuccess(true);
            groupResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            groupResponse.setSuccess(true);
            groupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return groupResponse;
    }

    @RequestMapping(value = "/get-groups", method = RequestMethod.POST)
    public GroupResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
            Page<Group> page = groupService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            groupResponse.setTotalRecords(page.getTotalElements());
            groupResponse.setTotalPages(page.getTotalPages());
            groupResponse.setGroupList(page.getContent());
            groupResponse.setCurrentRecords(groupResponse.getGroupList().size());
            groupResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            groupResponse.setSuccess(false);
            groupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return groupResponse;
    }

    @RequestMapping(value = "/search-groups", method = RequestMethod.POST)
    public GroupResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
            Page<Group> page;
            if (searchText == null) {
                page = groupService.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            } else {
                page = groupService.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
            }
            groupResponse.setTotalRecords(page.getTotalElements());
            groupResponse.setTotalPages(page.getTotalPages());
            groupResponse.setGroupList(page.getContent());
            groupResponse.setCurrentRecords(groupResponse.getGroupList().size());
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.getMessage(), ex);
            groupResponse.setSuccess(false);
            groupResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return groupResponse;
    }

    private boolean checkDuplicate(Group group) {
        List<Group> accountHeadList;
        if (group.getId().equals("")) {
            accountHeadList = groupService.findAllByClientIdAndIsDeletedAndName(group.getClientId(), 0, group.getName());
        } else {
            accountHeadList = groupService.findAllByClientIdAndIsDeletedAndNameAndIdIsNot(
                    group.getClientId(), 0, group.getName(), group.getId());
        }
        if (accountHeadList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}