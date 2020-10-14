package com.carmel.console.accessCard;

import com.carmel.console.accessCard.Model.AccessCard;
import com.carmel.console.accessCard.Utils.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

public class GenerateAccessCard extends Frame {

    private static int DETAIL_SEPERATOR_X = 70;
    private static int DETAIL_TITLE_X = 5;
    private static int DETAIL_START_X = 80;
    private static int DETAIL_LINE_INCREMENT_Y = 13;
    private java.util.List<AccessCard> accessCardList;
    private AccessCard currentAccessCard = null;

    private String imagePath = "";
    private String excelPath = "";

    private Graphics2D g2;

    private Properties properties;

    private Integer ID_NUMBER_COL = null;
    private Integer NAME_COL = null;
    private Integer IMAGE_URL_COL = null;
    private Integer BLOOD_GROUPS_COL = null;
    private Integer CONTACT_NUMBER_COL = null;
    private Integer EMERGENCY_NUMBER_COL = null;
    private Integer ADDRESS_COL = null;
    private Integer PROPERTY_NAME_COL = null;
    private Integer PROPERTY_ADDRESS_LINE_1_COL = null;
    private Integer PROPERTY_ADDRESS_LINE_2_COL = null;

    public GenerateAccessCard() {
        super("Guesture Access Card");
        properties = new Properties();
        try {
            InputStream inputStream = getClass().getResourceAsStream("/application.properties");
            if (inputStream != null) {
                properties.load(inputStream);
                this.excelPath = properties.getProperty("excelPath");
                this.imagePath = properties.getProperty("imagePath");
                accessCardList = new ArrayList<AccessCard>();
                FileInputStream excelFile = new FileInputStream(new File(excelPath));
                Workbook workbook = new XSSFWorkbook(excelFile);
                Sheet studentSheet = workbook.getSheetAt(0);
                Iterator<Row> iterator = studentSheet.iterator();
                AccessCard accessCard = new AccessCard();
                int rowCount = 0;
                int i = 0;
                while (iterator.hasNext()) {
                    Row currentRow = iterator.next();
                    Iterator<Cell> cellIterator = currentRow.iterator();
                    if (rowCount == 0) {
                        while (cellIterator.hasNext()) {
                            Cell currentCell = cellIterator.next();
                            if (currentCell.getStringCellValue() != null) {
                                switch (currentCell.getStringCellValue().toLowerCase().trim()) {
                                    case "idno":
                                        ID_NUMBER_COL = i;
                                        break;
                                    case "student name":
                                        NAME_COL = i;
                                        break;
                                    case "photo":
                                        IMAGE_URL_COL = i;
                                        break;
                                    case "blood group":
                                        BLOOD_GROUPS_COL = i;
                                        break;
                                    case "contact no":
                                        CONTACT_NUMBER_COL = i;
                                        break;
                                    case "emergency conatct no":
                                        EMERGENCY_NUMBER_COL = i;
                                        break;
                                    case "permanent residential address":
                                        ADDRESS_COL = i;
                                        break;
                                    case "property name":
                                        PROPERTY_NAME_COL = i;
                                        break;
                                    case "property address 1":
                                        PROPERTY_ADDRESS_LINE_1_COL = i;
                                        break;
                                    case "property address 2":
                                        PROPERTY_ADDRESS_LINE_2_COL = i;
                                        break;
                                }
                            }
                            i++;
                        }
                    }else{
                        accessCard = new AccessCard();
                        if(currentRow.getCell(NAME_COL)!=null) {
                            if (NAME_COL != null) {
                                if (currentRow.getCell(NAME_COL).getStringCellValue() != null) {
                                    accessCard.setName(currentRow.getCell(NAME_COL).getStringCellValue().trim());
                                }
                            }

                            if (ID_NUMBER_COL != null) {
                                if (currentRow.getCell(ID_NUMBER_COL).getStringCellValue() != null) {
                                    accessCard.setIdNumber(currentRow.getCell(ID_NUMBER_COL).getStringCellValue().trim());
                                }
                            }
                            if (IMAGE_URL_COL != null) {
                                if (currentRow.getCell(IMAGE_URL_COL).getStringCellValue() != null) {
                                    accessCard.setImageUrl(currentRow.getCell(IMAGE_URL_COL).getStringCellValue().trim());
                                }
                            }
                            if (BLOOD_GROUPS_COL != null) {
                                if (currentRow.getCell(BLOOD_GROUPS_COL).getStringCellValue() != null) {
                                    accessCard.setBloodGroup(currentRow.getCell(BLOOD_GROUPS_COL).getStringCellValue().trim());
                                }
                            }
                            if (CONTACT_NUMBER_COL != null) {
                                if (currentRow.getCell(CONTACT_NUMBER_COL).getStringCellValue() != null) {
                                    accessCard.setContactNumber(currentRow.getCell(CONTACT_NUMBER_COL).getStringCellValue().trim());
                                }
                            }
                            if (EMERGENCY_NUMBER_COL != null) {
                                if (currentRow.getCell(EMERGENCY_NUMBER_COL).getStringCellValue() != null) {
                                    accessCard.setEmergencyNumber(currentRow.getCell(EMERGENCY_NUMBER_COL).getStringCellValue().trim());
                                }
                            }
                            if (ADDRESS_COL != null) {
                                if (currentRow.getCell(ADDRESS_COL).getStringCellValue() != null) {
                                    accessCard.setAddress(currentRow.getCell(ADDRESS_COL).getStringCellValue().trim());
                                }
                            }
                            if (PROPERTY_NAME_COL != null) {
                                if (currentRow.getCell(PROPERTY_NAME_COL).getStringCellValue() != null) {
                                    accessCard.setPropertyName(currentRow.getCell(PROPERTY_NAME_COL).getStringCellValue().trim());
                                }
                            }
                            if (PROPERTY_ADDRESS_LINE_1_COL != null) {
                                if (currentRow.getCell(PROPERTY_ADDRESS_LINE_1_COL).getStringCellValue() != null) {
                                    accessCard.setPropertyAddressLine1(currentRow.getCell(PROPERTY_ADDRESS_LINE_1_COL).getStringCellValue().trim());
                                }
                            }
                            if (PROPERTY_ADDRESS_LINE_2_COL != null) {
                                if (currentRow.getCell(PROPERTY_ADDRESS_LINE_2_COL).getStringCellValue() != null) {
                                    accessCard.setPropertyAddressLine2(currentRow.getCell(PROPERTY_ADDRESS_LINE_2_COL).getStringCellValue().trim());
                                }
                            }
                            this.accessCardList.add(accessCard);
                        }

                    }
                    rowCount++;
                }

                for (AccessCard card : accessCardList) {
                    generateAccessCard(card);
                }

            } else {

            }
        } catch (Exception ex) {
            String msg = ex.getMessage();
        }
        prepareGUI();
    }

