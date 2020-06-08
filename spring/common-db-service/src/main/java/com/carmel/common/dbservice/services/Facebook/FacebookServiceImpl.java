package com.carmel.common.dbservice.services.Facebook;

import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.config.FacebookConfig;
import com.carmel.common.dbservice.config.YAMLConfig;
import com.carmel.common.dbservice.model.DTO.Facebook.FacebookColumnConfig;
import com.carmel.common.dbservice.model.Facebook.Facebook;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.repository.Facebook.FacebookRepository;
import com.carmel.common.dbservice.response.Facebook.FacebookResponse;
import com.carmel.common.dbservice.services.LinkedIn.LinkedInServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

@Service
public class FacebookServiceImpl implements FacebookService {


    Logger logger = LoggerFactory.getLogger(LinkedInServiceImpl.class);

    @Autowired
    YAMLConfig yamlConfig;

    @Autowired
    UserInformation userInformation;

    @Autowired
    FacebookRepository facebookRepository;

    @Override
    public FacebookResponse importData(String filepath) {
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfo userInfo = userInformation.getUserInfo();
        try {
            FacebookConfig facebookConfig = new FacebookConfig(yamlConfig);
            FacebookColumnConfig facebookColumnConfig = new FacebookColumnConfig();
            FileInputStream file = new FileInputStream(new File(filepath));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet worksheet = workbook.getSheetAt(0);
            if (worksheet.getPhysicalNumberOfRows() > 0) {
                Iterator<Cell> cellIterator = worksheet.getRow(0).cellIterator();//REM: Row 0 is always header row
                int colNumber = 1;
                while (cellIterator.hasNext()) {
                    Cell headerCell = cellIterator.next();
                    String headerName = headerCell.getStringCellValue().trim();
                    if (facebookConfig.getFbName().contains(headerName)) {
                        facebookColumnConfig.setFbName(colNumber);
                    } else if (facebookConfig.getTitleURL().contains(headerName)) {
                        facebookColumnConfig.setTitleURL(colNumber);
                    } else if (facebookConfig.getImage().contains(headerName)) {
                        facebookColumnConfig.setImage(colNumber);
                    } else if (facebookConfig.getCompanyName().contains(headerName)) {
                        facebookColumnConfig.setCompanyName(colNumber);
                    } else if (facebookConfig.getPacURL().contains(headerName)) {
                        facebookColumnConfig.setPacURL(colNumber);
                    } else if (facebookConfig.getDesignation().contains(headerName)) {
                        facebookColumnConfig.setDesignation(colNumber);
                    } else if (facebookConfig.getPacURL2().contains(headerName)) {
                        facebookColumnConfig.setPacURL2(colNumber);
                    } else if (facebookConfig.getCurrentWorking().contains(headerName)) {
                        facebookColumnConfig.setCurrentWorking(colNumber);
                    } else if (facebookConfig.getLiveAt().contains(headerName)) {
                        facebookColumnConfig.setLiveAt(colNumber);
                    } else if (facebookConfig.getEh52URL().contains(headerName)) {
                        facebookColumnConfig.setEh52URL(colNumber);
                    } else if (facebookConfig.getEh524().contains(headerName)) {
                        facebookColumnConfig.setEh524(colNumber);
                    } else if (facebookConfig.getFollowers().contains(headerName)) {
                        facebookColumnConfig.setFollowers(colNumber);
                    }

                    colNumber++;
                }

                for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                    Row row = worksheet.getRow(i);
                    if (row.getCell(facebookColumnConfig.getTitleURL() - 1) != null) {
                        if (!row.getCell(facebookColumnConfig.getTitleURL() - 1).getStringCellValue().trim().equals("")) {
                            Facebook facebook = this.prepareFacebookData(
                                    row,
                                    facebookColumnConfig,
                                    userInfo
                            );
                            if (facebook != null) {
                                facebookRepository.save(facebook);
                            }
                            logger.info("SAVED : " + objectMapper.writeValueAsString(facebook));
                        }
                    }
                }
            }


        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.toString(), ex);

        }
        return null;
    }

    private Facebook prepareFacebookData(
            Row row,
            FacebookColumnConfig facebookColumnConfig,
            UserInfo userInfo
    ) {
        try {
            Facebook facebook = new Facebook();
            String facebookTitle = row
                    .getCell(facebookColumnConfig.getTitleURL() - 1)
                    .getStringCellValue().trim();

            Optional<Facebook> optionalFacebook = facebookRepository
                    .findByTitleURL(facebookTitle);
            if (optionalFacebook.isPresent()) {
                facebook = optionalFacebook.get();
            }
            if (facebook.getId() == null) {
                facebook.setId("");
            }
            if (facebook.getId().equals("")) {
                facebook.setCreatedBy(userInfo.getId());
                facebook.setCreationTime(new Date());
            } else {
                facebook.setLastModifiedBy(userInfo.getId());
                facebook.setLastModifiedTime(new Date());
            }
            facebook.setClientId(userInfo.getClient().getClientId());
            if (facebookColumnConfig.getFbName() != 0
                    && row.getCell(facebookColumnConfig.getFbName() - 1) != null
            ) {

                facebook.setFbName(
                        row
                                .getCell(facebookColumnConfig.getFbName() - 1)
                                .getStringCellValue().trim()
                );
            }
            if (facebookColumnConfig.getTitleURL() != 0
                    && row.getCell(facebookColumnConfig.getTitleURL() - 1) != null
            ) {

                facebook.setTitleURL(
                        row
                                .getCell(facebookColumnConfig.getTitleURL() - 1)
                                .getStringCellValue()
                );
            }
            if (facebookColumnConfig.getImage() != 0
                    && row.getCell(facebookColumnConfig.getImage() - 1) != null
            ) {

                facebook.setImage(
                        row
                                .getCell(facebookColumnConfig.getImage() - 1)
                                .getStringCellValue()
                );
            }
            if (facebookColumnConfig.getCompanyName() != 0
                    && row.getCell(facebookColumnConfig.getCompanyName() - 1) != null
            ) {


                facebook.setCompanyName(
                        row
                                .getCell(facebookColumnConfig.getCompanyName() - 1)
                                .getStringCellValue()
                );
            }
            if (facebookColumnConfig.getPacURL() != 0
                    && row.getCell(facebookColumnConfig.getPacURL() - 1) != null
            ) {


                facebook.setPacURL(
                        row
                                .getCell(facebookColumnConfig.getPacURL() - 1)
                                .getStringCellValue()
                );
            }
            if (facebookColumnConfig.getDesignation() != 0
                    && row.getCell(facebookColumnConfig.getDesignation() - 1) != null
            ) {


                facebook.setDesignation(
                        row
                                .getCell(facebookColumnConfig.getDesignation() - 1)
                                .getStringCellValue()
                );
            }
            if (facebookColumnConfig.getPacURL2() != 0
                    && row.getCell(facebookColumnConfig.getPacURL2() - 1) != null
            ) {


                facebook.setPacURL2(
                        row
                                .getCell(facebookColumnConfig.getPacURL2() - 1)
                                .getStringCellValue()
                );
            }
            if (facebookColumnConfig.getCurrentWorking() != 0
                    && row.getCell(facebookColumnConfig.getCurrentWorking() - 1) != null
            ) {


                facebook.setCurrentWorking(
                        row
                                .getCell(facebookColumnConfig.getCurrentWorking() - 1)
                                .getStringCellValue()
                );
            }
            if (facebookColumnConfig.getLiveAt() != 0
                    && row.getCell(facebookColumnConfig.getLiveAt() - 1) != null
            ) {


                facebook.setLiveAt(
                        row
                                .getCell(facebookColumnConfig.getLiveAt() - 1)
                                .getStringCellValue()
                );
            }
            if (facebookColumnConfig.getEh52URL() != 0
                    && row.getCell(facebookColumnConfig.getEh52URL() - 1) != null
            ) {

                facebook.setEh52URL(
                        row
                                .getCell(facebookColumnConfig.getEh52URL() - 1)
                                .getStringCellValue()
                );
            }
            if (facebookColumnConfig.getEh524() != 0
                    && row.getCell(facebookColumnConfig.getEh524() - 1) != null
            ) {
                facebook.setEh524(
                        row
                                .getCell(facebookColumnConfig.getEh524() - 1)
                                .getStringCellValue()
                );
            }
            if (facebookColumnConfig.getFollowers() != 0
                    && row.getCell(facebookColumnConfig.getFollowers() - 1) != null
            ) {

                facebook.setFollowers(
                        row
                                .getCell(facebookColumnConfig.getFollowers() - 1)
                                .getStringCellValue()
                );
            }

            return facebook;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.toString(), ex);
        }
        //TODO:Write Log here
        return null;
    }
}
