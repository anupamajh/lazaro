package com.carmel.common.dbservice.response;

import com.carmel.common.dbservice.model.AddressBook;
import com.carmel.common.dbservice.model.UserPreference;

import java.util.List;

public class AddressBookResponse {
    private List<AddressBook> addressBookList;
    private AddressBook addressBook;
    private long totalPages;
    private long totalRecords;
    private long currentRecords;
    private boolean success;
    private String error;

    public List<AddressBook> getAddressBookList() {
        return addressBookList;
    }

    public void setAddressBookList(List<AddressBook> addressBookList) {
        this.addressBookList = addressBookList;
    }

    public AddressBook getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
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
}
