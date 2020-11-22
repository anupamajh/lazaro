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
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.Buffer;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

public class GenerateAccessCard extends Frame {

    private static int MULTI_FACTOR = 3;
    private static int DETAIL_SEPERATOR_X = 170;
    private static int DETAIL_TITLE_X = 15;
    private static int DETAIL_START_X = 190;
    private static int DETAIL_LINE_INCREMENT_Y = 35;
    private java.util.List<AccessCard> accessCardList;
    private AccessCard currentAccessCard = null;
    AffineTransform identity = new AffineTransform();

    private String imagePath = "";
    private String excelPath = "";

    private Graphics2D g2;

    private Properties properties;

    private Integer ID_NUMBER_COL = null;
    private Integer UNIT_COL = null;
    private Integer NAME_COL = null;
    private Integer IMAGE_URL_COL = null;
    private Integer BLOOD_GROUPS_COL = null;
    private Integer CONTACT_NUMBER_COL = null;
    private Integer EMERGENCY_NUMBER_COL = null;
    private Integer ADDRESS_COL = null;
    private Integer PROPERTY_NAME_COL = null;
    private Integer PROPERTY_ADDRESS_LINE_1_COL = null;
    private Integer PROPERTY_ADDRESS_LINE_2_COL = null;
    private Integer CHECKOUT_DATE_COL = null;

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
                                    case "unit":
                                        UNIT_COL = i;
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
                                    case "checkout date":
                                        CHECKOUT_DATE_COL = i;
                                        break;
                                }
                            }
                            i++;
                        }
                    } else {
                        accessCard = new AccessCard();
                        if (currentRow.getCell(NAME_COL) != null) {
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
                            if (UNIT_COL != null) {
                                if (currentRow.getCell(UNIT_COL).getStringCellValue() != null) {
                                    accessCard.setUnit(currentRow.getCell(UNIT_COL).getStringCellValue().trim());
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
                            if (CHECKOUT_DATE_COL != null) {
                                if (currentRow.getCell(CHECKOUT_DATE_COL).getDateCellValue() != null) {
                                    accessCard.setCheckoutDate(currentRow.getCell(CHECKOUT_DATE_COL).getDateCellValue());
                                }
                            }
                            this.accessCardList.add(accessCard);
                        }

                    }
                    rowCount++;
                }

               // this.currentAccessCard = accessCardList.get(0);
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
        setSize(204 * MULTI_FACTOR, 325 * MULTI_FACTOR);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g2 = (Graphics2D) g;
        //generateAccessCard(currentAccessCard);

    }


    private void generateAccessCard(AccessCard accessCard) {
        BufferedImage bi = new BufferedImage(204 * MULTI_FACTOR, 325 * MULTI_FACTOR, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = bi.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        try {
            Stroke oldStroke = g2.getStroke();
            g2.setStroke(new BasicStroke(5));
            g2.setColor(new Color(0, 0, 0));
            g2.drawRect(15, 15, 204 * MULTI_FACTOR - 30, 325 * MULTI_FACTOR -30);
            g2.setStroke(oldStroke);

            //g2.rotate(Math.toRadians(180));
            g2.setFont(new Font("Helvetica", Font.TRUETYPE_FONT, 13));
            Image image = ImageIO.read(new File(getClass().getResource("/logo.png").getFile()));

            g2.drawImage(image, 194, 80, 224, 70, null);


            BufferedImage orginalImage = ImageIO.read(new File((imagePath + accessCard.getImageUrl())));
            BufferedImage blackAndWhiteImg = new BufferedImage(
                    orginalImage.getWidth(), orginalImage.getHeight(),
                    BufferedImage.TYPE_BYTE_GRAY);

            Graphics2D graphics = blackAndWhiteImg.createGraphics();
            graphics.drawImage(orginalImage, 0, 0, null);

            g2.drawImage(blackAndWhiteImg, 216, 70 * MULTI_FACTOR, 60 * MULTI_FACTOR, 70 * MULTI_FACTOR, null);
            g2.setStroke(new BasicStroke(5));
            g2.setColor(new Color(0, 0, 0));
            g2.drawRect(216, 70 * MULTI_FACTOR, 60 * MULTI_FACTOR, 70 * MULTI_FACTOR);
            g2.setStroke(oldStroke);

            g2.setFont(new Font("Helvetica", Font.TRUETYPE_FONT, 28));
            String text = accessCard.getName();
            FontMetrics fm = g2.getFontMetrics();
            while (fm.stringWidth(text) < 200 * MULTI_FACTOR) {
                text = " " + text + " ";
            }

            g2.drawString(text, 0, 470);

            g2.setFont(new Font("Helvetica", Font.PLAIN, 18));
            text = "EMERGENCY CONTACT PHONE";
            fm = g2.getFontMetrics();
            while (fm.stringWidth(text) < 200 * MULTI_FACTOR) {
                text = " " + text + " ";
            }

            g2.drawString(text, 0, 600);

            g2.setFont(new Font("Helvetica", Font.PLAIN, 35));
            text = accessCard.getEmergencyNumber();
            fm = g2.getFontMetrics();
            while (fm.stringWidth(text) < 200 * MULTI_FACTOR) {
                text = " " + text + " ";
            }

            g2.drawString(text, 0, 640);

            int CardHeight = 325 * MULTI_FACTOR + 100;
            g2.setColor(new Color(0, 0, 0));
            g2.drawRect(50, CardHeight - 250, 60, 30);

            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(50, CardHeight - 250, 60, 30);

            g2.setColor(new Color(255, 255, 255));
            g2.setFont(new Font("Helvetica", Font.PLAIN, 13));
            g2.drawString("GUEST", 58, CardHeight - 230);

            g2.setColor(new Color(0, 0, 0));
            g2.drawString(accessCard.getIdNumber(), 54, CardHeight - 190);


            g2.setFont(new Font("Helvetica", Font.PLAIN, 18));
            g2.setColor(new Color(0, 0, 0));
            g2.drawString(accessCard.getUnit(), 54, CardHeight - 170);
            g2.drawString("Valid Thru", 204 * MULTI_FACTOR - 150, CardHeight - 250);
            SimpleDateFormat myFormatObj = new  SimpleDateFormat("dd MMM yyyy");

            g2.drawString(myFormatObj.format(accessCard.getCheckoutDate()), 204 * MULTI_FACTOR - 150, CardHeight - 231);




     /*
            g2.setColor(new Color(0, 0, 0));
            g2.drawRect(0, 50*MULTI_FACTOR, 204*MULTI_FACTOR, 40*MULTI_FACTOR);

            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 50*MULTI_FACTOR, 204*MULTI_FACTOR, 40*MULTI_FACTOR);

            image = ImageIO.read(new File((imagePath + accessCard.getImageUrl())));
            g2.drawImage(image, (204*MULTI_FACTOR - (60*MULTI_FACTOR + 25*MULTI_FACTOR)), 20 *MULTI_FACTOR, 80*MULTI_FACTOR, 90*MULTI_FACTOR, null);
//
            g2.setStroke(new BasicStroke(5));
            g2.setColor(new Color(112, 180, 47));
            g2.drawRect((204*MULTI_FACTOR - (60*MULTI_FACTOR + 25*MULTI_FACTOR)), 20 *MULTI_FACTOR, 80*MULTI_FACTOR, 90*MULTI_FACTOR);
            g2.setStroke(oldStroke);
//
            g2.setColor(new Color(255, 255, 255));
            g2.setFont(new Font("Helvetica", Font.TRUETYPE_FONT, 30));
            g2.drawString(accessCard.getIdNumber(), 50, 70*MULTI_FACTOR);
            g2.setColor(new Color(0, 0, 0));
            int lastY = 120*MULTI_FACTOR;
            g2.setFont(new Font("Helvetica", Font.TRUETYPE_FONT, 25));
            g2.drawString("Name", DETAIL_TITLE_X, lastY);
            g2.drawString(":", DETAIL_SEPERATOR_X, lastY);
            java.util.List<String> wrapped = StringUtils.wrap(accessCard.getName(), g2.getFontMetrics(), 110*MULTI_FACTOR);
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
            wrapped = StringUtils.wrap(accessCard.getAddress(), g2.getFontMetrics(), 110*MULTI_FACTOR);
            int MaxAddressLine = 4;
            MaxAddressLine = (wrapped.size() > MaxAddressLine)?MaxAddressLine:wrapped.size();
            for (int i = 0; i < MaxAddressLine; i++) {
                g2.drawString(wrapped.get(i), DETAIL_START_X, lastY);
                lastY += DETAIL_LINE_INCREMENT_Y;
            }


            g2.drawString("Authorised Signatory", DETAIL_START_X + 100, (325*MULTI_FACTOR - 200));
            image = ImageIO.read(new File(getClass().getResource("/signature.png").getFile()));
            g2.drawImage(image, (DETAIL_START_X + 150), 220*MULTI_FACTOR+25, 140, 70, null);

            g2.setColor(new Color(48, 53, 54));
            g2.fillRect(0, 325*MULTI_FACTOR - 120, 204*MULTI_FACTOR, 120);
            g2.setColor(new Color(255, 255, 255));
            g2.setFont(new Font("Helvetica", Font.TRUETYPE_FONT, 35));
            String propertyName = accessCard.getPropertyName();
            FontMetrics fm = g2.getFontMetrics();
            while (fm.stringWidth(propertyName) < 200*MULTI_FACTOR) {
                propertyName = " " + propertyName + " ";
            }

            g2.drawString(propertyName, 0, 325 *MULTI_FACTOR - 80);
            g2.setFont(new Font("Helvetica", Font.TRUETYPE_FONT, 25));

            propertyName = accessCard.getPropertyAddressLine1();
            fm = g2.getFontMetrics();
            while (fm.stringWidth(propertyName) < 200*MULTI_FACTOR) {
                propertyName = " " + propertyName + " ";
            }
            g2.drawString(propertyName, 0, 325*MULTI_FACTOR - 50);

            propertyName = accessCard.getPropertyAddressLine2();
            fm = g2.getFontMetrics();
            while (fm.stringWidth(propertyName) < 200 *MULTI_FACTOR) {
                propertyName = " " + propertyName + " ";
            }
            g2.drawString(propertyName, 0, 325*MULTI_FACTOR - 20);


      */
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


