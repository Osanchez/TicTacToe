package View;

import Controller.Controller;
import javax.swing.*;
import java.awt.*;

public class View {
    private Controller controller = new Controller();
    private JFrame gui = new JFrame("Tic Tac Toe");
    private JButton[][] blocks = new JButton[controller.getBoardSize()][controller.getBoardSize()];
    private JPanel game;
    private JTextArea playerTurn = new JTextArea();

    public View() {
        this.gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.setSize(new Dimension(500, 350));
        gui.setResizable(true);

        JPanel gamePanel = new JPanel(new FlowLayout());
        this.game = new JPanel(new GridLayout(this.controller.getBoardSize(), this.controller.getBoardSize()));
        gamePanel.add(this.game, BorderLayout.CENTER);

        JPanel options = new JPanel(new FlowLayout());
        JButton reset = new JButton("Reset");
        reset.addActionListener(e -> resetGame());

        options.add(reset);

        JPanel messages = new JPanel(new FlowLayout());
        messages.setBackground(Color.white);

        this.gui.add(gamePanel, BorderLayout.NORTH);
        this.gui.add(options, BorderLayout.CENTER);
        this.gui.add(messages, BorderLayout.SOUTH);

        messages.add(playerTurn);
        playerTurn.setText("Player 1 to play 'X'");
    }

    private void createBoard() {
        for (int row = 0; row < controller.getBoardSize(); row++) {
            for (int column = 0; column < controller.getBoardSize(); column++) {
                blocks[row][column] = new JButton();
                blocks[row][column].setPreferredSize(new Dimension(75, 75));
                blocks[row][column].setText("");
                this.game.add(blocks[row][column]);
            }
        }
    }

    private void initializeListeners() {
        for (int row = 0; row < controller.getBoardSize(); row++) {
            for (int column = 0; column < controller.getBoardSize(); column++) {
                blocks[row][column].addActionListener(e -> {
                    boolean isWinner = placePiece(e.getSource());
                    if (controller.getPlayerTurn().equals("X") && !isWinner) {
                        playerTurn.setText("'X': Player 1");
                    } else if (controller.getPlayerTurn().equals("O") && !isWinner) {
                        playerTurn.setText("'O': Player 2");
                    }
                });
            }
        }
    }

    private void placePieceTerminal() {

        boolean placed = controller.getPlayerMove();

        if (placed) {
            checkGame();
        }

        if (controller.getPlayerTurn().equals("X")) {
            playerTurn.setText("'X': Player 1");
        } else if (controller.getPlayerTurn().equals("O")) {
            playerTurn.setText("'O': Player 2");
        }
        if(!controller.getWinState())
            placePieceTerminal();
    }

    private boolean placePiece(Object e) {
        String currentPlayer = controller.getPlayerTurn();

        for (int row = 0; row < controller.getBoardSize(); row++) {
            for (int column = 0; column < controller.getBoardSize(); column++) {
                if (blocks[row][column] == e) {

                    boolean placed = controller.placePiece(currentPlayer, row, column);
                    if (placed) {
                        blocks[row][column].setText(currentPlayer);
                        return checkGame();
                    }
                }
            }
        }
        return false;
    }

    private boolean checkGame() {
        if (controller.getWinState()) {
            String winner = controller.getWinner();
            switch (winner) {
                case "X":
                    this.playerTurn.setText("Player 1 wins!");
                    break;
                case "O":
                    this.playerTurn.setText("Player 2 wins!");
                    break;
                default:
                    this.playerTurn.setText("Game ends in a draw");
                    break;
            }
            endGame();
            return true;
        }
        return false;
    }

    private void endGame() {
        for (int row = 0; row < controller.getBoardSize(); row++) {
            for (int column = 0; column < controller.getBoardSize(); column++) {
                blocks[row][column].setEnabled(false);
            }
        }
    }

    private void resetGame() {
        for (int row = 0; row < controller.getBoardSize(); row++) {
            for (int column = 0; column < controller.getBoardSize(); column++) {
                blocks[row][column].setEnabled(true);
                blocks[row][column].setText("");
            }
        }
        playerTurn.setText("Player 1 to play 'X'");
        controller.resetGame();
    }


    public static void main(String[] args) {
        View view = new View();
        view.createBoard();
        view.initializeListeners();
        view.gui.setVisible(true);
        //support for placing piece via terminal
        //view.placePieceTerminal();
    }

}