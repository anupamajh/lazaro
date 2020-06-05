package com.carmel.guestjini.KnowledgeBase;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.KnowledgeBase.KB;
import com.carmel.guestjini.Networking.KnowledgeBase.KBResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchKBDetailsUseCase
        extends BaseObservable<FetchKBDetailsUseCase.Listener> {
    public interface Listener {
        void onKBDetailsFetched(KB kb);

        void onKBDetailsFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchKBDetailsUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchKBDetailsAndNotify(String kbId) {
        Map<String, String> postData = new HashMap<>();
        postData.put("id", kbId);
        this.guestJiniAPI.getKBById(postData).enqueue(new Callback<KBResponse>() {
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
            listener.onKBDetailsFetchFailed();
        }
    }

    private void notifySuccess(KB kb) {
        for (Listener listener : getListeners()) {
            listener.onKBDetailsFetched(kb);
        }
    }
}
