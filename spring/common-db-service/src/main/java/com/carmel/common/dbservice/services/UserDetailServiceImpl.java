package com.carmel.common.dbservice.services;

import com.carmel.common.dbservice.model.User;
import com.carmel.common.dbservice.model.UserDetail;
import com.carmel.common.dbservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsService")
public class UserDetailServiceImpl  implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUserNameAndIsDeletedIs(userName, 0);
        optionalUser.orElseThrow(()->new UsernameNotFoundException("Username or password is wrong!"));
        UserDetail userDetail = new UserDetail(optionalUser.get());
        new AccountStatusUserDetailsChecker().check(userDetail);
        return userDetail;
    }
}
