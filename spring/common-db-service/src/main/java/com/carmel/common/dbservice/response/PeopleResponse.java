package com.carmel.common.dbservice.response;

import com.carmel.common.dbservice.model.AddressBook;
import com.carmel.common.dbservice.model.DTO.PersonDTO;
import com.carmel.common.dbservice.model.DTO.UserInterestCategoryDTO;
import com.carmel.common.dbservice.model.DTO.UserInterestsDTO;
import com.carmel.common.dbservice.model.InterestCategory;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.model.UserInterests;

import java.util.List;
import java.util.Map;

public class PeopleResponse {
    private UserInfo myUserInfo;
    private UserInfo otherUserInfo;
    private AddressBook myAddressBook;
    private AddressBook othersAddressBook;
    private List<AddressBook> peopleList;
    private List<UserInterestsDTO> myInterests;
    private List<UserInterestsDTO> otherInterests;
    private List<PersonDTO> personList;
    private List<UserInterestCategoryDTO> myInterestMap;
    private List<UserInterestCategoryDTO> otherInterestMap;
    private List<UserInterestCategoryDTO> commonInterest;
    private List<UserInterestCategoryDTO> unCommonInterest;
    private long totalInterestCount;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public UserInfo getMyUserInfo() {
        return myUserInfo;
    }

    public void setMyUserInfo(UserInfo myUserInfo) {
        this.myUserInfo = myUserInfo;
    }

    public UserInfo getOtherUserInfo() {
        return otherUserInfo;
    }

    public void setOtherUserInfo(UserInfo otherUserInfo) {
        this.otherUserInfo = otherUserInfo;
    }

    public AddressBook getMyAddressBook() {
        return myAddressBook;
    }

    public void setMyAddressBook(AddressBook myAddressBook) {
        this.myAddressBook = myAddressBook;
    }

    public AddressBook getOthersAddressBook() {
        return othersAddressBook;
    }

    public void setOthersAddressBook(AddressBook othersAddressBook) {
        this.othersAddressBook = othersAddressBook;
    }

    public List<AddressBook> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(List<AddressBook> peopleList) {
        this.peopleList = peopleList;
    }

    public List<UserInterestsDTO> getMyInterests() {
        return myInterests;
    }

    public void setMyInterests(List<UserInterestsDTO> myInterests) {
        this.myInterests = myInterests;
    }

    public List<UserInterestsDTO> getOtherInterests() {
        return otherInterests;
    }

    public void setOtherInterests(List<UserInterestsDTO> otherInterests) {
        this.otherInterests = otherInterests;
    }

    public long getTotalInterestCount() {
        return totalInterestCount;
    }

    public void setTotalInterestCount(long totalInterestCount) {
        this.totalInterestCount = totalInterestCount;
    }

    public List<PersonDTO> getPersonList() {
        return personList;
    }

    public void setPersonList(List<PersonDTO> personList) {
        this.personList = personList;
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

    public List<UserInterestCategoryDTO> getMyInterestMap() {
        return myInterestMap;
    }

    public void setMyInterestMap(List<UserInterestCategoryDTO> myInterestMap) {
        this.myInterestMap = myInterestMap;
    }

    public List<UserInterestCategoryDTO> getOtherInterestMap() {
        return otherInterestMap;
    }

    public void setOtherInterestMap(List<UserInterestCategoryDTO> otherInterestMap) {
        this.otherInterestMap = otherInterestMap;
    }

    public List<UserInterestCategoryDTO> getCommonInterest() {
        return commonInterest;
    }

    public void setCommonInterest(List<UserInterestCategoryDTO> commonInterest) {
        this.commonInterest = commonInterest;
    }

    public List<UserInterestCategoryDTO> getUnCommonInterest() {
        return unCommonInterest;
    }

    public void setUnCommonInterest(List<UserInterestCategoryDTO> unCommonInterest) {
        this.unCommonInterest = unCommonInterest;
    }
}
