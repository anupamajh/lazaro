package com.carmel.guestjini.Services.Ticket;

import com.carmel.guestjini.Common.EndPoints;
import com.carmel.guestjini.Models.Ticket.TaskNote;
import com.carmel.guestjini.Models.Ticket.TaskNotesResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TaskNoteService {
    String BASE_URL = EndPoints.END_POINT_URL;

    @POST("/guest-jini/task-ticket-notes/get-ticket-notes")
    Call<TaskNotesResponse> getAll(
            @Body Map<String, String> requestData
    );

    @POST("/guest-jini/task-ticket-notes/save")
    Call<TaskNotesResponse> save(
            @Body TaskNote taskNote
            );
}
