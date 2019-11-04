package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);

    List<User> findAllByUserName(String userName);

    List<User> findAllByUserNameAndIdIsNot(String userName, String id);

    Optional<User> findById(String id);

    List<User> findAllByDeletionStatus(int i);

    Page<User> findAll(Pageable pageable);

    Page<User> findAllByUserNameContainingIgnoreCase(String userName, Pageable pageable);
}
