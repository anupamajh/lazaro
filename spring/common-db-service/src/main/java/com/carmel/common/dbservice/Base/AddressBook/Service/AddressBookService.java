package com.carmel.common.dbservice.Base.AddressBook.Service;

import com.carmel.common.dbservice.Base.AddressBook.Model.AddressBook;
import com.carmel.common.dbservice.Base.AddressBook.Responce.AddressBookResponse;
import com.carmel.common.dbservice.common.Search.SearchRequest;


import java.util.Map;
import java.util.Optional;

public interface AddressBookService {
    AddressBook save(AddressBook addressBook)  throws Exception;

    Optional<AddressBook> findByUserId(String id) throws Exception;

    AddressBookResponse saveProfilePic(Map<String, String> formData) throws Exception;

    AddressBookResponse getMyAddressBook() throws Exception;

    AddressBookResponse search( SearchRequest searchRequest) throws Exception;


    AddressBookResponse findAllByIsDeleted(int isDeleted) throws Exception;

    AddressBookResponse findAllByIsDeletedAndUserIdIsNot(int isDeleted, String userId) throws Exception;
}
