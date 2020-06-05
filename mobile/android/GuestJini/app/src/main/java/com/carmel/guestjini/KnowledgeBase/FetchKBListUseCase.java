package com.carmel.guestjini.KnowledgeBase;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.KnowledgeBase.KB;
import com.carmel.guestjini.Networking.KnowledgeBase.KBResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchKBListUseCase extends BaseObservable<FetchKBListUseCase.Listener> {

    public interface Listener {
        void onKBListFetched(List<KB> kbList);

        void onKBListFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchKBListUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchKBListAndNotify() {
        this.guestJiniAPI.getKBList().enqueue(new Callback<KBResponse>() {
            @Override
            public void onResponse(Call<KBResponse> call, Response<KBResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess()) {
                        notifySuccess(response.body().getKbList());
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
            listener.onKBListFetchFailed();
        }
    }

    private void notifySuccess(List<KB> kbList) {
        for (Listener listener : getListeners()) {
            listener.onKBListFetched(kbList);
        }
    }
}
