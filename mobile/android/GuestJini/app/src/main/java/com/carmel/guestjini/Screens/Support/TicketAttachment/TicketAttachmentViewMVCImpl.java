package com.carmel.guestjini.Screens.Support.TicketAttachment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carmel.guestjini.Networking.Tickets.TaskAttachment;
import com.carmel.guestjini.R;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Common.Views.BaseObservableViewMvc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TicketAttachmentViewMVCImpl
        extends BaseObservableViewMvc<TicketAttachmentViewMVC.Listener>
        implements TicketAttachmentViewMVC,
        TicketAttachmentRecyclerAdapter.Listener {

    private final ProgressBar progressBar;
    private final ImageView btnBack;
    private final FloatingActionButton showAttachOptions;
    private final DrawerLayout attachmentDrawerLayout;
    private final RecyclerView lstAttachments;
    private final FloatingActionButton cameraIcon;
    private final FloatingActionButton galleryIcon;
    private final FloatingActionButton fileIcon;
    private final RelativeLayout cameraLayout;
    private final RelativeLayout galleryLayout;
    private final RelativeLayout fileLayout;

    private final TicketAttachmentRecyclerAdapter ticketAttachmentRecyclerAdapter;


    public TicketAttachmentViewMVCImpl(
            LayoutInflater inflater,
            @Nullable ViewGroup parent,
            ViewMVCFactory viewMVCFactory
    ) {
        setRootView(inflater.inflate(R.layout.layout_support_ticket_attachments, parent, false));
        progressBar = findViewById(R.id.progress);
        btnBack = findViewById(R.id.btnBack);
        showAttachOptions = findViewById(R.id.showAttachOptions);
        attachmentDrawerLayout = findViewById(R.id.attachmentDrawerLayout);
        cameraIcon = findViewById(R.id.cameraIcon);
        galleryIcon = findViewById(R.id.galleryIcon);
        fileIcon = findViewById(R.id.fileIcon);
        lstAttachments = findViewById(R.id.lstAttachments);
        lstAttachments.setLayoutManager(new LinearLayoutManager(getContext()));
        ticketAttachmentRecyclerAdapter = new TicketAttachmentRecyclerAdapter(this, viewMVCFactory);
        lstAttachments.setAdapter(ticketAttachmentRecyclerAdapter);
        cameraLayout = findViewById(R.id.cameraLayout);
        galleryLayout = findViewById(R.id.galleryLayout);
        fileLayout = findViewById(R.id.fileLayout);

        btnBack.setOnClickListener(v -> {
            for(Listener listener:getListeners()){
                listener.onBackClicked();
            }
        });

        showAttachOptions.setOnClickListener(v -> {
            attachmentDrawerLayout.openDrawer(GravityCompat.START);
        });

        cameraIcon.setOnClickListener(v -> {
            attachmentDrawerLayout.closeDrawers();
            for(Listener listener:getListeners()){
                listener.onCameraClicked();
            }
        });

        cameraLayout.setOnClickListener(v -> {
            attachmentDrawerLayout.closeDrawers();
            for(Listener listener:getListeners()){
                listener.onCameraClicked();
            }
        });

        galleryIcon.setOnClickListener(v -> {
            attachmentDrawerLayout.closeDrawers();
            for(Listener listener:getListeners()){
                listener.onGalleryClicked();
            }
        });

        galleryLayout.setOnClickListener(v -> {
            attachmentDrawerLayout.closeDrawers();
            for(Listener listener:getListeners()){
                listener.onGalleryClicked();
            }
        });

        fileIcon.setOnClickListener(v -> {
            attachmentDrawerLayout.closeDrawers();
            for(Listener listener:getListeners()){
                listener.onDocumentsClicked();
            }
        });

        fileLayout.setOnClickListener(v -> {
            attachmentDrawerLayout.closeDrawers();
            for(Listener listener:getListeners()){
                listener.onDocumentsClicked();
            }
        });

    }

    @Override
    public void onDeleteClicked(TaskAttachment taskAttachment) {
        for (Listener listener : getListeners()) {
            listener.onDeleteClicked(taskAttachment);
        }
    }

    @Override
    public void onAttachmentClicked(TaskAttachment taskAttachment) {
        for (Listener listener : getListeners()) {
            listener.onAttachmentClicked(taskAttachment);
        }
    }

    @Override
    public void bindAttachments(List<TaskAttachment> taskAttachmentList) {
        ticketAttachmentRecyclerAdapter.bindAttachments(taskAttachmentList);
    }

    @Override
    public void showProgressIndication() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        progressBar.setVisibility(View.GONE);
    }


}
