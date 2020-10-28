package com.carmel.guestjini.Tickets;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Tickets.Ticket;
import com.carmel.guestjini.Networking.Tickets.TicketRequest;
import com.carmel.guestjini.Networking.Tickets.TicketResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveTicketUseCase extends BaseObservable<SaveTicketUseCase.Listener> {

    public interface Listener {
        void onTicketSaved(Ticket ticket);

        void onTicketSaveFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public SaveTicketUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void saveTicketAndNotify(String ticketTitle, String ticketNarration, String ticketCategoryId, int saveStatus, String draftTicketId) {
        Ticket ticket = new Ticket();
        ticket.setId(draftTicketId);
        ticket.setTicketCategoryId(ticketCategoryId);
        ticket.setTicketTitle(ticketTitle);
        ticket.setTicketNarration(ticketNarration);
        ticket.setTicketStatus(saveStatus);
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setTaskTicket(ticket);
        ticketRequest.setTaskAttachmentList(new ArrayList<>());
        this.guestJiniAPI.saveTicket(ticketRequest).enqueue(new Callback<TicketResponse>() {
            @Override
            public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
                        notifySuccess(response.body().getTaskTicket());
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
            listener.onTicketSaveFailed();
        }
    }

    private void notifySuccess(Ticket ticket) {
        for (Listener listener : getListeners()) {
            listener.onTicketSaved(ticket);
        }
    }
}