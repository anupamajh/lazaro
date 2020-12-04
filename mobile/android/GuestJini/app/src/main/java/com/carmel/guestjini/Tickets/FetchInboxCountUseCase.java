package com.carmel.guestjini.Tickets;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Tickets.InboxCount;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchInboxCountUseCase  extends BaseObservable<FetchInboxCountUseCase.Listener> {
    public interface Listener {
        void onInboxCountFetched(InboxCount inboxCount);

        void onTicketCountFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchInboxCountUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchTaskCountAndNotify() {
        this.guestJiniAPI.getInboxCount().enqueue(new Callback<InboxCount>() {
            @Override
            public void onResponse(Call<InboxCount> call, Response<InboxCount> response) {
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
            public void onFailure(Call<InboxCount> call, Throwable t) {
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
            listener.onTicketCountFetchFailed();
        }
    }

    private void notifySuccess(InboxCount inboxCount) {
        for (Listener listener : getListeners()) {
            listener.onInboxCountFetched(inboxCount);
        }
    }
}