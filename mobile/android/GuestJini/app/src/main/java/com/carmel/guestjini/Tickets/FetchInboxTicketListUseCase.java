package com.carmel.guestjini.Tickets;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Common.Search.SearchRequest;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Tickets.TicketResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchInboxTicketListUseCase extends BaseObservable<FetchInboxTicketListUseCase.Listener> {

    public interface Listener {
        void onTicketListFetched(TicketResponse ticketResponse);

        void onTicketListFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchInboxTicketListUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchTicketListAndNotify(SearchRequest searchRequest) {
          this.guestJiniAPI.getSharedInboxList(searchRequest).enqueue(new Callback<TicketResponse>() {
            @Override
            public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
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
            public void onFailure(Call<TicketResponse> call, Throwable t) {
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
            listener.onTicketListFetchFailed();
        }
    }

    private void notifySuccess(TicketResponse ticketResponse) {
        for (Listener listener : getListeners()) {
            listener.onTicketListFetched(ticketResponse);
        }
    }
}