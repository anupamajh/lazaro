package com.carmel.android.guestjini.Presenters;

import android.os.AsyncTask;

import com.carmel.android.guestjini.UseCases.LoginUseCase;
import com.carmel.android.guestjini.View.LoginView;
import com.carmel.android.guestjini.models.User.AuthData;

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView view;
    private LoginUseCase loginUseCase;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        this.loginUseCase = new LoginUseCase();
    }

    @Override
    public void attemptLogin(String userName, String password) {
        new AsyncTask<Void, Void, AuthData>() {

            @Override
            protected AuthData doInBackground(Void... params) {
                return loginUseCase.attemptLogin(userName, password);
            }

            @Override
            protected void onPostExecute(AuthData authData) {
                super.onPostExecute(authData);
                view.processLogin(authData);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
