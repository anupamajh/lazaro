package com.carmel.common.dbservice.services.LinkedIn;

import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.config.LinkedInConfig;
import com.carmel.common.dbservice.config.YAMLConfig;
import com.carmel.common.dbservice.model.DTO.LinkedIn.LinkedInColumnConfig;
import com.carmel.common.dbservice.model.LinkedIn.LinkedIn;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.repository.LinkedIn.LinkedInRepository;
import com.carmel.common.dbservice.response.LinkedIn.LinkedInResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
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
public class LinkedInServiceImpl implements LinkedInService {

    Logger logger = LoggerFactory.getLogger(LinkedInServiceImpl.class);

    @Autowired
    YAMLConfig yamlConfig;

    @Autowired
    UserInformation userInformation;

    @Autowired
    LinkedInRepository linkedInRepository;

    /**
     * @param path
     * @return
     * @Rem: Row number 0 is always header row
     */
    @Override
    public LinkedInResponse importData(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfo userInfo = userInformation.getUserInfo();
        try {
            LinkedInConfig linkedInConfig = new LinkedInConfig(yamlConfig);
            LinkedInColumnConfig linkedInColumnConfig = new LinkedInColumnConfig();
            FileInputStream file = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet worksheet = workbook.getSheetAt(0);
            if (worksheet.getPhysicalNumberOfRows() > 0) {
                Iterator<Cell> cellIterator = worksheet.getRow(0).cellIterator();//REM: Row 0 is always header row
                int colNumber = 1;
                while (cellIterator.hasNext()) {
                    Cell headerCell = cellIterator.next();
                    String headerName = headerCell.getStringCellValue().trim();
                    if (linkedInConfig.getLinkedInId().contains(headerName)) {
                        linkedInColumnConfig.setLinkedInId(colNumber);
                    } else if (linkedInConfig.getFullName().contains(headerName)) {
                        linkedInColumnConfig.setFullName(colNumber);
                    } else if (linkedInConfig.getEmail().contains(headerName)) {
                        linkedInColumnConfig.setEmail(colNumber);
                    } else if (linkedInConfig.getProfileURL().contains(headerName)) {
                        linkedInColumnConfig.setProfileURL(colNumber);
                    } else if (linkedInConfig.getFirstName().contains(headerName)) {
                        linkedInColumnConfig.setFirstName(colNumber);
                    } else if (linkedInConfig.getLastName().contains(headerName)) {
                        linkedInColumnConfig.setLastName(colNumber);
                    } else if (linkedInConfig.getTitle().contains(headerName)) {
                        linkedInColumnConfig.setTitle(colNumber);
                    } else if (linkedInConfig.getAvatar().contains(headerName)) {
                        linkedInColumnConfig.setAvatar(colNumber);
                    } else if (linkedInConfig.getLocation().contains(headerName)) {
                        linkedInColumnConfig.setLocation(colNumber);
                    } else if (linkedInConfig.getAddress().contains(headerName)) {
                        linkedInColumnConfig.setAddress(colNumber);
                    } else if (linkedInConfig.getBirthday().contains(headerName)) {
                        linkedInColumnConfig.setBirthday(colNumber);
                    } else if (linkedInConfig.getSummary().contains(headerName)) {
                        linkedInColumnConfig.setSummary(colNumber);
                    }

                    colNumber++;
                }

                for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                    Row row = worksheet.getRow(i);
                    if (row.getCell(linkedInColumnConfig.getLinkedInId()) != null) {
                        if (!row.getCell(linkedInColumnConfig.getLinkedInId()).getStringCellValue().trim().equals("")) {
                            LinkedIn linkedIn = this.prepareLinkedInData(
                                    row,
                                    linkedInColumnConfig,
                                    userInfo
                            );
                            if (linkedIn != null) {
                                linkedInRepository.save(linkedIn);
                            }
                            logger.info("SAVED : " + objectMapper.writeValueAsString(linkedIn));
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

    private LinkedIn prepareLinkedInData(
            Row row,
            LinkedInColumnConfig linkedInColumnConfig,
            UserInfo userInfo
    ) {
        try {
            LinkedIn linkedIn = new LinkedIn();
            String linkedInId = row
                    .getCell(linkedInColumnConfig.getLinkedInId()-1)
                    .getStringCellValue().trim();

            Optional<LinkedIn> optionalLinkedIn = linkedInRepository
                    .findByLinkedInId(linkedInId);
            if (optionalLinkedIn.isPresent()) {
                linkedIn = optionalLinkedIn.get();
            }
            if (linkedIn.getId() == null) {
                linkedIn.setId("");
            }
            if (linkedIn.getId().equals("")) {
                linkedIn.setCreatedBy(userInfo.getId());
                linkedIn.setCreationTime(new Date());
            } else {
                linkedIn.setLastModifiedBy(userInfo.getId());
                linkedIn.setLastModifiedTime(new Date());
            }
            linkedIn.setClientId(userInfo.getClient().getClientId());
            if (linkedInColumnConfig.getLinkedInId() != 0
                    && row.getCell(linkedInColumnConfig.getLinkedInId() - 1) != null
            ) {

                linkedIn.setLinkedInId(
                        row
                                .getCell(linkedInColumnConfig.getLinkedInId() - 1)
                                .getStringCellValue().trim()
                );
            }
            if (linkedInColumnConfig.getFullName() != 0
                    && row.getCell(linkedInColumnConfig.getFullName() - 1) != null
            ) {

                linkedIn.setFullName(
                        row
                                .getCell(linkedInColumnConfig.getFullName() - 1)
                                .getStringCellValue()
                );
            }
            if (linkedInColumnConfig.getEmail() != 0
                    && row.getCell(linkedInColumnConfig.getEmail() - 1) != null
            ) {

                linkedIn.setEmail(
                        row
                                .getCell(linkedInColumnConfig.getEmail() - 1)
                                .getStringCellValue()
                );
            }
            if (linkedInColumnConfig.getProfileURL() != 0
                    && row.getCell(linkedInColumnConfig.getProfileURL() - 1) != null
            ) {


                linkedIn.setProfileURL(
                        row
                                .getCell(linkedInColumnConfig.getProfileURL() - 1)
                                .getStringCellValue()
                );
            }
            if (linkedInColumnConfig.getFirstName() != 0
                    && row.getCell(linkedInColumnConfig.getFirstName() - 1) != null
            ) {


                linkedIn.setFirstName(
                        row
                                .getCell(linkedInColumnConfig.getFirstName() - 1)
                                .getStringCellValue()
                );
            }
            if (linkedInColumnConfig.getLastName() != 0
                    && row.getCell(linkedInColumnConfig.getLastName() - 1) != null
            ) {


                linkedIn.setLastName(
                        row
                                .getCell(linkedInColumnConfig.getLastName() - 1)
                                .getStringCellValue()
                );
            }
            if (linkedInColumnConfig.getTitle() != 0
                    && row.getCell(linkedInColumnConfig.getTitle() - 1) != null
            ) {


                linkedIn.setTitle(
                        row
                                .getCell(linkedInColumnConfig.getTitle() - 1)
                                .getStringCellValue()
                );
            }
            if (linkedInColumnConfig.getAvatar() != 0
                    && row.getCell(linkedInColumnConfig.getAvatar() - 1) != null
            ) {


                linkedIn.setAvatar(
                        row
                                .getCell(linkedInColumnConfig.getAvatar() - 1)
                                .getStringCellValue()
                );
            }
            if (linkedInColumnConfig.getLocation() != 0
                    && row.getCell(linkedInColumnConfig.getLocation() - 1) != null
            ) {


                linkedIn.setLocation(
                        row
                                .getCell(linkedInColumnConfig.getLocation() - 1)
                                .getStringCellValue()
                );
            }
            if (linkedInColumnConfig.getAddress() != 0
                    && row.getCell(linkedInColumnConfig.getAddress() - 1) != null
            ) {

                linkedIn.setAddress(
                        row
                                .getCell(linkedInColumnConfig.getAddress() - 1)
                                .getStringCellValue()
                );
            }
            if (linkedInColumnConfig.getBirthday() != 0
                    && row.getCell(linkedInColumnConfig.getBirthday() - 1) != null
            ) {
                try {
                    linkedIn.setBirthday(
                            DateUtil.getJavaDate(
                                    row
                                            .getCell(linkedInColumnConfig.getBirthday() - 1)
                                            .getNumericCellValue()

                            )
                    );
                } catch (Exception birthDayException) {
                    logger.error(birthDayException.getMessage(), birthDayException);
                    logger.error(birthDayException.toString(), birthDayException);
                }
            }
            if (linkedInColumnConfig.getSummary() != 0
                    && row.getCell(linkedInColumnConfig.getSummary() - 1) != null
            ) {

                linkedIn.setSummary(
                        row
                                .getCell(linkedInColumnConfig.getSummary() - 1)
                                .getStringCellValue()
                );
            }

            if (linkedInColumnConfig.getFollowers() != 0
                    && row.getCell(linkedInColumnConfig.getFollowers() - 1) != null
            ) {

                linkedIn.setFollowers(
                        (int) row
                                .getCell(linkedInColumnConfig.getFollowers() - 1)
                                .getNumericCellValue()
                );
            }

            return linkedIn;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.toString(), ex);
        }
        //TODO:Write Log here
        return null;
    }
}
