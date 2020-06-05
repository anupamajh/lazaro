package com.carmel.guestjini.Community;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Users.PeopleResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchPersonDetailUseCase extends BaseObservable<FetchPersonDetailUseCase.Listener> {

    public interface Listener {
        void onPersonDetailFetched(PeopleResponse peopleResponse);

        void onPersonDetailFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchPersonDetailUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchPersonDetailAndNotify(String personId) {
        Map<String, String> postData = new HashMap<>();
        postData.put("userId", personId);
        this.guestJiniAPI.getPersonDetails(postData).enqueue(new Callback<PeopleResponse>() {
            @Override
            public void onResponse(Call<PeopleResponse> call, Response<PeopleResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
                        notifySuccess(response.body());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<PeopleResponse> call, Throwable t) {
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
            listener.onPersonDetailFetchFailed();
        }
    }

    private void notifySuccess(PeopleResponse peopleResponse) {
        for (Listener listener : getListeners()) {
            listener.onPersonDetailFetched(peopleResponse);
        }
    }
}