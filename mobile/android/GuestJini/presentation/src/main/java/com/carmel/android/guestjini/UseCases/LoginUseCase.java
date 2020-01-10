package com.carmel.android.guestjini.UseCases;

import com.carmel.android.guestjini.domain.User.AuthenticationBean;
import com.carmel.android.guestjini.domain.User.AuthenticationBeanImpl;
import com.carmel.android.guestjini.models.User.AuthData;

public class LoginUseCase {

    private AuthenticationBean authenticationBean;

    public LoginUseCase() {
        authenticationBean = new AuthenticationBeanImpl();
    }

    public AuthData attemptLogin(String userName, String password){
        return authenticationBean.attemptLogin(userName, password);
    }
}