    public static void main(String[] args) {
        GenerateAccessCard awtGraphicsDemo = new GenerateAccessCard();
        awtGraphicsDemo.setVisible(true);
    }

    private void prepareGUI() {
        setSize(204, 325);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g2 = (Graphics2D) g;

    }


    private void generateAccessCard(AccessCard accessCard) {
        BufferedImage bi = new BufferedImage(204, 325, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = bi.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        try {
            g2.setFont(new Font("Helvetica", Font.TRUETYPE_FONT, 13));
            Image image = ImageIO.read(new File(getClass().getResource("/logo.png").getFile()));
            Stroke oldStroke = g2.getStroke();
            g2.drawImage(image, 15, 30, 100, 40, null);

            g2.setColor(new Color(48, 53, 54));
            g2.drawRect(0, 75, 204, 40);


            g2.setColor(new Color(48, 53, 54));
            g2.fillRect(0, 75, 204, 40);
            image = ImageIO.read(new File((imagePath + accessCard.getImageUrl())));
            g2.drawImage(image, (204 - (60 + 25)), 40, 80, 90, null);

            g2.setStroke(new BasicStroke(2));
            g2.setColor(new Color(112, 180, 47));
            g2.drawRect((204 - (60 + 25)), 40, 80, 90);
            g2.setStroke(oldStroke);

            g2.setColor(new Color(255, 255, 255));
            g2.setFont(new Font("Helvetica", Font.TRUETYPE_FONT, 15));
            g2.drawString(accessCard.getIdNumber(), 20, 100);
            g2.setColor(new Color(0, 0, 0));
            int lastY = 145;
            g2.setFont(new Font("Helvetica", Font.TRUETYPE_FONT, 9));
            g2.drawString("Name", DETAIL_TITLE_X, lastY);
            g2.drawString(":", DETAIL_SEPERATOR_X, lastY);
            java.util.List<String> wrapped = StringUtils.wrap(accessCard.getName(), g2.getFontMetrics(), 110);
            for (String text : wrapped) {
                g2.drawString(text, DETAIL_START_X, lastY);
                lastY += DETAIL_LINE_INCREMENT_Y;
            }

            g2.drawString("Blood Group", DETAIL_TITLE_X, lastY);
            g2.drawString(":", DETAIL_SEPERATOR_X, lastY);
            g2.drawString(accessCard.getBloodGroup(), DETAIL_START_X, lastY);
            lastY += DETAIL_LINE_INCREMENT_Y;

            g2.drawString("Contact", DETAIL_TITLE_X, lastY);
            g2.drawString(":", DETAIL_SEPERATOR_X, lastY);
            g2.drawString(accessCard.getContactNumber(), DETAIL_START_X, lastY);
            lastY += DETAIL_LINE_INCREMENT_Y + 5;

            g2.drawString("Emergency", DETAIL_TITLE_X, lastY);
            lastY += DETAIL_LINE_INCREMENT_Y;
            g2.drawString("Contact", DETAIL_TITLE_X, lastY);
            g2.drawString(":", DETAIL_SEPERATOR_X, lastY);
            g2.drawString(accessCard.getEmergencyNumber(), DETAIL_START_X, lastY);
            lastY += DETAIL_LINE_INCREMENT_Y + 5;

            g2.drawString("Address", DETAIL_TITLE_X, lastY);
            g2.drawString(":", DETAIL_SEPERATOR_X, lastY);
            wrapped = StringUtils.wrap(accessCard.getAddress(), g2.getFontMetrics(), 110);
            for (int i = 0; i < 2; i++) {
                g2.drawString(wrapped.get(i), DETAIL_START_X, lastY);
                lastY += DETAIL_LINE_INCREMENT_Y;
            }


            g2.drawString("Authorised Signatory", DETAIL_START_X + 30, (325 - 45));
            image = ImageIO.read(new File(getClass().getResource("/signature.png").getFile()));
            g2.drawImage(image, (DETAIL_START_X + 50), 245, 35, 25, null);

            g2.setColor(new Color(48, 53, 54));
            g2.fillRect(0, 325 - 40, 204, 40);
            g2.setColor(new Color(255, 255, 255));
            g2.setFont(new Font("Helvetica", Font.TRUETYPE_FONT, 15));
            String propertyName = accessCard.getPropertyName();
            FontMetrics fm = g2.getFontMetrics();
            while (fm.stringWidth(propertyName) < 200) {
                propertyName = " " + propertyName + " ";
            }

            g2.drawString(propertyName, 0, 325 - 25);
            g2.setFont(new Font("Helvetica", Font.TRUETYPE_FONT, 8));

            propertyName = accessCard.getPropertyAddressLine1();
            fm = g2.getFontMetrics();
            while (fm.stringWidth(propertyName) < 200) {
                propertyName = " " + propertyName + " ";
            }
            g2.drawString(propertyName, 0, 325 - 15);

            propertyName = accessCard.getPropertyAddressLine2();
            fm = g2.getFontMetrics();
            while (fm.stringWidth(propertyName) < 200) {
                propertyName = " " + propertyName + " ";
            }
            g2.drawString(propertyName, 0, 325 - 5);
            ImageIO.write(bi, "PNG", new File(accessCard.getIdNumber() + ".png"));
        } catch (Exception ex) {
            String s = ex.getMessage();
        }
        /*
        Font font = new Font("Serif", Font.PLAIN, 24);
        g2.dra
        g2.setFont(font);
        g2.drawString("Welcome to TutorialsPoint", 50, 70);

         */
    }
}


