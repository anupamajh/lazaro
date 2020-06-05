package com.carmel.guestjini.Community;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Users.PeopleResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPersonToFavouriteUseCase extends BaseObservable<AddPersonToFavouriteUseCase.Listener> {

    public interface Listener {
        void onFavouriteAdded(PeopleResponse peopleResponse);

        void onFavouriteAddFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public AddPersonToFavouriteUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void addPersonToFavouriteAndNotify(String personId, int isFavourite) {
        Map<String, String> postData = new HashMap<>();
        postData.put("userId", personId);
        postData.put("isFavourite", String.valueOf(isFavourite));
        this.guestJiniAPI.addPersonFavourite(postData).enqueue(new Callback<PeopleResponse>() {
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
            listener.onFavouriteAddFailed();
        }
    }

    private void notifySuccess(PeopleResponse peopleResponse) {
        for (Listener listener : getListeners()) {
            listener.onFavouriteAdded(peopleResponse);
        }
    }
}