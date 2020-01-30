package com.carmel.common.dbservice.controller;

import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.Group;
import com.carmel.common.dbservice.model.GroupPeople;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.model.UserInterests;
import com.carmel.common.dbservice.response.UserInterestsResponse;
import com.carmel.common.dbservice.services.GroupPeopleService;
import com.carmel.common.dbservice.services.GroupService;
import com.carmel.common.dbservice.services.UserInterestsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user-interests")
public class UserInterestsController {

    Logger logger = LoggerFactory.getLogger(UserInterestsController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    UserInterestsService userInterestsService;

    @Autowired
    GroupPeopleService groupPeopleService;

    @Autowired
    GroupService groupService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public UserInterestsResponse save(@Valid @RequestBody UserInterests userInterests) {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        UserInterestsResponse userInterestsResponse = new UserInterestsResponse();
        try {
            if (userInterests.getId() == null) {
                userInterests.setId("");
            }
            if (userInterests.getOrgId() == null || userInterests.getOrgId().isEmpty()) {
                if (userInfo.getDefaultOrganization() != null)
                    userInterests.setOrgId(userInfo.getDefaultOrganization().getId());
            }
            if (userInterests.getId().equals("")) {
                userInterests.setCreatedBy(userInfo.getId());
                userInterests.setCreationTime(new Date());
                userInterests.setUserId(userInfo.getId());
            } else {
                userInterests.setLastModifiedBy(userInfo.getId());
                userInterests.setLastModifiedTime(new Date());
            }
            Optional<UserInterests> optionalUserInterests = userInterestsService.findByInterestIdAndUserId(userInterests.getInterestId(), userInfo.getId());
            optionalUserInterests.ifPresent(interests -> userInterests.setId(interests.getId()));
            userInterests.setClientId(userInfo.getClient().getClientId());
            UserInterests savedUserInterests = userInterestsService.save(userInterests);
            Optional<Group> optionalGroup = groupService.findByInterestId(savedUserInterests.getInterestId());
            if (optionalGroup.isPresent()) {
                Optional<GroupPeople> optionalGroupPeople = groupPeopleService.findByUserIdAndGroupId(userInfo.getId(), optionalGroup.get().getId());
                if (optionalGroupPeople.isPresent()) {
                    if (savedUserInterests.getIsInterested() == 0) {
                        groupPeopleService.delete(optionalGroupPeople.get());
                    }
                } else {
                    GroupPeople groupPeople = new GroupPeople();
                    groupPeople.setCreatedBy(userInfo.getId());
                    groupPeople.setCreationTime(new Date());
                    groupPeople.setUserId(userInfo.getId());
                    groupPeople.setGroupId(optionalGroup.get().getId());
                    groupPeople.setHasAcceptedInvitation(1);
                    groupPeople.setClientId(userInfo.getClient().getClientId());
                    groupPeople = groupPeopleService.save(groupPeople);
                }
            }
            userInterestsResponse.setUserInterests(savedUserInterests);
            userInterestsResponse.setSuccess(true);
            userInterestsResponse.setError("");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            userInterestsResponse.setSuccess(false);
            userInterestsResponse.setError(ex.getMessage());
        }
        return userInterestsResponse;
    }

    @RequestMapping(value = "/get-user-interests", method = RequestMethod.POST)
    public UserInterestsResponse getUserInterests() {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        UserInterestsResponse userInterestsResponse = new UserInterestsResponse();
        try {
            List<UserInterests> userInterestsList = userInterestsService.findByUserId(userInfo.getId());
            userInterestsResponse.setUserInterestsList(userInterestsList);
            userInterestsResponse.setError("");
            userInterestsResponse.setSuccess(true);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            userInterestsResponse.setSuccess(false);
            userInterestsResponse.setError(ex.getMessage());
        }
        return userInterestsResponse;

    }


}
