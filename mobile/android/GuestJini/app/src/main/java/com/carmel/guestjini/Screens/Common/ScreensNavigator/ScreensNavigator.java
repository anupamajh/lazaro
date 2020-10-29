package com.carmel.guestjini.Screens.Common.ScreensNavigator;

import com.carmel.guestjini.Screens.Accounts.AccountsHome.AccountsHomeFragment;
import com.carmel.guestjini.Screens.Accounts.Payments.PaymentsFragment;
import com.carmel.guestjini.Screens.Accounts.RentInvoiceDetails.RentInvoiceDetailFragment;
import com.carmel.guestjini.Screens.Accounts.RentInvoiceList.RentInvoiceListFragment;
import com.carmel.guestjini.Screens.AppAccessRequest.AppAccessRequestFragment;
import com.carmel.guestjini.Screens.Common.FragmentHelper.FragmentFrameHelper;
import com.carmel.guestjini.Screens.Community.CommunityHome.CommunityHomeFragment;
import com.carmel.guestjini.Screens.Community.CreateGroup.CreateGroupFragment;
import com.carmel.guestjini.Screens.Community.GroupConversation.GroupConversationFragment;
import com.carmel.guestjini.Screens.Community.GroupDetails.GroupDetailsFragment;
import com.carmel.guestjini.Screens.Community.GroupHome.GroupHomeFragment;
import com.carmel.guestjini.Screens.Community.GroupList.GroupListFragment;
import com.carmel.guestjini.Screens.Community.PeopleList.PeopleListFragment;
import com.carmel.guestjini.Screens.Community.PersonDetail.PersonDetailFragment;
import com.carmel.guestjini.Screens.ForgotPassword.ForgotPasswordFragment;
import com.carmel.guestjini.Screens.Login.LoginFragment;
import com.carmel.guestjini.Screens.OTP.OTPFragment;
import com.carmel.guestjini.Screens.SetPassword.SetPasswordFragment;
import com.carmel.guestjini.Screens.Settings.ChangePassword.ChangePasswordFragment;
import com.carmel.guestjini.Screens.Settings.MyInterests.MyInterestFragment;
import com.carmel.guestjini.Screens.Settings.MyProfile.MyProfileFragment;
import com.carmel.guestjini.Screens.Settings.PrivacyPolicy.PrivacyPolicyFragment;
import com.carmel.guestjini.Screens.Settings.SettingsHome.SettingsHomeFragment;
import com.carmel.guestjini.Screens.Settings.TermsAndConditions.TermsAndConditionsFragment;
import com.carmel.guestjini.Screens.Support.CreateTicket.CreateTicketFragment;
import com.carmel.guestjini.Screens.Support.KBDetail.KBDetailFragment;
import com.carmel.guestjini.Screens.Support.KBList.KBListFragment;
import com.carmel.guestjini.Screens.Support.SupportHome.SupportHomeFragment;
import com.carmel.guestjini.Screens.Support.TicketCategory.TicketCategoryListFragment;
import com.carmel.guestjini.Screens.Support.TicketDetail.TicketDetailsFragment;
import com.carmel.guestjini.Screens.Support.TicketList.TicketListFragment;
import com.carmel.guestjini.Screens.Welcome.WelcomeFragment;

public class ScreensNavigator {

    private FragmentFrameHelper mFragmentFrameHelper;

    public ScreensNavigator(FragmentFrameHelper fragmentFrameHelper) {
        mFragmentFrameHelper = fragmentFrameHelper;
    }

    public void toWelcomeScreen() {
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(WelcomeFragment.createFragment());
    }

    public void toLoginScreen() {
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(LoginFragment.createFragment());
    }


    public void toAppAccessRequestScreen() {
        mFragmentFrameHelper.replaceFragment(AppAccessRequestFragment.createFragment());
    }

    public void toForgotPasswordScreen() {
        mFragmentFrameHelper.replaceFragment(ForgotPasswordFragment.createFragment());
    }

    public void toSupportHome() {
        mFragmentFrameHelper.replaceFragmentAndClearBackstack(SupportHomeFragment.createFragment());
    }

    public void toCommunityHome() {
        mFragmentFrameHelper.replaceFragment(CommunityHomeFragment.createFragment());
    }

    public void toAccountsHome() {
        mFragmentFrameHelper.replaceFragment(AccountsHomeFragment.createFragment());
    }

