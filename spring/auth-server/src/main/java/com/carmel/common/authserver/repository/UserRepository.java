package com.carmel.common.authserver.repository;

import com.carmel.common.authserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>{
    Optional<User> findByUserNameAndIsDeletedIs(String userName, int i);
    Optional<User> findByUserName(String userName);
}