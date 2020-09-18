package com.carmel.common.dbservice.Base.Group.Service;

import com.carmel.common.dbservice.Base.AddressBook.DTO.AddressBookDTO;
import com.carmel.common.dbservice.Base.AddressBook.Model.AddressBook;
import com.carmel.common.dbservice.Base.AddressBook.Service.AddressBookService;
import com.carmel.common.dbservice.Base.Group.Controller.GroupController;
import com.carmel.common.dbservice.Base.Group.DTO.GroupDTO;
import com.carmel.common.dbservice.Base.Group.Model.Group;
import com.carmel.common.dbservice.Base.Group.Repository.GroupRepository;
import com.carmel.common.dbservice.Base.Group.Response.GroupResponse;
import com.carmel.common.dbservice.Base.InterestCategory.DTO.InterestCategoryDTO;
import com.carmel.common.dbservice.Base.InterestCategory.Model.InterestCategory;
import com.carmel.common.dbservice.Base.InterestCategory.Service.InterestCategoryService;
import com.carmel.common.dbservice.Base.UserInterests.DTO.UserInterestsDTO;
import com.carmel.common.dbservice.Base.UserInterests.Model.UserInterests;
import com.carmel.common.dbservice.Base.UserInterests.Service.UserInterestsService;
import com.carmel.common.dbservice.common.Search.SearchBuilder;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.*;
import com.carmel.common.dbservice.services.GroupPeopleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

import static com.carmel.common.dbservice.Base.Group.Specification.GroupSpecification.textInAllColumns;

@Service
public class GroupServiceImpl implements GroupService {

    private Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    GroupService groupService;

    @Autowired
    UserInformation userInformation;

    @Autowired
    InterestCategoryService interestCategoryService;

    @Autowired
    GroupPeopleService groupPeopleService;

    @Autowired
    AddressBookService addressBookService;

    @Autowired
    UserInterestsService userInterestsService;

    @Autowired
    EntityManager entityManager;

