package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.AddressBook;

import java.util.Optional;

public interface AddressBookService {
    Optional<AddressBook> findByUserId(String id);

    AddressBook save(AddressBook addressBook);
}
