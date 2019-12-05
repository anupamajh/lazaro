package com.carmel.common.dbservice.controller;

import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.User;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.repository.UserRepository;
import com.carmel.common.dbservice.response.UserResponse;
import com.carmel.common.dbservice.response.UsersResponse;
import com.carmel.common.dbservice.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();


    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public UserResponse save(@RequestBody User user) {

        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = userInformation.getUserInfo(userName);
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        UserResponse userResponse = new UserResponse();
        try {
            if (user.getId() == null) {
                user.setId("");
            }
            logger.trace("Data:{}", objectMapper.writeValueAsString(user));
            if (checkDuplicate(user)) {
                userResponse.setUser(user);
                userResponse.setSuccess(false);
                userResponse.setError("Duplicate user name!");
            } else {
                if (user.getId() == "") {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    user.setCreatedBy(userInfo.getId());
                    user.setCreationTime(new Date());
                }else{
                    Optional<User> optionalUser = userService.findById(user.getId());
                    user.setPassword(optionalUser.get().getPassword());
                    user.setLastModifiedBy(userInfo.getId());
                    user.setLastModifiedTime(new Date());
                }
                userResponse.setUser(userService.save(user));
                userResponse.setSuccess(true);
                userResponse.setError("");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            userResponse.setSuccess(false);
            userResponse.setError(ex.getMessage());
        }
        return userResponse;
    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public UserResponse moveToTrash(@RequestBody Map<String, String> formData) {
        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = userInformation.getUserInfo(userName);
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        UserResponse userResponse = new UserResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<User> userOptional = userService.findById(formData.get("id"));
            if (userOptional != null) {
                User user = userOptional.get();
                user.setIsDeleted(1);
                user.setDeletedBy(userInfo.getId());
                user.setDeletedTime(new Date());
                userResponse.setSuccess(true);
                userResponse.setError("");
                userResponse.setUser(userService.save(user));
            } else {
                userResponse.setSuccess(false);
                userResponse.setError("Error occurred while moving user to Trash!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            userResponse.setSuccess(false);
            userResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return userResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public UserResponse get(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        UserResponse userResponse = new UserResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            Optional<User> optionalUser = userService.findById(formData.get("id"));
            if (optionalUser != null) {
                User user = optionalUser.get();
                userResponse.setSuccess(true);
                userResponse.setError("");
                userResponse.setUser(user);
            } else {
                userResponse.setSuccess(false);
                userResponse.setError("Error occurred while fetching user!! Please try after sometime");
            }
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            userResponse.setSuccess(false);
            userResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return userResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public UsersResponse getAll() {
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            usersResponse.setUserList(userService.findAllByDeletionStatus(0));
            usersResponse.setSuccess(true);
            usersResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            usersResponse.setSuccess(false);
            usersResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return usersResponse;
    }

    @RequestMapping(value = "/get-deleted", method = RequestMethod.GET)
    public UsersResponse getDeleted() {
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            usersResponse.setUserList(userService.findAllByDeletionStatus(1));
            usersResponse.setSuccess(true);
            usersResponse.setError("");
        } catch (Exception ex) {
            usersResponse.setSuccess(false);
            usersResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return usersResponse;
    }

    @RequestMapping(value = "/get-users", method = RequestMethod.GET)
    public UsersResponse getPaginated(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("orgName"));
            Page<User> page = userService.findAll(pageable);
            usersResponse.setTotalRecords(page.getTotalElements());
            usersResponse.setTotalPages(page.getTotalPages());
            usersResponse.setUserList(page.getContent());
            usersResponse.setCurrentRecords(usersResponse.getUserList().size());
            usersResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            usersResponse.setSuccess(false);
            usersResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return usersResponse;
    }

    @RequestMapping(value = "/search-users", method = RequestMethod.GET)
    public UsersResponse searchPaginated(@RequestBody Map<String, String> formData) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        UsersResponse orgResponse = new UsersResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String userName = formData.get("user_name") == null ? null : String.valueOf(formData.get("user_name"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("userName"));
            Page<User> page;
            if (userName == null) {
                page = userService.findAll(pageable);
            } else {
                page = userService
                        .findAllByUserNameContainingIgnoreCase(userName, pageable);
            }
            orgResponse.setTotalRecords(page.getTotalElements());
            orgResponse.setTotalPages(page.getTotalPages());
            orgResponse.setUserList(page.getContent());
            orgResponse.setCurrentRecords(orgResponse.getUserList().size());
            orgResponse.setSuccess(true);
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            orgResponse.setSuccess(false);
            orgResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return orgResponse;
    }

    public boolean checkDuplicate(User user) {
        List<User> userList;
        if (user.getId() == null) {
            user.setId("");
        }
        if (user.getId().isEmpty()) {
            userList = userService.findAllByUserName(user.getUserName());
        } else {
            userList = userService.findAllByUserNameAndIdIsNot(user.getUserName(), user.getId());
        }
        if (userList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/me")
    public UserInfo user(Principal principal) {
        String userName = principal.getName();
        Optional<User> optionalUser = userService.findByUserName(userName);
        optionalUser.orElseThrow(() ->
                new UsernameNotFoundException("Cannot find the logged in principle, Please contact administrator"));
        UserInfo userInfo = new UserInfo(optionalUser.get());
        userInfo.setPrincipal(principal);
        return userInfo;
    }
}
