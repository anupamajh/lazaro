package com.carmel.guestjini.KnowledgeBase;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.KnowledgeBase.KBRating;
import com.carmel.guestjini.Networking.KnowledgeBase.KBRatingResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchKBRatingUseCase
        extends BaseObservable<FetchKBRatingUseCase.Listener> {
    public interface Listener {
        void onKBRatingFetched(KBRating kbRating);

        void onKBRatingFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchKBRatingUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchKBRatingAndNotify(String kbId) {
        Map<String, String> postData = new HashMap<>();
        postData.put("kbId", kbId);
        this.guestJiniAPI.getKBRatings(postData).enqueue(new Callback<KBRatingResponse>() {
            @Override
            public void onResponse(Call<KBRatingResponse> call, Response<KBRatingResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess()) {
                        notifySuccess(response.body().getKbRating());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<KBRatingResponse> call, Throwable t) {
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
            listener.onKBRatingFetchFailed();
        }
    }

    private void notifySuccess(KBRating kbRating) {
        for (Listener listener : getListeners()) {
            listener.onKBRatingFetched(kbRating);
        }
    }
}
