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

    @RequestMapping(value = "community/community-groups")
    public String communityGroups(){ return "community/community-groups";}

    @RequestMapping(value = "community/community-group-view")
    public String communityGroupView(){ return "community/community-group-view";}

    @RequestMapping(value = "community/community-group-conversation")
    public String communityGroupConversation(){ return "community/community-group-conversation";}

    @RequestMapping(value = "community/people-list")
    public String peopleList(){ return "community/people-list";}

    @RequestMapping(value = "community/people-search")
    public String peopleSearch(){ return "community/people-search";}

    @RequestMapping(value = "community/people-details")
    public String peopleDetails(){ return "community/people-details";}

    @RequestMapping(value = "community/people-no-details")
    public String peopleNoDetails(){ return "community/people-no-details";}

    @RequestMapping(value = "community/people-filter")
    public String peopleFilter(){ return "community/people-filter";}



}

