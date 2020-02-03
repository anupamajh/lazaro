package com.carmel.common.dbservice.response;

import com.carmel.common.dbservice.model.*;
import com.carmel.common.dbservice.model.DTO.AddressBookDTO;
import com.carmel.common.dbservice.model.DTO.GroupDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroupResponse {
    private GroupDTO group;
    private List<GroupDTO> groupList;
    private List<AddressBookDTO> groupPeople;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public GroupDTO getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = new GroupDTO(group);
    }

    public void setGroupDTO(GroupDTO group) {
        this.group = group;
    }

    public List<GroupDTO> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = new ArrayList<>();
        groupList.forEach(group1 -> {
            this.groupList.add(new GroupDTO(group1));
        });
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public long getCurrentRecords() {
        return currentRecords;
    }

    public void setCurrentRecords(long currentRecords) {
        this.currentRecords = currentRecords;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setGroupListWithInterestCategory(List<Group> groupList, List<InterestCategory> interestCategoryList, List<GroupPeople> groupPeopleList, List<UserInterests> userInterests) {
        this.groupList = new ArrayList<>();
        groupList.forEach(group1 -> {
            GroupDTO groupDTO = new GroupDTO(group1);
            groupDTO.setIsSubscribed(0);
            Optional<InterestCategory> optionalInterestCategory
                    = interestCategoryList.stream()
                    .filter(ic -> ic.getId().equals(group1.getInterestCategoryId()))
                    .findFirst();
            Optional<GroupPeople> optionalGroupPeople = groupPeopleList.stream().filter(gp -> gp.getGroupId().equals(group1.getId())).findFirst();
            groupDTO.setCardType(0);
            if(optionalGroupPeople.isPresent() && optionalGroupPeople.get().getHasAcceptedInvitation() == 1){
                groupDTO.setIsSubscribed(1);
                groupDTO.setCardType(1);//Subscribed Card
            }else{
                groupDTO.setIsSubscribed(0);
                groupDTO.setCardType(2);//Invited Card
            }
            optionalInterestCategory.ifPresent(interestCategory -> groupDTO.setInterestCategoryName(interestCategory.getName()));
            Optional<UserInterests> optionalUserInterests = userInterests.stream().filter(ui -> ui.getInterestId().equals(group1.getInterestId()) && ui.getIsInterested() == 1).findFirst();
            if(optionalUserInterests.isPresent()){
                groupDTO.setIsMatchingInterest(1);
                if(groupDTO.getCardType() != 1){
                    groupDTO.setCardType(3);//Matching Interest Card
                }
            }else{
                groupDTO.setIsMatchingInterest(0);
            }
            this.groupList.add(groupDTO);
        });
    }

    public List<AddressBookDTO> getGroupPeople() {
        return groupPeople;
    }

    public void setGroupPeople(List<AddressBookDTO> groupPeople) {
        this.groupPeople = groupPeople;
    }
}
