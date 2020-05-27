package com.carmel.guestjini.Screens.Common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Screens.Common.BaseActivityView.BaseActivityMVCView;
import com.carmel.guestjini.Screens.Common.BaseActivityView.BaseActivityMVCViewImpl;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptViewMvc;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptViewMvcImpl;
import com.carmel.guestjini.Screens.Login.LoginViewMVC;
import com.carmel.guestjini.Screens.Login.LoginViewMVCImpl;
import com.carmel.guestjini.Screens.Settings.ChangePassword.ChangePasswordViewMVC;
import com.carmel.guestjini.Screens.Settings.ChangePassword.ChangePasswordViewMVCImpl;
import com.carmel.guestjini.Screens.Settings.MyInterests.InterestCategoryItem.InterestCategoryItemViewMVC;
import com.carmel.guestjini.Screens.Settings.MyInterests.InterestCategoryItem.InterestCategoryItemViewMVCImpl;
import com.carmel.guestjini.Screens.Settings.MyInterests.InterestItem.InterestItemViewMVC;
import com.carmel.guestjini.Screens.Settings.MyInterests.InterestItem.InterestItemViewMVCImpl;
import com.carmel.guestjini.Screens.Settings.MyInterests.MyInterestViewMVC;
import com.carmel.guestjini.Screens.Settings.MyInterests.MyInterestViewMVCImpl;
import com.carmel.guestjini.Screens.Settings.MyProfile.MyProfileViewMVC;
import com.carmel.guestjini.Screens.Settings.MyProfile.MyProfileViewMVCImpl;
import com.carmel.guestjini.Screens.Settings.PrivacyPolicy.PrivacyPolicyViewMVC;
import com.carmel.guestjini.Screens.Settings.PrivacyPolicy.PrivacyPolicyViewMVCImpl;
import com.carmel.guestjini.Screens.Settings.SettingsHome.SettingsHomeViewMVC;
import com.carmel.guestjini.Screens.Settings.SettingsHome.SettingsHomeViewMVCImpl;
import com.carmel.guestjini.Screens.Settings.TermsAndConditions.TermsAndConditionsViewMVC;
import com.carmel.guestjini.Screens.Settings.TermsAndConditions.TermsAndConditionsViewMVCImpl;
import com.carmel.guestjini.Screens.Support.CreateTicket.CreateTicketViewMVC;
import com.carmel.guestjini.Screens.Support.CreateTicket.CreateTicketViewMVCImpl;
import com.carmel.guestjini.Screens.Support.KBDetail.KBDetailViewMVC;
import com.carmel.guestjini.Screens.Support.KBDetail.KBDetailViewMVCImpl;
import com.carmel.guestjini.Screens.Support.KBDetail.KBReviewItem.KBReviewItemViewMVC;
import com.carmel.guestjini.Screens.Support.KBDetail.KBReviewItem.KBReviewItemViewMVCImpl;
import com.carmel.guestjini.Screens.Support.KBList.KBListItem.KBListItemViewMVC;
import com.carmel.guestjini.Screens.Support.KBList.KBListItem.KBListItemViewMVCImpl;
import com.carmel.guestjini.Screens.Support.KBList.KBListViewMVC;
import com.carmel.guestjini.Screens.Support.KBList.KBListViewMVCImpl;
import com.carmel.guestjini.Screens.Support.SupportHome.SupportHomeViewMVC;
import com.carmel.guestjini.Screens.Support.SupportHome.SupportHomeViewMVCImpl;
import com.carmel.guestjini.Screens.Support.TicketDetail.TicketComments.TicketCommentsViewMVC;
import com.carmel.guestjini.Screens.Support.TicketDetail.TicketComments.TicketCommentsViewMVCImpl;
import com.carmel.guestjini.Screens.Support.TicketDetail.TicketDetailsViewMVC;
import com.carmel.guestjini.Screens.Support.TicketDetail.TicketDetailsViewMVCImpl;
import com.carmel.guestjini.Screens.Support.TicketList.TicketListItem.TicketListItemViewMVC;
import com.carmel.guestjini.Screens.Support.TicketList.TicketListItem.TicketListItemViewMVCImpl;
import com.carmel.guestjini.Screens.Support.TicketList.TicketListViewMVC;
import com.carmel.guestjini.Screens.Support.TicketList.TicketListViewMVCImpl;
import com.carmel.guestjini.Screens.Welcome.WelcomeViewMVC;
import com.carmel.guestjini.Screens.Welcome.WelcomeViewMVCImpl;

