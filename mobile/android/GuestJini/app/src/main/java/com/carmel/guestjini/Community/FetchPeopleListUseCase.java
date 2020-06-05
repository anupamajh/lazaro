package com.carmel.guestjini.Community;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Users.PeopleResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchPeopleListUseCase extends BaseObservable<FetchPeopleListUseCase.Listener> {

    public interface Listener {
        void onPeopleListFetched(PeopleResponse peopleResponse);

        void onPeopleListFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchPeopleListUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchPeopleListAndNotify() {
        this.guestJiniAPI.getPeopleList().enqueue(new Callback<PeopleResponse>() {
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
            listener.onPeopleListFetchFailed();
        }
    }

    private void notifySuccess(PeopleResponse peopleResponse) {
        for (Listener listener : getListeners()) {
            listener.onPeopleListFetched(peopleResponse);
        }
    }
}