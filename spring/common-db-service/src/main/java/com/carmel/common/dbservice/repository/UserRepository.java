package com.carmel.common.dbservice.repository;

import com.carmel.common.dbservice.model.Client;
import com.carmel.common.dbservice.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUserName(String userName);

    List<User> findAllByUserName(String userName);

    List<User> findAllByUserNameAndIdIsNot(String userName, String id);

    List<User> findAllByIsDeletedAndClient(int isDeleted, Client client);

    Page<User> findAllByIsDeletedAndClient(int isDeleted, Client client, Pageable pageable);

    Page<User> findAll(Specification<User> textInAllColumns, Pageable pageable);

    List<User> findAllByIdIn(List<String> userIds);
}
