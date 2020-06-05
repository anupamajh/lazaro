package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.AddressBook;
import com.carmel.common.dbservice.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public List<AddressBook> findAllByIsDeleted(int isDeleted) {
        return addressBookRepository.findAllByIsDeleted(isDeleted);
    }

    @Override
    public List<AddressBook> findAllByIsDeletedAndUserIdIsNot(int isDeleted, String userId) {
        return addressBookRepository.findAllByIsDeletedAndUserIdIsNot(isDeleted, userId);
    }
}
