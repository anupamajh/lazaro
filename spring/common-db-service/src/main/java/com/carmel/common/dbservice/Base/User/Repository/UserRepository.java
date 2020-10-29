package com.carmel.common.dbservice.Base.User.Repository;

import com.carmel.common.dbservice.Base.AppFeature.Model.AppFeatures;
import com.carmel.common.dbservice.Base.Client.Model.Client;
import com.carmel.common.dbservice.Base.User.Model.User;
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

    List<User> findAllByIsDeleted(int isDeleted);

    Page<User> findAllByIsDeleted(int isDeleted, Pageable pageable);

    List<User> findAllByIsDeletedAndClient(int isDeleted, Client client);

    Page<User> findAllByIsDeletedAndClient(int isDeleted, Client client, Pageable pageable);

    Page<User> findAll(Specification<User> textInAllColumns, Pageable pageable);

    List<User> findAllByIdIn(List<String> userIds);

    List<User> findAllByIsDeletedAndPhone(int isDeleted, String phone);

    List<User> findAllByIsDeletedAndEmail(int isDeleted, String email);

    List<User> findAllByIsDeletedAndUserName(int isDeleted, String userName);

    Optional<User> findByPhone(String phone);
}
