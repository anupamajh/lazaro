package com.carmel.guestjini.web.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String LoginPage(){ return "home/LoginPage";}

    @RequestMapping(value = "/ForgotPassword")
    public String ForgotPassword(){ return "home/ForgotPassword";}

    @RequestMapping(value = "/forgotpassword-otp")
    public String forgotpasswordotp(){ return "home/forgotpassword-otp";}

    @RequestMapping(value = "/AccountLanding")
    public String AccountLanding(){ return "home/AccountLanding";}

    @RequestMapping(value = "/AccountActivation")
    public String AccountActivate(){ return "home/AccountActivation";}


    @RequestMapping(value = "/change-password/logout-landing")
    public String loginLanding(){ return "change-password/logout-landing";}

    @RequestMapping(value = "/change-password/private-policy")
    public String privatePolicy(){ return "change-password/private-policy";}

    @RequestMapping(value = "/change-password/terms-conditions")
    public String termsConditions(){ return "change-password/terms-conditions";}

    @RequestMapping(value = "/change-password/change-password")
    public String changePassword(){ return "change-password/change-password";}

    @RequestMapping(value = "/Account-heads/account-heads")
    public String accountHeads(){ return "Account-heads/account-heads";}

    @RequestMapping(value = "/Account-heads/new-account-head")
    public String newAccountHead(){ return "Account-heads/new-account-head";}


    @RequestMapping(value = "/Account-heads/accounts-head-view2")
    public String AccountHeadView(){ return "Account-heads/accounts-head-view2";}

    @RequestMapping(value = "/Account-heads/account-heads-list")
    public String AccountHeadList(){ return "Account-heads/account-heads-list";}

    @RequestMapping(value = "/roles-permissions/roles")
    public String roles(){ return "roles-permissions/roles";}

    @RequestMapping(value = "/roles-permissions/create-user")
    public String createUser(){ return "roles-permissions/create-user";}

    @RequestMapping(value = "/roles-permissions/role-view")
    public String roleView(){ return "roles-permissions/role-view";}

    @RequestMapping(value = "community/community-profile")
    public String communityProfile(){ return "community/community-profile";}

    @RequestMapping(value = "community/community-groups/community-groups")
    public String communityGroups(){ return "community/community-groups/community-groups";}

    @RequestMapping(value = "community/community-groups/community-group-view")
    public String communityGroupView(){ return "community/community-groups/community-group-view";}

    @RequestMapping(value = "community/community-groups/community-existing-group-view")
    public String communityGroupExistingView(){ return "community/community-groups/community-existing-group-view";}

    @RequestMapping(value = "community/community-groups/community-awaiting-group")
    public String communityGroupAwaitingView(){ return "community/community-groups/community-awaiting-group";}

    @RequestMapping(value = "community/community-groups/community-open-group")
    public String communityGroupOpenView(){ return "community/community-groups/community-open-group";}

    @RequestMapping(value = "community/community-groups/community-group-conversation")
    public String communityGroupConversation(){ return "community/community-groups/community-group-conversation";}


    @RequestMapping(value = "community/community-groups/people-list")
    public String peopleList(){ return "community/people-list";}

    @RequestMapping(value = "community/community-groups/people-search")
    public String peopleSearch(){ return "community/people-search";}

    @RequestMapping(value = "community/community-groups/people-details")
    public String peopleDetails(){ return "community/people-details";}

    @RequestMapping(value = "community/community-groups/people-no-details")
    public String peopleNoDetails(){ return "community/people-no-details";}

    @RequestMapping(value = "community/community-groups/people-filter")
    public String peopleFilter(){ return "community/people-filter";}


    @RequestMapping(value = "InterestGroups/interest-groups")
    public String interestGroups(){ return "InterestGroups/interest-groups";}

    @RequestMapping(value = "InterestGroups/interest-group-list")
    public String groupsList(){ return "InterestGroups/interest-group-list";}

    @RequestMapping(value = "InterestGroups/group-conversation")
    public String groupConversation(){ return "InterestGroups/group-conversation";}

    @RequestMapping(value = "InterestGroups/interest-group-view")
    public String groupView(){ return "InterestGroups/interest-group-view";}

    @RequestMapping(value = "InterestGroups/group-hiking")
    public String groupHiking(){ return "InterestGroups/group-hiking";}


    @RequestMapping(value = "my-groups/my-groups")
    public String myGroups(){ return "my-groups/my-groups";}

    @RequestMapping(value = "my-groups/invite-people")
    public String invitePeople(){ return "my-groups/invite-people";}

    @RequestMapping(value = "/my-groups/my-groups-list")
    public String myGroupList(){ return "/my-groups/my-groups-list";}

    @RequestMapping(value = "/my-groups/my-group-view1")
    public String myGroupView1(){ return "/my-groups/my-group-view1";}

    @RequestMapping(value = "/my-groups/my-group-view")
    public String myGroupView(){ return "/my-groups/my-group-view";}

    @RequestMapping(value = "/my-groups/my-group-conversation")
    public String myGroupConversation(){ return "/my-groups/my-group-conversation";}

    @RequestMapping(value = "/my-groups/my-group-requests")
    public String myGroupRequests(){ return "/my-groups/my-group-requests";}


}

