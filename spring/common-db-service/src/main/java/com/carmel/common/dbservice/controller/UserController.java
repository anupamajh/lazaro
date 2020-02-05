package com.carmel.common.dbservice.controller;

import com.carmel.common.dbservice.component.MailClient;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.AddressBook;
import com.carmel.common.dbservice.model.User;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.model.UserPreference;
import com.carmel.common.dbservice.response.GenericResponse;
import com.carmel.common.dbservice.response.UserResponse;
import com.carmel.common.dbservice.response.UsersResponse;
import com.carmel.common.dbservice.services.AddressBookService;
import com.carmel.common.dbservice.services.UserPreferenceService;
import com.carmel.common.dbservice.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.carmel.common.dbservice.specifications.UserSpecification.textInAllColumns;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();


    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    UserService userService;

    @Autowired
    MailClient mailClient;

    @Autowired
    AddressBookService addressBookService;

    @Autowired
    UserPreferenceService userPreferenceService;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public UserResponse save(@Valid @RequestBody User user) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        UserResponse userResponse = new UserResponse();
        try {
            if (user.getId() == null) {
                user.setId("");
            }
            user.setClient(userInfo.getClient());
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
                    user.setClient(userInfo.getClient());
                } else {
                    Optional<User> optionalUser = userService.findById(user.getId());
                    user.setPassword(optionalUser.get().getPassword());
                    user.setLastModifiedBy(userInfo.getId());
                    user.setLastModifiedTime(new Date());
                }
                User savedUser = userService.save(user);
                AddressBook addressBook = new AddressBook();
                Optional<AddressBook> optionalAddressBook = addressBookService.findByUserId(savedUser.getId());
                if (optionalAddressBook.isPresent()) {
                    addressBook = optionalAddressBook.get();
                }
                addressBook.setEmail1(user.getUserName());
                addressBook.setDisplayName(user.getFullName());
                addressBook.setUserId(savedUser.getId());
                addressBookService.save(addressBook);
                userResponse.setUser(savedUser);
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
        UserInfo userInfo = userInformation.getUserInfo();
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
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            usersResponse.setUserList(userService.findAllByDeletionStatus(0, userInfo.getClient()));
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
        UserInfo userInfo = userInformation.getUserInfo();
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            usersResponse.setUserList(userService.findAllByDeletionStatus(1, userInfo.getClient()));
            usersResponse.setSuccess(true);
            usersResponse.setError("");
        } catch (Exception ex) {
            usersResponse.setSuccess(false);
            usersResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return usersResponse;
    }

    @RequestMapping(value = "/get-users", method = RequestMethod.POST)
    public UsersResponse getPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("fullName"));
            Page<User> page = userService.findAllByClient(pageable, userInfo.getClient());
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


    @RequestMapping(value = "/search-users", method = RequestMethod.POST)
    public UsersResponse searchPaginated(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        UsersResponse orgResponse = new UsersResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(formData));
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("userName"));
            Page<User> page;
            if (searchText == null) {
                page = userService.findAllByClient(pageable, userInfo.getClient());
            } else {
                page = userService
                        .findAll(textInAllColumns(searchText, userInfo.getClient()), pageable);
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

    @GetMapping("/old-me")
    public UserInfo oldUser(Principal principal) {
        String userName = principal.getName();
        Optional<User> optionalUser = userService.findByUserName(userName);
        optionalUser.orElseThrow(() ->
                new UsernameNotFoundException("Cannot find the logged in principal, Please contact administrator"));
        UserInfo userInfo = new UserInfo(optionalUser.get());
        userInfo.setPrincipal(principal);
        return userInfo;
    }

    @GetMapping("/me")
    public UserInfo user(Principal principal) {
        UserInfo userInfo = userInformation.getUserInfo();
        Optional<AddressBook> optionalAddressBook = addressBookService.findByUserId(userInfo.getId());
        optionalAddressBook.ifPresent(userInfo::setAddressBook);
        if (!optionalAddressBook.isPresent()) {
            AddressBook addressBook = new AddressBook();
            addressBook.setEmail1(userInfo.getUserName());
            addressBook.setDisplayName(userInfo.getFullName());
            addressBook.setUserId(userInfo.getId());
            userInfo.setAddressBook(addressBookService.save(addressBook));
        }
        List<UserPreference> userPreferences = userPreferenceService.findAllByUserId(userInfo.getId());
        userInfo.setUserPreferences(userPreferences);
        return userInfo;
    }

    @GetMapping("/me/pic")
    public String myPic() {
        try {
            UserInfo userInfo = userInformation.getUserInfo();
            Optional<AddressBook> optionalAddressBook = addressBookService.findByUserId(userInfo.getId());
            if (optionalAddressBook.isPresent()) {
                AddressBook addressBook = optionalAddressBook.get();
                if (addressBook.getLogoPath().trim() != "") {
                    String logoPath = addressBook.getLogoPath();
                    File myPic = new File(logoPath);
                    FileInputStream fileInputStreamReader = new FileInputStream(myPic);
                    byte[] bytes = new byte[(int) myPic.length()];
                    fileInputStreamReader.read(bytes);
                    return new String(Base64.encodeBase64(bytes), "UTF-8");
                }
            }
        } catch (Exception ex) {
            logger.trace(ex.getMessage());
        }
        return "";
    }


    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public UserInfo resetPassword(@RequestBody Map<String, String> formData) {
        String userName = formData.get("user_name");
        logger.trace(userName);
        Optional<User> optionalUser = userService.findByUserName(userName);
        optionalUser.orElseThrow(() ->
                new UsernameNotFoundException("Cannot find the logged in principal, Please contact administrator"));
        UserInfo userInfo = new UserInfo(optionalUser.get());
        logger.trace(userInfo.getId());

        mailClient.prepareAndSend(userInfo.getUserName(), "Reset password link here");

        return userInfo;
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public GenericResponse changePassword(@RequestBody Map<String, String> formData) {
        UserInfo userInfo = userInformation.getUserInfo();
        GenericResponse genericResponse = new GenericResponse();
        try {
            Optional<User> optionalUser = userService.findById(userInfo.getId());
            String currentPassword = formData.get("currentPassword");
            String newPasswordPassword = formData.get("newPassword");
            if (optionalUser.isPresent()) {
                if (passwordEncoder.matches(currentPassword, optionalUser.get().getPassword())) {
                    User user = optionalUser.get();
                    user.setPassword(passwordEncoder.encode(newPasswordPassword));
                    userService.save(user);
                    genericResponse.setSuccess(true);
                    genericResponse.setError("Password changed successfully");
                } else {
                    genericResponse.setSuccess(false);
                    genericResponse.setError("Current password do not match");
                }

            } else {
                genericResponse.setSuccess(false);
                genericResponse.setError("User not found.");
            }
        } catch (Exception ex) {
            genericResponse.setSuccess(false);
            genericResponse.setError(ex.getMessage());
        }
        return genericResponse;
    }

    @RequestMapping(value = "/find-users-in")
    public UsersResponse findUsersIn(@RequestBody List<String> userIds) {
        UsersResponse usersResponse = new UsersResponse();
        try {

            List<User> userList = userService.findAllByIdIn(userIds);
            usersResponse.setUserList(userList);
            usersResponse.setSuccess(true);
            usersResponse.setError("");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            usersResponse.setSuccess(false);
            usersResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return usersResponse;
    }
}
