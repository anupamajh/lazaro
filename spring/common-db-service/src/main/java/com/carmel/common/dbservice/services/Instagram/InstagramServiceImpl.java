package com.carmel.common.dbservice.services.Instagram;


import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.config.InstagramConfig;
import com.carmel.common.dbservice.config.YAMLConfig;
import com.carmel.common.dbservice.model.DTO.Instagram.InstagramColumnConfig;
import com.carmel.common.dbservice.model.Instagram.Instagram;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.repository.Instagram.InstagramRepository;
import com.carmel.common.dbservice.response.Instagram.InstagramResponse;
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
public class InstagramServiceImpl implements InstagramService {

    Logger logger = LoggerFactory.getLogger(LinkedInServiceImpl.class);

    @Autowired
    YAMLConfig yamlConfig;

    @Autowired
    UserInformation userInformation;

    @Autowired
    InstagramRepository instagramRepository;

    @Override
    public InstagramResponse importData(String filepath) {
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfo userInfo = userInformation.getUserInfo();
        try {
            InstagramConfig instagramConfig = new InstagramConfig(yamlConfig);
            InstagramColumnConfig instagramColumnConfig = new InstagramColumnConfig();
            FileInputStream file = new FileInputStream(new File(filepath));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet worksheet = workbook.getSheetAt(0);
            if (worksheet.getPhysicalNumberOfRows() > 0) {
                Iterator<Cell> cellIterator = worksheet.getRow(0).cellIterator();//REM: Row 0 is always header row
                int colNumber = 1;
                while (cellIterator.hasNext()) {
                    Cell headerCell = cellIterator.next();
                    String headerName = headerCell.getStringCellValue().trim();
                    if (instagramConfig.getInstagramId().contains(headerName)) {
                        instagramColumnConfig.setInstagramId(colNumber);
                    } else if (instagramConfig.getUserName().contains(headerName)) {
                        instagramColumnConfig.setUserName(colNumber);
                    } else if (instagramConfig.getFullName().contains(headerName)) {
                        instagramColumnConfig.setFullName(colNumber);
                    } else if (instagramConfig.getUserProfile().contains(headerName)) {
                        instagramColumnConfig.setUserProfile(colNumber);
                    } else if (instagramConfig.getIsFollowedByUser().contains(headerName)) {
                        instagramColumnConfig.setIsFollowedByUser(colNumber);
                    } else if (instagramConfig.getIsRequestedByUser().contains(headerName)) {
                        instagramColumnConfig.setIsRequestedByUser(colNumber);
                    } else if (instagramConfig.getIsUserFollowedBy().contains(headerName)) {
                        instagramColumnConfig.setIsUserFollowedBy(colNumber);
                    } else if (instagramConfig.getProfilePicUrl().contains(headerName)) {
                        instagramColumnConfig.setProfilePicUrl(colNumber);
                    } else if (instagramConfig.getIsVerified().contains(headerName)) {
                        instagramColumnConfig.setIsVerified(colNumber);
                    } else if (instagramConfig.getProfilePicUrlHD().contains(headerName)) {
                        instagramColumnConfig.setProfilePicUrlHD(colNumber);
                    } else if (instagramConfig.getIsPrivate().contains(headerName)) {
                        instagramColumnConfig.setIsPrivate(colNumber);
                    } else if (instagramConfig.getFollowsCount().contains(headerName)) {
                        instagramColumnConfig.setFollowsCount(colNumber);
                    } else if (instagramConfig.getFollowedByCount().contains(headerName)) {
                        instagramColumnConfig.setFollowedByCount(colNumber);
                    } else if (instagramConfig.getFollowingTagCount().contains(headerName)) {
                        instagramColumnConfig.setFollowingTagCount(colNumber);
                    } else if (instagramConfig.getMediaCount().contains(headerName)) {
                        instagramColumnConfig.setMediaCount(colNumber);
                    } else if (instagramConfig.getBiography().contains(headerName)) {
                        instagramColumnConfig.setBiography(colNumber);
                    } else if (instagramConfig.getIsBusiness().contains(headerName)) {
                        instagramColumnConfig.setIsBusiness(colNumber);
                    } else if (instagramConfig.getUserTags().contains(headerName)) {
                        instagramColumnConfig.setUserTags(colNumber);
                    } else if (instagramConfig.getMutualFollowersCount().contains(headerName)) {
                        instagramColumnConfig.setMutualFollowersCount(colNumber);
                    }
                    colNumber++;
                }

                for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                    Row row = worksheet.getRow(i);
                    if (row.getCell(instagramColumnConfig.getInstagramId() - 1) != null) {
                        Instagram instagram = this.prepareInstagramData(
                                row,
                                instagramColumnConfig,
                                userInfo
                        );
                        if (instagram != null) {
                            instagramRepository.save(instagram);
                        }
                        logger.info("SAVED : " + objectMapper.writeValueAsString(instagram));
                    }
                }
            }


        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.toString(), ex);

        }
        return null;
    }

    private Instagram prepareInstagramData(
            Row row,
            InstagramColumnConfig instagramColumnConfig,
            UserInfo userInfo
    ) {
        try {
            Instagram instagram = new Instagram();
            String instagramId = String.valueOf(row
                    .getCell(instagramColumnConfig.getInstagramId() - 1)
                    .getNumericCellValue());

            Optional<Instagram> optionalInstagram = instagramRepository
                    .findByInstagramId(instagramId);
            if (optionalInstagram.isPresent()) {
                instagram = optionalInstagram.get();
            }
            if (instagram.getId() == null) {
                instagram.setId("");
            }
            if (instagram.getId().equals("")) {
                instagram.setCreatedBy(userInfo.getId());
                instagram.setCreationTime(new Date());
            } else {
                instagram.setLastModifiedBy(userInfo.getId());
                instagram.setLastModifiedTime(new Date());
            }
            instagram.setClientId(userInfo.getClient().getClientId());
            instagram.setInstagramId(instagramId);
            if (instagramColumnConfig.getUserName() != 0
                    && row.getCell(instagramColumnConfig.getUserName() - 1) != null
            ) {

                instagram.setUserName(
                        row
                                .getCell(instagramColumnConfig.getUserName() - 1)
                                .getStringCellValue().trim()
                );
            }
            if (instagramColumnConfig.getFullName() != 0
                    && row.getCell(instagramColumnConfig.getFullName() - 1) != null
            ) {

                instagram.setFullName(
                        row
                                .getCell(instagramColumnConfig.getFullName() - 1)
                                .getStringCellValue()
                );
            }
            if (instagramColumnConfig.getUserProfile() != 0
                    && row.getCell(instagramColumnConfig.getUserProfile() - 1) != null
            ) {

                instagram.setUserProfile(
                        row
                                .getCell(instagramColumnConfig.getUserProfile() - 1)
                                .getStringCellValue()
                );
            }
            if (instagramColumnConfig.getIsFollowedByUser() != 0
                    && row.getCell(instagramColumnConfig.getIsFollowedByUser() - 1) != null
            ) {


                instagram.setFollowedByUser(
                        row
                                .getCell(instagramColumnConfig.getIsFollowedByUser() - 1)
                                .getBooleanCellValue()
                );
            }
            if (instagramColumnConfig.getIsRequestedByUser() != 0
                    && row.getCell(instagramColumnConfig.getIsRequestedByUser() - 1) != null
            ) {


                instagram.setRequestedByUser(
                        row
                                .getCell(instagramColumnConfig.getIsRequestedByUser() - 1)
                                .getBooleanCellValue()
                );
            }
            if (instagramColumnConfig.getIsUserFollowedBy() != 0
                    && row.getCell(instagramColumnConfig.getIsUserFollowedBy() - 1) != null
            ) {

                instagram.setUserFollowedBy(
                        row
                                .getCell(instagramColumnConfig.getIsUserFollowedBy() - 1)
                                .getBooleanCellValue()
                );
            }
            if (instagramColumnConfig.getProfilePicUrl() != 0
                    && row.getCell(instagramColumnConfig.getProfilePicUrl() - 1) != null
            ) {


                instagram.setProfilePicUrl(
                        row
                                .getCell(instagramColumnConfig.getProfilePicUrl() - 1)
                                .getStringCellValue()
                );
            }
            if (instagramColumnConfig.getIsVerified() != 0
                    && row.getCell(instagramColumnConfig.getIsVerified() - 1) != null
            ) {


                instagram.setVerified(
                        row
                                .getCell(instagramColumnConfig.getIsVerified() - 1)
                                .getBooleanCellValue()
                );
            }
            if (instagramColumnConfig.getProfilePicUrlHD() != 0
                    && row.getCell(instagramColumnConfig.getProfilePicUrlHD() - 1) != null
            ) {


                instagram.setProfilePicUrlHD(
                        row
                                .getCell(instagramColumnConfig.getProfilePicUrlHD() - 1)
                                .getStringCellValue()
                );
            }
            if (instagramColumnConfig.getIsPrivate() != 0
                    && row.getCell(instagramColumnConfig.getIsPrivate() - 1) != null
            ) {

                instagram.setPrivate(
                        row
                                .getCell(instagramColumnConfig.getIsPrivate() - 1)
                                .getBooleanCellValue()
                );
            }
            if (instagramColumnConfig.getFollowsCount() != 0
                    && row.getCell(instagramColumnConfig.getFollowsCount() - 1) != null
            ) {
                instagram.setFollowsCount(
                        (int) row
                                .getCell(instagramColumnConfig.getFollowsCount() - 1)
                                .getNumericCellValue()
                );
            }
            if (instagramColumnConfig.getFollowedByCount() != 0
                    && row.getCell(instagramColumnConfig.getFollowedByCount() - 1) != null
            ) {

                instagram.setFollowedByCount(
                        (int) row
                                .getCell(instagramColumnConfig.getFollowedByCount() - 1)
                                .getNumericCellValue()
                );
            }

            if (instagramColumnConfig.getFollowingTagCount() != 0
                    && row.getCell(instagramColumnConfig.getFollowingTagCount() - 1) != null
            ) {


                instagram.setFollowingTagCount(
                        (int) row
                                .getCell(instagramColumnConfig.getFollowingTagCount() - 1)
                                .getNumericCellValue()
                );
            }
            if (instagramColumnConfig.getMediaCount() != 0
                    && row.getCell(instagramColumnConfig.getMediaCount() - 1) != null
            ) {


                instagram.setMediaCount(
                        (int) row
                                .getCell(instagramColumnConfig.getMediaCount() - 1)
                                .getNumericCellValue()
                );
            }
            if (instagramColumnConfig.getBiography() != 0
                    && row.getCell(instagramColumnConfig.getBiography() - 1) != null
            ) {


                instagram.setBiography(
                        row
                                .getCell(instagramColumnConfig.getBiography() - 1)
                                .getStringCellValue()
                );
            }
            if (instagramColumnConfig.getIsBusiness() != 0
                    && row.getCell(instagramColumnConfig.getIsBusiness() - 1) != null
            ) {


                instagram.setBusiness(
                        row
                                .getCell(instagramColumnConfig.getIsBusiness() - 1)
                                .getBooleanCellValue()
                );
            }
            if (instagramColumnConfig.getUserTags() != 0
                    && row.getCell(instagramColumnConfig.getUserTags() - 1) != null
            ) {

                instagram.setUserTags(
                        (int) row
                                .getCell(instagramColumnConfig.getUserTags() - 1)
                                .getNumericCellValue()
                );
            }
            if (instagramColumnConfig.getMutualFollowersCount() != 0
                    && row.getCell(instagramColumnConfig.getMutualFollowersCount() - 1) != null
            ) {
                instagram.setMutualFollowersCount(
                        (int) row
                                .getCell(instagramColumnConfig.getMutualFollowersCount() - 1)
                                .getNumericCellValue()
                );
            }
            return instagram;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.toString(), ex);
        }
        //TODO:Write Log here
        return null;
    }
}
