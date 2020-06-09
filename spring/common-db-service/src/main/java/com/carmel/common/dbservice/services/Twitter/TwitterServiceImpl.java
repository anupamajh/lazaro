package com.carmel.common.dbservice.services.Twitter;

import com.carmel.common.dbservice.component.UserInformation;
import com.carmel.common.dbservice.config.TwitterConfig;
import com.carmel.common.dbservice.config.YAMLConfig;
import com.carmel.common.dbservice.model.DTO.Twitter.TwitterColumnConfig;
import com.carmel.common.dbservice.model.Twitter.Twitter;
import com.carmel.common.dbservice.model.UserInfo;
import com.carmel.common.dbservice.repository.Twitter.TwitterRepository;
import com.carmel.common.dbservice.response.Twitter.TwitterResponse;
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
public class TwitterServiceImpl implements TwitterService {


    Logger logger = LoggerFactory.getLogger(LinkedInServiceImpl.class);

    @Autowired
    YAMLConfig yamlConfig;

    @Autowired
    UserInformation userInformation;

    @Autowired
    TwitterRepository twitterRepository;

    @Override
    public TwitterResponse importData(String filepath) {
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfo userInfo = userInformation.getUserInfo();
        try {
            TwitterConfig twitterConfig = new TwitterConfig(yamlConfig);
            TwitterColumnConfig twitterColumnConfig = new TwitterColumnConfig();
            FileInputStream file = new FileInputStream(new File(filepath));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet worksheet = workbook.getSheetAt(0);
            if (worksheet.getPhysicalNumberOfRows() > 0) {
                Iterator<Cell> cellIterator = worksheet.getRow(0).cellIterator();//REM: Row 0 is always header row
                int colNumber = 1;
                while (cellIterator.hasNext()) {
                    Cell headerCell = cellIterator.next();
                    String headerName = headerCell.getStringCellValue().trim();
                    if (twitterConfig.getName().contains(headerName)) {
                        twitterColumnConfig.setName(colNumber);
                    } else if (twitterConfig.getScreenName().contains(headerName)) {
                        twitterColumnConfig.setScreenName(colNumber);
                    } else if (twitterConfig.getTwitterId().contains(headerName)) {
                        twitterColumnConfig.setTwitterId(colNumber);
                    } else if (twitterConfig.getLocation().contains(headerName)) {
                        twitterColumnConfig.setLocation(colNumber);
                    } else if (twitterConfig.getBiography().contains(headerName)) {
                        twitterColumnConfig.setBiography(colNumber);
                    } else if (twitterConfig.getTwitterCreated().contains(headerName)) {
                        twitterColumnConfig.setTwitterCreated(colNumber);
                    } else if (twitterConfig.getFollowers().contains(headerName)) {
                        twitterColumnConfig.setFollowers(colNumber);
                    } else if (twitterConfig.getFollowing().contains(headerName)) {
                        twitterColumnConfig.setFollowing(colNumber);
                    } else if (twitterConfig.getFavorites().contains(headerName)) {
                        twitterColumnConfig.setFavorites(colNumber);
                    } else if (twitterConfig.getWebsite().contains(headerName)) {
                        twitterColumnConfig.setWebsite(colNumber);
                    } else if (twitterConfig.getTimezone().contains(headerName)) {
                        twitterColumnConfig.setTimezone(colNumber);
                    } else if (twitterConfig.getGeoEnabled().contains(headerName)) {
                        twitterColumnConfig.setGeoEnabled(colNumber);
                    } else if (twitterConfig.getVerified().contains(headerName)) {
                        twitterColumnConfig.setVerified(colNumber);
                    } else if (twitterConfig.getLanguage().contains(headerName)) {
                        twitterColumnConfig.setLanguage(colNumber);
                    } else if (twitterConfig.getIsProtected().contains(headerName)) {
                        twitterColumnConfig.setIsProtected(colNumber);
                    }
                    colNumber++;
                }

                for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                    Row row = worksheet.getRow(i);
                    if (row.getCell(twitterColumnConfig.getTwitterId() - 1) != null) {
                        if (!row.getCell(twitterColumnConfig.getTwitterId() - 1).getStringCellValue().trim().equals("")) {
                            Twitter twitter = this.prepareTwitterData(
                                    row,
                                    twitterColumnConfig,
                                    userInfo
                            );
                            if (twitter != null) {
                                twitterRepository.save(twitter);
                            }
                            logger.info("SAVED : " + objectMapper.writeValueAsString(twitter));
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

    private Twitter prepareTwitterData(
            Row row,
            TwitterColumnConfig twitterColumnConfig,
            UserInfo userInfo
    ) {
        try {
            Twitter twitter = new Twitter();
            String twitterId = row
                    .getCell(twitterColumnConfig.getTwitterId() - 1)
                    .getStringCellValue().trim();

            Optional<Twitter> optionalTwitter = twitterRepository
                    .findByTwitterId(twitterId);
            if (optionalTwitter.isPresent()) {
                twitter = optionalTwitter.get();
            }
            if (twitter.getId() == null) {
                twitter.setId("");
            }
            if (twitter.getId().equals("")) {
                twitter.setCreatedBy(userInfo.getId());
                twitter.setCreationTime(new Date());
            } else {
                twitter.setLastModifiedBy(userInfo.getId());
                twitter.setLastModifiedTime(new Date());
            }
            twitter.setClientId(userInfo.getClient().getClientId());
            if (twitterColumnConfig.getName() != 0
                    && row.getCell(twitterColumnConfig.getName() - 1) != null
            ) {

                twitter.setName(
                        row
                                .getCell(twitterColumnConfig.getName() - 1)
                                .getStringCellValue().trim()
                );
            }
            if (twitterColumnConfig.getScreenName() != 0
                    && row.getCell(twitterColumnConfig.getScreenName() - 1) != null
            ) {

                twitter.setScreenName(
                        row
                                .getCell(twitterColumnConfig.getScreenName() - 1)
                                .getStringCellValue()
                );
            }
            if (twitterColumnConfig.getTwitterId() != 0
                    && row.getCell(twitterColumnConfig.getTwitterId() - 1) != null
            ) {

                twitter.setTwitterId(
                        row
                                .getCell(twitterColumnConfig.getTwitterId() - 1)
                                .getStringCellValue()
                );
            }
            if (twitterColumnConfig.getLocation() != 0
                    && row.getCell(twitterColumnConfig.getLocation() - 1) != null
            ) {


                twitter.setLocation(
                        row
                                .getCell(twitterColumnConfig.getLocation() - 1)
                                .getStringCellValue()
                );
            }
            if (twitterColumnConfig.getBiography() != 0
                    && row.getCell(twitterColumnConfig.getBiography() - 1) != null
            ) {

                twitter.setBiography(
                        row
                                .getCell(twitterColumnConfig.getBiography() - 1)
                                .getStringCellValue()
                );
            }
            if (twitterColumnConfig.getTwitterCreated() != 0
                    && row.getCell(twitterColumnConfig.getTwitterCreated() - 1) != null
            ) {


                twitter.setTwitterCreated(
                        row
                                .getCell(twitterColumnConfig.getTwitterCreated() - 1)
                                .getStringCellValue()
                );
            }
            if (twitterColumnConfig.getFollowers() != 0
                    && row.getCell(twitterColumnConfig.getFollowers() - 1) != null
            ) {
                twitter.setFollowers(
                        (int) row
                                .getCell(twitterColumnConfig.getFollowers() - 1)
                                .getNumericCellValue()
                );
            }
            if (twitterColumnConfig.getFollowing() != 0
                    && row.getCell(twitterColumnConfig.getFollowing() - 1) != null
            ) {
                twitter.setFollowing(
                        (int) row
                                .getCell(twitterColumnConfig.getFollowing() - 1)
                                .getNumericCellValue()
                );
            }
            if (twitterColumnConfig.getTweets() != 0
                    && row.getCell(twitterColumnConfig.getTweets() - 1) != null
            ) {
                twitter.setTweets(
                        (int) row
                                .getCell(twitterColumnConfig.getTweets() - 1)
                                .getNumericCellValue()
                );
            }
            if (twitterColumnConfig.getFavorites() != 0
                    && row.getCell(twitterColumnConfig.getFavorites() - 1) != null
            ) {
                twitter.setTweets(
                        (int) row
                                .getCell(twitterColumnConfig.getFavorites() - 1)
                                .getNumericCellValue()
                );
            }
            if (twitterColumnConfig.getWebsite() != 0
                    && row.getCell(twitterColumnConfig.getWebsite() - 1) != null
            ) {
                twitter.setWebsite(
                        row
                                .getCell(twitterColumnConfig.getWebsite() - 1)
                                .getStringCellValue()
                );
            }
            if (twitterColumnConfig.getTimezone() != 0
                    && row.getCell(twitterColumnConfig.getTimezone() - 1) != null
            ) {
                twitter.setTimezone(
                        row
                                .getCell(twitterColumnConfig.getTimezone() - 1)
                                .getStringCellValue()
                );
            }
            if (twitterColumnConfig.getGeoEnabled() != 0
                    && row.getCell(twitterColumnConfig.getGeoEnabled() - 1) != null
            ) {
                twitter.setGeoEnabled(
                        row
                                .getCell(twitterColumnConfig.getGeoEnabled() - 1)
                                .getStringCellValue()
                );
            }
            if (twitterColumnConfig.getVerified() != 0
                    && row.getCell(twitterColumnConfig.getVerified() - 1) != null
            ) {

                twitter.setVerified(
                        row
                                .getCell(twitterColumnConfig.getVerified() - 1)
                                .getStringCellValue()
                );
            }
            if (twitterColumnConfig.getLanguage() != 0
                    && row.getCell(twitterColumnConfig.getLanguage() - 1) != null
            ) {

                twitter.setLanguage(
                        row
                                .getCell(twitterColumnConfig.getLanguage() - 1)
                                .getStringCellValue()
                );
            }
            if (twitterColumnConfig.getIsProtected() != 0
                    && row.getCell(twitterColumnConfig.getIsProtected() - 1) != null
            ) {

                twitter.setIsProtected(
                        row
                                .getCell(twitterColumnConfig.getIsProtected() - 1)
                                .getStringCellValue()
                );
            }

            return twitter;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            logger.error(ex.toString(), ex);
        }
        //TODO:Write Log here
        return null;
    }
}
