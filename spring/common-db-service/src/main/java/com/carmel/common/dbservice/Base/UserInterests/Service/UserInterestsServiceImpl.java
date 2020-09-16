package com.carmel.common.dbservice.Base.UserInterests.Service;

import com.carmel.common.dbservice.Base.Group.Model.Group;
import com.carmel.common.dbservice.Base.Group.Service.GroupService;
import com.carmel.common.dbservice.Base.UserInterests.Model.UserInterests;
import com.carmel.common.dbservice.Base.UserInterests.Repository.UserInterestsRepository;
import com.carmel.common.dbservice.Base.UserInterests.Responce.UserInterestsResponse;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.GroupPeople;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.services.GroupPeopleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserInterestsServiceImpl implements UserInterestsService {

    private Logger logger = LoggerFactory.getLogger(UserInterestsServiceImpl.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    UserInterestsService userInterestsService;

    @Autowired
    GroupPeopleService groupPeopleService;

    @Autowired
    GroupService groupService;


    @Autowired
    UserInterestsRepository userInterestsRepository;

    @Override
    public Optional<UserInterests> findByInterestIdAndUserId(String interestId, String id) {
        return userInterestsRepository.findByInterestIdAndUserId(interestId, id);
    }

    @Override
    public UserInterests save(UserInterests userInterests) {
        return userInterestsRepository.save(userInterests);
    }

    @Override
    public UserInterestsResponse findByUserId(String id) {
        return (UserInterestsResponse) userInterestsRepository.findByUserId(id);
    }

    @Override
    public UserInterestsResponse findAll() {
        return (UserInterestsResponse) userInterestsRepository.findAll();
    }

    @Override
    public UserInterestsResponse saveUserInterests(UserInterests userInterests) throws Exception {
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
            throw ex;
        }
        return userInterestsResponse;
    }

    public UserInterestsResponse getUserInterests() throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        UserInterestsResponse userInterestsResponse = new UserInterestsResponse();
        try {
            List<UserInterests> userInterestsList = userInterestsRepository.findByUserId(userInfo.getId());
            userInterestsResponse.setUserInterestsList(userInterestsList);
            userInterestsResponse.setError("");
            userInterestsResponse.setSuccess(true);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        return userInterestsResponse;
    }
}
