package com.carmel.guestjini.Users;

import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Users.UserResponse;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccountUseCase  extends BaseObservableViewMvc<CreateAccountUseCase.Listener> {

    public interface Listener {
        void onAccountCrated(UserResponse userResponse);

        void onAccountCrateFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public CreateAccountUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void createAccountAndNotify(
            String mobile,
            String fullName,
            Boolean isSupportTeamMember
    ) {
        Map<String, String> postData = new HashMap<>();
        if(fullName.trim().equals("")){
            fullName = mobile;
        }
        postData.put("fullName", fullName);
        postData.put("phone", mobile);
        if(isSupportTeamMember){
            this.guestJiniAPI.createSupportUserAccount(postData).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            notifySuccess(response.body());
                        } else {
                            notifyFailure();
                        }
                    } else {
                        notifyFailure();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    notifyNetworkFailure();
                }
            });
        }else{
            this.guestJiniAPI.createUserAccount(postData).enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            notifySuccess(response.body());
                        } else {
                            notifyFailure();
                        }
                    } else {
                        notifyFailure();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    notifyNetworkFailure();
                }
            });
        }


    }

    private void notifyNetworkFailure() {
        for (Listener listener : getListeners()) {
            listener.onNetworkFailed();
        }
    }

    private void notifyFailure() {
        for (Listener listener : getListeners()) {
            listener.onAccountCrateFailed();
        }
    }

    private void notifySuccess(UserResponse userResponse) {
        for (Listener listener : getListeners()) {
            listener.onAccountCrated(userResponse);
        }

    }

}
