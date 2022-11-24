package ru.vsu.cs.gui;

import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GUICell extends JPanel {

    public GUICell() {
        this.setLayout(new BorderLayout(0,0));
        this.setBorder(new LineBorder(Color.BLACK, 1));
    }

    public void show(JPanel board, Player playerNow, PlayingField playingField){
    }
}
