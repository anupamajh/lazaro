package com.carmel.common.dbservice.Base.GroupConversation.Service;

import com.carmel.common.dbservice.Base.GroupConversation.DTO.GroupConversationDTO;
import com.carmel.common.dbservice.Base.GroupConversation.Model.GroupConversation;
import com.carmel.common.dbservice.Base.GroupConversation.Repository.GroupConversationRepository;
import com.carmel.common.dbservice.Base.GroupConversation.Responce.GroupConversationResponse;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.services.GroupPeopleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class GroupConversationServiceImpl implements GroupConversationService {

   private Logger logger = LoggerFactory.getLogger(GroupPeopleServiceImpl.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    GroupConversationService groupConversationService;

    @Autowired
    EntityManager entityManager;

    @Autowired
    GroupConversationRepository groupConversationRepository;

    @Override
    public GroupConversation save(GroupConversation groupConversation) {
        return groupConversationRepository.save(groupConversation);
    }

    @Override
    public GroupConversationResponse saveGroupConcersation(Map<String,String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        GroupConversationResponse groupConversationResponse = new GroupConversationResponse();
        try {
            String groupId = formData.get("groupId");
            String message = formData.get("message");
            GroupConversation groupConversation = new GroupConversation();
            groupConversation.setClientId(userInfo.getClient().getClientId());
            groupConversation.setGroupId(groupId);
            groupConversation.setUserId(userInfo.getId());
            groupConversation.setDisplayName(userInfo.getFullName());
            groupConversation.setMessage(message);
            groupConversation.setCreationTime(new Date());
            groupConversation.setCreatedBy(userInfo.getId());
            groupConversationResponse.setGroupConversation(groupConversationRepository.save(groupConversation));
            groupConversationResponse.setSuccess(true);
            groupConversationResponse.setError("");
        } catch (Exception ex) {

            logger.error(ex.getMessage(), ex);
           throw ex;
        }
        return groupConversationResponse;
    }

    @Override
    public GroupConversationResponse getByGroup(Map<String,String> formData) throws Exception {
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        GroupConversationResponse groupConversationResponse = new GroupConversationResponse();
        try {
            String groupId = formData.get("groupId");
            List<GroupConversation> groupConversationList = groupConversationRepository.findAllByGroupId(groupId);
            List<GroupConversationDTO> conversationDTOS = new ArrayList<>();
            groupConversationList.forEach(groupConversation -> {
                GroupConversationDTO groupConversationDTO = new GroupConversationDTO(groupConversation);
                if (groupConversation.getUserId().equals(userInfo.getId())) {
                    groupConversationDTO.setIsItMe(1);
                }
                conversationDTOS.add(groupConversationDTO);
            });

            groupConversationResponse.setGroupConversationListDTO(conversationDTOS);
            groupConversationResponse.setSuccess(true);
            groupConversationResponse.setError("");
        } catch (Exception ex) {

            logger.error(ex.getMessage(), ex);
          throw ex;
        }
        return groupConversationResponse;
    }

    @Override
    public GroupConversationResponse findAllByGroupId(String groupId) {
        GroupConversationResponse groupConversationResponse = new GroupConversationResponse();
        groupConversationResponse.setSuccess(true);
        groupConversationResponse.setGroupConversationList(groupConversationRepository.findAllByGroupId(groupId));
        return groupConversationResponse;
    }
}
