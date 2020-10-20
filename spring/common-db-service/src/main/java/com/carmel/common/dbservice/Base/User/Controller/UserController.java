package com.carmel.common.dbservice.Base.User.Controller;

import com.carmel.common.dbservice.Base.AddressBook.Model.AddressBook;
import com.carmel.common.dbservice.Base.AddressBook.Service.AddressBookService;
import com.carmel.common.dbservice.Base.User.Model.User;
import com.carmel.common.dbservice.Base.User.Response.UsersResponse;
import com.carmel.common.dbservice.Base.User.Service.UserService;
import com.carmel.common.dbservice.Base.UserPreference.Model.UserPreference;
import com.carmel.common.dbservice.Base.UserPreference.Service.UserPreferenceService;
import com.carmel.common.dbservice.common.Search.SearchRequest;
import com.carmel.common.dbservice.component.MailClient;
import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.response.GenericResponse;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    UserService userService;

    @Autowired
    MailClient mailClient;

    @Autowired
    UserInformation userInformation;

    @Autowired
    AddressBookService addressBookService;

    @Autowired
    UserPreferenceService userPreferenceService;

    @Autowired
    TokenStore jdbcTokenStore;

    @Autowired
    RemoteTokenServices tokenServices;


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public UsersResponse save(@Valid @RequestBody User user) {
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            usersResponse = userService
                    .saveUser(user);
        } catch (Exception ex) {
            usersResponse.setSuccess(true);
            usersResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return usersResponse;
    }

    @RequestMapping(value = "/guest-sign-up", method = RequestMethod.POST)
    public UsersResponse guestSignUp(@Valid @RequestBody User user) {
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            usersResponse = userService
                    .guestSignUp(user);
        } catch (Exception ex) {
            usersResponse.setSuccess(true);
            usersResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return usersResponse;
    }


    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public UsersResponse moveToTrash(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            usersResponse = userService
                    .moveToTrash(formData);
        } catch (Exception ex) {
            usersResponse.setSuccess(true);
            usersResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return usersResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public UsersResponse get(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            usersResponse = userService
                    .get(formData);
        } catch (Exception ex) {
            usersResponse.setSuccess(true);
            usersResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return usersResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public UsersResponse getAll() {
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            usersResponse = userService
                    .getAll();
        } catch (Exception ex) {
            usersResponse.setSuccess(true);
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
            usersResponse = userService
                    .getDeleted();
        } catch (Exception ex) {
            usersResponse.setSuccess(true);
            usersResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return usersResponse;
    }

    @RequestMapping(value = "/get-users", method = RequestMethod.POST)
    public UsersResponse getPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            usersResponse = userService
                    .getPaginated(formData);
        } catch (Exception ex) {
            usersResponse.setSuccess(true);
            usersResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return usersResponse;
    }


    @RequestMapping(value = "/search-users", method = RequestMethod.POST)
    public UsersResponse searchPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            usersResponse = userService
                    .getPaginated(formData);
        } catch (Exception ex) {
            usersResponse.setSuccess(true);
            usersResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return usersResponse;
    }


    @GetMapping("/old-me")
    public UserInfo oldUser(Principal principal) throws Exception {
        String userName = principal.getName();
        Optional<User> optionalUser = userService.findByUserName(userName);
        optionalUser.orElseThrow(() ->
                new UsernameNotFoundException("Cannot find the logged in principal, Please contact administrator"));
        UserInfo userInfo = new UserInfo(optionalUser.get());
        userInfo.setPrincipal(principal);
        return userInfo;
    }

    @GetMapping("/me")
    public UserInfo user(Principal principal) throws Exception {
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
    public UserInfo resetPassword(@RequestBody Map<String, String> formData) throws Exception {
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

    @RequestMapping(value = "/activate-account")
    public UsersResponse activateAccount(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            usersResponse = userService
                    .activateAccount(formData);
        } catch (Exception ex) {
            usersResponse.setSuccess(true);
            usersResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return usersResponse;
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public GenericResponse changePassword(@RequestBody Map<String, String> formData) throws Exception {
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

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public UsersResponse search(@RequestBody SearchRequest searchRequest) {
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            usersResponse = userService
                    .search(searchRequest);
        } catch (Exception ex) {
            usersResponse.setSuccess(true);
            usersResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return usersResponse;
    }


    @RequestMapping(value = "/find-users-in")
    public UsersResponse findUsersIn(@RequestBody List<String> userIds) {
        logger.trace("Entering");
        UsersResponse usersResponse = new UsersResponse();
        try {
            usersResponse = userService
                    .findUsersIn(userIds);
        } catch (Exception ex) {
            usersResponse.setSuccess(true);
            usersResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return usersResponse;
    }

    @GetMapping("/remove-access-token")
    public UsersResponse revokeToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = jdbcTokenStore.readAccessToken(tokenValue);
            jdbcTokenStore.removeAccessToken(accessToken);
        }

        return new UsersResponse();
    }
}
