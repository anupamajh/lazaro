package com.carmel.common.dbservice.component;

import com.carmel.common.dbservice.model.User;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInformation {
    @Autowired
    UserService service;

    public UserInfo getUserInfo(String userName){
        Optional<User> optionalUser = service.findByUserName(userName);
        optionalUser.orElseThrow(() ->
                new UsernameNotFoundException("Cannot find the logged in principle, Please contact administrator"));
        User user = optionalUser.get();
        return new UserInfo(user);
    }
}
