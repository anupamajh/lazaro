package com.carmel.guestjini.web.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String LoginPage(){ return "home/LoginPage";}

    @RequestMapping(value = "/ForgotPassword")
    public String ForgotPassword(){ return "home/ForgotPassword";}

    @RequestMapping(value = "/ForgotPswdSuccess")
    public String ForgotPswdSuccess(){ return "home/ForgotPswdSuccess";}

    @RequestMapping(value = "/ForgotPasswordError")
    public String ForgotPasswordError(){ return "home/ForgotPasswordError";}

    @RequestMapping(value = "/OTPforgotpassword")
    public String OTPforgotpassword(){ return "home/OTPforgotpassword";}

    @RequestMapping(value = "/AccountLanding")
    public String AccountLanding(){ return "home/AccountLanding";}

    @RequestMapping(value = "/OTPsuccess")
    public String OTPsuccess(){ return "home/OTPsuccess";}

    @RequestMapping(value = "/AppSuccess")
    public String AppSuccess(){ return "home/AppSuccess";}

    @RequestMapping(value = "/AccountActivation")
    public String AccountActivate(){ return "home/AccountActivation";}

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

    @RequestMapping(value = "community/people-search")
    public String peopleSearch(){ return "community/people-search";}

    @RequestMapping(value = "community/people-details")
    public String peopleDetails(){ return "community/people-details";}

    @RequestMapping(value = "community/people-no-details")
    public String peopleNoDetails(){ return "community/people-no-details";}

    @RequestMapping(value = "community/people-filter")
    public String peopleFilter(){ return "community/people-filter";}

    @RequestMapping(value = "InterestGroups/groups")
    public String interestGroups(){ return "InterestGroups/groups";}

    @RequestMapping(value = "InterestGroups/group-conversation")
    public String groupConversation(){ return "InterestGroups/group-conversation";}

    @RequestMapping(value = "InterestGroups/group-search")
    public String groupSearch(){ return "InterestGroups/group-search";}

    @RequestMapping(value = "InterestGroups/interest-group-view")
    public String groupView(){ return "InterestGroups/interest-group-view";}

    @RequestMapping(value = "InterestGroups/group-hiking")
    public String groupHiking(){ return "InterestGroups/group-hiking";}

    @RequestMapping(value = "my-groups/my-groups")
    public String myGroups(){ return "my-groups/my-groups";}

}

