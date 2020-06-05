package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.AddressBook;

import java.util.List;
import java.util.Optional;

public interface AddressBookService {
    Optional<AddressBook> findByUserId(String id);

    AddressBook save(AddressBook addressBook);

    List<AddressBook> findAllByIsDeleted(int isDeleted);

    List<AddressBook> findAllByIsDeletedAndUserIdIsNot(int isDeleted, String userId);
}
