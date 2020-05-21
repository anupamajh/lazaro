package com.carmel.guestjini.KnowledgeBase;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.KnowledgeBase.KB;
import com.carmel.guestjini.Networking.KnowledgeBase.KBResponse;
import com.carmel.guestjini.Networking.KnowledgeBase.KBReview;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveKBReviewUseCase
        extends BaseObservable<SaveKBReviewUseCase.Listener> {
    public interface Listener {
        void onKBReviewSaved(KB kb);

        void onKBReviewSaveFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public SaveKBReviewUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void saveKBReviewAndNotify(String kbId, String review) {
        KBReview kbReview = new KBReview();
        kbReview.setKbId(kbId);
        kbReview.setReviewComment(review);
        this.guestJiniAPI.saveKBReview(kbReview).enqueue(new Callback<KBResponse>() {
            @Override
            public void onResponse(Call<KBResponse> call, Response<KBResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess()) {
                        notifySuccess(response.body().getKb());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<KBResponse> call, Throwable t) {
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
            listener.onKBReviewSaveFailed();
        }
    }

    private void notifySuccess(KB kb) {
        for (Listener listener : getListeners()) {
            listener.onKBReviewSaved(kb);
        }
    }
}