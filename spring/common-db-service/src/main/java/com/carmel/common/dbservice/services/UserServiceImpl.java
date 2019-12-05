package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.User;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static UserRepository repository;

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    private void setRepository(){
        repository = this.userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllByUserName(String userName) {
        return userRepository.findAllByUserNameAndIsDeletedIs(userName,0);
    }

    @Override
    public List<User> findAllByUserNameAndIdIsNot(String userName, String id) {
        return userRepository.findAllByUserNameAndIdIsNotAndIsDeletedIs(userName, id,0);
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAllByDeletionStatus(int i) {
        return userRepository.findAllByIsDeleted(i);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findAllByUserNameContainingIgnoreCase(String userName, Pageable pageable) {
        return userRepository.findAllByUserNameContainingAndIsDeletedIs(userName, 0, pageable);
    }


    @Override
    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

}
