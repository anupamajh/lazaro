package com.carmel.guestjini.Screens.Settings.MyProfile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;

import androidx.fragment.app.FragmentActivity;

import com.carmel.guestjini.Networking.Users.UserInfo;
import com.carmel.guestjini.Networking.Users.UserPreferenceResponse;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialogEvent;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Users.FetchMyProfilePicUseCase;
import com.carmel.guestjini.Users.FetchMyProfileUseCase;
import com.carmel.guestjini.Users.SaveProfilePicUseCase;
import com.carmel.guestjini.Users.SaveUserPreferenceUseCase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class MyProfileController
        implements MyProfileViewMVC.Listener,
        DialogsEventBus.Listener,
        FetchMyProfileUseCase.Listener,
        SaveUserPreferenceUseCase.Listener,
        FetchMyProfilePicUseCase.Listener, SaveProfilePicUseCase.Listener {
    private enum ScreenState {
        IDLE,
        FETCHING_MY_PROFILE, MY_PROFILE_FETCHED,
        FETCHING_MY_PROFILE_PIC, MY_PROFILE_PIC_FETCHED,
        SAVING_USER_PREFERENCE, USER_PREFERENCE_SAVED,
        SAVING_USER_PROFILE_PIC, USER_PROFILE_PIC_SAVED,
        NETWORK_ERROR
    }

    private final FetchMyProfileUseCase fetchMyProfileUseCase;
    private final FetchMyProfilePicUseCase fetchMyProfilePicUseCase;
    private final SaveProfilePicUseCase saveProfilePicUseCase;
    private final SaveUserPreferenceUseCase saveUserPreferenceUseCase;
    private final ScreensNavigator screensNavigator;
    private final DialogsManager dialogsManager;
    private final DialogsEventBus dialogsEventBus;

    private MyProfileViewMVC viewMVC;
    private ScreenState mScreenState = ScreenState.IDLE;
    private Uri file;
    private FragmentActivity activity;


    public MyProfileController
            (
                    FetchMyProfileUseCase fetchMyProfileUseCase,
                    FetchMyProfilePicUseCase fetchMyProfilePicUseCase,
                    SaveProfilePicUseCase saveProfilePicUseCase,
                    SaveUserPreferenceUseCase saveUserPreferenceUseCase,
                    ScreensNavigator screensNavigator,
                    DialogsManager dialogsManager,
                    DialogsEventBus dialogsEventBus) {
        this.fetchMyProfileUseCase = fetchMyProfileUseCase;
        this.fetchMyProfilePicUseCase = fetchMyProfilePicUseCase;
        this.saveProfilePicUseCase = saveProfilePicUseCase;
        this.saveUserPreferenceUseCase = saveUserPreferenceUseCase;
        this.screensNavigator = screensNavigator;
        this.dialogsManager = dialogsManager;
        this.dialogsEventBus = dialogsEventBus;
    }

    public void bindView(MyProfileViewMVC viewMvc) {
        this.viewMVC = viewMvc;
    }

    public void bindActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            try {
                Bitmap bm = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                String encoded = Base64.encodeToString(byteArray, Base64.NO_WRAP);
                viewMVC.bindProfilePic(encoded);
                saveProfilePicAndNotify(encoded);
            } catch (Exception ex) {
                String e = ex.getMessage();
            }
        } else if (requestCode == 200) {
            if (resultCode == RESULT_OK) {
                try {
                    viewMVC.setImageURI(data.getData());
                    BitmapDrawable bitmapDrawable = ((BitmapDrawable) viewMVC.getProfilePicDrawable());
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    String encoded = Base64.encodeToString(byteArray, Base64.NO_WRAP);
                    saveProfilePicAndNotify(encoded);
                } catch (Exception ex) {

                }
            }
        }
    }


    public SavedState getSavedState() {
        return new SavedState(mScreenState);
    }

    public void restoreSavedState(SavedState savedState) {
        mScreenState = savedState.mScreenState;
    }

    public void onStart() {
        viewMVC.registerListener(this);
        fetchMyProfileUseCase.registerListener(this);
        fetchMyProfilePicUseCase.registerListener(this);
        saveUserPreferenceUseCase.registerListener(this);
        saveProfilePicUseCase.registerListener(this);
        dialogsEventBus.registerListener(this);
        if (mScreenState != ScreenState.NETWORK_ERROR) {
            fetchMyProfileAndNotify();
        }
    }

    public void onStop() {
        viewMVC.unregisterListener(this);
        fetchMyProfileUseCase.unregisterListener(this);
        fetchMyProfilePicUseCase.unregisterListener(this);
        saveUserPreferenceUseCase.unregisterListener(this);
        saveProfilePicUseCase.unregisterListener(this);
        dialogsEventBus.unregisterListener(this);
    }

    private void fetchMyProfileAndNotify() {
        viewMVC.showProgressIndication();
        mScreenState = ScreenState.FETCHING_MY_PROFILE;
        fetchMyProfileUseCase.fetchProfileAndNotify();
    }

    private void fetchMyProfilePicAndNotify() {
        viewMVC.showProgressIndication();
        mScreenState = ScreenState.FETCHING_MY_PROFILE_PIC;
        fetchMyProfilePicUseCase.fetchProfilePicAndNotify();
    }

    private void saveUserPreferenceAndNotify(int preferenceType, int isHidden) {
        mScreenState = ScreenState.SAVING_USER_PREFERENCE;
        viewMVC.showProgressIndication();
        saveUserPreferenceUseCase.saveUserPreferenceAndNotify(preferenceType, isHidden);
    }

    private void saveProfilePicAndNotify(String encoded) {
        mScreenState = ScreenState.SAVING_USER_PROFILE_PIC;
        viewMVC.showProgressIndication();
        saveProfilePicUseCase.saveProfilePicAndNotify(encoded);
    }

    @Override
    public void onCameraClicked() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        if (Build.VERSION.SDK_INT < 24) {
            file = Uri.fromFile(getOutputMediaFile());
        } else {
            try {
                file = Uri.parse(getOutputMediaFile().getPath()); // My work-around for new SDKs, doesn't work in Android 10.
            } catch (Exception ex) {
                String e = ex.getMessage();
            }
        }
        activity.startActivityForResult(intent, 100);
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

    @Override
    public void onGalleryClicked() {
        Intent i = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(i, 200);
    }

    @Override
    public void onProfileFetched(UserInfo response) {
        viewMVC.hideProgressIndication();
        mScreenState = ScreenState.MY_PROFILE_FETCHED;
        viewMVC.bindMyProfile(response);
        fetchMyProfilePicAndNotify();
    }

    @Override
    public void onProfileFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMVC.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("My Profile", null);
    }

    @Override
    public void onUserPreferenceSaved(UserPreferenceResponse userPreferenceResponse) {
        viewMVC.hideProgressIndication();
        mScreenState = ScreenState.USER_PREFERENCE_SAVED;
    }

    @Override
    public void onUserPreferenceSaveFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMVC.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("User Preference", null);
    }

    @Override
    public void onProfilePicFetched(String response) {
        viewMVC.hideProgressIndication();
        mScreenState = ScreenState.MY_PROFILE_PIC_FETCHED;
        viewMVC.bindProfilePic(response);
    }

    @Override
    public void onProfilePicFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMVC.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Profile Pic", null);
    }

    @Override
    public void onProfilePicSaved(UserInfo userInfo) {
        viewMVC.hideProgressIndication();
        mScreenState = ScreenState.USER_PROFILE_PIC_SAVED;
    }

    @Override
    public void onProfilePicSaveFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMVC.hideProgressIndication();
        dialogsManager.showUseCaseFailedDialog("Profile Pic", null);
    }

    @Override
    public void onNetworkFailed() {
        mScreenState = ScreenState.NETWORK_ERROR;
        viewMVC.hideProgressIndication();
        dialogsManager.showNetworkFailedInfoDialog(null, "My Profile");
    }


    @Override
    public void onBackClicked() {
        screensNavigator.navigateUp();
    }

    @Override
    public void onUserPreferenceChange(int preferenceType, boolean isVisible) {
        saveUserPreferenceAndNotify(preferenceType, (isVisible) ? 1 : 0);
    }

    @Override
    public void onShowInterestsClicked() {
        screensNavigator.toMyInterests();
    }

    @Override
    public void onDialogEvent(Object event) {
        if (event instanceof PromptDialogEvent) {
            switch (((PromptDialogEvent) event).getClickedButton()) {
                case POSITIVE:
                    fetchMyProfileAndNotify();
                    break;
                case NEGATIVE:
                    mScreenState = ScreenState.IDLE;
                    break;
            }
        }
    }


    public static class SavedState implements Serializable {
        private final ScreenState mScreenState;

        public SavedState(ScreenState screenState) {
            mScreenState = screenState;
        }
    }
}
