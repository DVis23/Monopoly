package ru.vsu.cs.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;


public class AuthorsFrame extends JFrame {

    public AuthorsFrame(int width, int height) {
        this.setSize(width, height);
        JPanel panel = new MainPanel(width, height, this);
        getContentPane().add(panel, "Center");;
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private static class MainPanel extends JPanel implements KeyListener{
        private int focusIndex;
        private JFrame frame;

        private static Font font;

        static {
            File font_file = new File("font\\Kosmos.ttf");
            try {
                Font fontNew = Font.createFont(Font.TRUETYPE_FONT, font_file);
                font = fontNew.deriveFont(45f);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
        }

        protected MainPanel(int width, int height, JFrame frame) {
            this.frame = frame;
            this.setSize(width, height);
            setSize(width, height);
            this.setFocusable(true);
            this.addKeyListener(this);
        }

        @Override
        protected void paintComponent(Graphics g) {
            // TODO Auto-generated method stub
            Image img = Toolkit.getDefaultToolkit().getImage("image\\background2.gif");
            g.drawImage(img, -190, -10, null);
            g.setFont(font);
            int y = -10;
            for (int i = 0; i < 23; i++) {
                if(i == focusIndex){
                    g.setColor(Color.red);

                }else{
                    g.setColor(Color.CYAN);
                }
                g.drawString("Даниил Высоцкий", 232, y);
                y += 40;
            }
            this.repaint();
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch(keyCode)
            {
                case KeyEvent.VK_UP:
                    focusIndex = (focusIndex + 23 - 1)%23;
                    this.repaint();
                    break;
                case KeyEvent.VK_DOWN:
                    focusIndex = (focusIndex + 1)%23;
                    this.repaint();
                    break;
                case KeyEvent.VK_ESCAPE:
                    frame.dispose();
                    new StartFrame();
                }
            }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }


}
