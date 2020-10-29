package com.carmel.common.authserver.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetail extends User implements UserDetails {
    public UserDetail(User user) {
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        super.getRoles().forEach(role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            role.getAppFeatures().forEach(appFeatures -> {
                grantedAuthorities.add(new SimpleGrantedAuthority(appFeatures.getSystemRole()));
            });
        });
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        if(this.getIsGod() == 1){
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_GOD"));

        }
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return super.getIsDeleted() == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return super.getAccountStatus() == 2;
    }
}
