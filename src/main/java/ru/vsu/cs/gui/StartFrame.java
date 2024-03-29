package ru.vsu.cs.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


public class StartFrame extends JFrame {
    private JPanel contentPanel;
    private Locale locale = new Locale("ru");

    private final int width = 1070;
    private final int height = 798;

    public StartFrame() {
        init();
    }

    public StartFrame(Locale locale) {
        this.locale = locale;
        init();
    }

    public void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle ("Monopoly");

        contentPanel  = new ContentPanel(width, height, this, locale);
        getContentPane().add(contentPanel, "Center");

        this.setSize(width, height);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private class ContentPanel extends JPanel implements KeyListener {
        private final int width;
        private final int height;
        private final JFrame frame;
        private static Font font;
        private static Font fontBig;

        private final Locale locale;
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

        public ContentPanel(int width, int height, JFrame frame, Locale locale) {
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
            menus = new String [] {messages.getString("newGame"),
                    messages.getString("languages"), messages.getString("exit")};

            g.drawImage(img, x, y, this);
            g.setFont(fontBig);
            g.setColor(Color.CYAN);
            g.drawString("Monopoly", 223, 330);

            this.repaint ();

            for (int i = 0; i < menus.length; i++) {
                int menu_y = menu_ys[i];
                g.setFont(font);

                FontMetrics fontMetrics = g.getFontMetrics();
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
                        frame.dispose();
                        new ParamsFrame(width, height, locale);
                    } else if (focusIndex == 1) {
                        frame.dispose();
                        new LanguagesFrame(width, height, locale);
                    } else if (focusIndex == 2) {
                        System.exit(0);
                    }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
    }



}
