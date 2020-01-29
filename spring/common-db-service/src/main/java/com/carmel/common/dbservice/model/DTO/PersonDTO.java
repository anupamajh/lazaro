package com.carmel.common.dbservice.model.DTO;

import com.carmel.common.dbservice.model.AddressBook;
import com.carmel.common.dbservice.model.UserInterests;

import java.util.List;

public class PersonDTO {
    private AddressBook addressBook;
    private List<UserInterestsDTO> userInterestsList;

    public AddressBook getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public List<UserInterestsDTO> getUserInterestsList() {
        return userInterestsList;
    }

    public void setUserInterestsList(List<UserInterestsDTO> userInterestsList) {
        this.userInterestsList = userInterestsList;
    }
}
