package com.carmel.common.helpers.controller;

import com.carmel.common.helpers.component.MailClient;
import com.carmel.common.helpers.config.YAMLConfig;
import com.carmel.common.helpers.model.User;
import com.carmel.common.helpers.model.UserInfo;
import com.carmel.common.helpers.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class UserController {

     Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    MailClient mailClient;

    @Autowired
    UserService userService;

    @Autowired
    YAMLConfig yamlConfig;

    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public UserInfo resetPassword(@RequestBody Map<String, String> formData) {
        String userName = formData.get("user_name");
        logger.trace(userName);
        Optional<User> optionalUser = userService.findByUserName(userName);
        if(optionalUser.isPresent()) {
            UserInfo userInfo = new UserInfo(optionalUser.get());
            userInfo.setPasswordResetLink("http://guesture.in/reset-password");
            logger.trace(userInfo.getId());
            mailClient.sendPasswordResetMail(userInfo.getUserName(), userInfo);
            return userInfo;
        }else{
            return new UserInfo();
        }
    }

    @RequestMapping(value = "/app-access-request", method = RequestMethod.POST)
    public UserInfo appAccessRequest(@RequestBody Map<String, String> formData) {
        String userName = formData.get("email");
        logger.trace(userName);
        Optional<User> optionalUser = userService.findByUserName(userName);
        if(optionalUser.isPresent()) {
            UserInfo userInfo = new UserInfo(optionalUser.get());
            userInfo.setPasswordResetLink("http://guesture.in/reset-password");
            logger.trace(userInfo.getId());
            mailClient.sendAppAccessRequestMail(yamlConfig.getSupportEmail(), userInfo);
            return userInfo;
        }else{
            return new UserInfo();
        }
    }
}
