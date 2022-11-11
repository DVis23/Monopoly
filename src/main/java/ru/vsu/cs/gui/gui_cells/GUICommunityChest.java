package ru.vsu.cs.gui.gui_cells;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUICommunityChest extends JPanel {

    private JPanel MainPanel;

    public GUICommunityChest() throws IOException {
        this.setPreferredSize(new Dimension(70, 70));
        this.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout(0,0));
        this.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        this.setBorder(new LineBorder(Color.BLACK));

        MainPanel = new JPanel();
        MainPanel.setPreferredSize(new Dimension(70, 70));
        MainPanel.setBackground(Color.WHITE);

        BufferedImage myPicture = ImageIO.read(new File("image/community_chest_icon.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        MainPanel.add(picLabel);


        this.add(MainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