    public void toRewardsHome() {
        //TODO: Implement this method
    }

    public void toSettingsHome() {
        mFragmentFrameHelper.replaceFragment(SettingsHomeFragment.createFragment());
    }

    public void toKBList(String searchText) {
        mFragmentFrameHelper.replaceFragment(KBListFragment.createFragment(searchText));
    }

    public void toKBList() {
        mFragmentFrameHelper.replaceFragment(KBListFragment.createFragment(""));
    }

    public void toCreateTicket(String ticketCategoryData) {
        mFragmentFrameHelper.replaceFragment(CreateTicketFragment.createFragment(ticketCategoryData));
    }

    public void toTicketList(int ticketStatus) {
        mFragmentFrameHelper.replaceFragment(TicketListFragment.createFragment(ticketStatus));
    }

    public void toTicketDetails(String ticketId) {
        mFragmentFrameHelper.replaceFragment(TicketDetailsFragment.createFragment(ticketId));
    }


    public void toKBDetails(String kbId) {
        mFragmentFrameHelper.replaceFragment(KBDetailFragment.createFragment(kbId));
    }

    public void navigateUp() {
        mFragmentFrameHelper.navigateUp();
    }


    public void toMyProfile() {
        mFragmentFrameHelper.replaceFragment(MyProfileFragment.createFragment());
    }

    public void toChangePassword() {
        mFragmentFrameHelper.replaceFragment(ChangePasswordFragment.createFragment());
    }

    public void toPrivacyPolicy() {
        mFragmentFrameHelper.replaceFragment(PrivacyPolicyFragment.createFragment());
    }

    public void toTermsAndConditions() {
        mFragmentFrameHelper.replaceFragment(TermsAndConditionsFragment.createFragment());
    }

    public void toNotifications() {
        //TODO: Implement this method
    }

    public void toMyInterests() {
        mFragmentFrameHelper.replaceFragment(MyInterestFragment.createFragment());
    }

    public void toRentInvoiceList() {
        mFragmentFrameHelper.replaceFragment(RentInvoiceListFragment.createFragment());
    }

    public void toRentInvoiceDetailsScreen(String accountTicketId) {
        mFragmentFrameHelper.replaceFragment(RentInvoiceDetailFragment.createFragment(accountTicketId));
    }

    public void toPaymentScreen
            (
                    String accountTicketId,
                    String guestId,
                    String email,
                    String transactionAmount
            ) {
        mFragmentFrameHelper.replaceFragment(PaymentsFragment.createFragment(
                accountTicketId,
                guestId,
                email,
                transactionAmount
        ));
    }

    public void toPeopleList() {
        mFragmentFrameHelper.replaceFragment(PeopleListFragment.createFragment());
    }

    public void toGroupHome() {
        mFragmentFrameHelper.replaceFragment(GroupHomeFragment.createFragment());
    }

    public void toPersonDetailsScreen(String userId) {
        mFragmentFrameHelper.replaceFragment(PersonDetailFragment.createFragment(userId));
    }

    public void toInterestGroupsScreen() {
        mFragmentFrameHelper.replaceFragment(GroupListFragment.createFragment(1));
    }

    public void toCommunityGroupsScreen() {
        mFragmentFrameHelper.replaceFragment(GroupListFragment.createFragment(2));
    }

    public void toMyGroupsScreen() {
        mFragmentFrameHelper.replaceFragment(GroupListFragment.createFragment(3));
    }

    public void toGroupDetailScreen(String groupId, Integer groupType) {
        mFragmentFrameHelper.replaceFragment(GroupDetailsFragment.createFragment(groupId, groupType));
    }

    public void toCreateGroupScreen() {
        mFragmentFrameHelper.replaceFragment(CreateGroupFragment.createFragment());
    }

    public void toGroupConversationScreen(String groupId, Integer groupType) {
        mFragmentFrameHelper.replaceFragment(GroupConversationFragment.createFragment(groupId, groupType));
    }

    public void toTicketCategoryList(String parentId, String ticketCategoryData) {
        mFragmentFrameHelper.replaceFragment(TicketCategoryListFragment.createFragment(parentId, ticketCategoryData));
    }

    public void toOTPScreen() {
        mFragmentFrameHelper.replaceFragment(OTPFragment.createFragment());
    }

    public void toSetPasswordScreen() {
        mFragmentFrameHelper.replaceFragment(SetPasswordFragment.createFragment());
    }
}
