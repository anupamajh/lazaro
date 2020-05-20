package com.carmel.guestjini.Screens.Common.Dialogs;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.Dialogs.InfoDialog.InfoDialog;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptDialog;

public class DialogsManager {

    private final Context mContext;
    private final FragmentManager mFragmentManager;

    public DialogsManager(Context context, FragmentManager fragmentManager) {
        mContext = context;
        mFragmentManager = fragmentManager;
    }

    public void showInfoDialog(
            @Nullable String tag,
            String title,
            String message,
            boolean isSuccess
    ) {
        DialogFragment dialogFragment = InfoDialog.createDialog(
                title,
                message,
                isSuccess
        );
        dialogFragment.show(mFragmentManager, tag);
    }


    private String getString(int stringId) {
        return mContext.getString(stringId);
    }

    public @Nullable
    String getShownDialogTag() {
        for (Fragment fragment : mFragmentManager.getFragments()) {
            if (fragment instanceof BaseDialog) {
                return fragment.getTag();
            }
        }
        return null;
    }

    public void showNetworkFailedInfoDialog(@Nullable String tag, String title) {
        DialogFragment dialogFragment = InfoDialog.createDialog(
                title,
                getString(R.string.network_filed_message),
                false
        );
        dialogFragment.show(mFragmentManager, tag);
    }

    public void showUseCaseFailedDialog(
            String title,
            @Nullable String tag
    ) {
        DialogFragment dialogFragment = PromptDialog.newPromptDialog(
                title,
                getString(R.string.error_use_case_network_failed_message),
                getString(R.string.error_network_call_failed_positive_button_caption),
                getString(R.string.error_network_call_failed_negative_button_caption)
        );
        dialogFragment.show(mFragmentManager, tag);
    }
}
