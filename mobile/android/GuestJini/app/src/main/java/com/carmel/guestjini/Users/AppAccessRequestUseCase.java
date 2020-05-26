package com.carmel.guestjini.Users;

import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Users.AppAccessRequestResponse;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppAccessRequestUseCase extends BaseObservableViewMvc<AppAccessRequestUseCase.Listener> {

    public interface Listener {
        void onAppAccessRequestSuccess(AppAccessRequestResponse appAccessRequestResponse);

        void onAppAccessRequestFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public AppAccessRequestUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void requestAppAccessAndNotify(
            String mobile,
            String email
    ) {
        Map<String, String> postData = new HashMap<>();
        postData.put("email", email);
        postData.put("mobile", mobile);

        this.guestJiniAPI.appAccessRequest(postData).enqueue(new Callback<AppAccessRequestResponse>() {
            @Override
            public void onResponse(Call<AppAccessRequestResponse> call, Response<AppAccessRequestResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getId()!=null) {
                        notifySuccess(response.body());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<AppAccessRequestResponse> call, Throwable t) {
                notifyNetworkFailure();
            }
        });
    }

    private void notifyNetworkFailure() {
        for (Listener listener : getListeners()) {
            listener.onNetworkFailed();
        }
    }

    private void notifyFailure() {
        for (Listener listener : getListeners()) {
            listener.onAppAccessRequestFailed();
        }
    }

    private void notifySuccess(AppAccessRequestResponse appAccessRequestResponse) {
        for (Listener listener : getListeners()) {
            listener.onAppAccessRequestSuccess(appAccessRequestResponse);
        }
        
    }

}
