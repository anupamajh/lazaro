package com.carmel.guestjini.Models.User;

import java.util.List;

public class PeopleResponse {
    private UserInfo myUserInfo;
    private UserInfo otherUserInfo;
    private AddressBook myAddressBook;
    private AddressBook othersAddressBook;
    private List<AddressBook> peopleList;
    private List<UserInterests> myInterests;
    private List<UserInterests> otherInterests;
    private List<InterestMap> otherInterestMap;
    private List<InterestMap> myInterestMap;
    private List<InterestMap> commonInterest;
    private List<InterestMap> unCommonInterest;
    private List<Person> personList;
    private int isFavourite;
    private int totalInterestCount;
    private int totalPages;
    private int totalRecords;
    private int currentRecords;
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

    public List<UserInterests> getMyInterests() {
        return myInterests;
    }

    public void setMyInterests(List<UserInterests> myInterests) {
        this.myInterests = myInterests;
    }

    public List<UserInterests> getOtherInterests() {
        return otherInterests;
    }

    public void setOtherInterests(List<UserInterests> otherInterests) {
        this.otherInterests = otherInterests;
    }

    public List<InterestMap> getOtherInterestMap() {
        return otherInterestMap;
    }

    public void setOtherInterestMap(List<InterestMap> otherInterestMap) {
        this.otherInterestMap = otherInterestMap;
    }

    public List<InterestMap> getMyInterestMap() {
        return myInterestMap;
    }

    public void setMyInterestMap(List<InterestMap> myInterestMap) {
        this.myInterestMap = myInterestMap;
    }

    public List<InterestMap> getCommonInterest() {
        return commonInterest;
    }

    public void setCommonInterest(List<InterestMap> commonInterest) {
        this.commonInterest = commonInterest;
    }

    public List<InterestMap> getUnCommonInterest() {
        return unCommonInterest;
    }

    public void setUnCommonInterest(List<InterestMap> unCommonInterest) {
        this.unCommonInterest = unCommonInterest;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public int getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(int isFavourite) {
        this.isFavourite = isFavourite;
    }

    public int getTotalInterestCount() {
        return totalInterestCount;
    }

    public void setTotalInterestCount(int totalInterestCount) {
        this.totalInterestCount = totalInterestCount;
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
}
