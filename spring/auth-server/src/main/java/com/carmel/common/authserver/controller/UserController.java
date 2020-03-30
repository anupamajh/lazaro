package com.carmel.common.authserver.controller;

import com.carmel.common.authserver.model.User;
import com.carmel.common.authserver.model.UserInfo;
import com.carmel.common.authserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/me")
    public UserInfo info(Principal principal){

        String userName = principal.getName();
        Optional<User> optionalUser = userRepository.findByUserName(userName);
        optionalUser.orElseThrow(() ->
                new UsernameNotFoundException("Cannot find the logged in principle, Please contact administrator"));
        UserInfo userInfo = new UserInfo(optionalUser.get());
        userInfo.setPrincipal(principal);
        return userInfo;
    }



}
