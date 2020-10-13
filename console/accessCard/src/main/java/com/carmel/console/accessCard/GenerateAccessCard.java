package com.carmel.console.accessCard;

import com.carmel.console.accessCard.Utils.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class GenerateAccessCard extends Frame {

    private static int DETAIL_SEPERATOR_X = 70;
    private static int DETAIL_TITLE_X = 5;
    private static int DETAIL_START_X = 80;
    private static int DETAIL_LINE_INCREMENT_Y = 13;

    public GenerateAccessCard() {
        super("Guesture Access Card");
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
        Graphics2D g2 = (Graphics2D) g;
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
            image = ImageIO.read(new File(getClass().getResource("/id_image.png").getFile()));
            g2.drawImage(image, (204 - (60 + 25)), 40, 80, 90, null);

            g2.setStroke(new BasicStroke(2));
            g2.setColor(new Color(112, 180, 47));
            g2.drawRect((204 - (60 + 25)), 40, 80, 90);
            g2.setStroke(oldStroke);

            g2.setColor(new Color(255, 255, 255));
            g2.setFont(new Font("Helvetica", Font.TRUETYPE_FONT, 15));
            g2.drawString("ID: 0000027", 20, 100);
            g2.setColor(new Color(0, 0, 0));
            int lastY = 145;
            g2.setFont(new Font("Helvetica", Font.TRUETYPE_FONT, 9));
            g2.drawString("Name", DETAIL_TITLE_X, lastY);
            g2.drawString(":", DETAIL_SEPERATOR_X, lastY);
            java.util.List<String> wrapped = StringUtils.wrap("Prasanna Kumar Pete", g2.getFontMetrics(), 110);
            for (String text : wrapped) {
                g2.drawString(text, DETAIL_START_X, lastY);
                lastY += DETAIL_LINE_INCREMENT_Y;
            }

            g2.drawString("Blood Group", DETAIL_TITLE_X, lastY);
            g2.drawString(":", DETAIL_SEPERATOR_X, lastY);
            g2.drawString("O +ve", DETAIL_START_X, lastY);
            lastY += DETAIL_LINE_INCREMENT_Y;

            g2.drawString("Contact", DETAIL_TITLE_X, lastY);
            g2.drawString(":", DETAIL_SEPERATOR_X, lastY);
            g2.drawString("+91-9886393685", DETAIL_START_X, lastY);
            lastY += DETAIL_LINE_INCREMENT_Y + 5;

            g2.drawString("Emergency", DETAIL_TITLE_X, lastY);
            lastY += DETAIL_LINE_INCREMENT_Y;
            g2.drawString("Contact", DETAIL_TITLE_X, lastY);
            g2.drawString(":", DETAIL_SEPERATOR_X, lastY);
            g2.drawString("+91-9731977000", DETAIL_START_X, lastY);
            lastY += DETAIL_LINE_INCREMENT_Y + 5;

            g2.drawString("Address", DETAIL_TITLE_X, lastY);
            g2.drawString(":", DETAIL_SEPERATOR_X, lastY);
            wrapped = StringUtils.wrap("#401, Shriya Mansion Apartments, Rajendra nagar,Gokul Road, RN Shetty Road, Hubballi - 580030, Karnataka", g2.getFontMetrics(), 110);
            for (int i = 0; i < 2; i++) {
                g2.drawString(wrapped.get(i), DETAIL_START_X, lastY);
                lastY += DETAIL_LINE_INCREMENT_Y;
            }


            g2.drawString("Authorised Signatory",DETAIL_START_X +30, (325-45));
            image = ImageIO.read(new File(getClass().getResource("/signature.png").getFile()));
            g2.drawImage(image, (DETAIL_START_X +50), 245, 35, 25, null);

            g2.setColor(new Color(48, 53, 54));
            g2.fillRect(0, 325 - 40, 204, 40);
            g2.setColor(new Color(255, 255, 255));
            g2.setFont(new Font("Helvetica", Font.TRUETYPE_FONT, 15));
            String propertyName = "SHANDERS ALTAVISTA";
            FontMetrics fm = g2.getFontMetrics();
            while (fm.stringWidth(propertyName) < 200) {
                propertyName = " " + propertyName + " ";
            }

            g2.drawString(propertyName, 0, 325 - 25);
            g2.setFont(new Font("Helvetica", Font.TRUETYPE_FONT, 8));

            propertyName = "Sy.No. 1/2, 1/3, 1/4 & 1/5";
            fm = g2.getFontMetrics();
            while (fm.stringWidth(propertyName) < 200) {
                propertyName = " " + propertyName + " ";
            }
            g2.drawString(propertyName, 0, 325 - 15);

            propertyName = "Veerasandra Villege, Attibele Hobli, Anekal Tq.";
            fm = g2.getFontMetrics();
            while (fm.stringWidth(propertyName) < 200) {
                propertyName = " " + propertyName + " ";
            }
            g2.drawString(propertyName, 0, 325 - 5);

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


