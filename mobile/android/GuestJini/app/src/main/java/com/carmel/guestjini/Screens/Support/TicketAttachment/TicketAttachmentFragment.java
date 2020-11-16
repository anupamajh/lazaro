package com.carmel.guestjini.Screens.Support.TicketAttachment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.carmel.guestjini.Screens.Common.Controllers.ActivityResultListener;
import com.carmel.guestjini.Screens.Common.Controllers.BaseFragment;

public class TicketAttachmentFragment
        extends BaseFragment
        implements ActivityResultListener {
    public static Fragment createFragment() {
        return new TicketAttachmentFragment();
    }

    private static final String SAVED_STATE_TICKET_ATTACHMENT_FRAGMENT = "SAVED_STATE_TICKET_ATTACHMENT_FRAGMENT";

    private TicketAttachmentController ticketAttachmentController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TicketAttachmentViewMVC viewMvc = getCompositionRoot().getViewMVCFactory().getTicketAttachmentViewMVC(container);
        ticketAttachmentController = getCompositionRoot().getTicketAttachmentController();
        getCompositionRoot().getActivityResultDispatcher().registerListener(this);
        if (savedInstanceState != null) {
            restoreControllerState(savedInstanceState);
        }
        ticketAttachmentController.bindView(viewMvc);
        ticketAttachmentController.bindActivity(this.getActivity());
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        ||ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 0);
        }
        return viewMvc.getRootView();
    }

    private void restoreControllerState(Bundle savedInstanceState) {
        ticketAttachmentController.restoreSavedState(
                (TicketAttachmentController.SavedState)
                        savedInstanceState.getSerializable(SAVED_STATE_TICKET_ATTACHMENT_FRAGMENT)
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        ticketAttachmentController.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        ticketAttachmentController.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_STATE_TICKET_ATTACHMENT_FRAGMENT, ticketAttachmentController.getSavedState());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        ticketAttachmentController.onActivityResult(requestCode, resultCode, data);
    }
}
