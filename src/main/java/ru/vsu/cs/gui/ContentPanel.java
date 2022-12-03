package ru.vsu.cs.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class ContentPanel extends JPanel implements KeyListener {
    private final int width;
    private final int height;
    private final JFrame frame;
    private static Font font;
    private static Font fontBig;

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

    public ContentPanel(int width, int height, JFrame frame) {
        this.width = width;
        this.height = height;
        this.frame = frame;
        this.setSize(width, height);

        this.setFocusable(true);
        this.addKeyListener(this);
    }


    private final String [] menus = new String [] {"НОВАЯ ИГРА", "АВТОРЫ", "ВЫЙТИ"};

    private int focusIndex;

    private final int[] menu_xs = new int[]{335, 387, 399};
    private final int[] menu_ys = new int[]{340, 400, 470};



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = Toolkit.getDefaultToolkit().getImage("image\\background2.gif");

        g.drawImage(img, -190, -10, null);
        g.setFont(fontBig);
        g.setColor(Color.CYAN);
        g.drawString("Monopoly", 223, 230);
        this.repaint ();

        for (int i = 0; i < menus.length; i++) {
            int x = menu_xs[i];
            int y = menu_ys[i];
            g.setFont(font);
            if(i == focusIndex){
                g.setColor(Color.red);

            }else{
                g.setColor(Color.black);
            }

            g.drawString(menus[i], x, y);
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
                    new ParamsFrame(width, height);
                } else if (focusIndex == 1) {
                    frame.dispose();
                    new AuthorsFrame(width, height);
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
