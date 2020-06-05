package com.carmel.guestjini.KnowledgeBase;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.KnowledgeBase.KBRatingPercentResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchKBRatingPercentageUseCase
        extends BaseObservable<FetchKBRatingPercentageUseCase.Listener> {
    public interface Listener {
        void onKBRatingPercentageFetched(KBRatingPercentResponse kbRatingPercentResponse);

        void onKBRatingPercentageFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchKBRatingPercentageUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchKBRatingPercentageAndNotify(String kbId) {
        Map<String, String> postData = new HashMap<>();
        postData.put("kbId", kbId);
        this.guestJiniAPI.getKBRatingPercentage(postData).enqueue(new Callback<KBRatingPercentResponse>() {
            @Override
            public void onResponse(Call<KBRatingPercentResponse> call, Response<KBRatingPercentResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess()) {
                        notifySuccess(response.body());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<KBRatingPercentResponse> call, Throwable t) {
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
            listener.onKBRatingPercentageFetchFailed();
        }
    }

    private void notifySuccess(KBRatingPercentResponse kbRatingPercentResponse) {
        for (Listener listener : getListeners()) {
            listener.onKBRatingPercentageFetched(kbRatingPercentResponse);
        }
    }
}