    @Override
    public Optional<Group> findByInterestId(String id) {
        return groupRepository.findByInterestId(id);
    }

    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public GroupResponse saveGroup(Group group) throws Exception {
        UserInfo userInfo = null;
        try {
            userInfo = userInformation.getUserInfo();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            if (group.getId() == null) {
                group.setId("");
            }
            if (group.getId().equals("")) {
                group.setCreatedBy(userInfo.getId());
                group.setCreationTime(new Date());
                group.setGroupOwnerId(userInfo.getId());
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
                Group savedGroup = this.save(group);
                if (group.getId().equals("")) {
                    GroupPeople groupPeople = new GroupPeople();
                    groupPeople.setCreatedBy(userInfo.getId());
                    groupPeople.setCreationTime(new Date());
                    groupPeople.setUserId(userInfo.getId());
                    groupPeople.setGroupId(savedGroup.getId());
                    groupPeople.setHasAcceptedInvitation(1);
                    groupPeople.setClientId(userInfo.getClient().getClientId());
                    groupPeople = groupPeopleService.save(groupPeople);
                }
                groupResponse.setGroup(savedGroup);
                groupResponse.setSuccess(true);
                groupResponse.setError("");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        return groupResponse;
    }

    @Override
    public GroupResponse moveToTrash(Map<String, String> formData) throws Exception {
        UserInfo userInfo = null;
        try {
            userInfo = userInformation.getUserInfo();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Group> optionalInterest = groupRepository.findById(formData.get("id"));
            if (optionalInterest.isPresent()) {
                Group group = optionalInterest.get();
                group.setIsDeleted(1);
                group.setDeletedBy(userInfo.getId());
                group.setDeletedTime(new Date());
                groupResponse.setSuccess(true);
                groupResponse.setError("");
                groupResponse.setGroup(this.save(group));
            } else {
                groupResponse.setSuccess(false);
                groupResponse.setError("Error occurred while moving group to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        return groupResponse;
    }

    @Override
    public GroupResponse subscribe(Map<String, String> formData) throws Exception {
        UserInfo userInfo = null;
        try {
            userInfo = userInformation.getUserInfo();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            GroupPeople groupPeople;
            Optional<GroupPeople> optionalGroupPeople = groupPeopleService.findByUserIdAndGroupId(userInfo.getId(), formData.get("groupId"));
            if (optionalGroupPeople.isPresent()) {
                groupPeople = optionalGroupPeople.get();
            } else {
                groupPeople = new GroupPeople();
            }
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
            throw ex;
        }
        return groupResponse;
    }

    @Override
    public GroupResponse get(Map<String, String> formData) throws Exception {
        UserInfo userInfo = null;
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            userInfo = userInformation.getUserInfo();
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<Group> optionalGroup = groupService.findById(formData.get("id"));
            if (optionalGroup.isPresent()) {

                Group group = optionalGroup.get();
                int inputGroupType = group.getGroupType();
                if (group.getGroupOwnerId() != null) {
                    if (group.getGroupOwnerId().equals(userInfo.getId())) {
                        inputGroupType = 3;
                    }
                }
                GroupDTO groupDTO = new GroupDTO(group);
                if (group.getInterestCategoryId() != null && !group.getInterestCategoryId().equals("")) {
                    Optional<InterestCategory> optionalInterestCategory = interestCategoryService.findById(group.getInterestCategoryId());
                    optionalInterestCategory.ifPresent(interestCategory -> groupDTO.setInterestCategoryName(interestCategory.getName()));
                }

                List<AddressBook> addressBookList = addressBookService.findAllByIsDeleted(0).getAddressBookList();
                List<GroupPeople> allGroupPeople = groupPeopleService.findAllByGroupId(formData.get("id"));
                List<GroupPeople> groupPeopleList = groupPeopleService
                        .findAllByGroupIdAndHasAcceptedInvitationAndUserIdIsNot(group.getId(), 1, userInfo.getId());
                List<AddressBookDTO> addressBookDTOS = new ArrayList<>();
                String userId = userInfo.getId();

                if (inputGroupType == 3) {
                    addressBookList.forEach(addressBook -> {
                        Optional<GroupPeople> optionalGroupPeople
                                = allGroupPeople.stream()
                                .filter(
                                        gp -> gp.getUserId()
                                                .equals(addressBook.getUserId())
                                ).findFirst();
                        AddressBookDTO addressBookDTO = new AddressBookDTO(addressBook);
                        if (optionalGroupPeople.isPresent()) {
                            addressBookDTO.setIsInvited(1);
                            addressBookDTO.setHasAcceptedInvitation(optionalGroupPeople.get().getHasAcceptedInvitation());
                            if (addressBookDTO.getUserId().equals(userId)) {
                                addressBookDTO.setIsMe(1);
                            } else {
                                addressBookDTO.setIsMe(0);
                            }
                        } else {
                            addressBookDTO.setIsInvited(0);
                        }
                        addressBookDTOS.add(addressBookDTO);
                    });
                } else {
                    groupPeopleList.forEach(groupPeople -> {
                        try {
                            Optional<AddressBook> optionalAddressBook = addressBookService.findByUserId(groupPeople.getUserId());
                            if (optionalAddressBook.isPresent()) {
                                AddressBook addressBook = optionalAddressBook.get();
                                addressBook.setCreationTime(groupPeople.getCreationTime());
                                addressBookDTOS.add(new AddressBookDTO(addressBook));
                            }
                        }catch (Exception ex){
                            logger.error(ex.getMessage());
                        }
                    });

                }

                Optional<GroupPeople> optionalGroupPeople = groupPeopleService.findByUserIdAndGroupId(userInfo.getId(), group.getId());
                if (optionalGroupPeople.isPresent()) {
                    if (optionalGroupPeople.get().getHasAcceptedInvitation() == 1) {
                        groupDTO.setIsSubscribed(1);
                    } else {
                        groupDTO.setIsSubscribed(0);
                    }
                    groupDTO.setIsInvited(1);
                    groupDTO.setSubscribedDate(optionalGroupPeople.get().getCreationTime());
                } else {
                    groupDTO.setIsInvited(0);
                }

                groupResponse.setGroupPeople(addressBookDTOS);
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
            throw ex;
        }
        logger.trace("Exiting");
        return groupResponse;
    }

    @Override
    public GroupResponse inviteToGroup(Map<String, String> formData) throws Exception {
        UserInfo userInfo = null;
        try {
            userInfo = userInformation.getUserInfo();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            GroupPeople groupPeople = new GroupPeople();
            groupPeople.setCreatedBy(userInfo.getId());
            groupPeople.setCreationTime(new Date());
            groupPeople.setUserId(formData.get("userId"));
            groupPeople.setGroupId(formData.get("groupId"));
            groupPeople.setHasAcceptedInvitation(0);
            groupPeople.setClientId(userInfo.getClient().getClientId());
            groupPeople = groupPeopleService.save(groupPeople);
            groupResponse.setSuccess(true);
            groupResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        return groupResponse;
    }

    @Override
    public GroupResponse getDeleted() throws Exception {
        UserInfo userInfo = null;
        try {
            userInfo = userInformation.getUserInfo();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            groupResponse.setGroupList(groupRepository.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 1));
            groupResponse.setSuccess(true);
            groupResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return groupResponse;
    }

    @Override
    public GroupResponse getAll() throws Exception {
        UserInfo userInfo = null;
        try {
            userInfo = userInformation.getUserInfo();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            groupResponse.setGroupList(groupRepository.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0));
            groupResponse.setSuccess(true);
            groupResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return groupResponse;
    }

    @Override
    public GroupResponse getAllByType(Map<String, String> formData) throws Exception {
        UserInfo userInfo = null;
        try {
            userInfo = userInformation.getUserInfo();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            int inputGroupType = Integer.parseInt(formData.get("groupType"));
            int groupType = 1;
            if (inputGroupType > 1) {
                groupType = 2;
            }
            List<InterestCategoryDTO> interestCategoryList =
                    interestCategoryService
                            .findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0).getInterestCategoryList();
            List<GroupPeople> groupPeopleList = groupPeopleService.findByUserId(userInfo.getId());
            List<UserInterestsDTO> userInterests = userInterestsService.findByUserId(userInfo.getId()).getUserInterestsList();
            if (inputGroupType == 1) {
                groupResponse.setGroupListWithInterestCategory(groupRepository
                                .findAllByClientIdAndIsDeletedAndGroupType(
                                        userInfo.getClient().getClientId(),
                                        0,
                                        groupType
                                ),
                        interestCategoryList,
                        groupPeopleList,
                        userInterests
                );
            } else if (inputGroupType == 2) {
                groupResponse.setGroupListWithInterestCategory(groupRepository
                                .findAllByClientIdAndIsDeletedAndGroupTypeAndGroupOwnerIdIsNot(
                                        userInfo.getClient().getClientId(),
                                        0,
                                        groupType,
                                        userInfo.getId()
                                ),
                        interestCategoryList,
                        groupPeopleList,
                        userInterests
                );
            } else if (inputGroupType == 3) {
                groupResponse.setGroupListWithInterestCategory(groupRepository
                                .findAllByClientIdAndIsDeletedAndGroupTypeAndGroupOwnerId(
                                        userInfo.getClient().getClientId(),
                                        0,
                                        groupType,
                                        userInfo.getId()
                                ),
                        interestCategoryList,
                        groupPeopleList,
                        userInterests
                );
            }
            groupResponse.setSuccess(true);
            groupResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return groupResponse;
    }

    @Override
    public GroupResponse getPaginated(Map<String, String> formData) throws Exception {
        UserInfo userInfo = null;
        try {
            userInfo = userInformation.getUserInfo();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        GroupResponse groupResponse = new GroupResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
            Page<Group> page = groupRepository.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            groupResponse.setTotalRecords(page.getTotalElements());
            groupResponse.setTotalPages(page.getTotalPages());
            groupResponse.setGroupList(page.getContent());
            groupResponse.setCurrentRecords(groupResponse.getGroupList().size());
            groupResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return groupResponse;
    }

    @Override
    public GroupResponse searchPaginated(Map<String, String> formData) throws Exception {
        UserInfo userInfo = null;
        try {
            userInfo = userInformation.getUserInfo();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
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
                page = groupRepository.findAllByClientIdAndIsDeleted(userInfo.getClient().getClientId(), 0, pageable);
            } else {
                page = groupRepository.findAll(textInAllColumns(searchText, userInfo.getClient().getClientId()), pageable);
            }
            groupResponse.setTotalRecords(page.getTotalElements());
            groupResponse.setTotalPages(page.getTotalPages());
            groupResponse.setGroupList(page.getContent());
            groupResponse.setCurrentRecords(groupResponse.getGroupList().size());
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        }
        logger.trace("Exiting");
        return groupResponse;
    }

    @Override
    public GroupResponse search(SearchRequest searchRequest) throws Exception {
        GroupResponse groupResponse = new GroupResponse();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
            Root<Group> root = criteriaQuery.from(Group.class);
            criteriaQuery = SearchBuilder.buildSearch(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root,
                    Group.class,
                    searchRequest
            );
            long totalRecords = SearchBuilder.getTotalRecordCount(
                    entityManager,
                    criteriaBuilder,
                    criteriaQuery,
                    root
            );
            TypedQuery<Group> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setFirstResult((searchRequest.getCurrentPage() - 1) * searchRequest.getPageSize());
            typedQuery.setMaxResults(searchRequest.getPageSize());
            List<Group> groupList = typedQuery.getResultList();
            groupResponse.setCurrentRecords(groupList.size());
            groupResponse.setTotalRecords(totalRecords);
            groupResponse.setSuccess(true);
            groupResponse.setError("");
            groupResponse.setGroupList(groupList);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.toString(), ex);
            throw ex;
        }
        return groupResponse;
    }



    @Override
    public Optional<Group> findById(String id) {
        return groupRepository.findById(id);
    }

    @Override
    public GroupResponse findAllByClientIdAndIsDeleted(String clientId, int isDeleted) {
        return null;
    }

    @Override
    public GroupResponse findAllByClientIdAndIsDeleted(String clientId, int isDeleted, Pageable pageable) {
        return null;
    }

    @Override
    public GroupResponse findAllByClientIdAndIsDeletedAndName(String clientId, int isDeleted, String name) {
        return null;
    }

    @Override
    public GroupResponse findAllByClientIdAndIsDeletedAndNameAndIdIsNot(String clientId, int isDeleted, String name, String id) {
        return null;
    }

    @Override
    public GroupResponse findAll(Specification<Group> textInAllColumns, Pageable pageable) {
        return null;
    }

    @Override
    public GroupResponse findAllByClientIdAndIsDeletedAndGroupType(String clientId, int isDeleted, int groupType) {
        return null;
    }

    @Override
    public GroupResponse findAllByClientIdAndIsDeletedAndGroupTypeAndGroupOwnerId(String clientId, int isDeleted, int groupType, String userId) {
        return null;
    }

    @Override
    public GroupResponse findAllByClientIdAndIsDeletedAndGroupTypeAndGroupOwnerIdIsNot(String clientId, int isDeleted, int groupType, String userId) {
        return null;
    }

    private boolean checkDuplicate(Group group) {
        List<Group> accountHeadList;
        if (group.getId().equals("")) {
            accountHeadList = groupRepository.findAllByClientIdAndIsDeletedAndName(group.getClientId(), 0, group.getName());
        } else {
            accountHeadList = groupRepository.findAllByClientIdAndIsDeletedAndNameAndIdIsNot(
                    group.getClientId(), 0, group.getName(), group.getId());
        }
        if (accountHeadList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