public class ViewMVCFactory {
    private final LayoutInflater layoutInflater;

    public ViewMVCFactory(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public BaseActivityMVCView getBaseActivityView(@Nullable ViewGroup parent) {
        return new BaseActivityMVCViewImpl(layoutInflater, parent);
    }

    public WelcomeViewMVC getWelcomeViewMVC(@Nullable ViewGroup parent) {
        return new WelcomeViewMVCImpl(layoutInflater, parent);
    }

    public LoginViewMVC getLoginViewMVC(@Nullable ViewGroup parent) {
        return new LoginViewMVCImpl(layoutInflater, parent);
    }

    public PromptViewMvc getPromptViewMvc(@Nullable ViewGroup parent) {
        return new PromptViewMvcImpl(layoutInflater, parent);
    }

    public SupportHomeViewMVC getSupportHomeViewMVC(@Nullable ViewGroup parent) {
        return new SupportHomeViewMVCImpl(layoutInflater, parent);
    }

    public KBListItemViewMVC getKBListItemViewMVC(@Nullable ViewGroup parent) {
        return new KBListItemViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public KBListViewMVC getKBListMVC(@Nullable ViewGroup parent) {
        return new KBListViewMVCImpl(
                layoutInflater,
                parent,
                this
        );
    }

    public KBDetailViewMVC getKBDetailsViewMVC(@Nullable ViewGroup parent) {
        return new KBDetailViewMVCImpl(
                layoutInflater,
                parent,
                this
        );
    }

    public KBReviewItemViewMVC getKBReviewItemViewMVC(@Nullable ViewGroup parent) {
        return new KBReviewItemViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public TicketListItemViewMVC getTicketListItemViewMVC(@Nullable ViewGroup parent) {
        return new TicketListItemViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public TicketListViewMVC getTicketListViewMVC(@Nullable ViewGroup parent) {
        return new TicketListViewMVCImpl(
                layoutInflater,
                parent,
                this
        );
    }

    public CreateTicketViewMVC getCreateTicketViewMVC(@Nullable ViewGroup parent) {
        return new CreateTicketViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public TicketCommentsViewMVC getTicketCommentsViewMVC(@Nullable ViewGroup parent) {
        return new TicketCommentsViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public TicketDetailsViewMVC getTicketDetailsViewMVC(@Nullable ViewGroup parent) {
        return new TicketDetailsViewMVCImpl(
                layoutInflater,
                parent,
                this
        );
    }

    public SettingsHomeViewMVC getSettingsHomeViewMVC(@Nullable ViewGroup parent) {
        return new SettingsHomeViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public InterestItemViewMVC getInterestItemViewMVC(@Nullable ViewGroup parent) {
        return new InterestItemViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public InterestCategoryItemViewMVC getInterestCategoryItemViewMVC(ViewGroup parent) {
        return new InterestCategoryItemViewMVCImpl(
                layoutInflater,
                parent,
                this
        );
    }

    public MyInterestViewMVC getMyInterestViewMVC(@Nullable ViewGroup parent) {
        return new MyInterestViewMVCImpl(layoutInflater,parent, this);
    }

    public MyProfileViewMVC getMyProfileViewMVC(@Nullable ViewGroup parent) {
        return new MyProfileViewMVCImpl(layoutInflater,parent);
    }

    public ChangePasswordViewMVC getChangePasswordViewMVC(@Nullable ViewGroup parent) {
        return new ChangePasswordViewMVCImpl(layoutInflater,parent);
    }

    public PrivacyPolicyViewMVC getPrivacyPolicyViewMVC(@Nullable ViewGroup parent) {
        return new PrivacyPolicyViewMVCImpl(layoutInflater,parent);
    }

    public TermsAndConditionsViewMVC getTermsAndConditionsViewMVC(@Nullable ViewGroup parent) {
        return new TermsAndConditionsViewMVCImpl(layoutInflater,parent);
    }
}
