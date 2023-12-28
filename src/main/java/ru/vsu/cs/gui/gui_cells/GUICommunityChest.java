package ru.vsu.cs.gui.gui_cells;

import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import ru.vsu.cs.cells.CommunityChest;
import ru.vsu.cs.gui.GUICell;
import ru.vsu.cs.gui.GUICellFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Function;

public class GUICommunityChest extends GUICell {
    private final JPanel mainPanel;
    private final CommunityChest communityChest;
    private Image picture;
    private JLabel picLabel;
    private static Font font;

    static {
        Function<CommunityChest, GUICommunityChest> function = c -> {
            try {
                return new GUICommunityChest(c);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        };
        GUICellFactory.registerType(CommunityChest.class, function);

        File fontFile1 = new File("font\\future.ttf");
        try {
            Font fontNew = Font.createFont(Font.TRUETYPE_FONT, fontFile1);
            font = fontNew.deriveFont(12f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public GUICommunityChest(CommunityChest communityChest) throws IOException {
        this.communityChest = communityChest;
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new BorderLayout());
        picture = ImageIO.read(new File("image\\cell_icon\\community_chest.png"));
        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void show(JPanel board, Player playerNow, PlayingField playingField, Locale locale){
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale);
        JLabel label;
        if (communityChest.getI() == 1) {
            label= new JLabel(messages.getString("community1"));
        } else if (communityChest.getI() == 2) {
            label= new JLabel(messages.getString("community2"));
        } else if (communityChest.getI() == 3) {
            label= new JLabel(messages.getString("community3"));
        } else {
            label= new JLabel(messages.getString("community4"));
        }
        label.setForeground(Color.WHITE);
        label.setFont(font);
        JOptionPane op = new JOptionPane(label, JOptionPane.INFORMATION_MESSAGE);
        op.setOpaque(true);
        op.setIcon(new ImageIcon("image\\dialog_icon\\lv.gif"));
        op.createDialog(null).setVisible(true);
    }

    @Override
    public void setScaledInstance(int x, int y){
        mainPanel.setPreferredSize(new Dimension(x, y));
        picture = picture.getScaledInstance(x - 20, y - 20, Image.SCALE_SMOOTH);
        picLabel = new JLabel(new ImageIcon(picture));
        mainPanel.add(picLabel, BorderLayout.CENTER);
    }

    @Override
    public void update(){
    }
}
