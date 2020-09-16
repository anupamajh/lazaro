package com.carmel.common.dbservice.controller.LinkedIn;

import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.config.LinkedInConfig;
import com.carmel.common.dbservice.response.LinkedIn.LinkedInResponse;
import com.carmel.common.dbservice.services.LinkedIn.LinkedInService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;

@RestController
@RequestMapping(value = "/linked-in")
public class LinkedInController {

    Logger logger = LoggerFactory.getLogger(LinkedInController.class);

    @Autowired
    private Environment env;

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

    @RequestMapping(value = "/linked-in-import-data", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile uploadedfile) {
        try {
            String file = uploadedfile.getOriginalFilename();
            String saveFileName = String.valueOf(System.nanoTime()) + "_" + file;
            String directory = env.getProperty("carmel.fileUploadPath");
            String filepath = Paths.get(directory, saveFileName).toString();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(filepath)));
            stream.write(uploadedfile.getBytes());
            stream.close();
            this.importLinkedInData(filepath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void importLinkedInData(String filepath) throws Exception {
        LinkedInResponse linkedInResponse = linkedInService.importData(filepath);
    }


}
