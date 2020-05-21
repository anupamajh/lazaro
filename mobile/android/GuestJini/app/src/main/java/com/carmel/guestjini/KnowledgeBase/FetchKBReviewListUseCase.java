package com.carmel.guestjini.KnowledgeBase;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.KnowledgeBase.KBReview;
import com.carmel.guestjini.Networking.KnowledgeBase.KBReviewResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchKBReviewListUseCase
        extends BaseObservable<FetchKBReviewListUseCase.Listener> {
    public interface Listener {
        void onKBReviewFetched(List<KBReview> kbReviewList);

        void onKBReviewFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchKBReviewListUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchKBReviewsAndNotify(String kbId) {
        Map<String, String> postData = new HashMap<>();
        postData.put("kbId", kbId);
        this.guestJiniAPI.getAllKBReviews(postData).enqueue(new Callback<KBReviewResponse>() {
            @Override
            public void onResponse(Call<KBReviewResponse> call, Response<KBReviewResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
                        notifySuccess(response.body().getKbReviewList());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<KBReviewResponse> call, Throwable t) {
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
            listener.onKBReviewFetchFailed();
        }
    }

    private void notifySuccess(List<KBReview> kbReviewList) {
        for (Listener listener : getListeners()) {
            listener.onKBReviewFetched(kbReviewList);
        }
    }
}