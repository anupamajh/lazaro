package com.carmel.common.dbservice.controller.LinkedIn;

import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.controller.AccountHeadController;
import com.carmel.common.dbservice.response.LinkedIn.LinkedInResponse;
import com.carmel.common.dbservice.services.LinkedIn.LinkedInService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/linked-in")
public class LinkedInController {

    Logger logger = LoggerFactory.getLogger(AccountHeadController.class);

    @Autowired
    UserInformation userInformation;

    @Autowired
    LinkedInService linkedInService;

    @RequestMapping(value = "/import-data")
    public LinkedInResponse importData() {
        LinkedInResponse linkedInResponse = new LinkedInResponse();
        String path = "/Users/pete/Desktop/WIP/Guest/LinkedIn/Linkedin_tech_Mahindra.xlsx";
        try {
            linkedInResponse = linkedInService.importData(path);
        } catch (Exception ex) {

        }
        return linkedInResponse;
    }


}
