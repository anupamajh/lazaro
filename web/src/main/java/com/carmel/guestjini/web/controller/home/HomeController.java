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

    @RequestMapping(value = "/LoginMandatory")
    public String LoginMandatory(){ return "home/LoginMandatory";}

    @RequestMapping(value = "/LoginValidation")
    public String LoginValidation(){ return "home/LoginValidation";}

    @RequestMapping(value = "/ForgotPasswordError")
    public String ForgotPasswordError(){ return "home/ForgotPasswordError";}

    @RequestMapping(value = "/PasswordUnmask")
    public String PasswordUnmask(){ return "home/PasswordUnmask";}

    @RequestMapping(value = "/PasswordValidation")
    public String PasswordValidation(){ return "home/PasswordValidation";}

    @RequestMapping(value = "/NumberValidation")
    public String NumberValidation(){ return "home/NumberValidation";}

    @RequestMapping(value = "/OTPVerification")
    public String OTPVerification(){ return "home/OTPVerification";}

    @RequestMapping(value = "/OTPforgotpassword")
    public String OTPforgotpassword(){ return "home/OTPforgotpassword";}

    @RequestMapping(value = "/AppRequestError")
    public String AppRequestError(){ return "home/AppRequestError";}

    @RequestMapping(value = "/AppValidation")
    public String AppValidation(){ return "home/AppValidation";}

    @RequestMapping(value = "/AccountLanding")
    public String AccountLanding(){ return "home/AccountLanding";}

    @RequestMapping(value = "/OTPsuccess")
    public String OTPsuccess(){ return "home/OTPsuccess";}

    @RequestMapping(value = "/AppSuccess")
    public String AppSuccess(){ return "home/AppSuccess";}

    @RequestMapping(value = "community/community-profle")
    public String communityProfile(){ return "community/community-profile";}


}

