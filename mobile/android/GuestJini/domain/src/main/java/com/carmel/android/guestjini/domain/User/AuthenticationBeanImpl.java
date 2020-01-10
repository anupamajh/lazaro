package com.carmel.android.guestjini.domain.User;

import com.carmel.android.guestjini.datalayer.User.UserDAO;
import com.carmel.android.guestjini.datalayer.User.UserDAOImpl;
import com.carmel.android.guestjini.models.User.AuthData;

public class AuthenticationBeanImpl implements AuthenticationBean {

    private UserDAO userDAO;

    public AuthenticationBeanImpl() {
        userDAO = new UserDAOImpl();
    }

    @Override
    public AuthData attemptLogin(String userName, String password) {
        return userDAO.attemptLogin(userName, password);
    }
}
