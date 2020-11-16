package com.carmel.guestjini.Screens.Support.TicketAttachment;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.webkit.MimeTypeMap;

import androidx.fragment.app.FragmentActivity;
import androidx.loader.content.CursorLoader;

import com.carmel.guestjini.Networking.Tickets.TaskAttachment;
import com.carmel.guestjini.Networking.Tickets.TaskAttachmentResponse;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Tickets.UploadFileUseCase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

public class TicketAttachmentController
        implements TicketAttachmentViewMVC.Listener,
        UploadFileUseCase.Listener,
        DialogsEventBus.Listener {

    private enum ScreenState {
        IDLE,
        UPLOAD_FILE, UPLOAD_FILE_SUCCESS, UPLOAD_FILE_FAILED,
        NETWORK_ERROR
    }

    private final UploadFileUseCase uploadFileUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;
    private List<TaskAttachment> taskAttachments = new ArrayList<>();

    private TicketAttachmentViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;
    private FragmentActivity activity;
    private Uri fileSaveURI;

    public TicketAttachmentController
            (
                    UploadFileUseCase uploadFileUseCase,
                    ScreensNavigator screensNavigator,
                    DialogsManager dialogsManager,
                    DialogsEventBus dialogsEventBus
            ) {
        this.uploadFileUseCase = uploadFileUseCase;
        this.screensNavigator = screensNavigator;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }


    public void bindView(TicketAttachmentViewMVC ticketAttachmentViewMVC) {
        this.viewMVC = ticketAttachmentViewMVC;
    }

    public void onStart() {
        viewMVC.registerListener(this);
        uploadFileUseCase.registerListener(this);
        dialogsEventBus.registerListener(this);
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
        dialogsEventBus.unregisterListener(this);
        uploadFileUseCase.unregisterListener(this);
    }

    public SavedState getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }


    public void bindActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onCameraClicked() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        if (Build.VERSION.SDK_INT < 24) {
            fileSaveURI = Uri.fromFile(getOutputMediaFile());
        } else {
            try {
                fileSaveURI = Uri.parse(getOutputMediaFile().getPath()); // My work-around for new SDKs, doesn't work in Android 10.
            } catch (Exception ex) {
                String e = ex.getMessage();
            }
        }
        activity.startActivityForResult(intent, 1000);
    }

    @Override
    public void onGalleryClicked() {
        Intent i = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(i, 2000);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1000) {
            try {
                Bitmap bm = (Bitmap) data.getExtras().get("data");
                String path = Environment.getExternalStorageDirectory().toString()
                        + "/Android/data/"
                        + viewMVC.getRootView().getContext().getPackageName()
                        + "/files";
                File dir = new File(path);
                if(!dir.exists()){
                    dir.mkdir();
                }
                OutputStream outputStream = null;
                String fileName = "GuestJini_" + System.nanoTime() + ".png";
                File savedFile = new File(path+fileName);
                savedFile.createNewFile();
                  outputStream = new FileOutputStream(savedFile);
                bm.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStream.flush();
                outputStream.close();
                MediaStore.Images.Media.insertImage(
                        viewMVC.getRootView().getContext().getContentResolver(),
                        savedFile.getAbsolutePath(),savedFile.getName(),savedFile.getName()
                );

                Uri fileURI = Uri.fromFile(savedFile);
                uploadFileAndNotify(fileURI, fileName);
            } catch (Exception ex) {
                String e = ex.getMessage();
            }
        } else if (requestCode == 2000) {
            if (resultCode == RESULT_OK) {
                try {
                    String fileName = "GuestJini_" + System.nanoTime() + ".png";
                    uploadFileAndNotify(data.getData(), fileName);
                } catch (Exception ex) {
                    String msg = ex.getMessage();
                }
            }
        }
    }


    private void uploadFileAndNotify(Uri fileURI, String fileName) {
        File file = new File(getFilePath(fileURI));
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(getMimeType(fileURI)),
                        file
                );

        uploadFileUseCase.uploadFileAndNotify(
                requestFile,
                fileName,
                file.getName()
        );

    }

    public String getFilePath(Uri fileURI){
        String path = null;
        String[] projection = { MediaStore.Files.FileColumns.DATA };
        Cursor cursor = viewMVC.getRootView().getContext().getContentResolver().query(fileURI, projection, null, null, null);

        if(cursor == null){
            path = fileURI.getPath();
        }
        else{
            cursor.moveToFirst();
            int column_index = cursor.getColumnIndexOrThrow(projection[0]);
            path = cursor.getString(column_index);
            cursor.close();
        }

        return ((path == null || path.isEmpty()) ? (fileURI.getPath()) : path);
    }

    public String getMimeType(Uri uri) {
        String mimeType = null;
        if (ContentResolver.SCHEME_CONTENT.equals(uri.getScheme())) {
            ContentResolver cr = viewMVC.getRootView().getContext().getContentResolver();
            mimeType = cr.getType(uri);
        } else {
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
                    .toString());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    fileExtension.toLowerCase());
        }
        return mimeType;
    }

    @Override
    public void onFileUploaded(TaskAttachmentResponse taskAttachmentResponse) {
        this.taskAttachments.add(taskAttachmentResponse.getTaskAttachment());
        viewMVC.bindAttachments(taskAttachments);
    }


    @Override
    public void onDialogEvent(Object event) {

    }

    @Override
    public void onAttachmentClicked(TaskAttachment taskAttachment) {

    }

    @Override
    public void onDeleteClicked(TaskAttachment taskAttachment) {

    }

    @Override
    public void onDocumentsClicked() {

    }

    @Override
    public void onBackClicked() {
        screensNavigator.navigateUp();
    }

    @Override
    public void onShowAttachmentOptionClicked() {

    }

    @Override
    public void onFileUploadFailed() {

    }

    @Override
    public void onNetworkFailed() {

    }

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg");
    }
}
