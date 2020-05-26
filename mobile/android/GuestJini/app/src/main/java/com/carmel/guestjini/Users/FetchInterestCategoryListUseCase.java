package com.carmel.guestjini.Users;

import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Users.InterestCategory;
import com.carmel.guestjini.Networking.Users.InterestCategoryResponse;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchInterestCategoryListUseCase extends BaseObservableViewMvc<FetchInterestCategoryListUseCase.Listener> {
    public interface Listener {
        void onInterestCategoryListFetched(List<InterestCategory> response);

        void onInterestCategoryListFetchFailed();

        void onNetworkFailed();

    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchInterestCategoryListUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }


    public void fetchInterestCategoryListAndNotify() {
        this.guestJiniAPI.getInterestCategoryList().enqueue(new Callback<InterestCategoryResponse>() {
            @Override
            public void onResponse(Call<InterestCategoryResponse> call, Response<InterestCategoryResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        notifySuccess(response.body().getInterestCategoryList());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<InterestCategoryResponse> call, Throwable t) {
                notifyNetworkFailure();
            }
        });
    }


    private void notifyFailure() {
        for (Listener listener : getListeners()) {
            listener.onInterestCategoryListFetchFailed();
        }
    }

    private void notifySuccess(List<InterestCategory> response) {
        for (Listener listener : getListeners()) {
            listener.onInterestCategoryListFetched(response);
        }
    }

    private void notifyNetworkFailure() {
        for (Listener listener : getListeners()) {
            listener.onNetworkFailed();
        }
    }
}