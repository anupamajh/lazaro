package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.Client;
import com.carmel.common.dbservice.model.User;
import com.carmel.common.dbservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<User> findAllByUserName(String userName) {
        return userRepository.findAllByUserName(userName);
    }

    @Override
    public List<User> findAllByUserNameAndIdIsNot(String userName, String id) {
        return userRepository.findAllByUserNameAndIdIsNot(userName, id);
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllByDeletionStatus(int isDeleted, Client client) {
        return userRepository.findAllByIsDeletedAndClient(isDeleted, client);
    }

    @Override
    public Page<User> findAllByClient(Pageable pageable, Client client) {
        return userRepository.findAllByIsDeletedAndClient(0, client,pageable);
    }

    @Override
    public Page<User> findAll(Specification<User> textInAllColumns, Pageable pageable) {
        return userRepository.findAll(textInAllColumns, pageable);
    }

    @Override
    public List<User> findAllByIdIn(List<String> userIds) {
        return userRepository.findAllByIdIn(userIds);
    }
}
