package com.carmel.guestjini.Common.DependencyInjection;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.carmel.guestjini.Authentication.AttemptLoginUseCase;
import com.carmel.guestjini.KnowledgeBase.FetchKBDetailsUseCase;
import com.carmel.guestjini.KnowledgeBase.FetchKBListUseCase;
import com.carmel.guestjini.KnowledgeBase.FetchKBRatingPercentageUseCase;
import com.carmel.guestjini.KnowledgeBase.FetchKBRatingUseCase;
import com.carmel.guestjini.KnowledgeBase.FetchKBReviewListUseCase;
import com.carmel.guestjini.KnowledgeBase.KBDislikeArticleUseCase;
import com.carmel.guestjini.KnowledgeBase.KBLikeArticleUseCase;
import com.carmel.guestjini.KnowledgeBase.SaveKBReviewUseCase;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.FragmentHelper.FragmentFrameHelper;
import com.carmel.guestjini.Screens.Common.FragmentHelper.FragmentFrameWrapper;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Screens.Common.SharedPreference.SharedPreferenceHelper;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Login.LoginController;
import com.carmel.guestjini.Screens.Login.LoginEventBus;
import com.carmel.guestjini.Screens.Support.KBDetail.KBDetailController;
import com.carmel.guestjini.Screens.Support.KBList.KBListController;
import com.carmel.guestjini.Screens.Support.SupportHome.SupportHomeController;
import com.carmel.guestjini.Screens.Support.TicketList.TicketListController;
import com.carmel.guestjini.Screens.Welcome.WelcomeController;
import com.carmel.guestjini.Tickets.FetchTicketListUseCase;

public class ControllerCompositionRoot {
    private final CompositionRoot compositionRoot;
    private final FragmentActivity fragmentActivity;
    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;


    public ControllerCompositionRoot(
            CompositionRoot compositionRoot,
            FragmentActivity fragmentActivity
    ) {
        this.compositionRoot = compositionRoot;
        this.fragmentActivity = fragmentActivity;
        this.preferences = fragmentActivity.getSharedPreferences("GuestJini", Context.MODE_PRIVATE);
        editor = this.preferences.edit();
    }

    private GuestJiniAPI getAuthenticatedGuestJiniAPI() {
        return compositionRoot.getAuthenticatedGuestJiniAPI(
                preferences.getString("access_token", ""),
                getActivity()
        );

    }

    private FragmentActivity getActivity() {
        return fragmentActivity;
    }

    private Context getContext() {
        return fragmentActivity;
    }

    private FragmentManager getFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(getContext());
    }

    public ScreensNavigator getScreensNavigator() {
        return new ScreensNavigator(getFragmentFrameHelper());
    }

    private FragmentFrameHelper getFragmentFrameHelper() {
        return new FragmentFrameHelper(getActivity(), getFragmentFrameWrapper(), getFragmentManager());
    }

    private FragmentFrameWrapper getFragmentFrameWrapper() {
        return (FragmentFrameWrapper) getActivity();
    }

    public ViewMVCFactory getViewMVCFactory() {
        return new ViewMVCFactory(getLayoutInflater());
    }

    public WelcomeController getWelcomeController() {
        return new WelcomeController(
                getScreensNavigator()
        );
    }

    private GuestJiniAPI getGuestJiniAPI() {
        return compositionRoot.getGuestJiniAPI();
    }

    public AttemptLoginUseCase getAttemptLoginUseCase() {
        return new AttemptLoginUseCase(getGuestJiniAPI());
    }

    private FetchKBListUseCase getFetchKBListUseCase() {
        return new FetchKBListUseCase(getAuthenticatedGuestJiniAPI());
    }

    private FetchKBDetailsUseCase getFetchKBDetailsUseCase() {
        return new FetchKBDetailsUseCase(getAuthenticatedGuestJiniAPI());
    }

    private FetchKBRatingUseCase getFetchKBRatingUseCaseUseCase() {
        return new FetchKBRatingUseCase(getAuthenticatedGuestJiniAPI());
    }

    private FetchKBRatingPercentageUseCase getFetchKBRatingPercentageUseCase() {
        return new FetchKBRatingPercentageUseCase(getAuthenticatedGuestJiniAPI());
    }

    private FetchKBReviewListUseCase getFetchKBReviewListUseCase() {
        return new FetchKBReviewListUseCase(getAuthenticatedGuestJiniAPI());
    }

    private KBLikeArticleUseCase getKBLikeArticleUseCasee() {
        return new KBLikeArticleUseCase(getAuthenticatedGuestJiniAPI());
    }

    private KBDislikeArticleUseCase getKBDislikeArticleUseCase() {
        return new KBDislikeArticleUseCase(getAuthenticatedGuestJiniAPI());
    }

    private SaveKBReviewUseCase getSaveKBReviewUseCase() {
        return new SaveKBReviewUseCase(getAuthenticatedGuestJiniAPI());
    }

    private FetchTicketListUseCase getFetchTicketListUseCase() {
        return new FetchTicketListUseCase(getAuthenticatedGuestJiniAPI());
    }


    public SharedPreferenceHelper getSharedPreferenceHelper() {
        return new SharedPreferenceHelper(preferences, editor);

    }

    public LoginController getLoginController() {
        return new LoginController(
                getScreensNavigator(),
                getAttemptLoginUseCase(),
                getSharedPreferenceHelper(),
                getDialogsManager(),
                getViewMVCFactory(),
                getLoginEventBus()
        );
    }

    public DialogsManager getDialogsManager() {
        return new DialogsManager(getContext(), getFragmentManager());
    }

    public DialogsEventBus getDialogsEventBus() {
        return compositionRoot.getDialogsEventBus();
    }

    public LoginEventBus getLoginEventBus() {
        return compositionRoot.getLoginEventBus();
    }

    public SupportHomeController getSupportHomeController() {
        return new SupportHomeController(
                getScreensNavigator()
        );
    }

    public KBListController getKBListController() {
        return new KBListController(
                getFetchKBListUseCase(),
                getScreensNavigator(),
                getDialogsManager(),
                getDialogsEventBus()
        );
    }


    public KBDetailController getKBDetailController() {
        return new KBDetailController(
                getFetchKBDetailsUseCase(),
                getFetchKBRatingUseCaseUseCase(),
                getFetchKBRatingPercentageUseCase(),
                getFetchKBReviewListUseCase(),
                getKBDislikeArticleUseCase(),
                getKBLikeArticleUseCasee(),
                getSaveKBReviewUseCase(),
                getScreensNavigator(),
                getDialogsManager(),
                getDialogsEventBus()
        );
    }

    public TicketListController getTicketListController() {
        return new TicketListController(
                getFetchTicketListUseCase(),
                getScreensNavigator(),
                getDialogsManager(),
                getDialogsEventBus()
        );
    }

 }
