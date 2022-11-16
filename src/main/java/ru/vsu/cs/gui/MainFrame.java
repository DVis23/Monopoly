package ru.vsu.cs.gui;

import org.json.simple.parser.ParseException;
import ru.vsu.cs.Cell;
import ru.vsu.cs.Game;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import ru.vsu.cs.cells.*;
import ru.vsu.cs.gui.gui_cells.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class MainFrame extends JFrame {

    private Game game;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<InformPlayer> playersInform = new ArrayList<>();
    private final PlayingField playingField;
    private Player playerNow;
    private JPanel panelMain;
    private JPanel board;
    private JPanel panelPlayer;
    private JPanel panelButton;
    private JPanel panelRight;
    private JButton makeAMove;
    private JButton manager;

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

    public MainFrame() throws IOException, ParseException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("MONOPOLY");
        this.pack();
        this.setSize(1070, 810);
        this.setResizable(false);

        panelMain = new JPanel();
        this.setContentPane(panelMain);
        this.getContentPane().setLayout(new BorderLayout());

        Font font = new Font("BOLD", Font.BOLD, 20);

        panelRight = new JPanel();
        panelRight.setPreferredSize(new Dimension(300, 770));
        panelRight.setBackground(new Color(61,60,59));

        panelButton = new JPanel();
        panelButton.setPreferredSize(new Dimension(300, 200));
        panelButton.setBackground(new Color(61,60,59));
        makeAMove = new JButton("Сделать ход");
        makeAMove.setFont(font);
        makeAMove.setBackground(Color.GRAY);
        manager = new JButton("Менеджер");
        manager.setFont(font);
        manager.setBackground(Color.GRAY);

        board = new JPanel();
        board.setPreferredSize(new Dimension(770, 770));
        board.setLayout(new BorderLayout(0,0));
        board.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));


        GridLayout layout = new GridLayout(6, 1, 0, 0);

        panelPlayer = new JPanel();
        panelPlayer.setLayout(layout);
        panelPlayer.setPreferredSize(new Dimension(250, 400));
        panelPlayer.setBackground(new Color(61,60,59));



        makeAMove.setPreferredSize(new Dimension(250, 40));
        panelButton.add(makeAMove);
        manager.setPreferredSize(new Dimension(250, 40));
        panelButton.add(manager);

        panelRight.add(panelButton, BorderLayout.NORTH);
        panelRight.add(panelPlayer, BorderLayout.SOUTH);

        GridLayout layoutVer = new GridLayout(9, 1, 0, 0);
        GridLayout layoutHor= new GridLayout(1, 11, 0, 0);

        panelWest = new JPanel();
        panelWest.setBackground(Color.BLACK);
        panelWest.setPreferredSize(new Dimension(70, 630));
        panelWest.setLayout(new BorderLayout(0,0));
        panelWest.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        panelWest.setLayout(layoutVer);
        board.add(panelWest, BorderLayout.WEST);

        panelEast = new JPanel();
        panelEast.setBackground(Color.BLACK);
        panelEast.setPreferredSize(new Dimension(70, 630));
        panelEast.setLayout(new BorderLayout(0,0));
        panelEast.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        panelEast.setLayout(layoutVer);
        board.add(panelEast, BorderLayout.EAST);

        panelSouth = new JPanel();
        panelSouth.setBackground(Color.BLACK);
        panelSouth.setPreferredSize(new Dimension(770, 70));
        panelSouth.setLayout(new BorderLayout(0,0));
        panelSouth.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        panelSouth.setLayout(layoutHor);
        board.add(panelSouth, BorderLayout.SOUTH);

        panelNorth = new JPanel();
        panelNorth.setBackground(Color.BLACK);
        panelNorth.setPreferredSize(new Dimension(770, 70));
        panelNorth.setLayout(new BorderLayout(0,0));
        panelNorth.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        panelNorth.setLayout(layoutHor);
        board.add(panelNorth, BorderLayout.NORTH);

        panelCenter = new JPanel();
        panelCenter.setPreferredSize(new Dimension(630, 630));
        panelCenter.setLayout(new BorderLayout(0,0));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        board.add(panelCenter, BorderLayout.CENTER);

        panelWestMini = new JPanel();
        panelWestMini.setBackground(Color.BLACK);
        panelWestMini.setPreferredSize(new Dimension(59, 524));
        panelWestMini.setLayout(new BorderLayout(0,0));
        panelWestMini.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        panelWestMini.setLayout(layoutVer);
        panelCenter.add(panelWestMini, BorderLayout.WEST);

        panelEastMini = new JPanel();
        panelEastMini.setBackground(Color.BLACK);
        panelEastMini.setPreferredSize(new Dimension(59, 524));
        panelEastMini.setLayout(new BorderLayout(0,0));
        panelEastMini.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        panelEastMini.setLayout(layoutVer);
        panelCenter.add(panelEastMini, BorderLayout.EAST);

        panelSouthMini = new JPanel();
        panelSouthMini.setBackground(Color.BLACK);
        panelSouthMini.setPreferredSize(new Dimension(630, 59));
        panelSouthMini.setLayout(new BorderLayout(0,0));
        panelSouthMini.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        panelSouthMini.setLayout(layoutHor);
        panelCenter.add(panelSouthMini, BorderLayout.SOUTH);

        panelNorthMini = new JPanel();
        panelNorthMini.setBackground(Color.BLACK);
        panelNorthMini.setPreferredSize(new Dimension(630, 59));
        panelNorthMini.setLayout(new BorderLayout(0,0));
        panelNorthMini.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        panelNorthMini.setLayout(layoutHor);
        panelCenter.add(panelNorthMini, BorderLayout.NORTH);


        int numberPlayers = 0;
        while(numberPlayers <= 1 || numberPlayers > 7) {
            String numberPlayersStr = JOptionPane.showInputDialog(this, "How many players?");
            if (numberPlayersStr != null) {
                try {
                    numberPlayers = Integer.parseInt(numberPlayersStr);
                    if (numberPlayers <= 1 || numberPlayers > 7) {
                        JOptionPane.showMessageDialog(this, "Please input a number between two and six");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Please input a number");
                }
            }
        }

        for (int i = 0; i < numberPlayers; i++) {
            String name = JOptionPane.showInputDialog(this, "Enter name");
            if(name == null && players.size() == 0) {
                System.exit(0);
            }
            Player player = new Player(name, 7500);
            players.add(player);
        }
        game = new Game(players);
        playingField = game.getPlayingField();

        playerNow = players.get(0);

        drawPlayersBoard();
        drawBoardPanel();
        drawBoardMini();

        makeAMove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                managerFrame.setVisible(false);
                game.playerAction(playerNow);
                int skipping = playerNow.getSkipping();
                if (skipping == 3 || skipping == 2) {
                    JOptionPane.showMessageDialog(board, " Вы пропускайте еще " + skipping + " хода ");
                } else if (skipping == 1) JOptionPane.showMessageDialog(board, " Вы пропускайте еще " + skipping + " ход ");
                try {
                    updateView();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                GUICell [] guiCells = new GUICell[0];
                try {
                    guiCells = guiCells();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                guiCells[playerNow.getStep()].show(board, playerNow, game.getPlayingField());

                nextPlayer();
                if (game.getGameState() == Game.GameState.GAME_OVER) {
                    JOptionPane.showMessageDialog(board, "Игра окончена, победил игрок: '" + players.get(0).getName() + "'");
                    panelRight.remove(panelButton);
                }
                try {
                    updateView();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        });

        manager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                managerFrame = new ManagerFrame(playerNow, playingField);
                managerFrame.setVisible(true);
            }
        });


        this.getContentPane().add(board, BorderLayout.WEST);
        this.getContentPane().add(panelRight, BorderLayout.EAST);
        this.setVisible(true);


    }

    private void drawBoardPanel() throws IOException {
        Cell [] cells = playingField.getCells();
        drawPanel(panelNorth, 0, cells.length/4);
        drawPanel(panelEast, cells.length/4 + 1, cells.length/2 - 1);
        drawPanel(panelSouth, cells.length/4*3,cells.length/2);
        drawPanel(panelWest, cells.length - 1, cells.length/4*3 + 1);
    }

    private void drawPanel(JPanel panel, int a, int b) throws IOException {
        GUICell [] guiCells = guiCells();
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

    private GUICell [] guiCells() throws IOException {
        Cell [] cells = playingField.getCells();
        GUICell [] guiCells = new GUICell[cells.length];
        for (int i = 0; i < cells.length; i++) {
            GUICell guiCell = GUICellFactory.createGUICell(cells[i]);
            guiCells[i] = guiCell;
        }
        return guiCells;
    }

    private void drawBoardMini() {
        Cell [] cells = playingField.getCells();
        drawPanelMini(panelNorthMini, 0, cells.length/4, players);
        drawPanelMini(panelEastMini, cells.length/4 + 1, cells.length/2 - 1, players);
        drawPanelMini(panelSouthMini, cells.length/4*3,cells.length/2, players);
        drawPanelMini(panelWestMini, cells.length - 1, cells.length/4*3 + 1, players);
    }

    private void drawPanelMini(JPanel panel, int a, int b, ArrayList<Player> players) {
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

    private void drawCellMini(JPanel panel, int i, ArrayList<Player> players, GridLayout layout) {
        JPanel panelMini = new JPanel();
        panelMini.setLayout(layout);
        panelMini.setBorder(new LineBorder(Color.BLACK));
        panelMini.setBackground(Color.WHITE);
        for (int j = 0; j < players.size(); j++) {
            if(players.get(j).getStep() == i) {
                JPanel player = new JPanel();
                if (j == 0) player.setBackground(Color.RED);
                else if (j == 1) player.setBackground(Color.PINK);
                else if (j == 2) player.setBackground(Color.ORANGE);
                else if (j == 3) player.setBackground(Color.MAGENTA);
                else if (j == 4) player.setBackground(Color.CYAN);
                else if (j == 5) player.setBackground(Color.YELLOW);
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


    private void clearBoard() {
       panelNorth.removeAll();
       panelEast.removeAll();
       panelSouth.removeAll();
       panelWest.removeAll();
    }

    private void clearBoardMini() {
        panelNorthMini.removeAll();
        panelEastMini.removeAll();
        panelSouthMini.removeAll();
        panelWestMini.removeAll();
    }

    private void updateView() throws IOException {
        updatePlayersBoard();
        clearBoard();
        drawBoardPanel();
        clearBoardMini();
        drawBoardMini();
        this.setVisible(true);
    }

    private void drawPlayersBoard() {
        for (int i = 0; i < players.size(); i++) {
            InformPlayer informPlayer = new InformPlayer(players.get(i));
            JPanel color = new JPanel();
            color.setPreferredSize(new Dimension(11, 11));
            if (i == 0) color.setBackground(Color.RED);
            else if (i == 1) color.setBackground(Color.PINK);
            else if (i == 2) color.setBackground(Color.ORANGE);
            else if (i == 3) color.setBackground(Color.MAGENTA);
            else if (i == 4) color.setBackground(Color.CYAN);
            else if (i == 5) color.setBackground(Color.YELLOW);

            if (players.get(i).equals(playerNow)) {
                informPlayer.textRed();
            }
            informPlayer.add(color);
            panelPlayer.add(informPlayer);
        }
    }

    private void updatePlayersBoard() {
        panelPlayer.removeAll();
        panelPlayer.repaint();
        drawPlayersBoard();
    }

}
