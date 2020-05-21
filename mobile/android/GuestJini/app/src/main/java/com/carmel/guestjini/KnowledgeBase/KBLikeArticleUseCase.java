package com.carmel.guestjini.KnowledgeBase;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.KnowledgeBase.KBRating;
import com.carmel.guestjini.Networking.KnowledgeBase.KBRatingResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KBLikeArticleUseCase
        extends BaseObservable<KBLikeArticleUseCase.Listener>
{
    public interface Listener {
        void onKBLiked(KBRating kbRating);

        void onKBLikeFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public KBLikeArticleUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void likeKBArticleAndNotify(String kbId, Integer isLiked) {
         KBRating kbRating = new KBRating();
        kbRating.setKbId(kbId);
        kbRating.setIsLiked(isLiked);
        this.guestJiniAPI.saveKBRating(kbRating).enqueue(new Callback<KBRatingResponse>() {
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
            listener.onKBLikeFailed();
        }
    }

    private void notifySuccess(KBRating kbRating) {
        for (Listener listener : getListeners()) {
            listener.onKBLiked(kbRating);
        }
    }
}
