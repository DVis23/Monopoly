package ru.vsu.cs.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguagesFrame extends JFrame {
    private final JPanel languagePanel;

    public LanguagesFrame(int width, int height, Locale locale) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle ("Monopoly");

        languagePanel  = new LanguagePanel(width, height, this, locale);
        getContentPane().add(languagePanel, "Center");
        this.setSize(width, height);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private class LanguagePanel extends JPanel implements KeyListener {
        private final int width;
        private final int height;
        private final JFrame frame;
        private static Font font;
        private static Font fontBig;
        private Locale locale;
        private int focusIndex;

        private final int[] menu_ys = new int[]{400, 460, 520};
        private String [] menus;


        static {
            File font_file = new File("font\\Kosmos.ttf");
            try {
                Font fontNew = Font.createFont(Font.TRUETYPE_FONT, font_file);
                font = fontNew.deriveFont(45f);
                fontBig = fontNew.deriveFont(90f);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
        }

        public LanguagePanel(int width, int height, JFrame frame, Locale locale) {
            this.width = width;
            this.height = height;
            this.frame = frame;
            this.locale = locale;
            this.setSize(width, height);

            this.setFocusable(true);
            this.addKeyListener(this);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Image img = Toolkit.getDefaultToolkit().getImage("image\\background\\background2.gif");

            int componentWidth = getWidth();
            int componentHeight = getHeight();

            int imageWidth = img.getWidth(this);
            int imageHeight = img.getHeight(this);

            int x = (componentWidth - imageWidth) / 2;
            int y = (componentHeight - imageHeight) / 2;

            ResourceBundle messages = ResourceBundle.getBundle("messages", locale);
            menus = new String [] {messages.getString("russian"),
                    messages.getString("english"), messages.getString("back")};

            g.drawImage(img, x, y, this);
            g.setFont(fontBig);
            g.setColor(Color.CYAN);
            String title = messages.getString("languages");
            FontMetrics fontMetrics = g.getFontMetrics();
            int xTitle = (componentWidth - fontMetrics.stringWidth(title))/ 2;
            g.drawString(title, xTitle, 330);
            this.repaint();

            for (int i = 0; i < menus.length; i++) {
                int menu_y = menu_ys[i];
                g.setFont(font);

                fontMetrics = g.getFontMetrics();
                int xMenu = (componentWidth - fontMetrics.stringWidth(menus[i]))/ 2;

                if (i == focusIndex) {
                    g.setColor(Color.red);
                } else {
                    g.setColor(Color.black);
                }
                g.drawString(menus[i], xMenu, menu_y);
            }
        }
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch(keyCode)
            {
                case KeyEvent.VK_UP:
                    focusIndex = (focusIndex + menus.length - 1)%menus.length;
                    this.repaint();
                    break;
                case KeyEvent.VK_DOWN:
                    focusIndex = (focusIndex + 1)%menus.length;
                    this.repaint();
                    break;
                case KeyEvent.VK_ENTER:
                    if (focusIndex == 0) {
                        locale = new Locale("ru");
                        this.repaint();
                    } else if (focusIndex == 1) {
                        locale = new Locale("en");
                        this.repaint();
                    } else if (focusIndex == 2) {
                        dispose();
                        new StartFrame(locale);
                    }
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
