package com.carmel.guestjini.Tickets;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.Networking.Tickets.TicketResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchTicketListUseCase extends BaseObservable<FetchTicketListUseCase.Listener> {

    public interface Listener {
        void onTicketListFetched(List<Ticket> ticketList);

        void onTicketListFetchFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public FetchTicketListUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchTicketListAndNotify(int ticketStatus) {
        Map<String, String> postData = new HashMap<>();
        postData.put("ticket_status", String.valueOf(ticketStatus));
        this.guestJiniAPI.getTicketList(postData).enqueue(new Callback<TicketResponse>() {
            @Override
            public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
                        notifySuccess(response.body().getTaskTicketList());
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

    private void notifySuccess(List<Ticket> ticketList) {
        for (Listener listener : getListeners()) {
            listener.onTicketListFetched(ticketList);
        }
    }
}