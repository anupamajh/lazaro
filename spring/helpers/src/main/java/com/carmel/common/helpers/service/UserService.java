package com.carmel.common.helpers.service;

import com.carmel.common.helpers.model.Client;
import com.carmel.common.helpers.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findByUserName(String userName);

    List<User> findAllByUserName(String userName);

    List<User> findAllByUserNameAndIdIsNot(String userName, String id);

    Optional<User> findById(String id);

    User save(User user);

    List<User> findAllByDeletionStatus(int isDeleted, Client client);

    Page<User> findAllByClient(Pageable pageable, Client client);

    Page<User> findAll(Specification<User> textInAllColumns, Pageable pageable);
}
