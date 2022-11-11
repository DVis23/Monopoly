package ru.vsu.cs.gui.gui_cells;

import ru.vsu.cs.cells.UtilityCompany;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class GUIUtilityCompany extends JPanel {

    private JPanel MainPanel;
    private JTextArea UCInform;

    public GUIUtilityCompany(UtilityCompany utilityCompany) throws IOException {
        this.setPreferredSize(new Dimension(70, 70));
        this.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout(0,0));
        this.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        this.setBorder(new LineBorder(Color.BLACK));

        MainPanel = new JPanel();
        MainPanel.setPreferredSize(new Dimension(70, 70));
        MainPanel.setBackground(Color.WHITE);

        if (Objects.equals(utilityCompany.getName(), "Электростанция")) {
            BufferedImage myPicture = ImageIO.read(new File("image/electric_icon.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setLayout(new BorderLayout(0,0));
            picLabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
            MainPanel.add(picLabel, BorderLayout.NORTH);
        } else if (Objects.equals(utilityCompany.getName(), "Водопровод")) {
            BufferedImage myPicture = ImageIO.read(new File("image/water_icon.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setLayout(new BorderLayout(0,0));
            picLabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
            MainPanel.add(picLabel, BorderLayout.NORTH);
        }

        UCInform = new JTextArea();
        UCInform.setPreferredSize(new Dimension(68, 53));
        UCInform.setEditable(false);
        Font font = new Font("BOLD", Font.BOLD, 10);
        UCInform.setFont(font);
        UCInform.setForeground(Color.BLACK);
        UCInform.setLineWrap(true);
        UCInform.setWrapStyleWord(true);

        StringBuilder sb = new StringBuilder();
        if (utilityCompany.getOwner() == null) {
            sb.append(utilityCompany.getCost());
        } else {
            sb.append(utilityCompany.getIncome()).append(" ");
            sb.append(utilityCompany.getOwner().getName());
        }
        String str = sb.toString();
        UCInform.setText(str);

        MainPanel.add(UCInform, BorderLayout.SOUTH);


        this.add(MainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
