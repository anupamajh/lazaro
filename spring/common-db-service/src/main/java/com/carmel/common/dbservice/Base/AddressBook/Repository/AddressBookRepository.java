package com.carmel.common.dbservice.Base.AddressBook.Repository;

import com.carmel.common.dbservice.Base.AddressBook.Model.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressBookRepository extends JpaRepository<AddressBook, String> {
    Optional<AddressBook> findByUserId(String id);

    List<AddressBook> findAllByIsDeleted(int isDeleted);

    List<AddressBook> findAllByIsDeletedAndUserIdIsNot(int isDeleted, String userId);

}