package com.carmel.android.guestjini.datalayer.User;

import android.util.Log;

import com.carmel.android.guestjini.datalayer.Common.EndPoints;
import com.carmel.android.guestjini.datalayer.User.API.UserAPI;
import com.carmel.android.guestjini.models.User.AuthData;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserDAOImpl implements UserDAO{

    @Override
    public AuthData attemptLogin(String userName, String password) {

        return null;
    }


}
