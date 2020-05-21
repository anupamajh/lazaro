package com.carmel.guestjini.Tickets;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Tickets.TaskNote;
import com.carmel.guestjini.Networking.Tickets.TaskNotesResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchTicketTaskNoteListUseCase extends BaseObservable<FetchTicketTaskNoteListUseCase.Listener> {

public interface Listener {
    void onTaskNoteListFetched(List<TaskNote> taskNoteList);

    void onTaskNoteListFetchFailed();

    void onNetworkFailed();
}

    private final GuestJiniAPI guestJiniAPI;

    public FetchTicketTaskNoteListUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void fetchTicketListAndNotify(String ticketId) {
        Map<String, String> requestData = new HashMap<>();
        requestData.put("ticketId", ticketId);
        this.guestJiniAPI.getTicketNotes(requestData).enqueue(new Callback<TaskNotesResponse>() {
            @Override
            public void onResponse(Call<TaskNotesResponse> call, Response<TaskNotesResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
                        notifySuccess(response.body().getTaskNoteList());
                    } else {
                        notifyFailure();
                    }
                } else {
                    notifyFailure();
                }
            }

            @Override
            public void onFailure(Call<TaskNotesResponse> call, Throwable t) {
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
            listener.onTaskNoteListFetchFailed();
        }
    }

    private void notifySuccess(List<TaskNote> taskNoteList) {
        for (Listener listener : getListeners()) {
            listener.onTaskNoteListFetched(taskNoteList);
        }
    }
}