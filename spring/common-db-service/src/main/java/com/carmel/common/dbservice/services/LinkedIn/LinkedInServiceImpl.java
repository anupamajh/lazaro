package com.carmel.common.dbservice.services.LinkedIn;

import com.carmel.common.dbservice.response.LinkedIn.LinkedInResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;

@Service
public class LinkedInServiceImpl implements LinkedInService {

    @Override
    public LinkedInResponse importData(String path) {
        try {
            FileInputStream file = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(file);

        } catch (Exception ex) {

        }
        return null;
    }
}
