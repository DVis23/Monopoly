package ru.vsu.cs.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ColorPanel extends JDialog{
    Color color;
    List<JButton> buttonList = new ArrayList<JButton>();

    public ColorPanel(JButton button){
        color = null;

        this.setSize(new Dimension(385, 370));
        this.setResizable(false);
        //this.setBackground(Color.BLACK);
        setModal(true);
        JPanel mainPanel = new JPanel();
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        mainPanel.add(ok);
        mainPanel.add(cancel);
        ok.setSize(new Dimension(175, 30));
        cancel.setSize(new Dimension(175, 30));
        ok.setLocation(10, 290);
        cancel.setLocation(185, 290);
        ok.setFocusPainted(false);
        cancel.setFocusPainted(false);
        mainPanel.setLayout(null);

        new ColorButton(Color.RED, this, 5, 20);
        new ColorButton(Color.CYAN, this, 65, 20);
        new ColorButton(Color.ORANGE, this, 125, 20);
        new ColorButton(Color.MAGENTA, this, 185, 20);
        new ColorButton(Color.YELLOW, this, 245, 20);
        new ColorButton(new Color(255, 122, 205), this, 305, 20);
        new ColorButton(new Color(255, 0, 75), this, 5, 80);
        new ColorButton(new Color(0, 255, 164), this, 65, 80);
        new ColorButton(new Color(255, 164, 0), this, 125, 80);
        new ColorButton(new Color(198, 16, 255), this, 185, 80);
        new ColorButton(new Color(252, 255, 98), this, 245, 80);
        new ColorButton(Color.PINK, this, 305, 80);
        new ColorButton(new Color(178, 46, 75), this, 5, 140);
        new ColorButton(new Color(86, 185, 137), this, 65, 140);
        new ColorButton(new Color(255, 124, 0), this, 125, 140);
        new ColorButton(new Color(167, 5, 173), this, 185, 140);
        new ColorButton(new Color(137, 206, 89), this, 245, 140);
        new ColorButton(new Color(169, 85, 89), this, 305, 140);
        new ColorButton(new Color(158, 26, 50), this, 5, 200);
        new ColorButton(new Color(65, 185, 152), this, 65, 200);
        new ColorButton(new Color(255, 137, 90), this, 125, 200);
        new ColorButton(new Color(133, 64, 176), this, 185, 200);
        new ColorButton(new Color(211, 255, 66), this, 245, 200);
        new ColorButton(new Color(193, 105, 134), this, 305, 200);


        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (color != null) {
                    button.setBackground(color);
                    dispose();
                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(mainPanel);
        this.setVisible(true);

    }

    private class ColorButton extends JButton{

        protected ColorButton(Color colorBackground, Container c, int x, int y) {
            this.setBackground(colorBackground);
            this.setFocusPainted(false);
            c.add(this);
            this.setLocation(x, y);
            this.setSize(60, 60);
            this.setBorder(new LineBorder(Color.WHITE, 1));
            buttonList.add(this);

            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    for (JButton button : buttonList) {
                        button.setBorder(new LineBorder(Color.WHITE, 1));
                    }
                    color = getBackground();
                    setBorder(new LineBorder(Color.WHITE, 7));
                }
            });
        }


    }
}
