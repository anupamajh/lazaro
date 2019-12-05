package com.carmel.common.dbservice.repository;

import com.carmel.common.dbservice.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAllByUserNameAndIsDeletedIs(String userName, int isDeleted);

    List<User> findAllByUserNameAndIdIsNotAndIsDeletedIs(String userName, String id, int isDeleted);

    List<User> findAllByIsDeleted(int i);

    Page<User> findAllByUserNameContainingAndIsDeletedIs(String userName, int i, Pageable pageable);

    Optional<User> findByUserNameAndIsDeletedIs(String userName, int i);

    Optional<User> findByUserName(String userName);
}
