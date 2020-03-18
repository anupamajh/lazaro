package com.carmel.common.dbservice.component;

import com.carmel.common.dbservice.model.Client;
import com.carmel.common.dbservice.model.User;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.services.ClientService;
import com.carmel.common.dbservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInformation {
    @Autowired
    UserService service;

    @Autowired
    ClientService clientService;

    public UserInfo getUserInfo() {
        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalUser = service.findByUserName(userName);
        Client client = null;
        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            user = new User();
            Optional<Client> optionalClient = clientService.findById(userName);
            if (optionalClient.isPresent()) {
                client = optionalClient.get();
                user.setClient(client);
            } else {
                throw new UsernameNotFoundException("Cannot find the logged in principal, Please contact administrator");
            }
        }
        return new UserInfo(user);
    }
}
