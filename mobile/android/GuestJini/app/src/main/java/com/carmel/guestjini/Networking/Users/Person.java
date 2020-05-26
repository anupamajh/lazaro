package com.carmel.guestjini.Networking.Users;

import java.util.List;

public class Person {
    private AddressBook addressBook;
    private List<UserInterests> userInterestsList;
    private int isFavourite;

    public AddressBook getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public List<UserInterests> getUserInterestsList() {
        return userInterestsList;
    }

    public void setUserInterestsList(List<UserInterests> userInterestsList) {
        this.userInterestsList = userInterestsList;
    }

    public int getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(int isFavourite) {
        this.isFavourite = isFavourite;
    }
}
