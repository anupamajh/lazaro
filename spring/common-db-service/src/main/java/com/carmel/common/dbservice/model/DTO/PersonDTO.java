package com.carmel.common.dbservice.model.DTO;


import com.carmel.common.dbservice.Base.AddressBook.Model.AddressBook;
import com.carmel.common.dbservice.Base.UserInterests.DTO.UserInterestsDTO;

import java.util.List;

public class PersonDTO {
    private AddressBook addressBook;
    private List<UserInterestsDTO> userInterestsList;
    private int isFavourite;

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

    public int getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(int isFavourite) {
        this.isFavourite = isFavourite;
    }
}
