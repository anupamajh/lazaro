package com.carmel.guestjini.Tickets;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Tickets.TaskNote;
import com.carmel.guestjini.Networking.Tickets.TaskNotesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveTaskNoteUseCase extends BaseObservable<SaveTaskNoteUseCase.Listener> {

    public interface Listener {
        void onTaskNoteSaved(TaskNote taskNote);

        void onTaskNoteSaveFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public SaveTaskNoteUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void saveTaskNoteAndNotify(String ticketId, String note) {
        TaskNote taskNote = new TaskNote();
        taskNote.setTicketId(ticketId);
        taskNote.setNotes(note);
        this.guestJiniAPI.saveTicketNotes(taskNote).enqueue(new Callback<TaskNotesResponse>() {
            @Override
            public void onResponse(Call<TaskNotesResponse> call, Response<TaskNotesResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
                        notifySuccess(response.body().getTaskNote());
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
            listener.onTaskNoteSaveFailed();
        }
    }

    private void notifySuccess(TaskNote taskNote) {
        for (Listener listener : getListeners()) {
            listener.onTaskNoteSaved(taskNote);
        }
    }
}