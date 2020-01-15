package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.AddressBook;
import com.carmel.common.dbservice.repository.AddressBookRepository;
import com.carmel.common.dbservice.response.AddressBookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    AddressBookRepository addressBookRepository;

    @Override
    public Optional<AddressBook> findByUserId(String id) {
        return addressBookRepository.findByUserId(id);
    }

    @Override
    public AddressBook save(AddressBook addressBook) {
        return addressBookRepository.save(addressBook);
    }
}
