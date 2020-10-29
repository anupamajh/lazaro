package com.carmel.common.authserver.controller;

import com.carmel.common.authserver.config.CarmelConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    CarmelConfig carmelConfig;


    @GetMapping(value = "/login")
    public String login(Model model, String error, String logout) {
         model.addAttribute("baseURL", carmelConfig.getGuestureRedirectURL());
        if (error != null) {
            model.addAttribute("showError", true);
            model.addAttribute("errorMsg", "Your username and password are invalid.");
        }
        else{
            model.addAttribute("showError", false);
            model.addAttribute("errorMsg", "");
        }
        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");
        return "login-screen";
    }

    
}
