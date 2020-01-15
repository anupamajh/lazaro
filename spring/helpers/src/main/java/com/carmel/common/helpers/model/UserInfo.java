package com.carmel.common.helpers.model;

import com.carmel.common.helpers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserInfo {

    @Autowired
    UserRepository repository;

    private String id;
    private String fullName;
    private String userName;


    public UserInfo(){

    }

    public UserInfo(User user) {
        this.fullName =user.getUserName();
        this.userName = user.getUserName();
        this.id = user.getId();

    }

    public UserInfo(String userName){
        Optional<User> optionalUser = repository.findByUserName(userName);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
             this.fullName = user.getUserName();
            this.userName = user.getUserName();
            this.id = user.getId();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
