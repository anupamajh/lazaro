package com.carmel.guestjini.Tickets;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Tickets.TicketCountDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchTicketCountUseCase  extends BaseObservable<FetchTicketCountUseCase.Listener> {
    public interface Listener {
        void onTicketCountFetched(TicketCountDTO ticketCountDTO);

        void onTicketCountFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchTicketCountUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchTicketCountAndNotify() {
        this.guestJiniAPI.getTicketCountByStatus().enqueue(new Callback<TicketCountDTO>() {
            @Override
            public void onResponse(Call<TicketCountDTO> call, Response<TicketCountDTO> response) {
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
            public void onFailure(Call<TicketCountDTO> call, Throwable t) {
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

    private void notifySuccess(TicketCountDTO ticketCountDTO) {
        for (Listener listener : getListeners()) {
            listener.onTicketCountFetched(ticketCountDTO);
        }
    }
}
