package com.carmel.guestjini.Screens.Common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.carmel.guestjini.Screens.Accounts.AccountsHome.AccountsHomeViewMVC;
import com.carmel.guestjini.Screens.Accounts.AccountsHome.AccountsHomeViewMVCImpl;
import com.carmel.guestjini.Screens.Accounts.Payments.PaymentsViewMVC;
import com.carmel.guestjini.Screens.Accounts.Payments.PaymentsViewMVCImpl;
import com.carmel.guestjini.Screens.Accounts.RentInvoiceDetails.RentInvoiceDetailViewMVC;
import com.carmel.guestjini.Screens.Accounts.RentInvoiceDetails.RentInvoiceDetailViewMVCImpl;
import com.carmel.guestjini.Screens.Accounts.RentInvoiceList.RentInvoiceItem.RentInvoiceItemMVC;
import com.carmel.guestjini.Screens.Accounts.RentInvoiceList.RentInvoiceItem.RentInvoiceItemMVCImpl;
import com.carmel.guestjini.Screens.Accounts.RentInvoiceList.RentInvoiceListViewMVC;
import com.carmel.guestjini.Screens.Accounts.RentInvoiceList.RentInvoiceListViewMVCImpl;
import com.carmel.guestjini.Screens.AppAccessRequest.AppAccessRequestViewMVC;
import com.carmel.guestjini.Screens.AppAccessRequest.AppAccessRequestViewMVCImpl;
import com.carmel.guestjini.Screens.Common.BaseActivityView.BaseActivityMVCView;
import com.carmel.guestjini.Screens.Common.BaseActivityView.BaseActivityMVCViewImpl;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptViewMvc;
import com.carmel.guestjini.Screens.Common.Dialogs.PromptDialog.PromptViewMvcImpl;
import com.carmel.guestjini.Screens.Community.CommunityHome.CommunityHomeViewMVC;
import com.carmel.guestjini.Screens.Community.CommunityHome.CommunityHomeViewMVCImpl;
import com.carmel.guestjini.Screens.Community.CreateGroup.CreateGroupViewMVC;
import com.carmel.guestjini.Screens.Community.CreateGroup.CreateGroupViewMVCImpl;
import com.carmel.guestjini.Screens.Community.GroupConversation.GroupConversationItem.GroupConversationItemMineViewMVCImpl;
import com.carmel.guestjini.Screens.Community.GroupConversation.GroupConversationItem.GroupConversationItemOtherViewMVC;
import com.carmel.guestjini.Screens.Community.GroupConversation.GroupConversationItem.GroupConversationItemViewMVC;
import com.carmel.guestjini.Screens.Community.GroupConversation.GroupConversationViewMVC;
import com.carmel.guestjini.Screens.Community.GroupConversation.GroupConversationViewMVCImpl;
import com.carmel.guestjini.Screens.Community.GroupDetails.GroupDetailsViewMVC;
import com.carmel.guestjini.Screens.Community.GroupDetails.GroupDetailsViewMVCImpl;
import com.carmel.guestjini.Screens.Community.GroupDetails.GroupMember.GroupMemberViewMVC;
import com.carmel.guestjini.Screens.Community.GroupDetails.GroupMember.GroupMemberViewMVCImpl;
import com.carmel.guestjini.Screens.Community.GroupHome.GroupHomeViewMVC;
import com.carmel.guestjini.Screens.Community.GroupHome.GroupHomeViewMVCImpl;
import com.carmel.guestjini.Screens.Community.GroupList.GroupListItem.GroupListItemViewMVC;
import com.carmel.guestjini.Screens.Community.GroupList.GroupListItem.GroupListItemViewMVCImpl;
import com.carmel.guestjini.Screens.Community.GroupList.GroupListItem.GroupListMatchingItemViewMVCImpl;
import com.carmel.guestjini.Screens.Community.GroupList.GroupListItem.GroupListUnSubScribedItemViewMVCImpl;
import com.carmel.guestjini.Screens.Community.GroupList.GroupListViewMVC;
import com.carmel.guestjini.Screens.Community.GroupList.GroupListViewMVCImpl;
import com.carmel.guestjini.Screens.Community.PeopleList.PeopleListItem.PeopleListItemViewMVC;
import com.carmel.guestjini.Screens.Community.PeopleList.PeopleListItem.PeopleListItemViewMVCImpl;
import com.carmel.guestjini.Screens.Community.PeopleList.PeopleListViewMVC;
import com.carmel.guestjini.Screens.Community.PeopleList.PeopleListViewMVCImpl;
import com.carmel.guestjini.Screens.Community.PersonDetail.PersonDetailViewMVC;
import com.carmel.guestjini.Screens.Community.PersonDetail.PersonDetailViewMVCImpl;
import com.carmel.guestjini.Screens.ForgotPassword.ForgotPasswordViewMVC;
import com.carmel.guestjini.Screens.ForgotPassword.ForgotPasswordViewMVCImpl;
import com.carmel.guestjini.Screens.Home.HomeViewMVC;
import com.carmel.guestjini.Screens.Home.HomeViewMVCImpl;
import com.carmel.guestjini.Screens.Login.LoginViewMVC;
import com.carmel.guestjini.Screens.Login.LoginViewMVCImpl;
import com.carmel.guestjini.Screens.OTP.OTPViewMVC;
import com.carmel.guestjini.Screens.OTP.OTPViewMVCImpl;
import com.carmel.guestjini.Screens.SetPassword.SetPasswordMVC;
import com.carmel.guestjini.Screens.SetPassword.SetPasswordMVCImpl;
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
import com.carmel.guestjini.Screens.Support.AssignTicketSheet.AssignTicketSheetViewMVC;
import com.carmel.guestjini.Screens.Support.AssignTicketSheet.AssignTicketSheetViewMVCImpl;
import com.carmel.guestjini.Screens.Support.AssignTicketToAgentSheet.AssignTicketToAgentSheetViewMVC;
import com.carmel.guestjini.Screens.Support.AssignTicketToAgentSheet.AssignTicketToAgentToAgentSheetViewMVCImpl;
import com.carmel.guestjini.Screens.Support.CloseTicketSheet.CloseTicketSheetViewMVC;
import com.carmel.guestjini.Screens.Support.CloseTicketSheet.CloseTicketSheetViewMVCImpl;
import com.carmel.guestjini.Screens.Support.CreateTicket.CreateTicketViewMVC;
import com.carmel.guestjini.Screens.Support.CreateTicket.CreateTicketViewMVCImpl;
import com.carmel.guestjini.Screens.Support.Inbox.InboxViewMVC;
import com.carmel.guestjini.Screens.Support.Inbox.InboxViewMVCImpl;
import com.carmel.guestjini.Screens.Support.InboxList.InboxListViewMVC;
import com.carmel.guestjini.Screens.Support.InboxList.InboxListViewMVCImpl;
import com.carmel.guestjini.Screens.Support.InboxList.TicketListItem.InboxListItemViewMVC;
import com.carmel.guestjini.Screens.Support.InboxList.TicketListItem.InboxListItemViewMVCImpl;
import com.carmel.guestjini.Screens.Support.InboxTicketDetail.InboxTicketCategoryItem.InboxTicketCategoryItemViewMVC;
import com.carmel.guestjini.Screens.Support.InboxTicketDetail.InboxTicketCategoryItem.InboxTicketCategoryItemViewMVCImpl;
import com.carmel.guestjini.Screens.Support.InboxTicketDetail.InboxTicketComments.InboxTicketCommentViewMVC;
import com.carmel.guestjini.Screens.Support.InboxTicketDetail.InboxTicketComments.InboxTicketCommentViewMVCImpl;
import com.carmel.guestjini.Screens.Support.InboxTicketDetail.InboxTicketDetailViewMVC;
import com.carmel.guestjini.Screens.Support.InboxTicketDetail.InboxTicketDetailViewMVCImpl;
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
import com.carmel.guestjini.Screens.Support.TicketAttachment.AttachmentListItem.AttachmentListItemViewMVC;
import com.carmel.guestjini.Screens.Support.TicketAttachment.AttachmentListItem.AttachmentListItemViewMVCImpl;
import com.carmel.guestjini.Screens.Support.TicketAttachment.TicketAttachmentViewMVC;
import com.carmel.guestjini.Screens.Support.TicketAttachment.TicketAttachmentViewMVCImpl;
import com.carmel.guestjini.Screens.Support.TicketCategory.TicketCategoryItem.TicketCategoryItemViewMVC;
import com.carmel.guestjini.Screens.Support.TicketCategory.TicketCategoryItem.TicketCategoryItemViewMVCImpl;
import com.carmel.guestjini.Screens.Support.TicketCategory.TicketCategoryListViewMVC;
import com.carmel.guestjini.Screens.Support.TicketCategory.TicketCategoryListViewMVCImpl;
import com.carmel.guestjini.Screens.Support.TicketDetail.TaskTicketCategory.TaskTicketCategoryViewMVC;
import com.carmel.guestjini.Screens.Support.TicketDetail.TaskTicketCategory.TaskTicketCategoryViewMVCImpl;
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
        return new SupportHomeViewMVCImpl(layoutInflater, parent, this);
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
                parent,
                this
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
        return new MyInterestViewMVCImpl(layoutInflater, parent, this);
    }

    public MyProfileViewMVC getMyProfileViewMVC(@Nullable ViewGroup parent) {
        return new MyProfileViewMVCImpl(layoutInflater, parent);
    }

    public ChangePasswordViewMVC getChangePasswordViewMVC(@Nullable ViewGroup parent) {
        return new ChangePasswordViewMVCImpl(layoutInflater, parent);
    }

    public PrivacyPolicyViewMVC getPrivacyPolicyViewMVC(@Nullable ViewGroup parent) {
        return new PrivacyPolicyViewMVCImpl(layoutInflater, parent);
    }

    public TermsAndConditionsViewMVC getTermsAndConditionsViewMVC(@Nullable ViewGroup parent) {
        return new TermsAndConditionsViewMVCImpl(layoutInflater, parent);
    }

    public AccountsHomeViewMVC getAccountsHomeViewMVC(@Nullable ViewGroup parent) {
        return new AccountsHomeViewMVCImpl(layoutInflater, parent);
    }

    public RentInvoiceItemMVC getRentInvoiceItemMVC(@Nullable ViewGroup parent) {
        return new RentInvoiceItemMVCImpl(
                layoutInflater,
                parent
        );
    }

    public RentInvoiceListViewMVC getRentInvoiceListViewMVC(@Nullable ViewGroup parent) {
        return new RentInvoiceListViewMVCImpl(
                layoutInflater,
                parent,
                this
        );
    }

    public RentInvoiceDetailViewMVC getRentInvoiceDetailViewMVC(@Nullable ViewGroup parent) {
        return new RentInvoiceDetailViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public PaymentsViewMVC getPaymentsViewMVC(@Nullable ViewGroup parent) {
        return new PaymentsViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public CommunityHomeViewMVC getCommunityHomeViewMVC(@Nullable ViewGroup parent) {
        return new CommunityHomeViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public PeopleListItemViewMVC getPeopleListItemViewMVC(@Nullable ViewGroup parent) {
        return new PeopleListItemViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public PeopleListViewMVC getPeopleListViewMVC(@Nullable ViewGroup parent) {
        return new PeopleListViewMVCImpl(
                layoutInflater,
                parent,
                this
        );
    }

    public PersonDetailViewMVC getPersonDetailViewMVC(@Nullable ViewGroup parent) {
        return new PersonDetailViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public GroupHomeViewMVC getGroupHomeViewMVC(@Nullable ViewGroup parent) {
        return new GroupHomeViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public GroupListItemViewMVC getGroupListItemViewMVC(@Nullable ViewGroup parent) {
        return new GroupListItemViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public GroupListViewMVC getGroupListViewMVC(@Nullable ViewGroup parent) {
        return new GroupListViewMVCImpl(
                layoutInflater,
                parent,
                this
        );
    }

    public GroupMemberViewMVC getGroupMemberViewMVC(@Nullable ViewGroup parent) {
        return new GroupMemberViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public GroupDetailsViewMVC getGroupDetailsViewMVC(@Nullable ViewGroup parent) {
        return new GroupDetailsViewMVCImpl(
                layoutInflater,
                parent,
                this
        );
    }

    public GroupListItemViewMVC getGroupListMatchingItemViewMVC(@Nullable ViewGroup parent) {
        return new GroupListMatchingItemViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public GroupListItemViewMVC getGroupListUnSubScribedItemViewMVCImpl(@Nullable ViewGroup parent) {
        return new GroupListUnSubScribedItemViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public CreateGroupViewMVC getCreateGroupViewMVC(@Nullable ViewGroup parent) {
        return new CreateGroupViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public GroupConversationItemViewMVC getGroupConversationItemMineViewMVC(@Nullable ViewGroup parent) {
        return new GroupConversationItemMineViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public GroupConversationItemViewMVC getGroupConversationItemOtherViewMVC(@Nullable ViewGroup parent) {
        return new GroupConversationItemOtherViewMVC(
                layoutInflater,
                parent
        );
    }

    public GroupConversationViewMVC getGroupConversationViewMVC(@Nullable ViewGroup parent) {
        return new GroupConversationViewMVCImpl(
                layoutInflater,
                parent,
                this
        );
    }

    public ForgotPasswordViewMVC getForgotPasswordViewMVC(@Nullable ViewGroup parent) {
        return new ForgotPasswordViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public AppAccessRequestViewMVC getAppAccessRequestViewMVC(@Nullable ViewGroup parent) {
        return new AppAccessRequestViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public TicketCategoryItemViewMVC getTicketCategoryItemViewMVC(@Nullable ViewGroup parent) {
        return new TicketCategoryItemViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public TicketCategoryListViewMVC getTicketCategoryListViewMVC(@Nullable ViewGroup parent) {
        return new TicketCategoryListViewMVCImpl(
                layoutInflater,
                parent,
                this
        );
    }

    public OTPViewMVC getOTPViewMVC(@Nullable ViewGroup parent) {
        return new OTPViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public SetPasswordMVC getSetPasswordMVC(@Nullable ViewGroup parent) {
        return new SetPasswordMVCImpl(
                layoutInflater,
                parent
        );
    }

    public TaskTicketCategoryViewMVC getTaskTicketCategoryViewMVC(@Nullable ViewGroup parent) {
        return new TaskTicketCategoryViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public AttachmentListItemViewMVC getAttachmentListItemViewMVC(@Nullable ViewGroup parent) {
        return new AttachmentListItemViewMVCImpl(
                layoutInflater,
                parent
        );
    }

    public TicketAttachmentViewMVC getTicketAttachmentViewMVC(@Nullable ViewGroup parent) {
        return new TicketAttachmentViewMVCImpl(
                layoutInflater,
                parent,
                this
        );
    }

    public HomeViewMVC getHomeViewMVC(@Nullable ViewGroup parent) {
        return new HomeViewMVCImpl(layoutInflater, parent);
    }

    public InboxViewMVC getInboxViewMVC(@Nullable ViewGroup parent) {
        return new InboxViewMVCImpl(layoutInflater, parent);
    }

    public InboxListViewMVC getInboxListViewMVC(@Nullable ViewGroup parent) {
        return new InboxListViewMVCImpl(layoutInflater, parent, this);
    }

    public InboxListItemViewMVC getInboxListItemViewMVC(@Nullable ViewGroup parent) {
        return new InboxListItemViewMVCImpl(layoutInflater, parent);
    }

    public InboxTicketCategoryItemViewMVC getInboxTicketCategoryItemViewMVC(@Nullable ViewGroup parent) {
        return new InboxTicketCategoryItemViewMVCImpl(layoutInflater, parent);
    }

    public InboxTicketCommentViewMVC getInboxTicketCommentViewMVC(@Nullable ViewGroup parent) {
        return new InboxTicketCommentViewMVCImpl(layoutInflater, parent);
    }

    public InboxTicketDetailViewMVC getInboxTicketDetailViewMVC(@Nullable ViewGroup parent) {
        return new InboxTicketDetailViewMVCImpl(layoutInflater, parent, this);
    }

    public AssignTicketSheetViewMVC getAssignTicketSheetViewMVC(@Nullable ViewGroup parent) {
        return new AssignTicketSheetViewMVCImpl(layoutInflater, parent);
    }

    public AssignTicketToAgentSheetViewMVC getAssignTicketToAgentSheetViewMVC(@Nullable ViewGroup parent) {
        return new AssignTicketToAgentToAgentSheetViewMVCImpl(layoutInflater, parent);
    }

    public CloseTicketSheetViewMVC getCloseTicketSheetViewMVC(@Nullable ViewGroup parent) {
        return new CloseTicketSheetViewMVCImpl(layoutInflater, parent);
    }
}
