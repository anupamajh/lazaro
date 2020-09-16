package com.carmel.common.dbservice.Base.UserPreference.Service;

import com.carmel.common.dbservice.Base.UserPreference.Model.UserPreference;
import com.carmel.common.dbservice.Base.UserPreference.Repository.UserPreferenceRepository;
import com.carmel.common.dbservice.Base.UserPreference.Response.UserPrefrenceResponse;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPreferenceServiceImpl implements UserPreferenceService {

    private Logger logger = LoggerFactory.getLogger(UserPreferenceServiceImpl.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    UserInformation userInformation;

    @Autowired
    UserPreferenceService userPreferenceService;

    @Autowired
    UserPreferenceRepository userPreferenceRepository;

    @Override
    public UserPreference save(UserPreference userPreference)  {
        return userPreferenceRepository.save(userPreference);
    }

    @Override
    public Optional<UserPreference> findByUserIdAndPreferenceType(String id, int preferenceType) {
        return userPreferenceRepository.findByUserIdAndPreferenceType(id, preferenceType);
    }

    @Override
    public UserPrefrenceResponse saveUserPreference(UserPreference userPreference) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        UserPrefrenceResponse userPrefrenceResponse = new UserPrefrenceResponse();
        try {
            if (userPreference.getId() == null) {
                userPreference.setId("");
            }
            logger.trace("Data:{}", objectMapper.writeValueAsString(userInfo));

            Optional<UserPreference> optionalUserPreference = userPreferenceService.findByUserIdAndPreferenceType(userInfo.getId(), userPreference.getPreferenceType());
            UserPreference tempUserPreference = userPreference;

            if (optionalUserPreference.isPresent()) {
                tempUserPreference = optionalUserPreference.get();
                logger.trace("Data:{}", objectMapper.writeValueAsString(tempUserPreference));

                tempUserPreference.setPreferenceType(userPreference.getPreferenceType());
                tempUserPreference.setIsHidden(userPreference.getIsHidden());
            } else {
                tempUserPreference.setUserId(userInfo.getId());
                tempUserPreference.setPreferenceType(userPreference.getPreferenceType());
                tempUserPreference.setIsHidden(userPreference.getIsHidden());

            }
            userPrefrenceResponse.setUserPreference(userPreferenceService.save(tempUserPreference));
            userPrefrenceResponse.setSuccess(true);
            userPrefrenceResponse.setError("");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            userPrefrenceResponse.setSuccess(false);
            userPrefrenceResponse.setError(ex.getMessage());
        }

        return userPrefrenceResponse;
    }

    @Override
    public UserPrefrenceResponse getAll() throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        UserPrefrenceResponse userPrefrenceResponse = new UserPrefrenceResponse();
        try {
            userPrefrenceResponse.setUserPreferenceList(userPreferenceRepository.findAllByUserId(userInfo.getId()));
            userPrefrenceResponse.setSuccess(true);
            userPrefrenceResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            userPrefrenceResponse.setSuccess(true);
            userPrefrenceResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return userPrefrenceResponse;
    }

    @Override
    public List<UserPreference> findAllByUserId(String id) throws Exception {
        return null;
    }




}
