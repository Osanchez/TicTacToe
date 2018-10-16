package Controller;
import Model.Model;

import java.util.Scanner;

public class Controller {
    private Model board = new Model();
    private Scanner sc = new Scanner(System.in);
    private String winner = "";
    private boolean isWinState;

    public Controller() {
        this.board.initializeBoard(); //controller initialized board to 3x3 by default
    }

    public String getWinner(){
        return this.winner;
    }

    public boolean getWinState(){
            return this.isWinState;
    }

    public String getPlayerTurn() {
        return board.getPlayerTurn();
    }

    public int getBoardSize() {
        return board.getBoardSize();
    }

    private String getPlayerMove() {
        int row;
        int column;

        String currentPlayerTurn = board.getPlayerTurn();

        System.out.println();
        board.printBoard();
        System.out.println("Player Turn: " + currentPlayerTurn);
        System.out.println();
        System.out.print("Row: ");
        row = Integer.parseInt(this.sc.next());
        System.out.println("");
        System.out.print("Column: ");
        column = Integer.parseInt(this.sc.next());

        this.placePiece(getPlayerTurn(), row, column);

        return board.getWinner();
    }



    //if move is valid, places piece in requested area by updating model return true if piece was placed
    public boolean placePiece(String player, int row, int column) {
        if (board.getTurnsLeft() > 0 && player.equals(board.getPlayerTurn())) {
            if (board.getPiece(row, column).equals("")) {
                //place piece
                board.setPiece(player, row, column);
                board.endPlayerTurn();
                //check win
                this.isWinState = board.checkBoard();
                if(this.isWinState) {
                    this.winner = board.getWinner();
                }
                //finish
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void resetGame() {
        board.initializeBoard();
    }

    public static void main(String[] args) {

    }
}