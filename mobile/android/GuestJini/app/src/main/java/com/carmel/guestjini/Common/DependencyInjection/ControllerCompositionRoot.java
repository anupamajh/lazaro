package com.carmel.guestjini.Common.DependencyInjection;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.carmel.guestjini.Accounts.FetchMyRentInvoiceDetailsUseCase;
import com.carmel.guestjini.Accounts.FetchMyRentInvoiceListUseCase;
import com.carmel.guestjini.Authentication.AttemptClientLoginUseCase;
import com.carmel.guestjini.Authentication.AttemptLoginUseCase;
import com.carmel.guestjini.Common.Constants;
import com.carmel.guestjini.Community.AddPersonToFavouriteUseCase;
import com.carmel.guestjini.Community.FetchGroupByIdUseCase;
import com.carmel.guestjini.Community.FetchGroupConversationByGroupUseCase;
import com.carmel.guestjini.Community.FetchGroupListByTypeUseCase;
import com.carmel.guestjini.Community.FetchPeopleListUseCase;
import com.carmel.guestjini.Community.FetchPersonDetailUseCase;
import com.carmel.guestjini.Community.InviteToGroupUseCase;
import com.carmel.guestjini.Community.SaveGroupConversationUseCase;
import com.carmel.guestjini.Community.SaveGroupUseCase;
import com.carmel.guestjini.Community.SubscribeToGroupUseCase;
import com.carmel.guestjini.KnowledgeBase.FetchKBDetailsUseCase;
import com.carmel.guestjini.KnowledgeBase.FetchKBListUseCase;
import com.carmel.guestjini.KnowledgeBase.FetchKBRatingPercentageUseCase;
import com.carmel.guestjini.KnowledgeBase.FetchKBRatingUseCase;
import com.carmel.guestjini.KnowledgeBase.FetchKBReviewListUseCase;
import com.carmel.guestjini.KnowledgeBase.KBDislikeArticleUseCase;
import com.carmel.guestjini.KnowledgeBase.KBLikeArticleUseCase;
import com.carmel.guestjini.KnowledgeBase.SaveKBReviewUseCase;
import com.carmel.guestjini.Networking.GuestJiniAPI;
import com.carmel.guestjini.Screens.Accounts.AccountsHome.AccountsHomeController;
import com.carmel.guestjini.Screens.Accounts.Payments.PaymentsController;
import com.carmel.guestjini.Screens.Accounts.RentInvoiceDetails.RentInvoiceDetailController;
import com.carmel.guestjini.Screens.Accounts.RentInvoiceList.RentInvoiceListController;
import com.carmel.guestjini.Screens.AppAccessRequest.AppAccessRequestController;
import com.carmel.guestjini.Screens.Common.Controllers.ActivityResultDispatcher;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsEventBus;
import com.carmel.guestjini.Screens.Common.Dialogs.DialogsManager;
import com.carmel.guestjini.Screens.Common.FragmentHelper.FragmentFrameHelper;
import com.carmel.guestjini.Screens.Common.FragmentHelper.FragmentFrameWrapper;
import com.carmel.guestjini.Screens.Common.ScreensNavigator.ScreensNavigator;
import com.carmel.guestjini.Screens.Common.SharedPreference.SharedPreferenceHelper;
import com.carmel.guestjini.Screens.Common.ViewMVCFactory;
import com.carmel.guestjini.Screens.Community.CommunityHome.CommunityHomeController;
import com.carmel.guestjini.Screens.Community.CreateGroup.CreateGroupController;
import com.carmel.guestjini.Screens.Community.GroupConversation.GroupConversationController;
import com.carmel.guestjini.Screens.Community.GroupDetails.GroupDetailsController;
import com.carmel.guestjini.Screens.Community.GroupHome.GroupHomeController;
import com.carmel.guestjini.Screens.Community.GroupList.GroupListController;
import com.carmel.guestjini.Screens.Community.PeopleList.PeopleListController;
import com.carmel.guestjini.Screens.Community.PersonDetail.PersonDetailController;
import com.carmel.guestjini.Screens.ForgotPassword.ForgotPasswordController;
import com.carmel.guestjini.Screens.Login.LoginController;
import com.carmel.guestjini.Screens.Login.LoginEventBus;
import com.carmel.guestjini.Screens.Settings.ChangePassword.ChangePasswordController;
import com.carmel.guestjini.Screens.Settings.MyInterests.MyInterestController;
import com.carmel.guestjini.Screens.Settings.MyProfile.MyProfileController;
import com.carmel.guestjini.Screens.Settings.PrivacyPolicy.PrivacyPolicyController;
import com.carmel.guestjini.Screens.Settings.SettingsHome.SettingsHomeController;
import com.carmel.guestjini.Screens.Settings.TermsAndConditions.TermsAndConditionsController;
import com.carmel.guestjini.Screens.Support.CreateTicket.CreateTicketController;
import com.carmel.guestjini.Screens.Support.KBDetail.KBDetailController;
import com.carmel.guestjini.Screens.Support.KBList.KBListController;
import com.carmel.guestjini.Screens.Support.SupportHome.SupportHomeController;
import com.carmel.guestjini.Screens.Support.TicketCategory.TicketCategoryListController;
import com.carmel.guestjini.Screens.Support.TicketDetail.TicketDetailsController;
import com.carmel.guestjini.Screens.Support.TicketList.TicketListController;
import com.carmel.guestjini.Screens.Welcome.WelcomeController;
import com.carmel.guestjini.TicketCategory.FetchTicketCategoryByParentIdUseCase;
import com.carmel.guestjini.Tickets.DeleteTicketUseCase;
import com.carmel.guestjini.Tickets.FetchTicketCountUseCase;
import com.carmel.guestjini.Tickets.FetchTicketListUseCase;
import com.carmel.guestjini.Tickets.FetchTicketTaskNoteListUseCase;
import com.carmel.guestjini.Tickets.FetchTicketUseCase;
import com.carmel.guestjini.Tickets.SaveTaskNoteUseCase;
import com.carmel.guestjini.Tickets.SaveTicketUseCase;
import com.carmel.guestjini.Users.AppAccessRequestUseCase;
import com.carmel.guestjini.Users.ChangePasswordUseCase;
import com.carmel.guestjini.Users.CheckPhoneNumberUseCase;
import com.carmel.guestjini.Users.FetchInterestCategoryListUseCase;
import com.carmel.guestjini.Users.FetchInterestListUseCase;
import com.carmel.guestjini.Users.FetchMyInterestsUseCase;
import com.carmel.guestjini.Users.FetchMyProfilePicUseCase;
import com.carmel.guestjini.Users.FetchMyProfileUseCase;
import com.carmel.guestjini.Users.RequestOTPUseCase;
import com.carmel.guestjini.Users.ResetPasswordUseCase;
import com.carmel.guestjini.Users.SaveMyInterestUseCase;
import com.carmel.guestjini.Users.SaveProfilePicUseCase;
import com.carmel.guestjini.Users.SaveUserPreferenceUseCase;
import com.carmel.guestjini.Users.VerifyOTPUseCase;

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

    private GuestJiniAPI getClientAuthenticatedGuestJiniAPI() {
        return compositionRoot.getClientAuthenticatedGuestJiniAPI(
                Constants.CLIENT_ID,
                Constants.CLIENT_SECRETE,
                getActivity()
        );

    }

    private FragmentActivity getActivity() {
        return fragmentActivity;
    }

    private Context getContext() {
        return fragmentActivity;
    }

    public ActivityResultDispatcher getActivityResultDispatcher() {
        return (ActivityResultDispatcher) getActivity();
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

    public AttemptClientLoginUseCase getAttemptClientLoginUseCase() {
        return new AttemptClientLoginUseCase(getGuestJiniAPI());
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


    private SaveTicketUseCase getSaveTicketUseCase() {
        return new SaveTicketUseCase(getAuthenticatedGuestJiniAPI());
    }

    private FetchTicketUseCase getFetchTicketUseCase() {
        return new FetchTicketUseCase(getAuthenticatedGuestJiniAPI());
    }


    private FetchTicketTaskNoteListUseCase getFetchTicketTaskNoteListUseCase() {
        return new FetchTicketTaskNoteListUseCase(getAuthenticatedGuestJiniAPI());
    }


    private AppAccessRequestUseCase getAppAccessRequestUseCase() {
        return new AppAccessRequestUseCase(getAuthenticatedGuestJiniAPI());
    }


    private ChangePasswordUseCase getChangePasswordUseCase() {
        return new ChangePasswordUseCase(getAuthenticatedGuestJiniAPI());
    }


    private FetchMyProfilePicUseCase getFetchMyProfilePicUseCase() {
        return new FetchMyProfilePicUseCase(getAuthenticatedGuestJiniAPI());
    }


    private FetchMyProfileUseCase getFetchMyProfileUseCase() {
        return new FetchMyProfileUseCase(getAuthenticatedGuestJiniAPI());
    }


    private ResetPasswordUseCase getResetPasswordUseCase() {
        return new ResetPasswordUseCase(getAuthenticatedGuestJiniAPI());
    }


    private SaveProfilePicUseCase getSaveProfilePicUseCase() {
        return new SaveProfilePicUseCase(getAuthenticatedGuestJiniAPI());
    }


    private SaveTaskNoteUseCase getSaveTaskNoteUseCase() {
        return new SaveTaskNoteUseCase(getAuthenticatedGuestJiniAPI());
    }


    private SaveMyInterestUseCase getSaveMyInterestsUseCase() {
        return new SaveMyInterestUseCase(getAuthenticatedGuestJiniAPI());
    }

    private FetchMyInterestsUseCase getFetchMyInterestsUseCase() {
        return new FetchMyInterestsUseCase(getAuthenticatedGuestJiniAPI());
    }

    private FetchInterestListUseCase getFetchInterestListUseCase() {
        return new FetchInterestListUseCase(getAuthenticatedGuestJiniAPI());
    }

    private FetchInterestCategoryListUseCase getFetchInterestCategoryListUseCase() {
        return new FetchInterestCategoryListUseCase(getAuthenticatedGuestJiniAPI());
    }

    private SaveUserPreferenceUseCase getSaveUserPreferenceUseCase() {
        return new SaveUserPreferenceUseCase(getAuthenticatedGuestJiniAPI());
    }

    private FetchMyRentInvoiceListUseCase getFetchMyRentInvoiceUseCase() {
        return new FetchMyRentInvoiceListUseCase(getAuthenticatedGuestJiniAPI());
    }

    private FetchMyRentInvoiceDetailsUseCase getFetchMyRentInvoiceDetailsUseCase() {
        return new FetchMyRentInvoiceDetailsUseCase(getAuthenticatedGuestJiniAPI());
    }

    private AddPersonToFavouriteUseCase getAddPersonToFavouriteUseCase() {
        return new AddPersonToFavouriteUseCase(getAuthenticatedGuestJiniAPI());
    }

    private FetchGroupByIdUseCase getFetchGroupByIdUseCase() {
        return new FetchGroupByIdUseCase(getAuthenticatedGuestJiniAPI());
    }

    private FetchGroupConversationByGroupUseCase getFetchGroupConversationByGroupUseCase() {
        return new FetchGroupConversationByGroupUseCase(getAuthenticatedGuestJiniAPI());
    }

    private FetchGroupListByTypeUseCase getFetchGroupListByTypeUseCase() {
        return new FetchGroupListByTypeUseCase(getAuthenticatedGuestJiniAPI());
    }

    private FetchPeopleListUseCase getFetchPeopleListUseCase() {
        return new FetchPeopleListUseCase(getAuthenticatedGuestJiniAPI());
    }

    private FetchPersonDetailUseCase getFetchPersonDetailUseCase() {
        return new FetchPersonDetailUseCase(getAuthenticatedGuestJiniAPI());
    }

    private InviteToGroupUseCase getInviteToGroupUseCase() {
        return new InviteToGroupUseCase(getAuthenticatedGuestJiniAPI());
    }

    private SaveGroupUseCase getSaveGroupUseCase() {
        return new SaveGroupUseCase(getAuthenticatedGuestJiniAPI());
    }

    private SaveGroupConversationUseCase getSaveGroupConversationUseCase() {
        return new SaveGroupConversationUseCase(getAuthenticatedGuestJiniAPI());
    }

    private SubscribeToGroupUseCase getSubscribeToGroupUseCase() {
        return new SubscribeToGroupUseCase(getAuthenticatedGuestJiniAPI());
    }

    private FetchTicketCategoryByParentIdUseCase getFetchTicketCategoryByParentIdUseCase() {
        return new FetchTicketCategoryByParentIdUseCase(getAuthenticatedGuestJiniAPI());
    }

    private FetchTicketCountUseCase getFetchTicketCountUseCase() {
        return new FetchTicketCountUseCase(getAuthenticatedGuestJiniAPI());
    }

    private CheckPhoneNumberUseCase getCheckPhoneNumberUseCase() {
        return new CheckPhoneNumberUseCase(getClientAuthenticatedGuestJiniAPI());
    }

    private RequestOTPUseCase getRequestOTPUseCase() {
        return new RequestOTPUseCase(getAuthenticatedGuestJiniAPI());
    }

    private VerifyOTPUseCase getVerifyOTPUseCase() {
        return new VerifyOTPUseCase(getAuthenticatedGuestJiniAPI());
    }

    private DeleteTicketUseCase getDeleteTicketUseCase() {
        return new DeleteTicketUseCase(getAuthenticatedGuestJiniAPI());
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
                getScreensNavigator(),
                getFetchTicketCategoryByParentIdUseCase(),
                getFetchTicketCountUseCase(),
                getDialogsManager(),
                getDialogsEventBus()
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

    public CreateTicketController getCreateTicketController() {
        return new CreateTicketController(
                getScreensNavigator(),
                getSaveTicketUseCase(),
                getDeleteTicketUseCase(),
                getDialogsManager(),
                getDialogsEventBus()
        );
    }

    public TicketDetailsController getTicketDetailsController() {
        return new TicketDetailsController(
                getFetchTicketUseCase(),
                getFetchTicketTaskNoteListUseCase(),
                getSaveTaskNoteUseCase(),
                getScreensNavigator(),
                getDialogsManager(),
                getDialogsEventBus()
        );
    }


    public SettingsHomeController getSettingsHomeController() {
        return new SettingsHomeController(
                getScreensNavigator(),
                getSharedPreferenceHelper(),
                getLoginEventBus());
    }

    public MyInterestController getMyInterestController() {
        return new MyInterestController(
                getFetchInterestCategoryListUseCase(),
                getFetchInterestListUseCase(),
                getFetchMyInterestsUseCase(),
                getSaveMyInterestsUseCase(),
                getScreensNavigator(),
                getDialogsManager(),
                getDialogsEventBus()
        );
    }

    public MyProfileController getMyProfileController() {
        return new MyProfileController(
                getFetchMyProfileUseCase(),
                getFetchMyProfilePicUseCase(),
                getSaveProfilePicUseCase(),
                getSaveUserPreferenceUseCase(),
                getScreensNavigator(),
                getDialogsManager(),
                getDialogsEventBus());
    }

    public ChangePasswordController getChangePasswordController() {
        return new ChangePasswordController(
                getChangePasswordUseCase(),
                getScreensNavigator(),
                getDialogsManager(),
                getDialogsEventBus()
        );
    }

    public PrivacyPolicyController getPrivacyPolicyController() {
        return new PrivacyPolicyController(
                getScreensNavigator()
        );
    }

    public TermsAndConditionsController getTermsAndConditionsController() {
        return new TermsAndConditionsController(
                getScreensNavigator()
        );
    }

    public AccountsHomeController getAccountsHomeController() {
        return new AccountsHomeController(
                getScreensNavigator()
        );
    }

    public RentInvoiceListController getRentInvoiceListController() {
        return new RentInvoiceListController(
                getFetchMyRentInvoiceUseCase(),
                getScreensNavigator(),
                getDialogsManager(),
                getDialogsEventBus()
        );
    }

    public RentInvoiceDetailController getRentInvoiceDetailController() {
        return new RentInvoiceDetailController(getFetchMyRentInvoiceDetailsUseCase(),
                getScreensNavigator(),
                getDialogsManager(),
                getDialogsEventBus());
    }

    public PaymentsController getPaymentsController() {
        return new PaymentsController(getScreensNavigator());
    }

    public CommunityHomeController getCommunityHomeController() {
        return new CommunityHomeController(getScreensNavigator());
    }

    public PeopleListController getPeopleListController() {
        return new PeopleListController(
                getFetchPeopleListUseCase(),
                getAddPersonToFavouriteUseCase(),
                getScreensNavigator(),
                getDialogsManager(),
                getDialogsEventBus()
        );
    }

    public PersonDetailController getPersonDetailController() {
        return new PersonDetailController(
                getFetchPersonDetailUseCase(),
                getAddPersonToFavouriteUseCase(),
                getScreensNavigator(),
                getDialogsManager(),
                getDialogsEventBus()
        );
    }

    public GroupHomeController getGroupHomeController() {
        return new GroupHomeController(getScreensNavigator());
    }

    public GroupListController getGroupListController() {
        return new GroupListController(
                getFetchGroupListByTypeUseCase(),
                getScreensNavigator(),
                getDialogsManager(),
                getDialogsEventBus()
        );
    }

    public GroupDetailsController getGroupDetailsController() {
        return new GroupDetailsController(
                getFetchGroupByIdUseCase(),
                getInviteToGroupUseCase(),
                getScreensNavigator(),
                getDialogsManager(),
                getDialogsEventBus()
        );
    }

    public CreateGroupController getCreateGroupController() {
        return new CreateGroupController(
                getScreensNavigator(),
                getSaveGroupUseCase(),
                getDialogsManager(),
                getDialogsEventBus()
        );
    }

    public GroupConversationController getGroupConversationController() {
        return new GroupConversationController(
                getFetchGroupByIdUseCase(),
                getFetchGroupConversationByGroupUseCase(),
                getSaveGroupConversationUseCase(),
                getScreensNavigator(),
                getDialogsManager(),
                getDialogsEventBus()
        );
    }

    public ForgotPasswordController getForgotPasswordController() {
        return new ForgotPasswordController(
                getResetPasswordUseCase(),
                getScreensNavigator(),
                getDialogsManager()
        );
    }

    public AppAccessRequestController getAppAccessRequestController() {
        return new AppAccessRequestController(
                getAttemptClientLoginUseCase(),
                getRequestOTPUseCase(),
                getCheckPhoneNumberUseCase(),
                getSharedPreferenceHelper(),
                getScreensNavigator(),
                getDialogsManager()
        );
    }

    public TicketCategoryListController getTicketCategoryListController() {
        return new TicketCategoryListController(
                getFetchTicketCategoryByParentIdUseCase(),
                getScreensNavigator(),
                getDialogsManager(),
                getDialogsEventBus()
        );
    }
}
