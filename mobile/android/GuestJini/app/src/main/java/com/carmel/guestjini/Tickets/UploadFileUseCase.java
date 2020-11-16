package com.carmel.guestjini.Tickets;

import com.carmel.guestjini.Common.BaseObservable;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Networking.Tickets.TaskAttachmentResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadFileUseCase
        extends BaseObservable<UploadFileUseCase.Listener> {

    public interface Listener {
        void onFileUploaded(TaskAttachmentResponse taskAttachmentResponse);

        void onFileUploadFailed();

        void onNetworkFailed();
    }

    private final GuestJiniAPI guestJiniAPI;

    public UploadFileUseCase(GuestJiniAPI guestJiniAPI) {
        this.guestJiniAPI = guestJiniAPI;
    }

    public void uploadFileAndNotify(RequestBody requestFile, String fileName, String fileRealName) {
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", fileRealName, requestFile);
        RequestBody fileNameBody =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, fileName);


        this.guestJiniAPI.uploadTicketAttachment(
                body,
                fileNameBody
        ).enqueue(new Callback<TaskAttachmentResponse>() {
            @Override
            public void onResponse(Call<TaskAttachmentResponse> call, Response<TaskAttachmentResponse> response) {
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
            public void onFailure(Call<TaskAttachmentResponse> call, Throwable t) {
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
            listener.onFileUploadFailed();
        }
    }

    private void notifySuccess(TaskAttachmentResponse taskAttachmentResponse) {
        for (Listener listener : getListeners()) {
            listener.onFileUploaded(taskAttachmentResponse);
        }
    }

}
