package ru.vsu.cs.gui;

import org.json.simple.parser.ParseException;
import ru.vsu.cs.Cell;
import ru.vsu.cs.Game;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class MainFrame extends JFrame {

    private final Game game;
    private final List<Player> players;
    private final Map<Player, Color> map;
    private final PlayingField playingField;
    private Player playerNow;

    private JPanel panelMain;
    private JPanel board;
    private JPanel panelPlayer;
    private JPanel panelButton;
    private JPanel panelRight;
    private JButton makeAMove;
    private JButton manager;
    private JButton back;

    private JPanel panelWest;
    private JPanel panelNorth;
    private JPanel panelEast;
    private JPanel panelSouth;

    private JPanel panelCenter;

    private JPanel panelWestMini;
    private JPanel panelNorthMini;
    private JPanel panelEastMini;
    private JPanel panelSouthMini;

    private ManagerFrame managerFrame = new ManagerFrame();
    private GUICell [] guiCells;

    private static Font font;

    static {
        File fontFile2 = new File("font\\Advanced.ttf");
        try {
            Font fontNew = Font.createFont(Font.TRUETYPE_FONT, fontFile2);
            font = fontNew.deriveFont(40f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public MainFrame(Map<Player, Color> map, int wight, int height) throws IOException, ParseException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("MONOPOLY");
        this.pack();
        this.setSize(wight, height);
        this.setResizable(false);
        this.map = map;

        players = new ArrayList<>(map.keySet());

        panelMain = new JPanel();
        this.setContentPane(panelMain);
        this.getContentPane().setLayout(new BorderLayout());

        game = new Game(players);
        playingField = game.getPlayingField();

        panelRight = new JPanel();
        panelRight.setPreferredSize(new Dimension(300, 770));
        panelRight.setBackground(new Color(23, 4, 41));

        panelButton = new JPanel();
        panelButton.setPreferredSize(new Dimension(300, 200));
        panelButton.setBackground(new Color(23, 4, 41));
        makeAMove = new SuperButton("СДЕЛАТЬ ХОД");
        manager = new SuperButton("МЕНЕДЖЕР");
        back = new SuperButton("ВЫЙТИ");


        GridLayout layout = new GridLayout(6, 1, 0, 0);
        panelPlayer = new JPanel();
        panelPlayer.setLayout(layout);
        panelPlayer.setPreferredSize(new Dimension(250, 400));
        panelPlayer.setBackground(new Color(23, 4, 41));

        panelButton.add(makeAMove);
        panelButton.add(manager);
        panelButton.add(back);
        makeAMove.setLocation(20, 10);
        makeAMove.setSize(300, 60);
        manager.setLocation(20, 60);
        manager.setSize(230, 60);
        back.setLocation(20, 110);
        back.setSize(200, 60);

        panelButton.setLayout(null);

        panelRight.add(panelButton, BorderLayout.NORTH);
        panelRight.add(panelPlayer, BorderLayout.SOUTH);

        board = new JPanel();
        int sizeBoard = 770;
        board.setPreferredSize(new Dimension(sizeBoard, sizeBoard));
        board.setLayout(new BorderLayout(0,0));
        board.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        int countCell = playingField.getConstCell();
        int countVerCell = countCell/4 - 1;
        int countHorCell = countCell/4 + 1;
        int sizeCell =  sizeBoard/countHorCell;
        int sizeMiniBoard = sizeBoard - 2*sizeCell;
        int sizeMiniCell = sizeMiniBoard/countHorCell;
        guiCells = GUICellFactory.guiCells(playingField, sizeCell, sizeCell);

        GridLayout layoutVer = new GridLayout(countVerCell, 1, 0, 0);
        GridLayout layoutHor= new GridLayout(1, countHorCell, 0, 0);

        panelWest = new JPanel();
        panelWest.setBackground(Color.BLACK);
        panelWest.setPreferredSize(new Dimension(sizeCell, sizeBoard - sizeCell*2));
        panelWest.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        panelWest.setLayout(layoutVer);
        board.add(panelWest, BorderLayout.WEST);

        panelEast = new JPanel();
        panelEast.setBackground(Color.BLACK);
        panelEast.setPreferredSize(new Dimension(sizeCell, sizeBoard - sizeCell*2));
        panelEast.setLayout(layoutVer);
        board.add(panelEast, BorderLayout.EAST);

        panelSouth = new JPanel();
        panelSouth.setBackground(Color.BLACK);
        panelSouth.setPreferredSize(new Dimension(sizeBoard, sizeCell));
        panelSouth.setLayout(layoutHor);
        board.add(panelSouth, BorderLayout.SOUTH);

        panelNorth = new JPanel();
        panelNorth.setBackground(Color.BLACK);
        panelNorth.setPreferredSize(new Dimension(sizeBoard, sizeCell));
        panelNorth.setLayout(layoutHor);
        board.add(panelNorth, BorderLayout.NORTH);

        panelCenter = new CenterPanel();
        panelCenter.setPreferredSize(new Dimension(sizeMiniBoard, sizeMiniBoard));
        panelCenter.setLayout(new BorderLayout(0,0));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        board.add(panelCenter, BorderLayout.CENTER);

        panelWestMini = new JPanel();
        panelWestMini.setBackground(Color.BLACK);
        panelWestMini.setPreferredSize(new Dimension(sizeMiniCell, sizeMiniBoard - 2*sizeMiniCell));
        panelWestMini.setLayout(layoutVer);
        panelWestMini.setBorder(BorderFactory.createEmptyBorder(-4, -1, -4, 0));
        panelCenter.add(panelWestMini, BorderLayout.WEST);

        panelEastMini = new JPanel();
        panelEastMini.setBackground(Color.BLACK);
        panelEastMini.setPreferredSize(new Dimension(sizeMiniCell, sizeMiniBoard - 2*sizeMiniCell));
        panelEastMini.setLayout(layoutVer);
        panelEastMini.setBorder(BorderFactory.createEmptyBorder(-4, 0, -4, -1));
        panelCenter.add(panelEastMini, BorderLayout.EAST);

        panelSouthMini = new JPanel();
        panelSouthMini.setBackground(Color.BLACK);
        panelSouthMini.setPreferredSize(new Dimension(sizeMiniBoard, sizeMiniCell));
        panelSouthMini.setLayout(layoutHor);
        panelSouthMini.setBorder(BorderFactory.createEmptyBorder(0, -4, 0, -4));
        panelCenter.add(panelSouthMini, BorderLayout.SOUTH);

        panelNorthMini = new JPanel();
        panelNorthMini.setBackground(Color.BLACK);
        panelNorthMini.setPreferredSize(new Dimension(sizeMiniBoard, sizeMiniCell));
        panelNorthMini.setLayout(layoutHor);
        panelNorthMini.setBorder(BorderFactory.createEmptyBorder(0, -4, 0, -4));
        panelCenter.add(panelNorthMini, BorderLayout.NORTH);



        drawPlayersBoard();
        drawBoardPanel();
        drawBoardMini();

        playerNow = players.get(0);

        makeAMove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                makeAMove.setForeground(Color.RED);
                Timer timer = new Timer(200, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        makeAMove.setForeground(Color.CYAN);
                    }
                });
                timer.setRepeats(false);
                timer.start();
                Timer timer2 = new Timer(300, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        managerFrame.setVisible(false);
                        game.playerAction(playerNow);
                        try {
                            updateView();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                        guiCells[playerNow.getStep()].show(board, playerNow, game.getPlayingField());

                        nextPlayer();
                        if (game.getGameState() == Game.GameState.GAME_OVER) {
                            JOptionPane.showMessageDialog(board, "Игра окончена, победил игрок: '" + players.get(0).getName() + "'");
                            makeAMove.setVisible(false);
                            manager.setVisible(false);
                            try {
                                updateView();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
                timer2.setRepeats(false);
                timer2.start();
            }


        });

        manager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                manager.setForeground(Color.RED);
                Timer timer = new Timer(200, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manager.setForeground(Color.CYAN);
                    }
                });
                timer.setRepeats(false);
                timer.start();
                Timer timer2 = new Timer(300, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        managerFrame = new ManagerFrame(playerNow, playingField);
                        managerFrame.setVisible(true);
                    }
                });
                timer2.setRepeats(false);
                timer2.start();
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                back.setForeground(Color.RED);
                Timer timer = new Timer(200, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        back.setForeground(Color.CYAN);
                    }
                });
                timer.setRepeats(false);
                timer.start();
                Timer timer2 = new Timer(300, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new StartFrame();
                    }
                });
                timer2.setRepeats(false);
                timer2.start();
            }
        });


        this.getContentPane().add(board, BorderLayout.WEST);
        this.getContentPane().add(panelRight, BorderLayout.EAST);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private class SuperButton extends JButton{

        protected SuperButton(String str) {
            this.setText(str);
            this.setFont(font);
            this.setForeground(Color.CYAN);
            this.setBorderPainted(false);
            this.setFocusPainted (false);
            this.setContentAreaFilled(false);
            this.setHorizontalAlignment(SwingConstants.LEFT);
        }
    }

    private class CenterPanel extends JPanel{

        protected CenterPanel(){
        }

        @Override
        protected void paintComponent(Graphics g) {
            // TODO Auto-generated method stub
            Graphics2D g2d = (Graphics2D)g;
            Color c1 = new Color(30, 12, 48);
            Color c2 = new Color(130, 39, 107);
            GradientPaint gradient = new GradientPaint(0,700,c2,0,0,c1,true);
            g2d.setPaint(gradient);
            g2d.fillRect(0,0,700,700);
        }
    }
    private void drawBoardPanel() throws IOException {
        Cell [] cells = playingField.getCells();
        drawPanel(panelNorth, 0, cells.length/4);
        drawPanel(panelEast, cells.length/4 + 1, cells.length/2 - 1);
        drawPanel(panelSouth, cells.length/4*3,cells.length/2);
        drawPanel(panelWest, cells.length - 1, cells.length/4*3 + 1);
    }

    private void drawPanel(JPanel panel, int a, int b) throws IOException {
        if (a < b) {
            for (int i = a; i <= b; i++) {
                panel.add(guiCells[i]);
            }
        } else if (a > b) {
            for (int i = a; i >= b; i--) {
                panel.add(guiCells[i]);
            }
        }
    }

    private void drawBoardMini() {
        Cell [] cells = playingField.getCells();
        drawPanelMini(panelNorthMini, 0, cells.length/4, players);
        drawPanelMini(panelEastMini, cells.length/4 + 1, cells.length/2 - 1, players);
        drawPanelMini(panelSouthMini, cells.length/4*3,cells.length/2, players);
        drawPanelMini(panelWestMini, cells.length - 1, cells.length/4*3 + 1, players);
    }

    private void drawPanelMini(JPanel panel, int a, int b, List<Player> players) {
        GridLayout layout = new GridLayout(players.size(), 1, 0, 0);
        if (a < b) {
            for (int i = a; i <= b; i++) {
                drawCellMini(panel, i, players, layout);
            }
        } else if (a > b) {
            for (int i = a; i >= b; i--) {
                drawCellMini(panel, i, players, layout);
            }
        }
    }


    private void drawCellMini(JPanel panel, int i, List<Player> players, GridLayout layout) {
        JPanel panelMini = new JPanel();
        panelMini.setLayout(layout);
        panelMini.setBorder(new LineBorder(Color.WHITE, 1));
        panelMini.setBackground(new Color(47, 23, 52));
        for (int j = 0; j < players.size(); j++) {
            if(players.get(j).getStep() == i) {
                JPanel player = new JPanel();
                Color color = map.get(players.get(j));
                player.setBackground(color);
                panelMini.add(player);
            }
        }
        panel.add(panelMini);
    }

    private void nextPlayer() {
        int i = players.indexOf(playerNow);
        i++;
        if (i == players.size()) i = 0;
        playerNow = players.get(i);
    }

    private void clearBoardMini() {
        panelNorthMini.removeAll();
        panelEastMini.removeAll();
        panelSouthMini.removeAll();
        panelWestMini.removeAll();
    }

    private void updateView() throws IOException {
        updatePlayersBoard();
        for (int i = 0; i < guiCells.length; i++) {
            guiCells[i].update();
        }
        drawBoardPanel();
        clearBoardMini();
        drawBoardMini();
        this.setVisible(true);
    }

    private void drawPlayersBoard() {
        for (int i = 0; i < players.size(); i++) {
            InformPlayer informPlayer = new InformPlayer(players.get(i));
            JPanel colorPanel = new JPanel();
            colorPanel.setPreferredSize(new Dimension(11, 11));
            Color color = map.get(players.get(i));
            colorPanel.setBackground(color);
            if (players.get(i).equals(playerNow)) {
                informPlayer.textRed();
            }
            informPlayer.add(colorPanel);
            panelPlayer.add(informPlayer);
        }
    }

    private void updatePlayersBoard() {
        panelPlayer.removeAll();
        panelPlayer.repaint();
        drawPlayersBoard();
    }

}
