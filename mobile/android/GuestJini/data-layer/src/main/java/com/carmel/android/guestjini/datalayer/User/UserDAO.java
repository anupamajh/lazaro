package com.carmel.android.guestjini.datalayer.User;

import com.carmel.android.guestjini.models.User.AuthData;

public interface UserDAO {
    AuthData attemptLogin(String userName, String password);
}
