package com.carmel.guestjini.Networking.Group;


import com.carmel.guestjini.Networking.Users.AddressBook;

import java.util.List;

public class GroupResponse {
    private Group group;
    private List<Group> groupList;
    private List<AddressBook> groupPeople;
    private int totalPages;
    private int totalRecords;
    private int currentRecords;
    private Boolean success;
    private String error;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public List<AddressBook> getGroupPeople() {
        return groupPeople;
    }

    public void setGroupPeople(List<AddressBook> groupPeople) {
        this.groupPeople = groupPeople;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getCurrentRecords() {
        return currentRecords;
    }

    public void setCurrentRecords(int currentRecords) {
        this.currentRecords = currentRecords;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
