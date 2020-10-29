package com.carmel.common.authserver.controller;

import com.carmel.common.authserver.model.User;
import com.carmel.common.authserver.model.UserInfo;
import com.carmel.common.authserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DefaultTokenServices tokenServices;

    @Autowired
    TokenStore tokenStore;

    @GetMapping("/me")
    public UserInfo info(Principal principal) {

        String userName = principal.getName();
        Optional<User> optionalUser = userRepository.findByUserName(userName);
        optionalUser.orElseThrow(() ->
                new UsernameNotFoundException("Cannot find the logged in principle, Please contact administrator"));
        UserInfo userInfo = new UserInfo(optionalUser.get());
        userInfo.setPrincipal(principal);
        return userInfo;
    }

    @GetMapping("/remove-access-token")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void revokeToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
        }
    }

}
